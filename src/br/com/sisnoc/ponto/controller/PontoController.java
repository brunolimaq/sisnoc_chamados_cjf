package br.com.sisnoc.ponto.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sisnoc.ponto.dao.RegistraBatidasDao;

@Controller
public class PontoController {

	@Autowired
	private RegistraBatidasDao daoPonto;
	
	@RequestMapping("")
	public String bateEntradaJornada(Model model) throws ParseException{
		
		return "#";
	}
	
	@RequestMapping("")
	public String bateSaidaJornada(Model model) throws ParseException{
		
		return "#";
	}
	
	@RequestMapping("")
	public String bateEntradaIntervalo(Model model) throws ParseException{
		
		return "#";
	}
	
	@RequestMapping("")
	public String bateSaidaIntervalo(Model model) throws ParseException{
		
		return "#";
	}
}
