package br.com.sisnoc.chamados.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sisnoc.chamados.dao.UsuariosDao;
import br.com.sisnoc.chamados.security.UsuarioSistema;

@Controller
public class UsuariosController {

	@Autowired
	private UsuariosDao dao;
	
	
	
	
	@RequestMapping("/cadastro")
	public String cadastro() throws SQLException{

		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (usuarioLogado  instanceof UsuarioSistema ) {
		   username= ( (UsuarioSistema)usuarioLogado).getUsuario().getNomeEquipe();
		} else {
		   username = usuarioLogado .toString();
		}

		System.out.println(username);
		
		
		return "chamados/ok";
	}
	
	
}
