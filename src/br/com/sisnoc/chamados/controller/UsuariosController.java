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
import br.com.sisnoc.chamados.dao.UsuariosLocalDao;
import br.com.sisnoc.chamados.modelo.Usuario;
import br.com.sisnoc.chamados.modelo.UsuarioLocal;
import br.com.sisnoc.chamados.security.UsuarioSistema;
import br.com.sisnoc.chamados.service.ContextoUsuario;

@Controller
public class UsuariosController {

	@Autowired
	private UsuariosDao dao;
	
	@Autowired
	private UsuariosLocalDao daoLocal;
	
	
	@RequestMapping("/cadastro")
	public String cadastro() throws SQLException{

		
		String username;
		
		username = ContextoUsuario.getUsername();
		
		
		return "chamados/ok";
	}
	
	@RequestMapping("/perfil")
	public ModelAndView perfil(Model model) {
		
	
		String username;
		String grupos;
		
		Collection<? extends GrantedAuthority> permissoes = null;
		
		username = ContextoUsuario.getUsername();
		grupos = ContextoUsuario.getEquipesRaw();
		permissoes = ContextoUsuario.getPermissao();
	
		
		
		model.addAttribute("nome", username);
		model.addAttribute("grupos", grupos);
		model.addAttribute("permissoes", permissoes);
		
		ModelAndView mv = new ModelAndView("chamados/editar_perfil");
		
		return mv;
		
	}
	
	
	
	@RequestMapping("/cadastroUsuario")
	public ModelAndView cadastroUsuario(Model model) {
		
		
		try {
			model.addAttribute("equipes", daoLocal.buscaEquipes());
			model.addAttribute("gerencia", daoLocal.buscaGerencia());
			model.addAttribute("permissoes", daoLocal.buscaPermissoes());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Lista de equipes VAZIA");
		}
		
		
		ModelAndView mv = new ModelAndView("chamados/cadastroUsuario");
		
		return mv;
		
	}
	

	@RequestMapping("/cadastrarUsuario")
	public ModelAndView cadastrar(Model model, UsuarioLocal usuario) throws SQLException {
		
		daoLocal.cadastro(usuario);
		
		ModelAndView mv = new ModelAndView("chamados/ok");
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
