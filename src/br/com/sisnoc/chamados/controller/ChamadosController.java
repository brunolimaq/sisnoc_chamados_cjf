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
import br.com.sisnoc.chamados.dao.PainelPessoalDestaquesDao;
import br.com.sisnoc.chamados.dao.PainelPessoalEquipeDao;
import br.com.sisnoc.chamados.dao.PainelPessoalMetasDao;
import br.com.sisnoc.chamados.dao.UsuariosDao;



@Controller
public class ChamadosController {

	

	
	@Autowired
	private PainelChamadosDao daoChamados;
	
	@Autowired
	private PainelPessoalMetasDao metasDao;

	@Autowired
	private PainelPessoalDestaquesDao destaquesDao;
	
	@Autowired
	private PainelPessoalEquipeDao equipeDao;
	
	
	@RequestMapping("/")
	public ModelAndView principal(Model model) throws ParseException{
		model.addAttribute("chamadosPainelChamados", ((PainelPessoalMetasDao) metasDao).listaPainelPessoalMetas());

		model.addAttribute("chamadosPainelPessoal", ((PainelPessoalDestaquesDao) destaquesDao).listaPainelPessoalDestaques());

		model.addAttribute("chamadosPainelEquipe", ((PainelPessoalEquipeDao) equipeDao).listaPainelGrupoDestaques());
		
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
		String equipe = "todas";
		String status = "";
		
		ModelAndView mv = new ModelAndView("chamados/pendencias");
		
		model.addAttribute("chamadosPendentes", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"R"));
		model.addAttribute("incidentesPendentes", ((PainelChamadosDao) daoChamados).listaPainelChamados(equipe, status,"I"));
		
		
		return mv;
	}


	
	
	
	
	
	
	

	
}
