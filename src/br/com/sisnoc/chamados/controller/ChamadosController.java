package br.com.sisnoc.chamados.controller;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sisnoc.chamados.dao.PainelChamadosDao;
import br.com.sisnoc.chamados.dao.PainelPessoalRequisicoesDao;
import br.com.sisnoc.chamados.dao.PainelPessoalEquipeDao;
import br.com.sisnoc.chamados.dao.PainelPessoalMetasDao;
import br.com.sisnoc.chamados.dao.PainelPessoalRdmDao;
import br.com.sisnoc.chamados.dao.PainelPessoalRdmGeral;
import br.com.sisnoc.chamados.dao.UsuariosDao;



@Controller
public class ChamadosController {

	

	
	@Autowired
	private PainelChamadosDao daoChamados;
	
	@Autowired
	private PainelPessoalMetasDao metasDao;

	@Autowired
	private PainelPessoalRequisicoesDao destaquesDao;
	
	@Autowired
	private PainelPessoalEquipeDao equipeDao;
	
	@Autowired
	private PainelPessoalRdmDao rdmDao;
	
	@Autowired
	private PainelPessoalRdmGeral rdmGeral;
	
	@RequestMapping("/")
	public ModelAndView principal(Model model) throws ParseException{
//		model.addAttribute("chamadosPainelChamados", ((PainelPessoalMetasDao) metasDao).listaPainelPessoalMetas());

		model.addAttribute("chamadosPainelPessoal", ((PainelPessoalRequisicoesDao) destaquesDao).listaPainelPessoalDestaques());

		model.addAttribute("chamadosPainelEquipe", ((PainelPessoalEquipeDao) equipeDao).listaPainelGrupoDestaques());
		
		model.addAttribute("chamadosRDMPessoal", ((PainelPessoalRdmDao) rdmDao).listaPainelPessoalRdm());
		
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
	
	@RequestMapping("/pendencias")
	public ModelAndView listaPendencias(Model model) throws ParseException{
		
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
		
		model.addAttribute("chamadosRDMGeralAprovada", ((PainelPessoalRdmGeral) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelAprovada));
		model.addAttribute("chamadosRDMGeralPlanejamento", ((PainelPessoalRdmGeral) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelPlanejamento));
		model.addAttribute("chamadosRDMGeralValidacao", ((PainelPessoalRdmGeral) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelValidacao));

		model.addAttribute("chamadosRDMGeralExecucao", ((PainelPessoalRdmGeral) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelExecucao));
		model.addAttribute("chamadosRDMGeralOutros", ((PainelPessoalRdmGeral) rdmGeral).listaPainelPessoalRdmGeral(rdmPainelOutros));
		
		ModelAndView mv = new ModelAndView("chamados/gmud");
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
