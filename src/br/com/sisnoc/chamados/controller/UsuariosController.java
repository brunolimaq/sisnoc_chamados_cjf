package br.com.sisnoc.chamados.controller;

import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




import br.com.sisnoc.chamados.dao.UsuariosDao;
import br.com.sisnoc.chamados.modelo.Usuario;
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
		
	
		
		
		return "chamados/ok";
	}
	
	@RequestMapping("/perfil")
	public ModelAndView perfil(Model model) {
		
		Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		String grupos;
		Collection<? extends GrantedAuthority> permissoes = null;
		if (usuarioLogado  instanceof UsuarioSistema ) {
		   username= ( (UsuarioSistema)usuarioLogado).getUsuario().getNome();
		   grupos =  ( (UsuarioSistema)usuarioLogado).getUsuario().getNomeEquipe();
		   permissoes =  ( (UsuarioSistema)usuarioLogado).getUsuario().getAuthority();
		} else {
		   username = usuarioLogado .toString();
		   grupos = usuarioLogado .toString();
		   
		}

	
		
		model.addAttribute("nome", username);
		model.addAttribute("grupos", grupos);
		model.addAttribute("permissoes", permissoes);
		
		ModelAndView mv = new ModelAndView("chamados/editar_perfil");
		
		return mv;
		
	}
	
	@RequestMapping("/alterarPerfil")
	public String alterarPerfil (Model model, Usuario usuario, RedirectAttributes attributes) throws SQLException{
		
		if(usuario.getSenhaUsuario().equals(usuario.getSenhaUsuarioValidacao())){
			dao.alterarSenha(usuario.getSenhaUsuario(), usuario.getNome());	
			attributes.addFlashAttribute("mensagemSucesso", "Senha alterada com sucesso!");
			return "redirect:/perfil";

		} else {
			attributes.addFlashAttribute("mensagem", "Senhas não conferem!");
			return "redirect:/perfil";
			
		}
		
	}
	
	
}
