package br.com.sisnoc.chamados.controller;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsXlsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsXlsxView;

import br.com.sisnoc.chamados.dao.PainelChamadosDao;
import br.com.sisnoc.chamados.dao.PainelPessoalRequisicoesDao;
import br.com.sisnoc.chamados.dao.PainelSemSlaDao;
import br.com.sisnoc.chamados.modelo.Relatorios;
import br.com.sisnoc.chamados.dao.PainelPessoalEquipeDao;
import br.com.sisnoc.chamados.dao.PainelPessoalMetasDao;
import br.com.sisnoc.chamados.dao.PainelPessoalRdmDao;
import br.com.sisnoc.chamados.dao.PainelGeralRdmDao;

import br.com.sisnoc.chamados.security.UsuarioSistema;



@Controller
public class ChamadosController {

	

	
	@Autowired
	private PainelChamadosDao daoChamados;
	
//	@Autowired
//	private PainelPessoalMetasDao metasDao;

	@Autowired
	private PainelPessoalRequisicoesDao destaquesDao;
	
	@Autowired
	private PainelPessoalEquipeDao equipeDao;
	
	@Autowired
	private PainelPessoalRdmDao rdmDao;
	
	@Autowired
	private PainelSemSlaDao osDao;
	
	@Autowired
	private PainelGeralRdmDao rdmGeral;
	
	@Autowired
	private PainelPessoalMetasDao metasDao;
	
	@RequestMapping("/")
	public ModelAndView principal(Model model) throws ParseException{
		
		
		
		String perfil;
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		Collection<? extends GrantedAuthority> permissao = null;
		String user_exclusao = "''";
		if (usuarioLogado  instanceof UsuarioSistema ) {
			   username = ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
			   permissao = ( (UsuarioSistema)usuarioLogado).getUsuario().getAuthority();
		} else {
		   username = usuarioLogado.toString();
		}
		
		
		
		for (GrantedAuthority autorizacao : permissao) {
			
			if (autorizacao.toString().equals("GESTOR")){

				perfil = "GESTOR";
				String rdmPainelAprovada = "APR";

				model.addAttribute("chamadosPainelEquipe", ((PainelPessoalEquipeDao) equipeDao).listaPainelGrupoDestaques(perfil));
				
				
				model.addAttribute("chamadosPainelPessoal", ((PainelPessoalEquipeDao) equipeDao).listaPainelGrupoPendentes(perfil));
				
				
				model.addAttribute("chamadosRDMPessoal", ((PainelPessoalRdmDao) rdmDao).listaPainelPessoalRdm());
				
				model.addAttribute("chamadosRDMGeralAprovada", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelAprovada));

				
				ModelAndView mv = new ModelAndView("chamados/indexGestor");
				return mv;

			}
		}
		
		perfil = "";
		model.addAttribute("chamadosPainelPessoal", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalDestaques());

		model.addAttribute("chamadosPainelEquipe", ((PainelPessoalEquipeDao) equipeDao).listaPainelGrupoDestaques(perfil));
		
		model.addAttribute("chamadosRDMPessoal", ((PainelPessoalRdmDao) rdmDao).listaPainelPessoalRdm());
		
		model.addAttribute("atualizacaoOS", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelAtualizacaoOS());
		
		ModelAndView mv = new ModelAndView("chamados/index");
		return mv;
	}
	
	@RequestMapping("/listaChamados")
	public String lista(Model model) throws ParseException{
		
		
		
		String equipe = "";
		String status = "";
		
		model.addAttribute("chamadosPainelChamados", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"R"));
	    model.addAttribute("chamadosPainelIncidentes", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"I"));
		
		status = "aberto";
		model.addAttribute("chamadosPainelNoc", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidentesPainelNoc", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"I"));
		
		return "chamados/chamados";
	}
	
	@RequestMapping("/debug")
	public String debug(Model model) throws ParseException{
		
		
		
		String equipe = "";
		String status = "";
		
		model.addAttribute("chamadosPainelChamados", ((PainelChamadosDao) daoChamados).listaChamadosDebug());
	
		
		return "chamados/debug";
	}
	
	@RequestMapping("/pendencias")
	public ModelAndView listaPendencias(Model model) throws ParseException{
		
		
		String perfil = "";
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		Collection<? extends GrantedAuthority> permissao = null;
		if (usuarioLogado  instanceof UsuarioSistema ) {
			   username = ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
			   permissao = ( (UsuarioSistema)usuarioLogado).getUsuario().getAuthority();
		} else {
		   username = usuarioLogado.toString();
		}
		
		
		
		
		
		for (GrantedAuthority autorizacao : permissao) {
			
			if (autorizacao.toString().equals("GESTOR")){

				perfil = "GESTOR";
				String status;
				status= "'AEUR'";
				model.addAttribute("chamadosPendenteSolicitante", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias(status));
				
				status= "'AWTVNDR'";
				model.addAttribute("chamadosPendenteFornecedor", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias(status));
				
				status= "'FIP'";
				model.addAttribute("chamadosPendenteFilho", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias(status));
				
				status= "'PNDCHG'";
				model.addAttribute("chamadosPendenteMudanca", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias(status));
				
				model.addAttribute("chamadosViolados", metasDao.listaPainelPessoalMetas(perfil));
				model.addAttribute("chamadosReabertos", metasDao.listaPainelPessoalReabertosLista(perfil));
				
				
				ModelAndView mv = new ModelAndView("chamados/pendenciasGestor");
				return mv;

			}
		}
		
		
		

			
		/*
		 * AEUR	Pendente informação do solicitante
		 * AWTVNDR	Pendente fornecedor
		 * FIP	Aberto chamado filho
		 * PNDCHG	Pendente janela de mudança
		 * 
		*/
		String status;
		status= "'AEUR'";
		model.addAttribute("chamadosPendenteSolicitante", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias(status));
		
		status= "'AWTVNDR'";
		model.addAttribute("chamadosPendenteFornecedor", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias(status));
		
		status= "'FIP'";
		model.addAttribute("chamadosPendenteFilho", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias(status));
		
		status= "'PNDCHG'";
		model.addAttribute("chamadosPendenteMudanca", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias(status));
		
		model.addAttribute("chamadosViolados", metasDao.listaPainelPessoalMetas(perfil));
		model.addAttribute("chamadosReabertos", metasDao.listaPainelPessoalReabertosLista(perfil));
		
		
		ModelAndView mv = new ModelAndView("chamados/pendenciasGestor");
		return mv;
	}

	
	@RequestMapping("/gmud")
	public ModelAndView gmud(Model model) throws ParseException{
		//APR - Aprovada
		//IMPL - Exeução
		//RFC - Em Planejamento
		//APP - Em validação

		String rdmPainelAprovada = "APR";
		String rdmPainelExecucao = "IMPL";
		String rdmPainelPlanejamento = "RFC";
		String rdmPainelValidacao = "APP";
		String rdmPainelOutros = "";
		
		model.addAttribute("chamadosRDMGeralAprovada", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelAprovada));
		model.addAttribute("chamadosRDMGeralPlanejamento", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelPlanejamento));
		model.addAttribute("chamadosRDMGeralValidacao", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelValidacao));

		model.addAttribute("chamadosRDMGeralExecucao", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelExecucao));
		model.addAttribute("chamadosRDMGeralOutros", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelOutros));
		
		ModelAndView mv = new ModelAndView("chamados/gmud");
		return mv;
	}

	@Autowired private ApplicationContext appContext;

	@RequestMapping("/relatorios")
	public ModelAndView relatorio_volumetria(Model model) throws ParseException{

		
	    return new ModelAndView("/chamados/relatorios");
	}
	

//	@RequestMapping("/relatorio_volumetria")
//	public ModelAndView relatorio(Model model) throws ParseException{
//
//		JasperReportsPdfView view = new JasperReportsPdfView();
//	    view.setJdbcDataSource(equipeDao.getDt());
//	    view.setUrl("resources/chamados.jrxml");
////	    Map<String, Object> params = new HashMap<>();
////	    params.put("soma", 22);
//	    view.setApplicationContext(appContext);
//	    
////	    return new ModelAndView(view, params);
//	    return new ModelAndView(view);
//	}
//	
//	@RequestMapping("/relatorio_usuario_tipo")
//	public ModelAndView relatorioUsuarioTipo(Model model) throws ParseException{
//
//		String perfil = "";
//		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = "";
//		Collection<? extends GrantedAuthority> permissao = null;
//		if (usuarioLogado  instanceof UsuarioSistema ) {
//			   username = ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
//			   permissao = ( (UsuarioSistema)usuarioLogado).getUsuario().getAuthority();
//		} else {
//		   username = usuarioLogado.toString();
//		}
//		System.out.println(username);
//		JasperReportsPdfView view = new JasperReportsPdfView();
//	    view.setJdbcDataSource(equipeDao.getDt());
//	    view.setUrl("resources/chamados_usuario_tipo.jrxml");
//	    Map<String, Object> params = new HashMap<>();
//	    params.put("usuario", username);
//	    view.setApplicationContext(appContext);
//	    
////	    return new ModelAndView(view, params);
//	    return new ModelAndView(view, params);
//	}
//	
//	@RequestMapping("/relatorio_grupo_tipo")
//	public ModelAndView relatorioGrupoTipo(Model model) throws ParseException{
//
//		String perfil = "";
//		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = "";
//		String equipe = "";
//		Collection<? extends GrantedAuthority> permissao = null;
//		if (usuarioLogado  instanceof UsuarioSistema ) {
//			   username = ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
//			   equipe = ( (UsuarioSistema)usuarioLogado).getUsuario().getNomeEquipe();
//		} else {
//		   username = usuarioLogado.toString();
//		}
//		
//		String[] splitEquipe = equipe.split(",");
//		
//		String listaEquipe = "\'\'";
//		
//		for (String eqp : splitEquipe) {
//			listaEquipe = listaEquipe +",\'" + eqp + "\'";
//		}
//
//		System.out.println(listaEquipe);
//		JasperReportsPdfView view = new JasperReportsPdfView();
//	    view.setJdbcDataSource(equipeDao.getDt());
//	    view.setUrl("resources/chamados_usuario_tipo.jrxml");
//	    Map<String, Object> params = new HashMap<>();
//	    params.put("equipe", listaEquipe);
//	    view.setApplicationContext(appContext);
//	    
////	    return new ModelAndView(view, params);
//	    return new ModelAndView(view, params);
//	}


	@RequestMapping("/relatoriosDinamicos")
	public ModelAndView relatorioListaChamados(Model model, Relatorios relatorios) throws ParseException{

		
		System.out.println("opcao"+ relatorios.getOpcao());
		JasperReportsPdfView view = new JasperReportsPdfView();
	    view.setJdbcDataSource(equipeDao.getDt());
	    
	    if (relatorios.getOpcao().equals("rel_chamados")){
	    	
		    view.setUrl("resources/Lista_chamados.jrxml");
		    Map<String, Object> params = new HashMap<>();
		    params.put("mes", relatorios.getMes());
		    params.put("ano", relatorios.getAno());
		    view.setApplicationContext(appContext);

		    return new ModelAndView(view, params);

	    }
	    
	    if (relatorios.getOpcao().equals("vol_requisicoes")){
	    	
		    view.setUrl("resources/requisicoes_mensal_por_semanal.jrxml");
		    view.setApplicationContext(appContext);

		    return new ModelAndView(view);
	    }
	    
	    
	    if (relatorios.getOpcao().equals("rel_reabertos")){
	    	
		    view.setUrl("resources/chamados_reabertos.jrxml");
		    view.setApplicationContext(appContext);

		    return new ModelAndView(view);
	    }
	    
	    view.setUrl("resources/chamados_reabertos.jrxml");
	    view.setApplicationContext(appContext);

	    return new ModelAndView(view);


	}
	
	
		

//	@RequestMapping("/relatorio_mensal_semanal")
//	public ModelAndView relatorio_mensal_semanal(Model model) throws ParseException{
//
//		JasperReportsPdfView view = new JasperReportsPdfView();
//		
//
//		view.setJdbcDataSource(equipeDao.getDt());
//	    view.setUrl("resources/requisicoes_mensal_por_semanal.jrxml");
////	    Map<String, Object> params = new HashMap<>();
////	    params.put("soma", 22);
//	    view.setApplicationContext(appContext);
//	    
////	    return new ModelAndView(view, params);
//	    return new ModelAndView(view);
//	}
	
//	@RequestMapping("/relatorioReabertos")
//	public ModelAndView relatorioReabertos(Model model) throws ParseException{
//
//
//		JasperReportsPdfView view = new JasperReportsPdfView();
//
//		view.setJdbcDataSource(equipeDao.getDt());
//	    view.setUrl("resources/chamados_reabertos.jrxml");
//	    view.setApplicationContext(appContext);
//	    
////	    return new ModelAndView(view, params);
//	    return new ModelAndView(view);
//	}
//


	@RequestMapping("/ordemServicos")
	public ModelAndView listaOrdemServicoTarefaInterna(Model model) throws ParseException{
		
		//Infra.Tarefas Internas
		//INFRA.Ordem de Servico
		
		model.addAttribute("chamadosOSPessoal", ((PainelSemSlaDao) osDao).listaPainelPessoalOs("INFRA.Ordem de Servico"));
		model.addAttribute("chamadosOSEquipePendente", ((PainelSemSlaDao) osDao).listaPainelPessoalOsPendente("INFRA.Ordem de Servico"));
		model.addAttribute("chamadosOSGeralGrupo", ((PainelSemSlaDao) osDao).listaPainelPessoalEquipeOs("INFRA.Ordem de Servico"));
		


		model.addAttribute("chamadosTarefaPessoal", ((PainelSemSlaDao) osDao).listaPainelPessoalOs("Infra.Tarefas Internas"));
		model.addAttribute("chamadosTarefaEquipePendente", ((PainelSemSlaDao) osDao).listaPainelPessoalOsPendente("Infra.Tarefas Internas"));
		model.addAttribute("chamadosTarefaGeralGrupo", ((PainelSemSlaDao) osDao).listaPainelPessoalEquipeOs("Infra.Tarefas Internas"));
		
		ModelAndView mv = new ModelAndView("chamados/ordemServicos");
		return mv;
	}
	
	
	@RequestMapping("/problemas")
	public ModelAndView listaProblemas(Model model) throws ParseException{
		String status;
		
		status= "'OP'";
		model.addAttribute("problemasAbertos", ((PainelSemSlaDao) osDao).listaPainelGeralProblema(status));
		status= "'RSCH'";
		model.addAttribute("problemasInvestigacao", ((PainelSemSlaDao) osDao).listaPainelGeralProblema(status));
		status= "'PRBANCOMP'";
		model.addAttribute("problemasCausaIdentificada", ((PainelSemSlaDao) osDao).listaPainelGeralProblema(status));
		status= "'PO'";
		model.addAttribute("problemasSolucaoImplatacao", ((PainelSemSlaDao) osDao).listaPainelGeralProblema(status));
		status= "'PF'";
		model.addAttribute("problemasEmRevisao", ((PainelSemSlaDao) osDao).listaPainelGeralProblema(status));

		
		ModelAndView mv = new ModelAndView("chamados/problemas");
		return mv;
	}


	
	
	
	
	
	
	

	
}