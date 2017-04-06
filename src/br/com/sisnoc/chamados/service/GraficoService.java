package br.com.sisnoc.chamados.service;

import java.text.ParseException;

import br.com.sisnoc.chamados.modelo.Grafico;

public interface GraficoService {

	Grafico getGraficoTeste();
	
	Grafico getGraficoMetaIndividual() throws ParseException;
	
	Grafico getGraficoChamadosPessoa();
}
