package br.com.sisnoc.chamados.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class Usuario {
	
	private String ID;
	
	private String nome;
	private String loginUsuario;
	private String senhaUsuario;
	private String senhaUsuarioValidacao;
	private String nomeEquipe;
	private String grupo;
	private String gerencia;
	private Collection<? extends GrantedAuthority> permissao;
	private Set<SimpleGrantedAuthority> authority;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return getLoginUsuario();
	}
	public void setEmail(String email) {
		this.setLoginUsuario(email);
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
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public void setPermisao(Collection<? extends GrantedAuthority> collection) {
		// TODO Auto-generated method stub
		
	}
	public String getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

}
