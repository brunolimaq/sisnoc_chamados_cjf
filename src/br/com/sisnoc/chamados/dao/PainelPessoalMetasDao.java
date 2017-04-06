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
	
	
	
	private int meta2h;
	private int meta4h;
	private int violados;
	private int chamadosMes;


	
	public Integer listaPainelPessoalMetas() throws ParseException {
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

			System.out.println(username);
			
			

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
						+"and stat.code in ('RE','CL') "
						+"and usu.userid = '"+username+"' "
						+"and close_date  + DATEPART(tz,SYSDATETIMEOFFSET())*60 >= DATEDIFF(s, '1970-01-01 00:00:00',CONVERT(VARCHAR(25),DATEADD(dd,-(DAY(getdate())-1),getdate()),101)) ";

//						+"and close_date  + DATEPART(tz,SYSDATETIMEOFFSET())*60 >= DATEDIFF(s, '1970-01-01 00:00:00',CONVERT(VARCHAR(25),'03/01/2017',101)) ";

				

			PreparedStatement stmt = connection
					.prepareStatement(sql_listaChamados);
			ResultSet rs_listaChamados = stmt.executeQuery();
			
			String lista = "\'\'";
			
			int countChamadosMes = 0;
			while (rs_listaChamados.next()){

				lista = lista +",\'" + rs_listaChamados.getString("ID") + "\'";
				countChamadosMes++;
			}
			
			
			
			
			System.out.println(getChamadosMes());
			
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
					ListaChamados.add(chamados);
					count++;
				}
			
				
				rs_listalog.close();
				stmt.close();

				if(ListaChamados.isEmpty()){
					return null;
				} else {
					
					int countTotal = 0;
					int countMeta2h = 0;
					int countMeta4h = 0;
					int countViolados = 0;
					
					for (Chamado chamado : CalculaSla.SlaMec(ListaChamados)) {
						
						countTotal++;
						if(chamado.getSla().equals("Violado")){
							countViolados++;
						}
						if(chamado.getSla2() <= 7200){
							countMeta2h++;
						}
						if(chamado.getSla2() <= 14400){
							countMeta4h++;
						}
						
					} 
					
					this.setMeta2h((countMeta2h*100)/countTotal);
					this.setMeta4h((countMeta4h*100)/countTotal);
					this.setViolados(countViolados);
					this.setChamadosMes(countChamadosMes);
					
					System.out.println(this.getViolados());
					return 1;
				}

			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

	public int getMeta2h() {
		return meta2h;
	}

	public void setMeta2h(int meta2h) {
		this.meta2h = meta2h;
	}

	public int getMeta4h() {
		return meta4h;
	}

	public void setMeta4h(int meta4h) {
		this.meta4h = meta4h;
	}

	public int getViolados() {
		return violados;
	}

	public void setViolados(int violados) {
		this.violados = violados;
	}

	public int getChamadosMes() {
		return chamadosMes;
	}

	public void setChamadosMes(int chamadosMes) {
		this.chamadosMes = chamadosMes;
	}
	
}
