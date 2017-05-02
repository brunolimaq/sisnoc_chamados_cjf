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
import org.springframework.web.context.annotation.RequestScope;

import br.com.sisnoc.chamados.dao.util.SemSlaDao;
import br.com.sisnoc.chamados.modelo.Chamado;
import br.com.sisnoc.chamados.negocio.Popula;
import br.com.sisnoc.chamados.security.UsuarioSistema;



@Repository
@SemSlaDao

public class PainelSemSlaDao {

	private  final Connection connection;


	@Autowired
	public PainelSemSlaDao(@Qualifier("datasourceSQL")DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Chamado> listaPainelSemSla(String equipe, String status,String categoria) throws ParseException {
		try {
			
			ArrayList<Chamado> ListaChamados = new ArrayList<Chamado>();
			String sql_listaChamados = "";
			
			String tipo = "R";
			
			// categoria = "INFRA.Ordem de Servico";
			// categoria = "INFRA.Tarefas Internas";
			
			if(status.equals("aberto")){

				sql_listaChamados = "select "
						+"req.ref_num as chamado, "
						+"req.id as ID "
					+"from "
						+"call_req req WITH(NOLOCK) join cr_stat stat WITH(NOLOCK) on "
						+"req.status = stat.code join prob_ctg cat WITH(NOLOCK) on "
						+"cat.persid = req.category "
					+"where cat.sym like '"+categoria+"' "
						+"and req.type in('"+tipo+"') "
						+"and stat.code = 'OP' ";

			}else if ( equipe.equals("")){
				
			
			sql_listaChamados = "select "
									+"req.ref_num as chamado, "
									+"req.id as ID "
								+"from "
									+"call_req req WITH(NOLOCK) join cr_stat stat WITH(NOLOCK) on "
									+"req.status = stat.code join prob_ctg cat WITH(NOLOCK) on "
									+"cat.persid = req.category "
								+"where cat.sym like '"+categoria+"' "
									+"and req.type in('"+tipo+"') "
									//+"and req.ref_num = 65799"
									+"and stat.code in('WIP','PRBAPP') ";
			
			}else if (equipe.equals("todas")) {
				
				sql_listaChamados = "select "
										+"req.ref_num as chamado, "
										+"req.id as ID "
									+"from "
										+"call_req req WITH(NOLOCK) "
										+"join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
										+"join prob_ctg cat WITH(NOLOCK) on cat.persid = req.category "
									+"where cat.sym like '"+categoria+"' "
										+"and req.type in('"+tipo+"') "
										+"and stat.code in ('AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'RSCH', 'PF', 'ACK') ";			
				
			}else if(status.equals("pendente")){
			
				
					sql_listaChamados = "select "
											+"req.ref_num as chamado, "
											+"req.id as ID "
										+"from "
											+"call_req req WITH(NOLOCK) "
											+"join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
											+"join prob_ctg cat WITH(NOLOCK) on cat.persid = req.category "
											+"join View_Group vwg  WITH (NOLOCK) on req.group_id = vwg.contact_uuid "
										+"where cat.sym like '"+categoria+"' "
											+"and req.type in('"+tipo+"') "
											+"and stat.code in ('AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'RSCH', 'PF', 'ACK') "
											+"and vwg.last_name = '"+equipe+"'";	
					
					//System.out.println(sql_listaChamados);
					
				}else if(status.equals("andamento")){

					sql_listaChamados = "select "
											+"req.ref_num as chamado, "
											+"req.id as ID "
										+"from "
											+"call_req req WITH(NOLOCK) "
											+"join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
											+"join prob_ctg cat WITH(NOLOCK) on cat.persid = req.category "
											+"join View_Group vwg  WITH (NOLOCK) on req.group_id = vwg.contact_uuid "
										+"where cat.sym like '"+categoria+"' "
											+"and req.type in('"+tipo+"') "
											+"and stat.code in('WIP','PRBAPP') "
											+"and vwg.last_name = '"+equipe+"'";	
					//System.out.println(sql_listaChamados);

					
			}
			
				
			
			
			
			 
			PreparedStatement stmt = connection
					.prepareStatement(sql_listaChamados);
			ResultSet rs_listaChamados = stmt.executeQuery();
			
			String lista = "\'\'";

			while (rs_listaChamados.next()){

				lista = lista +",\'" + rs_listaChamados.getString("ID") + "\'";
			}
			
			rs_listaChamados.close();
			
			//System.out.println(lista);
			// 65497
			// 65529
			// 65536
			// 65538
			String sql_listaLog = "select "
									+"req.id as ID, "
									+"req.ref_num as chamados, "
									+"usu.first_name as responsavel,"
									+"vwg.last_name as equipe,"
									+"ctg.sym as grupo,"
									+"req.summary as titulo, "
									
									+ "stat.sym as status "
								+"from "
									+"call_req req WITH(NOLOCK) "
									+"join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
									+"join prob_ctg ctg WITH(NOLOCK) on ctg.persid = req.category "
									+"join View_Group vwg  WITH (NOLOCK) on req.group_id = vwg.contact_uuid "
									+"left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee "
									
								+"where "
									
									//+"and req.id in  (470837) "
									+" req.id in  ("+ lista + ") "
									+" order by req.id ";
			
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
					
					chamados.setGrupo(popula.populaGrupo(rs_listalog));
					chamados.setTipo(tipo);
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getChamado());
//					System.out.println(chamados.getEpoch());
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getTime());
//					System.out.println(chamados.getStatus());
					ListaChamados.add(chamados);
					count++;
				}
			
				
				rs_listalog.close();
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
	
	public List<Chamado> listaPainelPessoalProblema() throws ParseException {
		try {
			
			ArrayList<Chamado> ListaProblema = new ArrayList<Chamado>();
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
					+ "ctg.sym as grupo " 
					+ "from " 
					+ "call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+ "left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
					+ "join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category " 
					+ "join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid  "
					+ "join View_Group vwg WITH (NOLOCK)  on req.group_id = vwg.contact_uuid " 
					+ "where ctg.sym like 'INFRA%' "
					+ "and ctg.sym not like 'INFRA.Ordem de Servico' "
					+ "and req.type in ('P') "
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
					
					
					
					
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getChamado());
//					System.out.println(chamados.getEpoch());
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getTime());
//					System.out.println(chamados.getStatus());
					ListaProblema.add(chamados);
					count++;
				}
			
				
				rs_listaOs.close();
				stmt.close();

				return ListaProblema;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Chamado> listaPainelPessoalEquipeProblema() throws ParseException {
		try {
			
			ArrayList<Chamado> ListaProblema = new ArrayList<Chamado>();
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
					+ "CASE req.type  as tipo " 
					+ "from " 
					+ "call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+ "left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
					+ "join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category " 
					+ "join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid  "
					+ "join View_Group vwg WITH (NOLOCK)  on req.group_id = vwg.contact_uuid " 
					+ "where ctg.sym like 'INFRA%' "
					+ "and ctg.sym not like 'INFRA.Ordem de Servico' "
					+ "and req.type in ('P') "
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
					
					
					
					
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getChamado());
//					System.out.println(chamados.getEpoch());
//					System.out.println("$$$$$$$$$$$$$$###########$$$$$$$$$$$");
//					System.out.println(chamados.getTime());
//					System.out.println(chamados.getStatus());
					ListaProblema.add(chamados);
					count++;
				}
			
				
				rs_listaOs.close();
				stmt.close();

				return ListaProblema;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() throws SQLException {
		return connection;
	}
}
