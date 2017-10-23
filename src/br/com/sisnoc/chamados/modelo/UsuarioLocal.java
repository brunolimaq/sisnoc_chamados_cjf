package br.com.sisnoc.chamados.modelo;

import java.util.List;

public class UsuarioLocal {
	
	private List<Integer> equipes;
	private List<Integer> permissoes;
	private Integer gerencia;
	private String nome;
	private String login;
	private String email;
	private String senhaUsuario;
	private String senhaValidacao;
	
	
	
	
	@Override
	public String toString() {
		return "UsuarioLocal [equipes=" + equipes + ", permissoes=" + permissoes + ", gerencia=" + gerencia + ", nome="
				+ nome + ", login=" + login + ", email=" + email + ", senhaUsuario=" + senhaUsuario
				+ ", senhaValidacao=" + senhaValidacao + "]";
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	public String getSenhaValidacao() {
		return senhaValidacao;
	}
	public void setSenhaValidacao(String senhaValidacao) {
		this.senhaValidacao = senhaValidacao;
	}
	
	public List<Integer> getEquipes() {
		return equipes;
	}
	public void setEquipes(List<Integer> equipes) {
		this.equipes = equipes;
	}
	public Integer getGerencia() {
		return gerencia;
	}
	public void setGerencia(Integer gerencia) {
		this.gerencia = gerencia;
	}
	public List<Integer> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(List<Integer> permissoes) {
		this.permissoes = permissoes;
	}
	
	

	
}
