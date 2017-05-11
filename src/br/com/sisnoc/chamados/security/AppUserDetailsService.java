package br.com.sisnoc.chamados.security;

import java.sql.SQLException;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import br.com.sisnoc.chamados.dao.UsuariosDao;
import br.com.sisnoc.chamados.modelo.Usuario;

@Service
public class AppUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UsuariosDao usuarios;
	
	public UserDetails loadUserByUsername(String loginUsuario) throws UsernameNotFoundException {
		
			
			Optional<Usuario> usuarioOptional = null;
			try {
				usuarioOptional = usuarios.validaLogin(loginUsuario);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
			
			return new UsuarioSistema(usuario, usuario.getAuthority());

		
	}


}
