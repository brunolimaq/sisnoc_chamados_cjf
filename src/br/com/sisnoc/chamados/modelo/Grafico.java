package br.com.sisnoc.chamados.modelo;


import java.util.List;
import java.util.Map;

public class Grafico {
	
	
	private String nome;
	private Map<String, List<Integer>> dados;
	private Map<String, List<String>> dados2;

	private String equipe;
	private Integer qtdPendencia;
	
	
	
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
	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	public Integer getQtdPendencia() {
		return qtdPendencia;
	}
	public void setQtdPendencia(Integer qtdPendencia) {
		this.qtdPendencia = qtdPendencia;
	}
	public Map<String, List<String>> getDados2() {
		return dados2;
	}
	public void setDados2(Map<String, List<String>> dados2) {
		this.dados2 = dados2;
	}



}
