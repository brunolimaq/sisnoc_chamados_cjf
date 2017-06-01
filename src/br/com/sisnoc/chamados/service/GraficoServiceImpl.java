package br.com.sisnoc.chamados.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
			
//			List<String> listaEquipesPendencia = new ArrayList<String>();
//			listaEquipesPendencia.add(metasPessoal.ge);
// 
//			List<Integer> listaQtdPendencia = new ArrayList<Integer>();
//			listaQtdPendencia.add(metasPessoal.getQtdPendencias());
			
			HashMap<String, List<Integer>> dados = new HashMap<String, List<Integer>>();
			
			HashMap<String, List<String>> dados2 = new HashMap<String, List<String>>();

			dados.put("meta2", listaMetaDuasHoras);
			dados.put("meta4", listaMetaQuatroHoras);
			dados.put("violados", listaViolados);
			dados.put("requisicoesMes", listaRequiscoesMes);
			dados.put("chamadosMes", listaChamadosMes);
			dados.put("incidentesMes", listaIncidentes);
			dados.put("reabertosMes", listaReabertosMes);
			dados.put("pendencias", listaPendencias);

			ArrayList<String[]> listaPendenteEquipe = metasPessoal.getListaPendenteEquipe();
			
			List<String> equipes = new ArrayList<String>();
			List<String> qtd = new ArrayList<String>();

			List<Integer> qtdconvertido = new ArrayList<Integer>();


			for (String[] string : listaPendenteEquipe) {
				
				equipes.add(string[0]);
				

			}

			for (String[] string : listaPendenteEquipe) {
				
				
				//qtd.add(string[1]);
				
					qtdconvertido.add(Integer.valueOf(string[1]));
			

			}


			dados2.put("equipe", equipes);
			dados.put("qtd", qtdconvertido );
			
//			dados2.put("equipe", listaEquipesPendencia);
//			dados.put("qtdPendencias", listaQtdPendencia);
//			
			
			grafico.setDados(dados);
			grafico.setDados2(dados2);
			grafico.setNome("Meta 2/hs");
			
		
		
		
		return grafico;
	}

	@Override
	public Grafico getGraficoChamadosPessoa() {


		
		
		return null;
	}
	
	
	

}
