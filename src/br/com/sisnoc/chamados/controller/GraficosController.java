package br.com.sisnoc.chamados.controller;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.sisnoc.chamados.modelo.Grafico;
import br.com.sisnoc.chamados.negocio.GraficosPessoal;
import br.com.sisnoc.chamados.service.GraficoService;

@Controller
@RequestMapping("/graficos")
public class GraficosController {

	
	private final GraficoService graficoService;
	
	private final GraficosPessoal metasPessoal;
	
	@Autowired
	public GraficosController(GraficoService graficoService,GraficosPessoal metasPessoal) {
		super();
		this.graficoService = graficoService;
		this.metasPessoal = metasPessoal;
                
	}
	
	@RequestMapping
	public String graficos(){
		
		return "chamados/graficos";
	}
	
	@RequestMapping("/2")
	public String graficos2(){
		
		return "chamados/graficos2";
	}

	
	@RequestMapping(value = "/teste", produces = "application/json")
	public  @ResponseBody Grafico chamadosTotal(){
		
		return graficoService.getGraficoChamadosPessoa();
	}
	
	@RequestMapping(value = "/metasIndividual", produces = "application/json")
	public  @ResponseBody Grafico metasIndividual() throws ParseException{
		
		
		System.out.println("chamando metas");
		metasPessoal.calcMetas();
		System.out.println(metasPessoal);
		
		return graficoService.getGraficoMetaIndividual(metasPessoal);
	}
	
	
}
