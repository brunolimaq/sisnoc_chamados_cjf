package br.com.sisnoc.chamados.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.sisnoc.chamados.modelo.Grafico;
import br.com.sisnoc.chamados.service.GraficoService;

@Controller
@RequestMapping("/graficos")
public class GraficosController {

	
	private final GraficoService graficoService;

	@Autowired
	public GraficosController(GraficoService graficoService) {
		super();
		this.graficoService = graficoService;
                
	}
	
	@RequestMapping
	public String graficos(){
		
		return "chamados/graficos";
	}
	
	@RequestMapping(value = "/teste", produces = "application/json")
	public  @ResponseBody Grafico chamadosTotal(){
		
		return graficoService.getGraficoTeste();
	}
	
	@RequestMapping(value = "/metasIndividualDuasHoras", produces = "application/json")
	public  @ResponseBody Grafico metasIndividualDuashoras(){
		
		return graficoService.getGraficoMetaIndividualDuasHoras();
	}
	
	
}
