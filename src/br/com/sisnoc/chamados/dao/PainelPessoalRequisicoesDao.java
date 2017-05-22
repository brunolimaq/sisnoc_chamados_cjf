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

import br.com.sisnoc.chamados.dao.util.DestaquesDao;

import br.com.sisnoc.chamados.modelo.Chamado;
import br.com.sisnoc.chamados.negocio.CalculaSla;
import br.com.sisnoc.chamados.negocio.Popula;
import br.com.sisnoc.chamados.security.UsuarioSistema;

@Repository
@DestaquesDao
public class PainelPessoalRequisicoesDao {

	
private  final Connection connection;

	
	@Autowired
	public PainelPessoalRequisicoesDao(@Qualifier("datasourceSQL") DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Chamado> listaPainelPessoalDestaques() throws ParseException {
		try {
			
			ArrayList<Chamado> ListaChamados = new ArrayList<Chamado>();
			String sql_listaChamados = "";
			
			// tipo = "R";
			Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			Integer flagFilho = 0;
			
			if (usuarioLogado  instanceof UsuarioSistema ) {
			   username= ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
			} else {
			   username = usuarioLogado.toString();
			}
			

			// chamados em andamento
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
						+"and stat.code in ('OP','WIP','PRBAPP') "
						+"and usu.userid = '"+username+"'";

			PreparedStatement stmt = connection
					.prepareStatement(sql_listaChamados);
			ResultSet rs_listaChamados = stmt.executeQuery();
			
			String lista = "\'\'";
			
			int countTeste = 0;
			while (rs_listaChamados.next()){
				countTeste++;
				lista = lista +",\'" + rs_listaChamados.getString("ID") + "\'";
			}
			
			rs_listaChamados.close();
			
			// chamados filhos atendidos
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
					+"and stat.code = 'FIP' "
					+"and usu.userid = '"+username+"'"
					+"and (select count(1) from call_req where parent = req.persid) = (select count(1) from call_req where parent = req.persid and status in ('CL','RE','CNCL','AEUR'))";

		 stmt = connection
				.prepareStatement(sql_listaChamados);
		ResultSet rs_listaChamadosFilhos = stmt.executeQuery();
		
		
		while (rs_listaChamadosFilhos.next()){
			flagFilho = 1;
			countTeste++;
			lista = lista +",\'" + rs_listaChamadosFilhos.getString("ID") + "\'";
		}
		
		rs_listaChamadosFilhos.close();
			
		
			if(countTeste == 0){
				
				
				// chamados pendentes
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
						+"and req.type != 'P'"
						+"and stat.code in ('AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'RSCH', 'PF', 'ACK') "
						+"and usu.userid = '"+username+"'"
						+"and datediff(hh,DATEADD(hh,-3,DATEADD(SS,req.last_mod_dt,'19700101')), getdate()) > 22";

			 stmt = connection
					.prepareStatement(sql_listaChamados);
			ResultSet rs_listaChamadosPendente = stmt.executeQuery();
			
			 lista = "\'\'";
			
			countTeste = 0;
			while (rs_listaChamadosPendente.next()){
				
				countTeste++;
				lista = lista +",\'" + rs_listaChamadosPendente.getString("ID") + "\'";
			}
					
			rs_listaChamadosPendente.close();
				
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
									+"DATEDIFF(s, '1970-01-01 00:00:00', GETDATE()) as epoch, "
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
					chamados.setFlagFilho(flagFilho);
					

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
	
	public Integer listaPainelAtualizacaoOS() throws ParseException {
		try {
			
						
			// tipo = "R";
			Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			
			
			if (usuarioLogado  instanceof UsuarioSistema ) {
			   username= ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
			} else {
			   username = usuarioLogado.toString();
			}
			

			String sql_atualizacaoOs = "select "
					+ "MAX(datediff(dd,DATEADD(hh,-3,DATEADD(SS,req.last_mod_dt,'19700101')), getdate())) as diasatualizacao " 
					+ "from " 
					+ "call_req req WITH (NOLOCK)  join cr_stat stat WITH (NOLOCK) on req.status = stat.code " 
					+ "left join ca_contact usu WITH (NOLOCK)  on usu.contact_uuid = req.assignee " 
					+ "join prob_ctg ctg WITH (NOLOCK)  on ctg.persid = req.category " 
					+ "join act_log log WITH (NOLOCK)  on log.call_req_id = req.persid  "
					+ "join View_Group vwg WITH (NOLOCK)  on req.group_id = vwg.contact_uuid " 
					+ "where ctg.sym like 'INFRA.Ordem de Servico' " 
					+ "and stat.code in ('OP','WIP','PRBAPP') " 
					+ "and log.type='INIT' " 
					+ "and usu.userid = '"+username+"' "
					+ "and datediff(dd,DATEADD(hh,-3,DATEADD(SS,req.last_mod_dt,'19700101')), getdate()) > 3"
					+ "order by 1 ";
			
			PreparedStatement stmt = connection
					.prepareStatement(sql_atualizacaoOs);
			ResultSet rs_atualizacaoOs = stmt.executeQuery();
			
			
			
			Integer atualizacaoOS = 0;
				while (rs_atualizacaoOs.next()){
					
	
					atualizacaoOS = rs_atualizacaoOs.getInt("diasatualizacao");
				}
			
				
				rs_atualizacaoOs.close();
				stmt.close();

				
					return atualizacaoOS;
				

			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Chamado> listaPainelPessoalPendencias() throws ParseException {
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
						+"and stat.code in ('AEUR' , 'AWTVNDR', 'FIP', 'PNDCHG' , 'PO', 'PRBANCOMP', 'RSCH', 'PF', 'ACK') "
						+"and usu.userid = '"+username+"'";

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
									+"DATEDIFF(s, '1970-01-01 00:00:00', GETDATE()) as epoch, "
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
									//+"and req.id in  (470837) "
									+"and req.id in  ("+ lista + ") "
									+ "order by req.type, stat.sym";
			
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
	
	
	
	
	public Connection getConnection() throws SQLException {
		return connection;
	}
}
