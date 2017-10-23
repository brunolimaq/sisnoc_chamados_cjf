package br.com.sisnoc.chamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.client.InterceptingAsyncClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;


import br.com.sisnoc.chamados.modelo.Usuario;
import br.com.sisnoc.chamados.modelo.UsuarioLocal;


@Repository
public class UsuariosLocalDao {
	
	private  final Connection connection;

    
	
	@Autowired
	public UsuariosLocalDao(@Qualifier("datasourceMySql") DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
				
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	
	public HashMap<String, Integer> buscaEquipes() throws SQLException {

		
		HashMap<String, Integer> lista = new HashMap<String, Integer>();
		
		String busca = "select idEquipe, NomeEquipe from equipe";
		PreparedStatement stmt = connection
				.prepareStatement(busca);
		
		ResultSet rs = stmt.executeQuery();		
		
		while (rs.next()) {
		
		lista.put(rs.getString("NomeEquipe"), rs.getInt("idEquipe"));
			
		}
		stmt.close();
		
		return lista;

	}
	
	
	
	
	public HashMap<String, Integer> buscaGerencia() throws SQLException {

		
		HashMap<String, Integer> lista = new HashMap<String, Integer>();
		
		String busca = "select idgerencia, nomeGerencia from gerencia";
		PreparedStatement stmt = connection
				.prepareStatement(busca);
		
		ResultSet rs = stmt.executeQuery();		
		
		while (rs.next()) {
		
		lista.put(rs.getString("nomeGerencia"), rs.getInt("idgerencia"));
			
		}
		stmt.close();
		
		return lista;

	}
	
	
	

	public HashMap<String, Integer> buscaPermissoes() throws SQLException {

		
		HashMap<String, Integer> lista = new HashMap<String, Integer>();
		
		String busca = "select idpermissao, nomePermissao from permissao";
		PreparedStatement stmt = connection
				.prepareStatement(busca);
		
		ResultSet rs = stmt.executeQuery();		
		
		while (rs.next()) {
		
		lista.put(rs.getString("nomePermissao"), rs.getInt("idpermissao"));
			
		}
		stmt.close();
		
		return lista;

	}
	
	
	public void cadastro(UsuarioLocal usuario) throws SQLException{
		
		Integer codigo = 0; 
		String sql_equipe = "";
		String sql_gerencia = "";
		String sql_permissao = "";


		String sql_buscaID = "select max(idUsuario) + 1 as id from usuario";
		
		PreparedStatement stmt = connection
				.prepareStatement(sql_buscaID);
		ResultSet rs = stmt.executeQuery();	
		
		
		while (rs.next()) {
			 codigo = rs.getInt("id");	
		}
		
		String sql_cadastroUsuario = "insert into usuario value ("+ codigo +", '" + usuario.getNome() + "', '" + usuario.getLogin() + "' ,'" + usuario.getSenhaUsuario() 
		+ "' , '" + usuario.getEmail() + "',1)";

		stmt = connection
				
				.prepareStatement(sql_cadastroUsuario);
		stmt.executeUpdate();	
		
		System.out.println(sql_cadastroUsuario);
		
		for (Integer equipe : usuario.getEquipes()) {
			
			sql_equipe = "insert into usuario_equipe values ("+ codigo + "," + equipe +")";
			stmt = connection
					.prepareStatement(sql_equipe);
			stmt.executeUpdate();	
			
			System.out.println(sql_equipe);
		}
		
		
		for (Integer permissao : usuario.getPermissoes()) {
			
			sql_permissao = "insert into usuario_permissao values ("+ codigo + "," + permissao +")"; 	
			stmt = connection
					.prepareStatement(sql_permissao);
			stmt.executeUpdate();
			System.out.println(sql_permissao);
		}
		
			
			sql_gerencia = "insert into usuario_gerencia values ("+ codigo + "," + usuario.getGerencia() +")"; 	
			stmt = connection
					.prepareStatement(sql_gerencia);
			stmt.executeUpdate();
			System.out.println(sql_gerencia);
		
		
		
		
	}
	
	public Connection getConnection() throws SQLException {
		
		return connection; 
	}









}
