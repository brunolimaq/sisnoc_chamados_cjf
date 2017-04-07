package br.com.sisnoc.chamados.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import br.com.sisnoc.chamados.modelo.Grafico;
import br.com.sisnoc.chamados.negocio.GraficosPessoal;

@Service
public class GraficoServiceImpl implements GraficoService {

	
	
	
	@Override
	public Grafico getGraficoTeste() {
		
		
//		Grafico grafico = new Grafico();
//		
//		
//		List<Double> lista = new ArrayList<Double>();
//		lista.add(1050.0);
//		
//		List<Double> lista2 = new ArrayList<Double>();
//		lista2.add(5054.0);
//		
//		
//		Map<String, List<Double>> dados = new HashMap<String, List<Double>>();
//		
//		grafico.setNome("Teste");
//		dados.put("chamados", lista);
//		dados.put("incidentes", lista2);
//		grafico.setDados(dados);
		
		
		return null;	}

	@Override
	public Grafico getGraficoMetaIndividual(GraficosPessoal metasPessoal){
		


			Grafico grafico = new Grafico();
		
		
			
			List<Integer> listaMetaDuasHoras = new ArrayList<Integer>();
			listaMetaDuasHoras.add(metasPessoal.getMeta2h());

			List<Integer> listaMetaQuatroHoras = new ArrayList<Integer>();
			listaMetaQuatroHoras.add(metasPessoal.getMeta4h());

			List<Integer> listaViolados = new ArrayList<Integer>();
			listaViolados.add(metasPessoal.getViolados());

			List<Integer> listaRequiscoesMes = new ArrayList<Integer>();
			listaRequiscoesMes.add(metasPessoal.getRequisicoesMes());

			List<Integer> listaChamadosMes = new ArrayList<Integer>();
			listaChamadosMes.add(metasPessoal.getChamadosMes());

			List<Integer> listaIncidentes = new ArrayList<Integer>();
			listaIncidentes.add(metasPessoal.getIncidentesMes());

			List<Integer> listaReabertosMes = new ArrayList<Integer>();
			listaReabertosMes.add(metasPessoal.getReabertosMes());

			List<Integer> listaPendencias = new ArrayList<Integer>();
			listaPendencias.add(metasPessoal.getPendencias());

			
			HashMap<String, List<Integer>> dados = new HashMap<String, List<Integer>>();
			
			dados.put("meta2", listaMetaDuasHoras);
			dados.put("meta4", listaMetaQuatroHoras);
			dados.put("violados", listaViolados);
			dados.put("requisicoesMes", listaRequiscoesMes);
			dados.put("chamadosMes", listaChamadosMes);
			dados.put("incidentesMes", listaIncidentes);
			dados.put("reabertosMes", listaReabertosMes);
			dados.put("pendencias", listaPendencias);
			grafico.setDados(dados);
			grafico.setNome("Meta 2/hs");
			
			System.out.println(listaChamadosMes);
			System.out.println(listaIncidentes);
			System.out.println(listaReabertosMes);
		
		
		return grafico;
	}

	@Override
	public Grafico getGraficoChamadosPessoa() {
//		
//		Grafico grafico = new Grafico();
//		
//		List<BigDecimal> listaChamados = new ArrayList<BigDecimal>();
//		listaChamados.add();
//		
//		List<BigDecimal> listaIncidentes = new ArrayList<BigDecimal>();
//		listaIncidentes.add(30.0);
//		
//		List<BigDecimal> listaTarefasInternas = new ArrayList<BigDecimal>();
//		listaTarefasInternas.add(10.0);
//		
//		List<BigDecimal> listaProblemas = new ArrayList<BigDecimal>();
//		listaProblemas.add(2.0);
//		
//		List<BigDecimal> listaRDM = new ArrayList<BigDecimal>();
//		listaRDM.add(8.0);
//		
//		
//				
//		
//		HashMap<String, List<Integer>> dados = new HashMap<String, List<Integer>>();
//		
//		
//		dados.put("chamados", chamados);
//		dados.put("incidentes", listaIncidentes);
//		dados.put("tarefasInternas", listaTarefasInternas);
//		dados.put("problemas", listaProblemas);
//		dados.put("rdm", listaProblemas);
//		
//		grafico.setDados(dados);
//		grafico.setNome("Quantidade");

		
		
		return null;
	}
	
	
	

}
