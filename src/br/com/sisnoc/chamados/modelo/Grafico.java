package br.com.sisnoc.chamados.modelo;


import java.util.List;
import java.util.Map;

public class Grafico {
	
	
	private String nome;
	private Map<String, List<Integer>> dados;
	
	
	
	public Map<String, List<Integer>> getDados() {
		return dados;
	}
	public void setDados(Map<String, List<Integer>> dados) {
		this.dados = dados;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
