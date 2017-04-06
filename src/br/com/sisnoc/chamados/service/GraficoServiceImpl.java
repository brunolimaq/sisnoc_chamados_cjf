package br.com.sisnoc.chamados.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sisnoc.chamados.dao.PainelPessoalMetasDao;
import br.com.sisnoc.chamados.modelo.Grafico;

@Service
public class GraficoServiceImpl implements GraficoService {

	@Autowired
	PainelPessoalMetasDao metasDao;
	
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
	public Grafico getGraficoMetaIndividual() throws ParseException {
		

		Grafico grafico = new Grafico();

		metasDao.listaPainelPessoalMetas();
		
			
			List<Integer> listaMetaDuasHoras = new ArrayList<Integer>();
			listaMetaDuasHoras.add(metasDao.getMeta2h());

			List<Integer> listaMetaQuatroHoras = new ArrayList<Integer>();
			listaMetaQuatroHoras.add(metasDao.getMeta4h());

			
			
			HashMap<String, List<Integer>> dados = new HashMap<String, List<Integer>>();
			
			dados.put("meta2", listaMetaDuasHoras);
			dados.put("meta4", listaMetaQuatroHoras);
			grafico.setDados(dados);
			grafico.setNome("Meta 2/hs");
			

		
		
		return grafico;
	}

	@Override
	public Grafico getGraficoChamadosPessoa() {
		Grafico grafico = new Grafico();
		
		List<Integer> listaChamados = new ArrayList<Integer>();
		listaChamados.add(50);
		
		List<Integer> listaIncidentes = new ArrayList<Integer>();
		listaIncidentes.add(30);
		
		List<Integer> listaTarefasInternas = new ArrayList<Integer>();
		listaTarefasInternas.add(10);
		
		List<Integer> listaProblemas = new ArrayList<Integer>();
		listaProblemas.add(2);
		
		List<Integer> listaRDM = new ArrayList<Integer>();
		listaRDM.add(8);
		
		
				
		
		HashMap<String, List<Integer>> dados = new HashMap<String, List<Integer>>();
		
		dados.put("chamados", listaChamados);
		dados.put("incidentes", listaIncidentes);
		dados.put("tarefasInternas", listaTarefasInternas);
		dados.put("problemas", listaProblemas);
		dados.put("rdm", listaProblemas);
		
		grafico.setDados(dados);
		grafico.setNome("Quantidade");

		
		
		return grafico;
	}
	
	
	

}
