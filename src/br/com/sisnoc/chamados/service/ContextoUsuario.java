package br.com.sisnoc.chamados.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sisnoc.chamados.security.UsuarioSistema;

public class ContextoUsuario {

	public static String getUsername(){
		
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		
		username = ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
		
		return username;
	}
	
	public static String getEquipes(){
		
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String equipe = "";
		
		equipe = ( (UsuarioSistema)usuarioLogado).getUsuario().getNomeEquipe();
				
		String[] splitEquipe = equipe.split(",");
		String listaEquipe = "\'\'";
		
		for (String eqp : splitEquipe) {
			
			listaEquipe = listaEquipe +",\'" + eqp + "\'";
		}
		
		return listaEquipe;
	}
	
	public static String getEquipesRaw(){
		
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String equipe = "";
		
		equipe = ( (UsuarioSistema)usuarioLogado).getUsuario().getNomeEquipe();
		
		return equipe;
	}
	
	
	public static Collection<? extends GrantedAuthority> getPermissao(){
		
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		
		Collection<? extends GrantedAuthority> permissao = ( (UsuarioSistema)usuarioLogado).getUsuario().getAuthority();
				
				
		return permissao;
	}
	
	public static String getGerencia(){
		
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String gerencia;
		
		gerencia = ( (UsuarioSistema)usuarioLogado).getUsuario().getGerencia();
		
		return gerencia;
	}
}
