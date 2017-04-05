package br.com.sisnoc.chamados.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sisnoc.chamados.modelo.Grafico;

@Service
public class GraficoServiceImpl implements GraficoService {

	@Override
	public Grafico getGraficoTeste() {
		Grafico grafico = new Grafico();
		
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(1050);
		
		List<Integer> lista2 = new ArrayList<Integer>();
		lista2.add(5054);
		
		
		HashMap<String, List<Integer>> dados = new HashMap<String, List<Integer>>();
		
		grafico.setNome("Teste");
		dados.put("chamados", lista);
		dados.put("incidentes", lista2);
		grafico.setDados(dados);
		
		
		return grafico;	}

	@Override
	public Grafico getGraficoMetaIndividualDuasHoras() {
		
		Grafico grafico = new Grafico();
		
		List<Integer> lista = new ArrayList<Integer>();
		
		
		lista.add(90);
		
		HashMap<String, List<Integer>> dados = new HashMap<String, List<Integer>>();
		
		dados.put("meta2", lista);
		grafico.setDados(dados);
		grafico.setNome("Meta 2/hs");
		
		
		return grafico;
	}
	
	
	

}
