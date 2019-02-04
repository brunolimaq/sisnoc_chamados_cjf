package br.com.sisnoc.chamados.negocio;

import java.util.ArrayList;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import br.com.sisnoc.chamados.modelo.Url;

public class VerificaUrl {

	public static ArrayList<Url> verificaLista(ArrayList<Url> lista){
		long start = System.currentTimeMillis();
		ArrayList<Url> listaVerificada = new ArrayList<Url>();
		
		for (Url url : lista) {
			
			if(url.getAtivo().equals("1")) {
				url.setResultado(verifica(url.getUrl()));
				listaVerificada.add(url);
			}else {
				url.setResultado("INDEFINIDO");
				listaVerificada.add(url);
			}
			
			
		}
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("###################################################");
		System.out.println("As URLs foram testadas em "+elapsed+"ms");
		System.out.println("###################################################");
		return listaVerificada;
	}
	
	public static String verifica(String searchUrl) {
		
		long start = System.currentTimeMillis();
		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  
		client.getOptions().setUseInsecureSSL(true);
		try {  
		  
		  HtmlPage page = client.getPage(searchUrl);
		  //HtmlElement htmlItem = page.getFirstByXPath(".//title");
		  //HtmlAnchor anchor = page.getAnchorByName("title");
		  page.getTitleText();
		  //System.out.println(searchUrl +" - Está Tudo OK");
		  long elapsed = System.currentTimeMillis() - start;
		  //System.out.println(searchUrl+" - Respondeu em "+elapsed+"ms");
		  return "OK";
		}catch(Exception e){
		  
			//System.out.println(searchUrl + " - Não está OK");
			//e.printStackTrace();
			long elapsed = System.currentTimeMillis() - start;
			//System.out.println(searchUrl+" - Não respondeu em "+elapsed+"ms");
			return "ERRO";
			
		}
	
	}
}
