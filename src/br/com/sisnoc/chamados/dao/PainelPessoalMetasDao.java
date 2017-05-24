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


import br.com.sisnoc.chamados.dao.util.MetasDao;
import br.com.sisnoc.chamados.modelo.Chamado;
import br.com.sisnoc.chamados.negocio.CalculaSla;
import br.com.sisnoc.chamados.negocio.Popula;
import br.com.sisnoc.chamados.security.UsuarioSistema;

@Repository
@MetasDao
public class PainelPessoalMetasDao {

private  final Connection connection;

	
	@Autowired
	public PainelPessoalMetasDao(@Qualifier("datasourceSQL") DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	

	
	public ArrayList<Chamado> listaPainelPessoalMetas(String perfil) throws ParseException {
		
		
		try {
			
			ArrayList<Chamado> ListaChamados = new ArrayList<Chamado>();
			String sql_listaChamados = "";
			
			
			
			// tipo = "R";
			Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if (usuarioLogado  instanceof UsuarioSistema ) {
			   username= ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
			} else {
			   username = usuarioLogado.toString();
			}
			
			if (perfil == "GESTOR"){
				
				sql_listaChamados = "select "
						+"req.ref_num as chamado, "
						+"req.id as ID "
					+"from "
						+"call_req req WITH(NOLOCK) join cr_stat stat WITH(NOLOCK) on "
						+"req.status = stat.code join prob_ctg cat WITH(NOLOCK) on "
						+"cat.persid = req.category "
						+" join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee "
					+"where "
						+"cat.sym like 'INFRA%' "
						+"and cat.sym not like 'INFRA.Ordem de Servico' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
						+"and cat.sym not like 'Infra.Tarefas Internas' "
						+"and req.type != 'P' "
						+"and stat.code in ('RE','CL') "
						+"and resolve_date  + DATEPART(tz,SYSDATETIMEOFFSET())*60 >= DATEDIFF(s, '1970-01-01 00:00:00',CONVERT(VARCHAR(25),DATEADD(dd,-(DAY(getdate())-1),getdate()),101)) ";
				
			} else {

				sql_listaChamados = "select "
						+"req.ref_num as chamado, "
						+"req.id as ID "
					+"from "
						+"call_req req WITH(NOLOCK) join cr_stat stat WITH(NOLOCK) on "
						+"req.status = stat.code join prob_ctg cat WITH(NOLOCK) on "
						+"cat.persid = req.category "
						+" join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee "
					+"where "
						+"cat.sym like 'INFRA%' "
						+"and cat.sym not like 'INFRA.Ordem de Servico' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
						+"and cat.sym not like 'Infra.Tarefas Internas' "
						+"and req.type != 'P' "
						+"and stat.code in ('RE','CL') "
						+"and usu.userid = '"+username+"' "
						+"and resolve_date  + DATEPART(tz,SYSDATETIMEOFFSET())*60 >= DATEDIFF(s, '1970-01-01 00:00:00',CONVERT(VARCHAR(25),DATEADD(dd,-(DAY(getdate())-1),getdate()),101)) ";



			}

			PreparedStatement stmt = connection
					.prepareStatement(sql_listaChamados);
			ResultSet rs_listaChamados = stmt.executeQuery();
			
			String lista = "\'\'";
			
			
			while (rs_listaChamados.next()){

				lista = lista +",\'" + rs_listaChamados.getString("ID") + "\'";
				
			}
			
			
			rs_listaChamados.close();
			
			String sql_listaLog = "select "
									+"req.id as ID, "
									+"req.ref_num as chamados, "
									+"usu.first_name as responsavel,"
									+"vwg.last_name as equipe,"
									+"ctg.sym as grupo,"
									+"req.type as tipo, "
									+"req.summary as titulo, "
									+"log.time_stamp + DATEPART(tz,SYSDATETIMEOFFSET())*60 as time,"
									+"DATEDIFF(s, '1970-01-01 00:00:00', GETDATE()) as epoch,"
									+ "log.type as status "
								+"from "
									+"call_req req WITH(NOLOCK) "
									+"join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
									+"join prob_ctg ctg WITH(NOLOCK) on ctg.persid = req.category "
									+"join View_Group vwg  WITH (NOLOCK) on req.group_id = vwg.contact_uuid "
									+"left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee "
									+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid "
								+"where "
									+"log.type in ('INIT','SLADELAY','SLARESUME','RE') "
									+"and req.id in  ("+ lista + ") "
									+ "order by req.id, log.time_stamp";
			
			stmt = connection
					.prepareStatement(sql_listaLog);
			ResultSet rs_listalog = stmt.executeQuery();

			Popula popula = new Popula();
			
			
			
			
			
			//Corre o ResultSet
			Integer count = 0;
				while (rs_listalog.next()){
					// adiciona um chamado na lista
	
					Chamado chamados = new Chamado();
					chamados.setId(popula.populaID(rs_listalog));
					chamados.setEquipe(popula.populaEquipe(rs_listalog));
					chamados.setChamado(popula.populaChamados(rs_listalog));
					chamados.setTitulo(popula.populaTitulo(rs_listalog));
					chamados.setStatus(popula.populaStatus(rs_listalog));
					chamados.setTime(popula.populaTime(rs_listalog));
					chamados.setEpoch(popula.populaEpoch(rs_listalog));
					chamados.setGrupo(popula.populaGrupo(rs_listalog));
					chamados.setTipo(popula.populaTipo(rs_listalog));
					chamados.setTipoLegivel(popula.populaTipoLegivel(rs_listalog));

					ListaChamados.add(chamados);
					count++;
				}
			
				
				rs_listalog.close();
				stmt.close();

				if(ListaChamados.isEmpty()){
					return null;
				} else {
					
					return CalculaSla.SlaCjf(ListaChamados);
				
					
				}

			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
public int listaPainelPessoalReabertos(String perfil) throws ParseException {
		
		
		
		
		
		try {
			
			
			String sql_listaChamados = "";
			
			
			
			// tipo = "R";
			Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if (usuarioLogado  instanceof UsuarioSistema ) {
			   username= ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
			} else {
			   username = usuarioLogado.toString();
			}
		
			
			if (perfil == "GESTOR"){

				sql_listaChamados = "select "
						+"req.ref_num as chamado, "
						+"req.id as ID , "
						+"count(cast(log.action_desc as varchar)) reaberturas "
					+"from "
						+"call_req req WITH(NOLOCK) "
						+ "join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
						+ "join prob_ctg cat WITH(NOLOCK) on cat.persid = req.category "
						+"join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee "
						+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid "
					+"where "
						+"cat.sym like 'INFRA%' "
						+"and cat.sym not like 'INFRA.Ordem de Servico' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
						+"and cat.sym not like 'Infra.Tarefas Internas' "
						+"and req.type != 'P' "
						+"and stat.code in ('RE','CL') "
						+"and resolve_date  + DATEPART(tz,SYSDATETIMEOFFSET())*60 >= DATEDIFF(s, '1970-01-01 00:00:00',CONVERT(VARCHAR(25),DATEADD(dd,-(DAY(getdate())-1),getdate()),101)) "
						+ "and log.action_desc like 'registrar texto da solução' "
						+ "group by req.ref_num ,req.id ";

			} else {
			
			

				sql_listaChamados = "select "
						+"req.ref_num as chamado, "
						+"req.id as ID , "
						+"count(cast(log.action_desc as varchar)) reaberturas "
					+"from "
						+"call_req req WITH(NOLOCK) "
						+ "join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
						+ "join prob_ctg cat WITH(NOLOCK) on cat.persid = req.category "
						+"join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee "
						+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid "
					+"where "
						+"cat.sym like 'INFRA%' "
						+"and cat.sym not like 'INFRA.Ordem de Servico' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
						+"and cat.sym not like 'Infra.Tarefas Internas' "
						+"and req.type != 'P' "
						+"and stat.code in ('RE','CL') "
						+"and usu.userid = '"+username+"' "
						+"and resolve_date  + DATEPART(tz,SYSDATETIMEOFFSET())*60 >= DATEDIFF(s, '1970-01-01 00:00:00',CONVERT(VARCHAR(25),DATEADD(dd,-(DAY(getdate())-1),getdate()),101)) "
						+ "and log.action_desc like 'registrar texto da solução' "
						+ "group by req.ref_num ,req.id ";


			}
				//registrar texto da solução

			PreparedStatement stmt = connection
					.prepareStatement(sql_listaChamados);
			ResultSet rs_listaChamados = stmt.executeQuery();
			
			
			int countReabertos = 0;
			
			while (rs_listaChamados.next()){

				if(Integer.parseInt(rs_listaChamados.getString("reaberturas"))>1){
					countReabertos++;
				}
				
			}
			
			
			rs_listaChamados.close();
			stmt.close();
			
			return countReabertos;
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


public int listaPainelPessoalPendentes(String perfil) throws ParseException {
	
	
	
	
	
	try {
		
		
		String sql_listaChamados = "";
		
		
		
		// tipo = "R";
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (usuarioLogado  instanceof UsuarioSistema ) {
		   username= ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
		} else {
		   username = usuarioLogado.toString();
		}
	
		
		if (perfil == "GESTOR"){

			sql_listaChamados = "select "
					+"count(1) as pendentes  "
					
				+"from "
					+"call_req req WITH(NOLOCK) "
					+ "join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
					+ "join prob_ctg cat WITH(NOLOCK) on cat.persid = req.category "
					+"join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee "
				+"where "
					+"cat.sym like 'INFRA%' "
					+"and stat.code in ('AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'RSCH', 'PF', 'ACK') ";


		} else {
		

			sql_listaChamados = "select "
					+"count(1) as pendentes  "
					
				+"from "
					+"call_req req WITH(NOLOCK) "
					+ "join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
					+ "join prob_ctg cat WITH(NOLOCK) on cat.persid = req.category "
					+"join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee "
				+"where "
					+"cat.sym like 'INFRA%' "
					+"and stat.code in ('AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'RSCH', 'PF', 'ACK') "
					+"and usu.userid = '"+username+"'  ";

		}
			//registrar texto da solução

		PreparedStatement stmt = connection
				.prepareStatement(sql_listaChamados);
		ResultSet rs_listaChamados = stmt.executeQuery();
		
		
		int countPendentes = 0;
		
		while (rs_listaChamados.next()){

				countPendentes = Integer.parseInt(rs_listaChamados.getString("pendentes"));
			
		}
		
		
		rs_listaChamados.close();
		stmt.close();
		
		return countPendentes;
		
		
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}
	
	

public List<Chamado>  listaPainelPessoalReabertosLista(String perfil) throws ParseException {
	
	
	ArrayList<Chamado> ListaChamados = new ArrayList<Chamado>();

	
	
	try {
		
		
		String sql_listaChamados = "";
		
		
		
		// tipo = "R";
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (usuarioLogado  instanceof UsuarioSistema ) {
		   username= ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
		} else {
		   username = usuarioLogado.toString();
		}
	
		
		if (perfil == "GESTOR"){

			sql_listaChamados = "select " 
					+"req.ref_num as chamados, "
					+"req.id as ID , "
					+"count(cast(log.action_desc as varchar)) reaberturas ,  "
					+"usu.first_name as responsavel , "
					+"vwg.last_name as equipe, "
					+"cat.sym as grupo, "
					+"req.type as tipo,  "
					+"req.summary as titulo,  "
					+"DATEDIFF(s, '1970-01-01 00:00:00', GETDATE()) as epoch, "
					+"stat.sym as statusDescricao " 
				+"from  "
					+"call_req req WITH(NOLOCK)  "
					+"join cr_stat stat WITH(NOLOCK) on req.status = stat.code  "
					+"join prob_ctg cat WITH(NOLOCK) on cat.persid = req.category " 
					+"join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee  "
					+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid " 
					+"join View_Group vwg  WITH (NOLOCK) on req.group_id = vwg.contact_uuid  "
				+"where  "
					+"cat.sym like 'INFRA%' " 
					+"and cat.sym not like 'INFRA.Ordem de Servico' " 
					+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' " 
					+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' " 
					+"and cat.sym not like 'Infra.Tarefas Internas' " 
					+"and req.type != 'P' " 
					+"and stat.code in ('RE','CL') " 
					+"and usu.userid = '"+username+"' "
					+"and resolve_date  + DATEPART(tz,SYSDATETIMEOFFSET())*60 >= DATEDIFF(s, '1970-01-01 00:00:00',CONVERT(VARCHAR(25),DATEADD(dd,-(DAY(getdate())-1),getdate()),101)) "
					+"and log.action_desc like 'registrar texto da solução' " 
					+"group by req.ref_num ,req.id, usu.first_name, vwg.last_name, cat.sym, req.type, req.summary, stat.sym " ;
		} else {
		
		

			sql_listaChamados ="select " 
					+"req.ref_num as chamados, "
					+"req.id as ID , "
					+"count(cast(log.action_desc as varchar)) reaberturas ,  "
					+"usu.first_name as responsavel , "
					+"vwg.last_name as equipe, "
					+"cat.sym as grupo, "
					+"req.type as tipo,  "
					+"req.summary as titulo,  "
					+"DATEDIFF(s, '1970-01-01 00:00:00', GETDATE()) as epoch, "
					+"stat.sym as statusDescricao " 
				+"from  "
					+"call_req req WITH(NOLOCK)  "
					+"join cr_stat stat WITH(NOLOCK) on req.status = stat.code  "
					+"join prob_ctg cat WITH(NOLOCK) on cat.persid = req.category " 
					+"join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee  "
					+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid " 
					+"join View_Group vwg  WITH (NOLOCK) on req.group_id = vwg.contact_uuid  "
				+"where  "
					+"cat.sym like 'INFRA%' " 
					+"and cat.sym not like 'INFRA.Ordem de Servico' " 
					+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' " 
					+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' " 
					+"and cat.sym not like 'Infra.Tarefas Internas' " 
					+"and req.type != 'P' " 
					+"and stat.code in ('RE','CL') " 
					+"and usu.userid = '"+username+"' "
					+"and resolve_date  + DATEPART(tz,SYSDATETIMEOFFSET())*60 >= DATEDIFF(s, '1970-01-01 00:00:00',CONVERT(VARCHAR(25),DATEADD(dd,-(DAY(getdate())-1),getdate()),101)) "
					+"and log.action_desc like 'registrar texto da solução' " 
					+"group by req.ref_num ,req.id, usu.first_name, vwg.last_name, cat.sym, req.type, req.summary, stat.sym " ;
		}
			//registrar texto da solução

		PreparedStatement stmt = connection
				.prepareStatement(sql_listaChamados);
		ResultSet rs_listaChamados = stmt.executeQuery();
		
		
		
		Popula popula = new Popula();

		int countReabertos = 0;
		
		while (rs_listaChamados.next()){
			Chamado chamados = new Chamado();
			
			chamados.setId(popula.populaID(rs_listaChamados));
			chamados.setEquipe(popula.populaEquipe(rs_listaChamados));
			chamados.setChamado(popula.populaChamados(rs_listaChamados));
			chamados.setTitulo(popula.populaTitulo(rs_listaChamados));
			chamados.setGrupo(popula.populaGrupo(rs_listaChamados));
			chamados.setTipo(popula.populaTipo(rs_listaChamados));
			chamados.setTipoLegivel(popula.populaTipoLegivel(rs_listaChamados));
			chamados.setStatusDescricao(popula.populaStatusDescricao(rs_listaChamados));
			chamados.setReaberto(popula.populaReaberto(rs_listaChamados));
			System.out.println("Chamado reaberto" + chamados.getChamado() + "reaberto?" + chamados.getReaberto());
			ListaChamados.add(chamados);
			
		}
		rs_listaChamados.close();
		stmt.close();

		if(ListaChamados.isEmpty()){
			return null;
		} else {
			return ListaChamados;
		}

	
} catch (SQLException e) {
	throw new RuntimeException(e);
}
}


public Connection getConnection() throws SQLException {
	return connection;
}


	
}
