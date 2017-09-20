package br.com.sisnoc.ponto.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import br.com.sisnoc.ponto.dao.RegistraBatidasDao;

@Controller
@RequestMapping("/ponto")
public class PontoController {

	@Autowired
	private RegistraBatidasDao daoPonto;
	
	@RequestMapping("/entrada")
	public ModelAndView bateEntradaJornada(Model model) throws ParseException{
		
		daoPonto.entrada();
		
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
		
	}
	
	@RequestMapping("/saida")
	public String bateSaidaJornada(Model model) throws ParseException{
		
		return "#";
	}
	
	@RequestMapping("/entradaIntervalo")
	public String bateEntradaIntervalo(Model model) throws ParseException{
		
		return "#";
	}
	
	@RequestMapping("/saidaIntervalo")
	public String bateSaidaIntervalo(Model model) throws ParseException{
		
		return "#";
	}
}
