package br.com.sisnoc.chamados.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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

import br.com.sisnoc.chamados.dao.JdbcChamadoDao;
import br.com.sisnoc.chamados.dao.PainelChamadosDao;
import br.com.sisnoc.chamados.dao.PainelPessoalRequisicoesDao;
import br.com.sisnoc.chamados.dao.PainelSemSlaDao;
import br.com.sisnoc.chamados.modelo.Chamado;
import br.com.sisnoc.chamados.modelo.Relatorios;
import br.com.sisnoc.chamados.dao.PainelPessoalEquipeDao;
import br.com.sisnoc.chamados.dao.PainelPessoalMetasDao;
import br.com.sisnoc.chamados.dao.PainelPessoalRdmDao;
import br.com.sisnoc.chamados.dao.PainelGeralRdmDao;

import br.com.sisnoc.chamados.security.UsuarioSistema;
import br.com.sisnoc.chamados.service.ContextoUsuario;



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
	
	@Autowired
	private JdbcChamadoDao dao;
	
	
	@RequestMapping("/")
	public ModelAndView principal(Model model) throws ParseException{
		
			String equipe = "";
			String status = "";
			
			model.addAttribute("chamadosPainelChamados", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"R"));
		    model.addAttribute("chamadosPainelIncidentes", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"I"));
			
			status = "aberto";
			model.addAttribute("chamadosPainelNoc", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"R"));
			model.addAttribute("incidentesPainelNoc", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"I"));
			model.addAttribute("menuSAC", 1);
			ModelAndView mv = new ModelAndView("chamados/chamados");
		return mv;
	}
	
	@RequestMapping("/painel")
	public ModelAndView painel(Model model) throws ParseException{
		
		
		
		String perfil;
		
		Collection<? extends GrantedAuthority> permissao = ContextoUsuario.getPermissao();
		
		
		
		
		
		for (GrantedAuthority autorizacao : permissao) {
			
			if (autorizacao.toString().equals("GESTOR")){

				perfil = "GESTOR";
				String rdmPainelAprovada = "APR";

				model.addAttribute("chamadosPainelEquipe", ((PainelPessoalEquipeDao) equipeDao).listaPainelGrupoDestaques(perfil));
				
				
				model.addAttribute("chamadosPainelPessoal", ((PainelPessoalEquipeDao) equipeDao).listaPainelGrupoPendentes(perfil));
				
				
				model.addAttribute("chamadosRDMPessoal", ((PainelPessoalRdmDao) rdmDao).listaPainelPessoalRdm());
				
				model.addAttribute("chamadosRDMGeralAprovada", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelAprovada));

				//model.addAttribute("alerta", equipeDao.chamadosEmAndamento(perfil));
				//System.out.println(equipeDao.chamadosEmAndamento(perfil) + "Andamentos?");
				ModelAndView mv = new ModelAndView("chamados/indexGestor");
				return mv;

			}
		}
		ArrayList<Chamado> listaChamadosPainelPessoal = new ArrayList<Chamado>();

	
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
		Collection<? extends GrantedAuthority> permissao = ContextoUsuario.getPermissao();
		
		
		
		
		
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
				model.addAttribute("menuSAC", 1);
				
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
		model.addAttribute("menuSAC", 1);
		model.addAttribute("countPendencias", destaquesDao.getCountPendencias());
		System.out.println(destaquesDao.getCountPendencias());
		
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
		String rdmPainelExec = "VRFY";
		//String rdmPainelOutros = "";
		
		model.addAttribute("chamadosRDMGeralAprovada", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelAprovada));
		model.addAttribute("chamadosRDMGeralPlanejamento", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelPlanejamento));
		model.addAttribute("chamadosRDMGeralValidacao", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelValidacao));

		model.addAttribute("chamadosRDMGeralExecucao", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelExecucao));
		model.addAttribute("chamadosRDMGeralOutros", ((PainelGeralRdmDao) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelExec));
		
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

		
		
		JasperReportsPdfView view = new JasperReportsPdfView();
	    view.setJdbcDataSource(equipeDao.getDt());
	    
	    
	    if (relatorios.getOpcao().equals("rel_chamados")){
	    	
	    	if(relatorios.getTipo().equals("TODOS")){

		    	view.setUrl("resources/Lista_chamados.jrxml");
			    Map<String, Object> params = new HashMap<>();
			    params.put("mes", relatorios.getMes());
			    params.put("ano", relatorios.getAno());
			    view.setApplicationContext(appContext);
			    return new ModelAndView(view, params);

	    	}else {
	    		
	    	
	    		view.setUrl("resources/Lista_chamados2.jrxml");
			    Map<String, Object> params = new HashMap<>();
			    params.put("mes", relatorios.getMes());
			    params.put("ano", relatorios.getAno());
			    params.put("tipo",relatorios.getTipo());
			    view.setApplicationContext(appContext);
			    return new ModelAndView(view, params);
	    		
	    		
	    	}
	    	


	    }
	    
	    if (relatorios.getOpcao().equals("rel_satisfacao")){

	    	view.setUrl("resources/satisfacao_por_equipe.jrxml");
		    Map<String, Object> params = new HashMap<>();
		    params.put("mes", relatorios.getMes());
		    params.put("ano", relatorios.getAno());
		    view.setApplicationContext(appContext);
		    return new ModelAndView(view, params);
	    	
	    }
	    
	    if (relatorios.getOpcao().equals("rel_estatistica")){

	    	view.setUrl("resources/AtendimentosVip.jrxml");
	    	String InicioParametro = relatorios.getAnoInicial()+relatorios.getMesInicial(); 
		    String FimParametro = relatorios.getAnoFinal()+relatorios.getMesFinal();
		    String InicioParametroTela = relatorios.getMesInicial()+ "/" + relatorios.getAnoInicial();
		    String FimParametroTela = relatorios.getMesFinal()+ "/" + relatorios.getAnoFinal();
		    System.out.println("Incio " + InicioParametro + "|||" + "Fim " + FimParametro);
		    
	    	Map<String, Object> params = new HashMap<>();
		    params.put("Inicio", InicioParametro);
		    params.put("Fim", FimParametro);
		    params.put("InicioLegivel", InicioParametroTela);
		    params.put("FimLegivel", FimParametroTela);
		    view.setApplicationContext(appContext);
		    return new ModelAndView(view, params);
		    
		    
	    	
	    }
	    
	    if (relatorios.getOpcao().equals("rel_estatistica_solicitante")){

	    	view.setUrl("resources/satisfacao_por_equipe.jrxml");
		    Map<String, Object> params = new HashMap<>();
		    params.put("mesInicial", relatorios.getMesInicial());
		    params.put("anoInicial", relatorios.getAnoInicial());
		    params.put("mesFinal", relatorios.getMesFinal());
		    params.put("anoFinal", relatorios.getAnoFinal());
		    params.put("solicitante", relatorios.getSolicitante());
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
	    
	    if (relatorios.getOpcao().equals("rel_Aberto7Dias")){
	    	
	    	if(relatorios.getResponsavel().equals("TODOS")){
	    		view.setUrl("resources/Abertos7dias.jrxml");
			    view.setApplicationContext(appContext);
			    return new ModelAndView(view);

	    	}else {
	    		
	    		view.setUrl("resources/Abertos7diasUsu.jrxml");
			    Map<String, Object> params = new HashMap<>();
			    params.put("responsavel", relatorios.getResponsavel());
			    view.setApplicationContext(appContext);
			    return new ModelAndView(view, params);
	    	}
	    	
    	
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


	
	
	@RequestMapping("/listaChamadosSac")
	public String listaSac(Model model) throws ParseException{

		//Chamados Filhos
		
		model.addAttribute("chamadosFilhos", dao.listaFilhos());
		model.addAttribute("chamadosFilhosCarinha", dao.listaFilhosCarinha());
		
		//Geração Paineis 
		
		model.addAttribute("chamadosPainelMon", dao.listaPainelMonSac());
		model.addAttribute("chamadosPainelSol", dao.listaPainelSolSac());
		model.addAttribute("chamadosPainelInc", dao.listaPainelIncSac());

		//Geração das Listas
		model.addAttribute("chamadosSac", dao.listaSac());
		
		//Count de Chamados
		model.addAttribute("countSac", dao.getCount_sac());


		return "chamadosSac/chamados";
	}
	
	@RequestMapping("/vip")
	public String listaVip(Model model) throws ParseException{
		
		String status = "naoencerrados";
		model.addAttribute("listaVipNaoEncerrados", osDao.listaVIPEncerrados(status));
		status = "encerrados";
		model.addAttribute("listaVipEncerrados", osDao.listaVIPEncerrados(status));

		return "chamados/vip";
	}
	
	
	
	
	
	
	

	
}