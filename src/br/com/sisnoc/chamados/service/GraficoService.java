package br.com.sisnoc.chamados.service;

import java.text.ParseException;

import br.com.sisnoc.chamados.modelo.Grafico;
import br.com.sisnoc.chamados.negocio.GraficosPessoal;

public interface GraficoService {

	Grafico getGraficoTeste();
	
	Grafico getGraficoMetaIndividual(GraficosPessoal metasPessoal);
	
	Grafico getGraficoChamadosPessoa();
}
