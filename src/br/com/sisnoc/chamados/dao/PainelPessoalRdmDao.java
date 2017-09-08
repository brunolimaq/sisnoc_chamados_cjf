package br.com.sisnoc.chamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;


import br.com.sisnoc.chamados.dao.util.RDMDao;

import br.com.sisnoc.chamados.modelo.Mudanca;

import br.com.sisnoc.chamados.negocio.Popula;
import br.com.sisnoc.chamados.security.UsuarioSistema;
import br.com.sisnoc.chamados.service.ContextoUsuario;


@Repository
@RDMDao
public class PainelPessoalRdmDao {


private  final Connection connection;

	
	@Autowired
	public PainelPessoalRdmDao(@Qualifier("datasourceSQL") DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Mudanca> listaPainelPessoalRdm() throws ParseException {
		try {
			
			ArrayList<Mudanca> ListaRDM = new ArrayList<Mudanca>();
			String sql_listaRDM = "";
			
			String username = ContextoUsuario.getUsername();
			String listaEquipe = ContextoUsuario.getEquipes();
			String gerencia = ContextoUsuario.getGerencia();
		
		
			
			sql_listaRDM = "select " 
						+"chg.id as ID , "
						+"chg_ref_num as mudanca, " 
						+"summary as titulo, "
						+ "ca_contact.first_name as responsavel, " 
						+"dateadd(hh,DATEPART(tz,SYSDATETIMEOFFSET())/60,dateadd(SS,sched_start_date,'19700101')) agendamento, " 
						+"replace(chgstat.sym,' (Mudança)','') statusDescricao, " 
						+"ca_contact.first_name nome, " 
						+"ca_contact.userid username, " 
						+"vwg.last_name as grupo "
						+"from chg WITH (NOLOCK) " 
						+"join chgstat WITH (NOLOCK) on chg.status = chgstat.code " 
						+"join ca_contact WITH (NOLOCK)  on ca_contact.contact_uuid = chg.assignee " 
						+"join View_Group vwg WITH (NOLOCK)  on chg.group_id = vwg.contact_uuid "
						+ "where chgstat.code in ('IMPL', 'APR','APP','RFC') "
						+"and vwg.last_name in ("+ listaEquipe + ") "
						+"order by 6,5";

						
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
