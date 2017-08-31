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

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


import br.com.sisnoc.chamados.dao.util.ChamadosDao;
import br.com.sisnoc.chamados.modelo.Chamado;
import br.com.sisnoc.chamados.negocio.CalculaSla;
import br.com.sisnoc.chamados.negocio.CalculaSla2;
import br.com.sisnoc.chamados.negocio.Popula;

@Primary
@Repository
@ChamadosDao
public class PainelChamadosDao {
	
	private  final Connection connection;

	
	@Autowired
	public PainelChamadosDao(@Qualifier("datasourceSQL") DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	


	
	public List<Chamado> listaPainelChamados(String equipe, String status,String tipo) throws ParseException {
		try {
			
			ArrayList<Chamado> ListaChamados = new ArrayList<Chamado>();
			String sql_listaChamados = "";
			String sql_listaChamadosFilhoAtd = "";
			String sql_listaChamadosSemRDM = "";
			String sql_listaChamadosRDMAtendida = "";
			// tipo = "R";
			
			
			if(status.equals("aberto")){

				sql_listaChamados = "select "
						+"req.ref_num as chamado, "
						+"req.id as ID "
					+"from "
						+"call_req req WITH(NOLOCK) join cr_stat stat WITH(NOLOCK) on "
						+"req.status = stat.code join prob_ctg cat WITH(NOLOCK) on "
						+"cat.persid = req.category "
					+"where "
						+"cat.sym like 'INFRA%' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
						+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
						+"and req.type in('"+tipo+"') "
						+"and stat.code = 'OP' ";
				
				if (tipo.equals("R")){
					

					sql_listaChamadosFilhoAtd = "select "
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
													+"and stat.code = 'FIP' "
													+"and (select count(1) from call_req where parent = req.persid) = (select count(1) from call_req where parent = req.persid and status in ('CL','RE','CNCL','AEUR'))";
				
					sql_listaChamadosSemRDM = "select "
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
							+"and stat.code = 'PNDCHG' "
							+ "and change is null";

					
					sql_listaChamadosRDMAtendida = "select "
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
							+"and stat.code = 'PNDCHG' "
							+ "and 'VRFY' = (select status from chg where chg.id = req.change)";
					
					
				}
				

			}else if ( equipe.equals("")){
				
			
			sql_listaChamados = "select "
									+"req.ref_num as chamado, "
									+"req.id as ID "
								+"from "
									+"call_req req WITH(NOLOCK) join cr_stat stat WITH(NOLOCK) on "
									+"req.status = stat.code join prob_ctg cat WITH(NOLOCK) on "
									+"cat.persid = req.category "
								+"where "
									+"cat.sym like 'INFRA%' "
									+"and cat.sym not like 'INFRA.Ordem de Servico' "
									+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
									+"and cat.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
									+"and cat.sym not like 'Infra.Tarefas Internas' "
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
										+"join prob_ctg cat1 WITH(NOLOCK) on cat1.persid = req.category "
									+"where "
										+"cat1.sym like 'INFRA%' "
										+"and cat1.sym not like 'INFRA.Ordem de Servico' "
										+"and cat1.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
										+"and cat1.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
										+"and cat1.sym not like 'Infra.Tarefas Internas' "
										+"and req.type in('"+tipo+"') "
										+"and stat.code in ('AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'RSCH', 'PF', 'ACK') ";			
				
			}else if(status.equals("pendente")){
			
				
					sql_listaChamados = "select "
											+"req.ref_num as chamado, "
											+"req.id as ID "
										+"from "
											+"call_req req WITH(NOLOCK) "
											+"join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
											+"join prob_ctg cat2 WITH(NOLOCK) on cat2.persid = req.category "
											+"join View_Group vwg  WITH (NOLOCK) on req.group_id = vwg.contact_uuid "
										+"where "
											+"cat2.sym like 'INFRA%' "
											+"and cat2.sym not like 'INFRA.Ordem de Servico' "
											+"and cat2.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
											+"and cat2.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
											+"and cat2.sym not like 'Infra.Tarefas Internas' "
											+"and req.type in('"+tipo+"') "
											+"and stat.code in ('AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'RSCH', 'PF', 'ACK') "
											+"and vwg.last_name = '"+equipe+"'";	
					
		
					
				}else if(status.equals("andamento")){

					sql_listaChamados = "select "
											+"req.ref_num as chamado, "
											+"req.id as ID "
										+"from "
											+"call_req req WITH(NOLOCK) "
											+"join cr_stat stat WITH(NOLOCK) on req.status = stat.code "
											+"join prob_ctg cat3 WITH(NOLOCK) on cat3.persid = req.category "
											+"join View_Group vwg  WITH (NOLOCK) on req.group_id = vwg.contact_uuid "
										+"where "
											+"cat3.sym like 'INFRA%' "
											+"and cat3.sym not like 'INFRA.Ordem de Servico' "
											+"and cat3.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
											+"and cat3.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
											+"and cat3.sym not like 'Infra.Tarefas Internas' "
											+"and req.type in('"+tipo+"') "
											+"and stat.code in('WIP','PRBAPP') "
											+"and vwg.last_name = '"+equipe+"'";	
						

					
			}
			
				
			
			
			
			 
			PreparedStatement stmt = connection
					.prepareStatement(sql_listaChamados);
			ResultSet rs_listaChamados = stmt.executeQuery();
			
			String lista = "\'\'";

			while (rs_listaChamados.next()){

				lista = lista +",\'" + rs_listaChamados.getString("ID") + "\'";
			}
			
			rs_listaChamados.close();
			
			if(!sql_listaChamadosFilhoAtd.equals("")){
				
				stmt = connection
						.prepareStatement(sql_listaChamadosFilhoAtd);
				ResultSet rs_listaChamadosFilho = stmt.executeQuery();
				
				while (rs_listaChamadosFilho.next()){

					lista = lista +",\'" + rs_listaChamadosFilho.getString("ID") + "\'";
				}
				
				rs_listaChamadosFilho.close();
				
			}
			
		if(!sql_listaChamadosSemRDM.equals("")){
				
				stmt = connection
						.prepareStatement(sql_listaChamadosSemRDM);
				ResultSet rs_sql_listaChamadosSemRDM = stmt.executeQuery();
				
				while (rs_sql_listaChamadosSemRDM.next()){

					lista = lista +",\'" + rs_sql_listaChamadosSemRDM.getString("ID") + "\'";
				}
				
				rs_sql_listaChamadosSemRDM.close();
				
			}
		
		if(!sql_listaChamadosRDMAtendida.equals("")){
			
			stmt = connection
					.prepareStatement(sql_listaChamadosRDMAtendida);
			ResultSet rs_sql_listaChamadosRDMAtendida = stmt.executeQuery();
			
			while (rs_sql_listaChamadosRDMAtendida.next()){

				lista = lista +",\'" + rs_sql_listaChamadosRDMAtendida.getString("ID") + "\'";
			}
			
			rs_sql_listaChamadosRDMAtendida.close();
			
		}
			
		
			
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
									+ "stat.sym as statusDescricao, "
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
					chamados.setStatusDescricao(popula.populaStatusDescricao(rs_listalog));

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

	
	public List<Chamado> listaChamadosDebug() throws ParseException {
		try {
			
			ArrayList<Chamado> ListaChamados = new ArrayList<Chamado>();
			String sql_listaChamados = "";
			String sql_listaChamadosFilhoAtd = "";
			
			
			
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
									+ "stat.sym as statusDescricao, "
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
									+"and req.id in  (479123) "
									+ "order by req.id, log.time_stamp";
									//476918
									//476872
			//+"DATEDIFF(s, '1970-01-01 00:00:00', GETDATE()) as epoch,"
			PreparedStatement stmt = connection
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
					chamados.setStatusDescricao(popula.populaStatusDescricao(rs_listalog));
					
					
					
					ListaChamados.add(chamados);
					count++;
				}
			
				
				rs_listalog.close();
				stmt.close();

				if(ListaChamados.isEmpty()){
					return null;
				} else {
					ArrayList<Chamado> listaCalculada = new ArrayList<Chamado>();
					
					listaCalculada.add(CalculaSla2.SlaCjf(ListaChamados));
					return listaCalculada;
				}

			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() throws SQLException {
		return connection;
	}
}
