package br.com.sisnoc.chamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;


import br.com.sisnoc.chamados.dao.util.URLDAO;
import br.com.sisnoc.chamados.modelo.Chamado;
import br.com.sisnoc.chamados.modelo.Url;
import br.com.sisnoc.chamados.negocio.CalculaSla;
import br.com.sisnoc.chamados.negocio.Popula;
import br.com.sisnoc.chamados.negocio.VerificaUrl;
import br.com.sisnoc.chamados.security.UsuarioSistema;
import br.com.sisnoc.chamados.service.ContextoUsuario;

@Repository
@URLDAO

public class PainelUrlDao {

	private  final Connection connection;


	@Autowired
	public PainelUrlDao(@Qualifier("datasourceMySql")DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Url> listaStatusUrls(String ambiente) throws ParseException {
		try {
			//Infra.Tarefas Internas
			//INFRA.Ordem de Servico
			ArrayList<Url> ListaURL = new ArrayList<Url>();
			
			
			String username = ContextoUsuario.getUsername();
			String listaEquipe = ContextoUsuario.getEquipes();
			String gerencia = ContextoUsuario.getGerencia();
		
		
			
			
			String sql_listaURL = "select nome_url,nome_app, grupo, ambiente, ativo from url_monit where ativo = 1 and ambiente = '"+ambiente+"' order by ativo desc";
					

						
			PreparedStatement stmt = connection
					.prepareStatement(sql_listaURL);				
			
		
			ResultSet rs_listaURL = stmt.executeQuery();

			
			
			
			
			
			//Corre o ResultSet
			Integer count = 0;
				while (rs_listaURL.next()){
					// adiciona um chamado na lista
					
					Url app = new Url();
					
					app.setUrl(rs_listaURL.getString("nome_url"));
					app.setNomeAPP(rs_listaURL.getString("nome_app"));
					app.setAmbiente(rs_listaURL.getString("ambiente"));
					app.setAtivo(rs_listaURL.getString("ativo"));
					
					
					
					ListaURL.add(app);
				
					count++;
				}
				
				rs_listaURL.close();
				stmt.close();


				return VerificaUrl.verificaLista(ListaURL);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
