package br.com.sisnoc.chamados.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class Usuario {
	
	private String nome;
	private String loginUsuario;
	private String senhaUsuario;
	private String senhaUsuarioValidacao;
	private String nomeEquipe;
	private String grupo;
	private Collection<? extends GrantedAuthority> permissao;
	private Set<SimpleGrantedAuthority> authority;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return loginUsuario;
	}
	public void setEmail(String email) {
		this.loginUsuario= email;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	public String getNomeEquipe() {
		return nomeEquipe;
	}
	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public Collection<? extends GrantedAuthority> getPermissao() {
		return permissao;
	}
	public void setPermissao(String permissao) {
		
		List<String> permissoes = new ArrayList<String>();
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		String[] listaPermissao = permissao.split(",");
		
		for (String string : listaPermissao) {
			permissoes.add(string);
		}


		
		permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
		
		this.setAuthority(authorities);

		
	}
	public Set<SimpleGrantedAuthority> getAuthority() {
		return authority;
	}
	public void setAuthority(Set<SimpleGrantedAuthority> authority) {
		this.authority = authority;
	}
	public String getSenhaUsuarioValidacao() {
		return senhaUsuarioValidacao;
	}
	public void setSenhaUsuarioValidacao(String senhaUsuarioValidacao) {
		this.senhaUsuarioValidacao = senhaUsuarioValidacao;
	}

}
