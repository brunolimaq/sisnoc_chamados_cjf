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
		
		Boolean batida = daoPonto.entrada();
		
		if(batida){
			System.out.println("Bateu o pondo Entrada Jornada");
		}
		
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
		
	}
	
	@RequestMapping("/saida")
	public ModelAndView bateSaidaJornada(Model model) throws ParseException{
		
		Boolean batida = daoPonto.saida();
		
		if(batida){
			System.out.println("Bateu o pondo Saida Jornada");
		}
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/entradaIntervalo")
	public ModelAndView bateEntradaIntervalo(Model model) throws ParseException{
		
		Boolean batida = daoPonto.entradaIntervalo();
		
		if(batida){
			System.out.println("Bateu o pondo Entrada Intervalo");
		}
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/saidaIntervalo")
	public ModelAndView bateSaidaIntervalo(Model model) throws ParseException{
		
		Boolean batida = daoPonto.saidaIntervalo();
		
		if(batida){
			System.out.println("Bateu o pondo Saida Intervalo");
		}
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
}
