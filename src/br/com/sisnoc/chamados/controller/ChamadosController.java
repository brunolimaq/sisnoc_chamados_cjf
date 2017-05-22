package br.com.sisnoc.chamados.controller;

import java.text.ParseException;
import java.util.Collection;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sisnoc.chamados.dao.PainelChamadosDao;
import br.com.sisnoc.chamados.dao.PainelPessoalRequisicoesDao;
import br.com.sisnoc.chamados.dao.PainelSemSlaDao;
import br.com.sisnoc.chamados.dao.PainelPessoalEquipeDao;

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
		
		
		String perfil;
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
				model.addAttribute("chamadosPainelPessoal", ((PainelPessoalEquipeDao) equipeDao).listaPainelGrupoPendentes(perfil));
				//model.addAttribute("chamadosPainelPessoalPendencias", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias());

				
				ModelAndView mv = new ModelAndView("chamados/pendenciasGestor");
				return mv;

				
			}
		}
		
		model.addAttribute("chamadosPainelPessoalPendencias", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalPendencias());
		
		
		ModelAndView mv = new ModelAndView("chamados/pendencias");
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

//	@RequestMapping("/pendencias")
//	public ModelAndView listaPendencias(Model model) throws ParseException{
//		String equipe = "todas";
//		String status = "";
//		
//		ModelAndView mv = new ModelAndView("chamados/pendencias");
//		
//		model.addAttribute("chamadosPendentes", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"R"));
//		model.addAttribute("incidentesPendentes", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"I"));
//		
//		
//		return mv;
//	}

	
	
	
	
	
	
	

	
}
