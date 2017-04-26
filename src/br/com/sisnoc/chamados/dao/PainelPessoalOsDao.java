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

import br.com.sisnoc.chamados.dao.util.OsDao;
import br.com.sisnoc.chamados.modelo.Chamado;
import br.com.sisnoc.chamados.modelo.Mudanca;
import br.com.sisnoc.chamados.negocio.Popula;
import br.com.sisnoc.chamados.security.UsuarioSistema;

@Repository
@OsDao
public class PainelPessoalOsDao {

	private  final Connection connection;

	@Autowired
	public PainelPessoalOsDao(@Qualifier("datasourceSQL") DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Chamado> listaPainelPessoalOs() throws ParseException {
		try {
			
			ArrayList<Chamado> ListaOs = new ArrayList<Chamado>();
			String sql_listaRDM = "";
			
			// tipo = "R";
			Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			String equipe = "";
			String user_exclusao = "''";
			if (usuarioLogado  instanceof UsuarioSistema ) {
				   username = ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
				   equipe = ( (UsuarioSistema)usuarioLogado).getUsuario().getNomeEquipe();
			} else {
			   username = usuarioLogado.toString();
			}
					
			String[] splitEquipe = equipe.split(",");
			
			String listaEquipe = "\'\'";
			
			for (String eqp : splitEquipe) {
				listaEquipe = listaEquipe +",\'" + eqp + "\'";
			}

		
			if (username.equals("bruno.queiroz") || username.equals("walison.morales")){
				
				user_exclusao = "'antonio.junior'";
			}
			
			sql_listaRDM = "select "
					+ "req.id as ID, " 
					+ "usu.first_name as responsavel, "
					+ "req.ref_num as chamados, "
					+ "vwg.last_name as equipe," 
					+ "req.summary as titulo,  "
					+ "stat.sym as statusDescricao, " 
					+ "ctg.sym as grupo, " 
					+ "CASE req.type  as tipo, " 
					+ "datediff(dd,DATEADD(hh,-3,DATEADD(SS,req.last_mod_dt,'19700101')), getdate()) as diasatualizacao, " 
					+ "(DATEADD(HOUR, -3, DATEADD(SS,req.last_mod_dt,'19700101'))) as atualizacao, " 
					+ "(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio, " 
					+ "COALESCE(DATEADD(HOUR, -3, DATEADD(SS,call_back_date,'19700101')),0) as data_retorno " 
					+ "from " 
					+ "call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+ "left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
					+ "join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category " 
					+ "join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid  "
					+ "join View_Group vwg WITH (NOLOCK)  on req.group_id = vwg.contact_uuid " 
					+ "where ctg.sym like 'INFRA.Ordem de Servico' " 
					+ "and stat.code in ('RSCH', 'OP', 'PF', 'AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'ACK','WIP','PRBAPP') " 
					+ "and log.type='INIT' " 
					+ "and usu.userid = '"+username+"' "
					+ "order by 1 ";
					

//						+"and ca_contact.userid  not in ("+user_exclusao+") "
//						+"and vwg.last_name in ("+ listaEquipe + ") "
//						+"order by 6,5";

						
			PreparedStatement stmt = connection
					.prepareStatement(sql_listaRDM);				
			
			stmt = connection
					.prepareStatement(sql_listaRDM);
			ResultSet rs_listaOs = stmt.executeQuery();

			Popula popula = new Popula();
			
			
			
			
			
			//Corre o ResultSet
			Integer count = 0;
				while (rs_listaOs.next()){
					// adiciona um chamado na lista
					
					Chamado chamados = new Chamado();
					chamados.setId(popula.populaID(rs_listaOs));
					chamados.setEquipe(popula.populaEquipe(rs_listaOs));
					chamados.setChamado(popula.populaChamados(rs_listaOs));
					chamados.setTitulo(popula.populaTitulo(rs_listaOs));
					chamados.setStatus(popula.populaStatus(rs_listaOs));
					chamados.setGrupo(popula.populaGrupo(rs_listaOs));
					chamados.setTipo(popula.populaTipo(rs_listaOs));
					chamados.setTipoLegivel(popula.populaTipoLegivel(rs_listaOs));
					
					chamados.setDiasAtualizacao(popula.populaDiasAtualizacao(rs_listaOs));
					chamados.setAtualizacao(popula.populaAtualizacao(rs_listaOs));
					chamados.setData_inicio(popula.populaData_inicio(rs_listaOs));
					chamados.setData_retorno(popula.populaData_retorno(rs_listaOs));
					
					
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getChamado());
//					System.out.println(chamados.getEpoch());
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getTime());
//					System.out.println(chamados.getStatus());
					ListaOs.add(chamados);
					count++;
				}
			
				
				rs_listaOs.close();
				stmt.close();

				return ListaOs;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Chamado> listaPainelPessoalEquipeOs() throws ParseException {
		try {
			
			ArrayList<Chamado> ListaOs = new ArrayList<Chamado>();
			String sql_listaRDM = "";
			
			// tipo = "R";
			Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			String equipe = "";
			String user_exclusao = "''";
			if (usuarioLogado  instanceof UsuarioSistema ) {
				   username = ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
				   equipe = ( (UsuarioSistema)usuarioLogado).getUsuario().getNomeEquipe();
			} else {
			   username = usuarioLogado.toString();
			}
					
			String[] splitEquipe = equipe.split(",");
			
			String listaEquipe = "\'\'";
			
			for (String eqp : splitEquipe) {
				listaEquipe = listaEquipe +",\'" + eqp + "\'";
			}

		
			if (username.equals("bruno.queiroz") || username.equals("walison.morales")){
				
				user_exclusao = "'antonio.junior'";
			}
			
			sql_listaRDM = "select "
					+ "req.id as ID, " 
					+ "usu.first_name as responsavel, "
					+ "req.ref_num as chamados, "
					+ "vwg.last_name as equipe," 
					+ "req.summary as titulo,  "
					+ "stat.sym as statusDescricao, " 
					+ "ctg.sym as grupo, " 
					+ "CASE req.type  as tipo, " 
					+ "datediff(dd,DATEADD(hh,-3,DATEADD(SS,req.last_mod_dt,'19700101')), getdate()) as diasatualizacao, " 
					+ "(DATEADD(HOUR, -3, DATEADD(SS,req.last_mod_dt,'19700101'))) as atualizacao, " 
					+ "(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio, " 
					+ "COALESCE(DATEADD(HOUR, -3, DATEADD(SS,call_back_date,'19700101')),0) as data_retorno " 
					+ "from " 
					+ "call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+ "left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
					+ "join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category " 
					+ "join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid  "
					+ "join View_Group vwg WITH (NOLOCK)  on req.group_id = vwg.contact_uuid " 
					+ "where ctg.sym like 'INFRA.Ordem de Servico' " 
					+ "and stat.code in ('RSCH', 'OP', 'PF', 'AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'ACK','WIP','PRBAPP') " 
					+ "and log.type='INIT' " 
					+"and vwg.last_name in ("+ listaEquipe + ") "
					+ "and usu.userid != '"+username+"' "
					+ "order by 1 ";
					

//						+"and ca_contact.userid  not in ("+user_exclusao+") "
//						+"and vwg.last_name in ("+ listaEquipe + ") "
//						+"order by 6,5";

						
			PreparedStatement stmt = connection
					.prepareStatement(sql_listaRDM);				
			
			stmt = connection
					.prepareStatement(sql_listaRDM);
			ResultSet rs_listaOs = stmt.executeQuery();

			Popula popula = new Popula();
			
			
			
			
			
			//Corre o ResultSet
			Integer count = 0;
				while (rs_listaOs.next()){
					// adiciona um chamado na lista
					
					Chamado chamados = new Chamado();
					chamados.setId(popula.populaID(rs_listaOs));
					chamados.setEquipe(popula.populaEquipe(rs_listaOs));
					chamados.setChamado(popula.populaChamados(rs_listaOs));
					chamados.setTitulo(popula.populaTitulo(rs_listaOs));
					chamados.setStatus(popula.populaStatus(rs_listaOs));
					chamados.setGrupo(popula.populaGrupo(rs_listaOs));
					chamados.setTipo(popula.populaTipo(rs_listaOs));
					chamados.setTipoLegivel(popula.populaTipoLegivel(rs_listaOs));
					
					chamados.setDiasAtualizacao(popula.populaDiasAtualizacao(rs_listaOs));
					chamados.setAtualizacao(popula.populaAtualizacao(rs_listaOs));
					chamados.setData_inicio(popula.populaData_inicio(rs_listaOs));
					chamados.setData_retorno(popula.populaData_retorno(rs_listaOs));
					
					
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getChamado());
//					System.out.println(chamados.getEpoch());
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getTime());
//					System.out.println(chamados.getStatus());
					ListaOs.add(chamados);
					count++;
				}
			
				
				rs_listaOs.close();
				stmt.close();

				return ListaOs;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Connection getConnection() throws SQLException {
		return connection;
	}
}
