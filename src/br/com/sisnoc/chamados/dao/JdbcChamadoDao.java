package br.com.sisnoc.chamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.sisnoc.chamados.modelo.Chamado;
import br.com.sisnoc.chamados.modelo.Relatorios;



@Repository
public class JdbcChamadoDao {

	private  final Connection connection;
	private Integer count_app;
	private Integer count_bd;
	private Integer count_bkp;
	private Integer count_Vm;
	private Integer count_So;
	private Integer count_Rede;
	private Integer count_Mon;
	
	private Integer count_Za;
	private Integer count_Sto;
	private Integer count_Doc;
	private Integer count_Corp;
	private Integer count_Seg;

	private Integer count_Ger;
	private Integer count_Pro;
	private Integer count_Os;
	private Integer count_PainelMon;
	private Integer count_PainelSol;
	private Integer count_PainelInc;
	private Integer count_PainelOrdemServico;
	private Integer Count_PainelRdm;
	
	//SAC
	
	private Integer count_sac;

	
	
	@Autowired
	public JdbcChamadoDao(DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//3 primeiras Query do Painel
	
			//Query / Lista RDM
			public List<br.com.sisnoc.chamados.modelo.Chamado> listaPainelRdm() throws ParseException {
				try {
					List<br.com.sisnoc.chamados.modelo.Chamado> ListaChamado = new ArrayList<br.com.sisnoc.chamados.modelo.Chamado>();


					String sql_mon = "select "
							+"chg_ref_num, "
			     			+"summary, "
							+"dateadd(hh,-3,dateadd(SS,sched_start_date,'19700101')) agendamento, "
							+"(SELECT [dbo].[ALG_calcula_tempo] (sched_duration)) duracao, "
							+"chgstat.sym status, "
							+"ca_contact.first_name nome, "
							+"replace(vwg.last_name,'Analistas ','') as grupo "
							+"from chg WITH (NOLOCK) "
							+"join chgstat WITH (NOLOCK) on chg.status = chgstat.code "
							+"join ca_contact WITH (NOLOCK)  on ca_contact.contact_uuid = chg.assignee "
							+"join View_Group vwg WITH (NOLOCK)  on chg.group_id = vwg.contact_uuid "
							+"where datepart(YYYY,dateadd(hh,-3,dateadd(SS,sched_start_date,'19700101'))) = datepart(YYYY,getdate()) "
							+"and datepart(MM,dateadd(hh,-3,dateadd(SS,sched_start_date,'19700101'))) = datepart(MM,getdate()) "
							+"and datepart(DD,dateadd(hh,-3,dateadd(SS,sched_start_date,'19700101'))) = datepart(DD,getdate()) "
							+"order by 1";
					
						//"where (ctg.sym like 'INFRA%'  or ctg.sym like 'SEGURAN�A%') "				
					PreparedStatement stmt = connection
							.prepareStatement(sql_mon);
					ResultSet rs = stmt.executeQuery();

				
					
					Integer count = 0;

					while (rs.next()){
						count++;
						// adiciona um chamado na lista
						ListaChamado.add(populaChamadoPainelRdm(rs));

						 
					}
					
					setCount_PainelRdm(count);
					
					rs.close();
					stmt.close();

					return ListaChamado;
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
	
	//Query / Lista Monitoração
	public List<Chamado> listaPainelMon() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_mon = "select "
					+"usu.first_name as name, "
					+"req.ref_num as chamado, "
					+"req.summary as descricao, "
					+"stat.sym as Status, "
					+"req.category, "
					+"ctg.sym as categoria, "
					+"CASE req.type "
					+    "when 'R' then 'Solicitação' "  
					+    "when 'I' then 'Incidente' "
					+"end as tipo, "
					+"req.time_spent_sum, "
					+"req.id as ID, "
					+"(SELECT [mdb].[dbo].[ALG_calcula_sla_atual](req.ref_num)) AS SLA, "
					+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio "
					+"from "
					+"call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+"left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
					+"join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category "
					+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid "
					+"where ctg.sym like 'INFRA%' "
					+"and ctg.sym not like 'INFRA.Solicitacao.Seguranca.Firewall.Outros' "  
					+"and ctg.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
					+"and req.type in ('R', 'I', 'P') and stat.code in ('OP', 'ACK') "
					+"and log.type='INIT' "
					+"order by 10";
			
				//"where (ctg.sym like 'INFRA%'  or ctg.sym like 'SEGURAN�A%') "				
			PreparedStatement stmt = connection
					.prepareStatement(sql_mon);
			ResultSet rs = stmt.executeQuery();

		
			
			Integer count = 0;

			while (rs.next()){
				count++;
				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoPainelMon(rs));

				 
			}
			
			setCount_PainelMon(count);
			
			rs.close();
			stmt.close();

			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Query / Lista Solicitação
	public List<Chamado> listaPainelSol() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_sol = "select "
					+"usu.first_name as name, "
					+"req.ref_num as chamado, "
					+"req.summary as descricao, "
					+"stat.sym as Status, "
					+"req.category, "
					+"ctg.sym as categoria, "
					+"CASE req.type "
					+    "when 'R' then 'Solicitação' "  
					+    "when 'I' then 'Incidente' "
					+"end as tipo, "
					+"req.time_spent_sum, "
					+"req.id as ID, "
					+"(SELECT [mdb].[dbo].[ALG_calcula_sla_atual](req.ref_num)) AS SLA, "
					+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio "
					+"from "
					+"call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+"left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
					+"join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category "
					+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid "
					+"where ctg.sym like 'INFRA%'"
					+"and ctg.sym not like 'INFRA.Ordem de Servico' "  
					+"and ctg.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' "
					+"and ctg.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' "
					+"and req.type in ('R') and stat.code in ('WIP','PRBAPP') "
					+"and log.type='INIT' "
					+"order by 10";
			
								
			PreparedStatement stmt = connection
					.prepareStatement(sql_sol);
			ResultSet rs = stmt.executeQuery();

		
			Integer count = 0;
			while (rs.next()){
				count++;

				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoPainelSol(rs));
			}
			
			setCount_PainelSol(count);
			
			rs.close();
			stmt.close();

			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Query / Lista Incidentes
	public List<Chamado> listaPainelInc() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_inc = "select "
					+"usu.first_name as name, "
					+"req.ref_num as chamado, "
					+"req.summary as descricao, "
					+"stat.sym as Status, "
					+"req.category, "
					+"ctg.sym as categoria, "
					+"CASE req.type "
					+    "when 'R' then 'Solicitação' "  
					+    "when 'I' then 'Incidente' "
					+"end as tipo, "
					+"req.time_spent_sum, "
					+"req.id as ID, "
					+"(SELECT [mdb].[dbo].[ALG_calcula_sla_atual](req.ref_num)) AS SLA, "
					+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio "
					+"from "
					+"call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK)  on req.status = stat.code " 
					+"left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
					+"join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category "
					+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid "
					+"where ctg.sym like 'INFRA%' "
					+"and ctg.sym not like 'INFRA.Ordem de Servico' "  
					+"and ctg.sym not like 'INFRA.Solicitacao.Atividades.Documentacao' " 
					+"and ctg.sym not like 'INFRA.Solicitacao.Atividades.Tarefas Internas' " 
					+"and req.type in ('I') and stat.code in ('WIP','PRBAPP') "
					+"and log.type='INIT' "
					+"order by 10";
			
								
			PreparedStatement stmt = connection
					.prepareStatement(sql_inc);
			ResultSet rs = stmt.executeQuery();

		
			
			Integer count=0;
			while (rs.next()){
				count++;
				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoPainelInc(rs));
				 
			}
			
			rs.close();
			stmt.close();
			
			setCount_PainelInc(count);
			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	//Query / Lista Ordem de Serviço
	public List<Chamado> listaPainelOrdemServico() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();



			String sql_OrdemServico = "select "
					+"usu.first_name as name, "
					+"req.ref_num as chamado, "
					+"req.summary as descricao, "
					+"stat.sym as Status, "
					+"req.category, "
					+"ctg.sym as categoria, "
					+"CASE req.type "
					+    "when 'R' then 'Solicitação' "  
					+    "when 'I' then 'Incidente' "
					+"end as tipo, "
					+"req.time_spent_sum, "
					+"req.id as ID, "
					+"datediff(dd,DATEADD(hh,-3,DATEADD(SS,req.last_mod_dt,'19700101')), getdate()) as diasatualizacao, "
					+"(DATEADD(HOUR, -3, DATEADD(SS,req.last_mod_dt,'19700101'))) as atualizacao, "
					+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio, "
					+"COALESCE(DATEADD(HOUR, -3, DATEADD(SS,call_back_date,'19700101')),0) as data_retorno "
					+"from "
					+"call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+"left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
					+"join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category "
					+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid "
					+"where ctg.sym like 'INFRA.Ordem de Servico' "
					+"and stat.code in ('WIP','PRBAPP') "
					+"and log.type='INIT' "
				//	+"and call_back_date is not null "
					+"order by 1";
			
								
			PreparedStatement stmt = connection
					.prepareStatement(sql_OrdemServico);
			ResultSet rs = stmt.executeQuery();

		
			
			Integer count=0;
			while (rs.next()){
				count++;
				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoPainelOrdemServico(rs));
				 
			}
			
			rs.close();
			stmt.close();
			
			setCount_PainelOrdemServico(count);
			
			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Query / Lista Aplicação
	public List<Chamado> listaApp() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_app = "select * from painel_chamados_pend where grupo = 'Analistas Aplicações' order by 2";
			//String sql_app = "select * from painel_Chamado_pend_filho where chamado_pai  in (select chamado from painel_chamados_pend_pai)";
								
			PreparedStatement stmt = connection
					.prepareStatement(sql_app);
			ResultSet rs = stmt.executeQuery();

		
			
			Integer count = 0;

			while (rs.next()){
				count++;

				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoApp(rs));

				 
			}
			setCount_app(count);
			
			rs.close();
			stmt.close();

			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//Query / Lista Bando de Dados
	public List<Chamado> listaBd() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_bd = "select * from painel_chamados_pend where grupo = 'Analistas Banco de Dados'  order by 2";
			
			
		
			PreparedStatement stmt = connection
					.prepareStatement(sql_bd);
			ResultSet rs = stmt.executeQuery();

			Integer count = 0;

			while (rs.next()) {
				count++;
				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoBd(rs));
				
				
				
			}
			setCount_bd(count);
			rs.close();
			stmt.close();

			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Query / Lista  Backup
	public List<Chamado> listaBkp() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_bkp = "select * from painel_chamados_pend where grupo = 'Analistas Backup' order by 2";
			
			
		
			PreparedStatement stmt = connection
					.prepareStatement(sql_bkp);
			ResultSet rs = stmt.executeQuery();

			Integer count = 0;
			while (rs.next()) {
				count++;
				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoBkp(rs));
											
			}
			setCount_bkp(count);
			rs.close();
			stmt.close();

			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Query / Lista  VMWare 
		public List<Chamado> listaVm() throws ParseException {
			try {
				List<Chamado> ListaChamado = new ArrayList<Chamado>();


				String sql_vm = "select * from painel_chamados_pend where grupo = 'Analistas Virtualização' order by 2";
				
				
			
				PreparedStatement stmt = connection
						.prepareStatement(sql_vm);
				ResultSet rs = stmt.executeQuery();

				Integer count = 0;
				while (rs.next()) {
					count++;
					// adiciona um chamado na lista
					ListaChamado.add(populaChamadoVm(rs));
												
				}
				setCount_Vm(count);
				rs.close();
				stmt.close();

				return ListaChamado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		//Query / Lista  sistemas operacionais
		public List<Chamado> listaSo() throws ParseException {
			try {
				List<Chamado> ListaChamado = new ArrayList<Chamado>();


				String sql_win = "select * from painel_chamados_pend where grupo = 'Analistas Sistemas Operacionais' order by 2";
				
				
			
				PreparedStatement stmt = connection
						.prepareStatement(sql_win);
				ResultSet rs = stmt.executeQuery();

				Integer count = 0;
				while (rs.next()) {
					count++;
					// adiciona um chamado na lista
					ListaChamado.add(populaChamadoo(rs));
												
				}
				setCount_So(count);
				rs.close();
				stmt.close();

				return ListaChamado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//Query / Lista  Rede
		public List<Chamado> listaRede() throws ParseException {
			try {
				List<Chamado> ListaChamado = new ArrayList<Chamado>();


				String sql_rede = "select * from painel_chamados_pend where grupo = 'Analistas Redes' order by 2";
				
				
			
				PreparedStatement stmt = connection
						.prepareStatement(sql_rede);
				ResultSet rs = stmt.executeQuery();

				Integer count = 0;
				while (rs.next()) {
					count++;
					// adiciona um chamado na lista
					ListaChamado.add(populaChamadoRede(rs));
												
				}
				setCount_Rede(count);
				rs.close();
				stmt.close();

				return ListaChamado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//Query / Lista  Monitoração
		
		public List<Chamado> listaMon() throws ParseException {
			try {
				List<Chamado> ListaChamado = new ArrayList<Chamado>();


				String sql_mon = "select * from painel_chamados_pend where grupo = 'Monitoradores' order by 2";
				
				
			
				PreparedStatement stmt = connection
						.prepareStatement(sql_mon);
				ResultSet rs = stmt.executeQuery();

				Integer count = 0;
				while (rs.next()) {
					count++;
					// adiciona um chamado na lista
					ListaChamado.add(populaChamadoMon(rs));
												
				}
				setCount_Mon(count);
				rs.close();
				stmt.close();

				return ListaChamado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//Query / Lista  Gerência
		public List<Chamado> listaGer() throws ParseException {
			try {
				List<Chamado> ListaChamado = new ArrayList<Chamado>();


				String sql_ger = "select * from painel_chamados_pend where grupo = 'Supervisores Datacenter' order by 2";
				
				
			
				PreparedStatement stmt = connection
						.prepareStatement(sql_ger);
				ResultSet rs = stmt.executeQuery();

				Integer count = 0;
				while (rs.next()) {
					count++;
					// adiciona um chamado na lista
					ListaChamado.add(populaChamadoGer(rs));
												
				}
				setCount_Ger(count);
				rs.close();
				stmt.close();

				return ListaChamado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//Query / Lista  seguranca
				public List<Chamado> listaSeg() throws ParseException {
					try {
						List<Chamado> ListaChamado = new ArrayList<Chamado>();


						String sql_ger = "select * from painel_chamados_pend where grupo = 'Analistas Segurança' order by 2";
						
						
					
						PreparedStatement stmt = connection
								.prepareStatement(sql_ger);
						ResultSet rs = stmt.executeQuery();

						Integer count = 0;
						while (rs.next()) {
							count++;
							// adiciona um chamado na lista
							ListaChamado.add(populaChamadoeg(rs));
														
						}
						setCount_Seg(count);
						rs.close();
						stmt.close();

						return ListaChamado;
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
				//Query / Lista  servicor corporativos
				public List<Chamado> listaCorp() throws ParseException {
					try {
						List<Chamado> ListaChamado = new ArrayList<Chamado>();


						String sql_ger = "select * from painel_chamados_pend where grupo = 'Analistas Serviços Corporativos' order by 2";
						
						
					
						PreparedStatement stmt = connection
								.prepareStatement(sql_ger);
						ResultSet rs = stmt.executeQuery();

						Integer count = 0;
						while (rs.next()) {
							count++;
							// adiciona um chamado na lista
							ListaChamado.add(populaChamadoCorp(rs));
														
						}
						setCount_Corp(count);
						rs.close();
						stmt.close();

						return ListaChamado;
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
				//Query / Lista  analistas monit
				public List<Chamado> listaMonit() throws ParseException {
					try {
						List<Chamado> ListaChamado = new ArrayList<Chamado>();


						String sql_ger = "select * from painel_chamados_pend where grupo = 'Analistas Monitoração' order by 2";
						
						
					
						PreparedStatement stmt = connection
								.prepareStatement(sql_ger);
						ResultSet rs = stmt.executeQuery();

						Integer count = 0;
						while (rs.next()) {
							count++;
							// adiciona um chamado na lista
							ListaChamado.add(populaChamadoMonit(rs));
														
						}
						setCount_Za(count);
						rs.close();
						stmt.close();

						return ListaChamado;
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
				//Query / Lista  storage
				public List<Chamado> listaStor() throws ParseException {
					try {
						List<Chamado> ListaChamado = new ArrayList<Chamado>();


						String sql_ger = "select * from painel_chamados_pend where grupo = 'Analistas Storage' order by 2";
						
						
					
						PreparedStatement stmt = connection
								.prepareStatement(sql_ger);
						ResultSet rs = stmt.executeQuery();

						Integer count = 0;
						while (rs.next()) {
							count++;
							// adiciona um chamado na lista
							ListaChamado.add(populaChamadotor(rs));
														
						}
						setCount_Sto(count);
						rs.close();
						stmt.close();

						return ListaChamado;
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
				//Query / Lista  documentadores
				public List<Chamado> listaDocu() throws ParseException {
					try {
						List<Chamado> ListaChamado = new ArrayList<Chamado>();


						String sql_ger = "select * from painel_chamados_pend where grupo = 'Documentadores' order by 2";
						
						
					
						PreparedStatement stmt = connection
								.prepareStatement(sql_ger);
						ResultSet rs = stmt.executeQuery();

						Integer count = 0;
						while (rs.next()) {
							count++;
							// adiciona um chamado na lista
							ListaChamado.add(populaChamadoDocu(rs));
														
						}
						setCount_Doc(count);
						rs.close();
						stmt.close();

						return ListaChamado;
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
		
		
		//Query / Lista  Problemas 
		
		public List<Chamado> listaPro() throws ParseException {
			try {
				List<Chamado> ListaChamado = new ArrayList<Chamado>();


				String sql_pro = "select "
						+"usu.first_name as name, "
						+"req.ref_num as chamado, "
						+"req.summary as descricao, "
						+"stat.sym as Status, "
						+"req.category, "
						+"ctg.sym as categoria, "
						+"CASE req.type "
						+    "when 'R' then 'Solicitação' "  
						+    "when 'I' then 'Incidente' "
						+"end as tipo, "
						+"req.time_spent_sum, "
						+"req.id as ID, "
						+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio "
						+"from "
						+"call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
						+"left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
						+"join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category "
						+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid "
						+"where ctg.sym like 'INFRA%' "
						+"and ctg.sym not like 'INFRA.Ordem de Servico' " 
						+"and req.type in ('P') "
						+"and stat.code in ('RSCH', 'OP', 'PF', 'WIP', 'AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'PRBAPP', 'ACK') "
						+"and log.type='INIT' "
						+"order by 4";
				
				
			
				PreparedStatement stmt = connection
						.prepareStatement(sql_pro);
				ResultSet rs = stmt.executeQuery();

				Integer count = 0;
				while (rs.next()) {
					count++;
					// adiciona um chamado na lista
					ListaChamado.add(populaChamadoPro(rs));
												
				}
				setCount_Pro(count);
				rs.close();
				stmt.close();

				return ListaChamado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
			
		//Query / Lista  Orden de Serviço
		public List<Chamado> listaOs() throws ParseException {
			try {
				List<Chamado> ListaChamado = new ArrayList<Chamado>();


				String sql_Os = "select "
						+"usu.first_name as name, "
						+"req.ref_num as chamado, "
						+"req.summary as descricao, "
						+"stat.sym as Status, "
						+"req.category, "
						+"ctg.sym as categoria, "
						+"CASE req.type "
						+    "when 'R' then 'Solicitação' "  
						+    "when 'I' then 'Incidente' "
						+"end as tipo, "
						+"req.time_spent_sum, "
						+"req.id as ID, "
						+"datediff(dd,DATEADD(hh,-3,DATEADD(SS,req.last_mod_dt,'19700101')), getdate()) as diasatualizacao, "
						+"(DATEADD(HOUR, -3, DATEADD(SS,req.last_mod_dt,'19700101'))) as atualizacao, "
						+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio, "
						+"COALESCE(DATEADD(HOUR, -3, DATEADD(SS,call_back_date,'19700101')),0) as data_retorno "
						+"from "
						+"call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
						+"left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
						+"join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category "
						+"join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid "
						+"where ctg.sym like 'INFRA.Ordem de Servico' "
						+"and stat.code in ('RSCH', 'OP', 'PF', 'AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'ACK') "
						+"and log.type='INIT' "
						+"order by 1";
				/*		
						"select "
						+"usu.first_name as name, "
						+"req.ref_num as chamado, "
						+"req.summary as descricao, "
						+"stat.sym as Status, "
						+"req.category, "
						+"ctg.sym as categoria, "
						+"CASE req.type "
						+    "when 'R' then 'Solicitação' "  
						+    "when 'I' then 'Incidente' "
						+"end as tipo, "
						+"req.time_spent_sum, "
						+"req.id as ID, "
						+"datediff(dd,DATEADD(hh,-3,DATEADD(SS,req.last_mod_dt,'19700101')), getdate()) as diasatualizacao, "
						+"(DATEADD(HOUR, -3, DATEADD(SS,req.last_mod_dt,'19700101'))) as atualizacao, "
						+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio, "
						+"(DATEADD(HOUR, -3, DATEADD(SS,call_back_date,'19700101'))) as data_retorno "
						+"from "
						+"call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK)  on req.status = stat.code " 
						+"left join ca_contact usu WITH (NOLOCK) on usu.contact_uuid = req.assignee " 
						+"join prob_ctg ctg WITH (NOLOCK) on ctg.persid = req.category "
						+"join act_log log WITH (NOLOCK) on log.call_req_id = req.persid "
						+"where ctg.sym like 'INFRA.Ordem de Servico' "
						+"and stat.code in ('RSCH', 'OP', 'PF', 'AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'ACK') "
						+"and log.type='INIT' "
						+"and call_back_date is not null "
						+"order by 1"; 
						*/
					
			
				PreparedStatement stmt = connection
						.prepareStatement(sql_Os);
				ResultSet rs = stmt.executeQuery();

				Integer count = 0;
				while (rs.next()) {
					count++;
					// adiciona um chamado na lista
					ListaChamado.add(populaChamadoOs(rs));
												
				}
				setCount_Os(count);
				rs.close();
				stmt.close();

				return ListaChamado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//Metódos para popular Listas
		//Paineis
		
		//Lista geral de Filhos
		
		public List<Chamado> listaFilhos() throws ParseException {
			try {
				List<Chamado> ListaChamado = new ArrayList<Chamado>();


				String sql_filhos = "select * from painel_chamados_pend_filho where chamado_pai  in (select chamado from painel_chamados_pend_pai)";
									
				PreparedStatement stmt = connection
						.prepareStatement(sql_filhos);
				ResultSet rs = stmt.executeQuery();

			
				
				Integer count = 0;

				while (rs.next()){
					count++;

					// adiciona um chamado na lista
					ListaChamado.add(populaChamadoFilhos(rs));

					 
				}
				setCount_app(count);
				
				rs.close();
				stmt.close();

				return ListaChamado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	//Lista geral de Filhos 2 - Resolver carinha
		
		public List<Chamado> listaFilhosCarinha() throws ParseException {
			try {
				List<Chamado> ListaChamado = new ArrayList<Chamado>();


				//String sql_filhos_carinha = "select chamado from painel_chamados_pend_pai where chamado in ( select chamado  from painel_Chamado_pend_filho)";
				String sql_filhos_carinha = "SELECT * FROM painel_chamados_pend_pai AS a WHERE chamado IN (SELECT chamado_pai FROM painel_chamados_pend_filho AS b WHERE b.chamado_pai = a.chamado) order by 1";
								
				PreparedStatement stmt = connection
						.prepareStatement(sql_filhos_carinha);
				ResultSet rs = stmt.executeQuery();

			
				
				Integer count = 0;

				while (rs.next()){
					count++;

					// adiciona um chamado na lista
					ListaChamado.add(populaChamadoFilhosCarinha(rs));

					 
				}
				setCount_app(count);
				
				rs.close();
				stmt.close();

				return ListaChamado;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		//Popula Chamado Filhos Carinha
				private Chamado populaChamadoFilhosCarinha(ResultSet rs) throws SQLException, ParseException {
					 Chamado chamado = new Chamado();

					
					 // popula o objeto chamado
					 			
					 chamado.setChamado(rs.getString("chamado"));
					 return  chamado;
				}
		
		//Popula Chamado Filhos
		private Chamado populaChamadoFilhos(ResultSet rs) throws SQLException, ParseException {
			 Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setChamado_Pai(rs.getString("chamado_pai"));
			 chamado.setNome(rs.getString("name"));
			 chamado.setDescricao(rs.getString("descricao"));
			 chamado.setChamado(rs.getString("chamado"));
			 chamado.setTipo(rs.getString("tipo"));
			 chamado.setCategoria(rs.getString("categoria"));
			 chamado.setStatus(rs.getString("Status"));
			 //chamado.setAbertura(rs.getString("Inicio"));
//			 chamado.setSla(rs.getString("SLA"));
//			 chamado.setSla2(rs.getInt("SLA2"));
			 
			 
			 ///VOLTAR
			 
//			 
//			 String [] a = rs.getString("SLA").split("XXX");
//
//			 chamado.setSla(a[0]);
//			 chamado.setSla2(Integer.parseInt(a[1]));
			 
			 chamado.setSla("");
			 chamado.setSla2(0);
			 

			 chamado.setId(rs.getString("ID"));
		//	 chamado.setDiasAtualizacao(rs.getString("diasatualizacao"));
			 
		//	 Date atualizacao = rs.getDate("atualizacao");
		//	 DateFormat formataDataAtualizacao = DateFormat.getDateInstance();
		//	 chamado.setAtualizacao(formataDataAtualizacao.format(atualizacao));

			 //Formatando a Data
			 Date dataincial = rs.getDate("data_inicio");
			 DateFormat formataData = DateFormat.getDateInstance();
			 chamado.setDataInicio(formataData.format(dataincial));
	 

		
			 return  chamado;
		}
		private Chamado populaChamadoPainelRdm(ResultSet rs) throws SQLException, ParseException {
			
			Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setNome(rs.getString("nome"));
			 chamado.setDescricao(rs.getString("summary"));
			 chamado.setChamado(rs.getString("chg_ref_num"));
			 chamado.setCategoria(rs.getString("grupo"));
			 chamado.setStatus(rs.getString("status"));
			 chamado.setDataAgendamento(rs.getString("agendamento"));
			 //chamado.setAbertura(rs.getString("Inicio"));
			 //chamado.setId(rs.getString("ID"));
			 //Formatando a Data
			 //Date dataagendamento = rs.getDate("agendamento");
			 //DateFormat formataData = DateFormat.getDateInstance();
			 //chamado.setDataAgendamento(formataData.format(dataagendamento));
			 

		
			 return  chamado;
		}
		
		
		private Chamado populaChamadoPainelMon(ResultSet rs) throws SQLException, ParseException {
			
			Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setNome(rs.getString("name"));
			 chamado.setDescricao(rs.getString("descricao"));
			 chamado.setChamado(rs.getString("chamado"));
			 chamado.setTipo(rs.getString("tipo"));
			 chamado.setCategoria(rs.getString("categoria"));
			 chamado.setStatus(rs.getString("Status"));
			 //chamado.setAbertura(rs.getString("Inicio"));
			 String [] a = rs.getString("SLA").split("XXX");

			 chamado.setSla(a[0]);
			 chamado.setSla2(Integer.parseInt(a[1]));

			 chamado.setId(rs.getString("ID"));
			 //Formatando a Data
			 Date dataincial = rs.getDate("data_inicio");
			 DateFormat formataData = DateFormat.getDateInstance();
			 chamado.setDataInicio(formataData.format(dataincial));
			 

		
			 return  chamado;
		}
		
		
		private Chamado populaChamadoPainelSol(ResultSet rs) throws SQLException, ParseException {
			 Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setNome(rs.getString("name"));
			 chamado.setDescricao(rs.getString("descricao"));
			 chamado.setChamado(rs.getString("chamado"));
			 chamado.setTipo(rs.getString("tipo"));
			 chamado.setCategoria(rs.getString("categoria"));
			 chamado.setStatus(rs.getString("Status"));
			 //chamado.setAbertura(rs.getString("Inicio"));

			 String [] a = rs.getString("SLA").split("XXX");

			 chamado.setSla(a[0]);
			 chamado.setSla2(Integer.parseInt(a[1]));
			 
			 
			 chamado.setId(rs.getString("ID"));
			 //Formatando a Data
			 Date dataincial = rs.getDate("data_inicio");
			 DateFormat formataData = DateFormat.getDateInstance();
			 chamado.setDataInicio(formataData.format(dataincial));
			 

		
			 return  chamado;
		}
		
		
		private Chamado populaChamadoPainelInc(ResultSet rs) throws SQLException, ParseException {
			 Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setNome(rs.getString("name"));
			 chamado.setDescricao(rs.getString("descricao"));
			 chamado.setChamado(rs.getString("chamado"));
			 chamado.setTipo(rs.getString("tipo"));
			 chamado.setCategoria(rs.getString("categoria"));
			 chamado.setStatus(rs.getString("Status"));
			 //chamado.setAbertura(rs.getString("Inicio"));
			 String [] a = rs.getString("SLA").split("XXX");

			 chamado.setSla(a[0]);
			 chamado.setSla2(Integer.parseInt(a[1]));

			 chamado.setId(rs.getString("ID"));
			 //Formatando a Data
			 Date dataincial = rs.getDate("data_inicio");
			 DateFormat formataData = DateFormat.getDateInstance();
			 chamado.setDataInicio(formataData.format(dataincial));
			 

		
			 return  chamado;
		}
		
		// Popula Lista Painel Ordem Serviço
		
		private Chamado populaChamadoPainelOrdemServico(ResultSet rs) throws SQLException, ParseException {
			 Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setNome(rs.getString("name"));
			 chamado.setDescricao(rs.getString("descricao"));
			 chamado.setChamado(rs.getString("chamado"));
			 chamado.setTipo(rs.getString("tipo"));
			 chamado.setCategoria(rs.getString("categoria"));
			 chamado.setStatus(rs.getString("Status"));
			 //chamado.setAbertura(rs.getString("Inicio"));
			 chamado.setId(rs.getString("ID"));
			 chamado.setDiasAtualizacao(rs.getString("diasatualizacao"));
			 
			 Date atualizacao = rs.getDate("atualizacao");
			 DateFormat formataDataAtualizacao = DateFormat.getDateInstance();
			 chamado.setAtualizacao(formataDataAtualizacao.format(atualizacao));

			 //Formatando a Data
			 Date dataincial = rs.getDate("data_inicio");
			 DateFormat formataData = DateFormat.getDateInstance();
			 chamado.setDataInicio(formataData.format(dataincial));
			
			 Date data_retorno = rs.getDate("data_retorno");
			 DateFormat formataDataRetorno = DateFormat.getDateInstance();
			 chamado.setData_retorno(formataDataRetorno.format(data_retorno));
			 
			 return  chamado;
		}
		
		//Aplicação
		
	private Chamado populaChamadoApp(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));

		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 

	
		 return  chamado;
	}
	
	//Banco de Dados
	
	private Chamado populaChamadoBd(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();
		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));

		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));


		 return  chamado;
	}
	
	//Backup
	private Chamado populaChamadoBkp(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();
		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));

		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));

		 
		 return  chamado;
	}
	
	//VMware
	private Chamado populaChamadoVm(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));

		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 
	
		 return  chamado;
	}
	
	//sistemas operacionais
	private Chamado populaChamadoo(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));

		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 
	
		 return  chamado;
	}
	
	//Rede
	private Chamado populaChamadoRede(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));

		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 
	
		 return  chamado;
	}
	
	//Monitoração
	private Chamado populaChamadoMon(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));
		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 
	
		 return  chamado;
	}
	
	//Gerência 
	private Chamado populaChamadoGer(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));
		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 
	
		 return  chamado;
	}
	
	//seguranca
	private Chamado populaChamadoeg(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));
		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 
	
		 return  chamado;
	}
	
	//corporativo 
		private Chamado populaChamadoCorp(ResultSet rs) throws SQLException, ParseException {
			 Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setNome(rs.getString("name"));
			 chamado.setDescricao(rs.getString("descricao"));
			 chamado.setChamado(rs.getString("chamado"));
			 chamado.setTipo(rs.getString("tipo"));
			 chamado.setCategoria(rs.getString("categoria"));
			 chamado.setStatus(rs.getString("Status"));
			 //chamado.setAbertura(rs.getString("Inicio"));
			 String [] a = rs.getString("SLA").split("XXX");

			 chamado.setSla(a[0]);
			 chamado.setSla2(Integer.parseInt(a[1]));
			 chamado.setId(rs.getString("ID"));
			 //Formatando a Data
			 Date dataincial = rs.getDate("data_inicio");
			 DateFormat formataData = DateFormat.getDateInstance();
			 chamado.setDataInicio(formataData.format(dataincial));
			 
		
			 return  chamado;
		}
		//analista monitoracao 
				private Chamado populaChamadoMonit(ResultSet rs) throws SQLException, ParseException {
					 Chamado chamado = new Chamado();

					
					 // popula o objeto chamado
					 			
					 chamado.setNome(rs.getString("name"));
					 chamado.setDescricao(rs.getString("descricao"));
					 chamado.setChamado(rs.getString("chamado"));
					 chamado.setTipo(rs.getString("tipo"));
					 chamado.setCategoria(rs.getString("categoria"));
					 chamado.setStatus(rs.getString("Status"));
					 //chamado.setAbertura(rs.getString("Inicio"));
					 String [] a = rs.getString("SLA").split("XXX");

					 chamado.setSla(a[0]);
					 chamado.setSla2(Integer.parseInt(a[1]));
					 chamado.setId(rs.getString("ID"));
					 //Formatando a Data
					 Date dataincial = rs.getDate("data_inicio");
					 DateFormat formataData = DateFormat.getDateInstance();
					 chamado.setDataInicio(formataData.format(dataincial));
					 
				
					 return  chamado;
				}
				//analista storage 
				private Chamado populaChamadotor(ResultSet rs) throws SQLException, ParseException {
					 Chamado chamado = new Chamado();

					
					 // popula o objeto chamado
					 			
					 chamado.setNome(rs.getString("name"));
					 chamado.setDescricao(rs.getString("descricao"));
					 chamado.setChamado(rs.getString("chamado"));
					 chamado.setTipo(rs.getString("tipo"));
					 chamado.setCategoria(rs.getString("categoria"));
					 chamado.setStatus(rs.getString("Status"));
					 //chamado.setAbertura(rs.getString("Inicio"));
					 String [] a = rs.getString("SLA").split("XXX");

					 chamado.setSla(a[0]);
					 chamado.setSla2(Integer.parseInt(a[1]));
					 chamado.setId(rs.getString("ID"));
					 //Formatando a Data
					 Date dataincial = rs.getDate("data_inicio");
					 DateFormat formataData = DateFormat.getDateInstance();
					 chamado.setDataInicio(formataData.format(dataincial));
					 
				
					 return  chamado;
				}
				//analista documentadores
				private Chamado populaChamadoDocu(ResultSet rs) throws SQLException, ParseException {
					 Chamado chamado = new Chamado();

					
					 // popula o objeto chamado
					 			
					 chamado.setNome(rs.getString("name"));
					 chamado.setDescricao(rs.getString("descricao"));
					 chamado.setChamado(rs.getString("chamado"));
					 chamado.setTipo(rs.getString("tipo"));
					 chamado.setCategoria(rs.getString("categoria"));
					 chamado.setStatus(rs.getString("Status"));
					 //chamado.setAbertura(rs.getString("Inicio"));
					 String [] a = rs.getString("SLA").split("XXX");

					 chamado.setSla(a[0]);
					 chamado.setSla2(Integer.parseInt(a[1]));
					 chamado.setId(rs.getString("ID"));
					 //Formatando a Data
					 Date dataincial = rs.getDate("data_inicio");
					 DateFormat formataData = DateFormat.getDateInstance();
					 chamado.setDataInicio(formataData.format(dataincial));
					 
				
					 return  chamado;
				}
	
	//Problemas
	private Chamado populaChamadoPro(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 chamado.setId(rs.getString("ID"));
		 
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 
	
		 return  chamado;
	}

	//Ordem de Serviço
	private Chamado populaChamadoOs(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 chamado.setId(rs.getString("ID"));
		 chamado.setDiasAtualizacao(rs.getString("diasatualizacao"));
		 
		 Date atualizacao = rs.getDate("atualizacao");
		 DateFormat formataDataAtualizacao = DateFormat.getDateInstance();
		 chamado.setAtualizacao(formataDataAtualizacao.format(atualizacao));

		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 
	//	chamado.setData_retorno(rs.getString("data_retorno"));
		 Date data_retorno = rs.getDate("data_retorno");
		 DateFormat formataDataRetorno = DateFormat.getDateInstance();
		 chamado.setData_retorno(formataDataRetorno.format(data_retorno));
	
		 return  chamado;
	}
	
	//Relátórios
	

	public List<Relatorios> listaRelatorio() throws ParseException {
		try {
			List<Relatorios> listaRelatorio = new ArrayList<Relatorios>();


			String sql_rel = "select Responsável, Chamado, Resumo, categoria, tipo, data, hora, qtd_aberto,result from chamados_alg where data_filtro > 20150701  and data_filtro < 20150713";
			
								
			PreparedStatement stmt = connection
					.prepareStatement(sql_rel);
			ResultSet rs = stmt.executeQuery();

		
			

			while (rs.next()){
				// adiciona um chamado na lista
				listaRelatorio.add(populaRelatorio(rs));

				 
			}
			
			rs.close();
			stmt.close();

			return listaRelatorio;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private Relatorios populaRelatorio(ResultSet rs) throws SQLException, ParseException {
		 
		Relatorios relatorio = new Relatorios();

		
		 // popula o objeto chamado
		 			
		 relatorio.setResponsavel(rs.getString("Responsável"));
		 relatorio.setChamado(rs.getString("Chamado"));
		 relatorio.setResumo(rs.getString("Resumo"));
		 relatorio.setCategoria(rs.getString("categoria"));
		 relatorio.setTipo(rs.getString("tipo"));
		 relatorio.setQts_aberto(rs.getString("qtd_aberto"));
		 relatorio.setResult(rs.getString("result"));
		 
		 
		 //Formatando a Data
	//	 Date dataincial = rs.getDate("data_inicio");
	//	 DateFormat formataData = DateFormat.getDateInstance();
	//	 relatorio.setDataInicio(formataData.format(dataincial));
		 

	
		 return  relatorio;
	}
	
	
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	

	
	//Query / Lista Monitoração
	public List<Chamado> listaPainelMonSac() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_mon = "select "
					+"usu.first_name as name, "
					+"req.ref_num as chamado, "
					+"req.summary as descricao, "
					+"stat.sym as Status, "
					+"req.category, "
					+"ctg.sym as categoria, "
					+"CASE req.type "
					+    "when 'R' then 'Solicitação' "  
					+    "when 'I' then 'Incidente' "
					+"end as tipo, "
					+"req.time_spent_sum, "
					+"req.id as ID, "
					+"(SELECT [mdb].[dbo].[ALG_calcula_sla_atual_sac](req.ref_num)) AS SLA, "
					+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio "
					+"from "
					+"call_req req WITH (NOLOCK) join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+"left join ca_contact usu WITH (NOLOCK) on usu.contact_uuid = req.assignee " 
					+"join prob_ctg ctg WITH (NOLOCK) on ctg.persid = req.category "
					+"join act_log log WITH (NOLOCK) on log.call_req_id = req.persid "
					+"where ctg.sym like 'SAC%' "
					+"and stat.code in ('OP', 'ACK') "
					+"and log.type='INIT' "
					+"order by 10";
			
				//"where (ctg.sym like 'INFRA%'  or ctg.sym like 'SEGURAN�A%') "				
			PreparedStatement stmt = connection
					.prepareStatement(sql_mon);
			ResultSet rs = stmt.executeQuery();

		
			

			while (rs.next()){
				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoPainelMonSac(rs));

				 
			}
			
			
			rs.close();
			stmt.close();

			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Query / Lista Solicitação
	public List<Chamado> listaPainelSolSac() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_sol = "select "
					+"usu.first_name as name, "
					+"req.ref_num as chamado, "
					+"req.summary as descricao, "
					+"stat.sym as Status, "
					+"req.category, "
					+"ctg.sym as categoria, "
					+"CASE req.type "
					+    "when 'R' then 'Solicitação' "  
					+    "when 'I' then 'Incidente' "
					+"end as tipo, "
					+"req.time_spent_sum, "
					+"req.id as ID, "
					+"(SELECT [mdb].[dbo].[ALG_calcula_sla_atual_sac](req.ref_num)) AS SLA, "
					+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio "
					+"from "
					+"call_req req WITH (NOLOCK) join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+"left join ca_contact usu WITH (NOLOCK) on usu.contact_uuid = req.assignee " 
					+"join prob_ctg ctg WITH (NOLOCK) on ctg.persid = req.category "
					+"join act_log log WITH (NOLOCK) on log.call_req_id = req.persid "
					+"where ctg.sym like 'SAC%'"
					+"and req.type in ('R') and stat.code in ('WIP','PRBAPP','AEUR') "
					+"and log.type='INIT' "
					+"order by 10";
			
								
			PreparedStatement stmt = connection
					.prepareStatement(sql_sol);
			ResultSet rs = stmt.executeQuery();

		
			while (rs.next()){

				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoPainelSolSac(rs));
			}
			
			
			rs.close();
			stmt.close();

			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Query / Lista Incidentes
	public List<Chamado> listaPainelIncSac() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_inc = "select "
					+"usu.first_name as name, "
					+"req.ref_num as chamado, "
					+"req.summary as descricao, "
					+"stat.sym as Status, "
					+"req.category, "
					+"ctg.sym as categoria, "
					+"CASE req.type "
					+    "when 'R' then 'Solicitação' "  
					+    "when 'I' then 'Incidente' "
					+"end as tipo, "
					+"req.time_spent_sum, "
					+"req.id as ID, "
					+"(SELECT [mdb].[dbo].[ALG_calcula_sla_atual_sac](req.ref_num)) AS SLA, "
					+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio "
					+"from "
					+"call_req req WITH (NOLOCK) join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+"left join ca_contact usu WITH (NOLOCK) on usu.contact_uuid = req.assignee " 
					+"join prob_ctg ctg WITH (NOLOCK) on ctg.persid = req.category "
					+"join act_log log WITH (NOLOCK) on log.call_req_id = req.persid "
					+"where ctg.sym like 'SAC%' "
					+"and req.type in ('I') and stat.code in ('WIP','PRBAPP','AEUR') "
					+"and log.type='INIT' "
					+"order by 10";
			
								
			PreparedStatement stmt = connection
					.prepareStatement(sql_inc);
			ResultSet rs = stmt.executeQuery();

		
			
			while (rs.next()){
				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoPainelIncSac(rs));
				 
			}
			
			rs.close();
			stmt.close();
			
			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	


	//Query / Lista SAC
	public List<Chamado> listaSac() throws ParseException {
		try {
			List<Chamado> ListaChamado = new ArrayList<Chamado>();


			String sql_app =  "select "
					+"usu.first_name as name, "
					+"req.ref_num as chamado, "
					+"req.summary as descricao, "
					+"stat.sym as Status, "
					+"req.category, "
					+"ctg.sym as categoria, "
					+"CASE req.type "
					+    "when 'R' then 'Solicitação' "  
					+    "when 'I' then 'Incidente' "
					+"end as tipo, "
					+"req.time_spent_sum, "
					+"req.id as ID, "
					+"(SELECT [mdb].[dbo].[ALG_calcula_sla_atual_sac](req.ref_num)) AS SLA, "
					+"(DATEADD(HOUR, -3, DATEADD(SS,log.time_stamp,'19700101'))) as data_inicio "
					+"from "
					+"call_req req WITH (NOLOCK) join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+"left join ca_contact usu WITH (NOLOCK) on usu.contact_uuid = req.assignee " 
					+"join prob_ctg ctg WITH (NOLOCK) on ctg.persid = req.category "
					+"join act_log log WITH (NOLOCK) on log.call_req_id = req.persid "
					+"where ctg.sym like 'SAC%' "
					+"and stat.code in ('RSCH' , 'FIP' , 'CLUNRSLV' , 'PO' , 'PF' , 'WIP' , 'ACK' , 'HOLD' , 'AEUR' , 'AWTVNDR' , 'PNDCHG' , 'APP' , 'FXD' , 'SARES' , 'SAABND,KE' , 'PRBANCOMP' , 'PRBAPP') "
					+"and log.type='INIT' "
					+"order by 10";
			


			
			PreparedStatement stmt = connection
					.prepareStatement(sql_app);
			ResultSet rs = stmt.executeQuery();

		
			
			Integer count = 0;

			while (rs.next()){
				count++;

				// adiciona um chamado na lista
				ListaChamado.add(populaChamadoac(rs));

				 
			}
			setCount_sac(count);
			
			rs.close();
			stmt.close();

			return ListaChamado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
		private Chamado populaChamadoPainelMonSac(ResultSet rs) throws SQLException, ParseException {
			
			Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setNome(rs.getString("name"));
			 chamado.setDescricao(rs.getString("descricao"));
			 chamado.setChamado(rs.getString("chamado"));
			 chamado.setTipo(rs.getString("tipo"));
			 chamado.setCategoria(rs.getString("categoria"));
			 chamado.setStatus(rs.getString("Status"));
			 //chamado.setAbertura(rs.getString("Inicio"));
			 String [] a = rs.getString("SLA").split("XXX");

			 chamado.setSla(a[0]);
			 chamado.setSla2(Integer.parseInt(a[1]));
			 chamado.setId(rs.getString("ID"));
			 //Formatando a Data
			 Date dataincial = rs.getDate("data_inicio");
			 DateFormat formataData = DateFormat.getDateInstance();
			 chamado.setDataInicio(formataData.format(dataincial));
			 

		
			 return  chamado;
		}
		
		
		private Chamado populaChamadoPainelSolSac(ResultSet rs) throws SQLException, ParseException {
			 Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setNome(rs.getString("name"));
			 chamado.setDescricao(rs.getString("descricao"));
			 chamado.setChamado(rs.getString("chamado"));
			 chamado.setTipo(rs.getString("tipo"));
			 chamado.setCategoria(rs.getString("categoria"));
			 chamado.setStatus(rs.getString("Status"));
			 //chamado.setAbertura(rs.getString("Inicio"));
			 String [] a = rs.getString("SLA").split("XXX");

			 chamado.setSla(a[0]);
			 chamado.setSla2(Integer.parseInt(a[1]));
			 chamado.setId(rs.getString("ID"));
			 //Formatando a Data
			 Date dataincial = rs.getDate("data_inicio");
			 DateFormat formataData = DateFormat.getDateInstance();
			 chamado.setDataInicio(formataData.format(dataincial));
			 

		
			 return  chamado;
		}
		
		
		private Chamado populaChamadoPainelIncSac(ResultSet rs) throws SQLException, ParseException {
			 Chamado chamado = new Chamado();

			
			 // popula o objeto chamado
			 			
			 chamado.setNome(rs.getString("name"));
			 chamado.setDescricao(rs.getString("descricao"));
			 chamado.setChamado(rs.getString("chamado"));
			 chamado.setTipo(rs.getString("tipo"));
			 chamado.setCategoria(rs.getString("categoria"));
			 chamado.setStatus(rs.getString("Status"));
			 //chamado.setAbertura(rs.getString("Inicio"));
			 String [] a = rs.getString("SLA").split("XXX");

			 chamado.setSla(a[0]);
			 chamado.setSla2(Integer.parseInt(a[1]));
			 chamado.setId(rs.getString("ID"));
			 //Formatando a Data
			 Date dataincial = rs.getDate("data_inicio");
			 DateFormat formataData = DateFormat.getDateInstance();
			 chamado.setDataInicio(formataData.format(dataincial));
			 

		
			 return  chamado;
		}
		
		
		//Aplicação
		
	private Chamado populaChamadoac(ResultSet rs) throws SQLException, ParseException {
		 Chamado chamado = new Chamado();

		
		 // popula o objeto chamado
		 			
		 chamado.setNome(rs.getString("name"));
		 chamado.setDescricao(rs.getString("descricao"));
		 chamado.setChamado(rs.getString("chamado"));
		 chamado.setTipo(rs.getString("tipo"));
		 chamado.setCategoria(rs.getString("categoria"));
		 chamado.setStatus(rs.getString("Status"));
		 //chamado.setAbertura(rs.getString("Inicio"));
		 String [] a = rs.getString("SLA").split("XXX");

		 chamado.setSla(a[0]);
		 chamado.setSla2(Integer.parseInt(a[1]));
		 chamado.setId(rs.getString("ID"));
		 //Formatando a Data
		 Date dataincial = rs.getDate("data_inicio");
		 DateFormat formataData = DateFormat.getDateInstance();
		 chamado.setDataInicio(formataData.format(dataincial));
		 

	
		 return  chamado;
	}
	
	
	

	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################
	///#################################################################################################

	public Integer getCount_sac() {
		return count_sac;
	}




	public void setCount_sac(Integer count_sac) {
		this.count_sac = count_sac;
	}
	
	
	public Integer getCount_app() {
		return count_app;
	}

	public void setCount_app(Integer count_app) {
		this.count_app = count_app;
	}

	public Integer getCount_bd() {
		return count_bd;
	}

	public void setCount_bd(Integer count_bd) {
		this.count_bd = count_bd;
	}

	public Integer getCount_bkp() {
		return count_bkp;
	}

	public void setCount_bkp(Integer count_bkp) {
		this.count_bkp = count_bkp;
	}

	public Integer getCount_Vm() {
		return count_Vm;
	}

	public void setCount_Vm(Integer count_Vm) {
		this.count_Vm = count_Vm;
	}

	public Integer getCount_Rede() {
		return count_Rede;
	}

	public void setCount_Rede(Integer count_Rede) {
		this.count_Rede = count_Rede;
	}

	public Integer getCount_Mon() {
		return count_Mon;
	}

	public void setCount_Mon(Integer count_Mon) {
		this.count_Mon = count_Mon;
	}

	public Integer getCount_Ger() {
		return count_Ger;
	}

	public void setCount_Ger(Integer count_Ger) {
		this.count_Ger = count_Ger;
	}

	public Integer getCount_Pro() {
		return count_Pro;
	}

	public void setCount_Pro(Integer count_Pro) {
		this.count_Pro = count_Pro;
	}

	public Integer getCount_Os() {
		return count_Os;
	}

	public void setCount_Os(Integer count_Os) {
		this.count_Os = count_Os;
	}

	public Integer getCount_PainelMon() {
		return count_PainelMon;
	}

	public void setCount_PainelMon(Integer count_PainelMon) {
		this.count_PainelMon = count_PainelMon;
	}

	public Integer getCount_PainelSol() {
		return count_PainelSol;
	}

	public void setCount_PainelSol(Integer count_PainelSol) {
		this.count_PainelSol = count_PainelSol;
	}

	public Integer getCount_PainelInc() {
		return count_PainelInc;
	}

	public void setCount_PainelInc(Integer count_PainelInc) {
		this.count_PainelInc = count_PainelInc;
	}

	public Integer getCount_So() {
		return count_So;
	}

	public void setCount_So(Integer count_So) {
		this.count_So = count_So;
	}

	public Integer getCount_Za() {
		return count_Za;
	}

	public void setCount_Za(Integer count_Za) {
		this.count_Za = count_Za;
	}

	public Integer getCount_Sto() {
		return count_Sto;
	}

	public void setCount_Sto(Integer count_Sto) {
		this.count_Sto = count_Sto;
	}

	public Integer getCount_Doc() {
		return count_Doc;
	}

	public void setCount_Doc(Integer count_Doc) {
		this.count_Doc = count_Doc;
	}

	public Integer getCount_Corp() {
		return count_Corp;
	}

	public void setCount_Corp(Integer count_Corp) {
		this.count_Corp = count_Corp;
	}

	public Integer getCount_Seg() {
		return count_Seg;
	}

	public void setCount_Seg(Integer count_Seg) {
		this.count_Seg = count_Seg;
	}

	public Integer getCount_PainelOrdemServico() {
		return count_PainelOrdemServico;
	}

	public void setCount_PainelOrdemServico(Integer count_PainelOrdemServico) {
		this.count_PainelOrdemServico = count_PainelOrdemServico;
	}


	public Integer getCount_PainelRdm() {
		return Count_PainelRdm;
	}


	public void setCount_PainelRdm(Integer count_PainelRdm) {
		Count_PainelRdm = count_PainelRdm;
	}
	
}