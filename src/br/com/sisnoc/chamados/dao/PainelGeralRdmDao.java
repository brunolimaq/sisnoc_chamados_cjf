package br.com.sisnoc.chamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import br.com.sisnoc.chamados.dao.util.RDMDao;

import br.com.sisnoc.chamados.modelo.Mudanca;

import br.com.sisnoc.chamados.negocio.Popula;
import br.com.sisnoc.chamados.service.ContextoUsuario;
import br.com.sisnoc.chamados.service.Util;


@Repository
@RDMDao
public class PainelGeralRdmDao {


private  final Connection connection;

	
	@Autowired
	public PainelGeralRdmDao(@Qualifier("datasourceSQL") DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Mudanca> listaPainelPessoalRdmGeral(String rdmPainel) throws ParseException {
		try {
			
			ArrayList<Mudanca> ListaRDM = new ArrayList<Mudanca>();
			String sql_listaRDM = "";
			
			
			String rdm = rdmPainel;
			String username = ContextoUsuario.getUsername();
			String listaEquipe = ContextoUsuario.getEquipes();
			String gerencia = ContextoUsuario.getGerencia();

			//APR - Aprovada
			//IMPL - Exeução
			//RFC - Em Planejamento
			//APP - Em validação
			//VRFY - Executada
			// Outros Null
			
			if (rdmPainel.equals("")){
				
				sql_listaRDM = "select top 10" 
						+"chg.id as ID , "
						+"chg_ref_num as mudanca, " 
						+"summary as titulo, "
						+ "ca_contact.first_name as responsavel, "
						+"dateadd(hh,DATEPART(tz,SYSDATETIMEOFFSET())/60,dateadd(SS,sched_start_date,'19700101')) agendamento, " 
						+"chgstat.sym statusDescricao, " 
						+"ca_contact.first_name nome, " 
						+"ca_contact.userid username, " 
						+"chg.cab_approval as ccm, "
						+"vwg.last_name as grupo "
						+"from chg WITH (NOLOCK) " 
						+"join chgstat WITH (NOLOCK) on chg.status = chgstat.code " 
						+"join ca_contact WITH (NOLOCK)  on ca_contact.contact_uuid = chg.assignee " 
						+"join View_Group vwg WITH (NOLOCK)  on chg.group_id = vwg.contact_uuid "
						+ "where chgstat.code not in ('APR', 'IMPL', 'RFC', 'APP','VRFY') "
						+"order by 5 DESC";
			}else {
				
			
			sql_listaRDM = "select " 
						+"chg.id as ID , "
						+"chg_ref_num as mudanca, " 
						+"summary as titulo, "
						+ "ca_contact.first_name as responsavel, "
						+"dateadd(hh,DATEPART(tz,SYSDATETIMEOFFSET())/60,dateadd(SS,sched_start_date,'19700101')) agendamento, " 
						+"chgstat.sym statusDescricao, " 
						+"ca_contact.first_name nome, " 
						+"ca_contact.userid username, " 
						+"chg.cab_approval as ccm, "
						+"vwg.last_name as grupo "
						+"from chg WITH (NOLOCK) " 
						+"join chgstat WITH (NOLOCK) on chg.status = chgstat.code " 
						+"join ca_contact WITH (NOLOCK)  on ca_contact.contact_uuid = chg.assignee " 
						+"join View_Group vwg WITH (NOLOCK)  on chg.group_id = vwg.contact_uuid "
						+ "where chgstat.code in ("+ "'" + rdm + "'"+ ") "
						+"order by 6,5";
			}
						
			PreparedStatement stmt = connection
					.prepareStatement(sql_listaRDM);				
			
			stmt = connection
					.prepareStatement(sql_listaRDM);
			ResultSet rs_listaChamado = stmt.executeQuery();

			Popula popula = new Popula();
			
			
			
			
			
			//Corre o ResultSet
			Integer count = 0;
				while (rs_listaChamado.next()){
					// adiciona um chamado na lista
					
					Mudanca mudancas = new Mudanca();
					
				
					mudancas.setMudanca(popula.populaRdm(rs_listaChamado));
					mudancas.setId(popula.populaID(rs_listaChamado));
					mudancas.setResumo(popula.populaTitulo(rs_listaChamado));
					mudancas.setAgendamento(popula.populaAgendamento(rs_listaChamado));
					mudancas.setStatusDescricao(popula.populaStatusDescricao(rs_listaChamado));
					mudancas.setResponsavel(popula.populResponsavel(rs_listaChamado));
					mudancas.setCcm(popula.populaCCM(rs_listaChamado));
					mudancas.setAlerta("9");
					
				
					
					if (rdm.equals("APR")){
						
						Util utilitarios = new Util();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
						Date data = sdf.parse(mudancas.getAgendamento());
					
						if(((data.getTime()/1000)-43200) < utilitarios.epochAtual()){
							
							mudancas.setAlerta("0"); //RDM das próximas 12 horas
						}
						
						if(((data.getTime()/1000)-3600) < utilitarios.epochAtual()){
							
							mudancas.setAlerta("1"); //RDM falta 1 hora para execução da RDM
						}
						
						if((data.getTime()/1000) < utilitarios.epochAtual()){
							
							mudancas.setAlerta("2"); //RDM com data violada
						}
						
					}
					
					
					
				
					
					
				

					ListaRDM.add(mudancas);
					
					
					count++;
				}
			
				
				rs_listaChamado.close();
				stmt.close();

				return ListaRDM;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Connection getConnection() throws SQLException {
		return connection;
	}
	
}
