package br.com.sisnoc.ponto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.com.sisnoc.chamados.service.ContextoUsuario;
import br.com.sisnoc.ponto.dao.util.BatidasDao;


@Primary
@Repository
@BatidasDao
public class RegistraBatidasDao {

	private  final Connection connection;

	
	@Autowired
	public RegistraBatidasDao(@Qualifier("datasourceSQL") DataSource datasource) {
		try {
			this.connection = datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public Boolean entrada(){
		
		String idUsuario = ContextoUsuario.getID();
		
		String insert_ponto = "insert into sisnoc2.ponto "
				+ "(seqBatida,"
				+ "idUsuario,"
				+ "datetime,"
				+ "tipo "
				+ ")values( "
				+ "1,"
				+ idUsuario+","
				+ "CURRENT_TIMESTAMP(),"
				+ "'entrada_jornada');";
		
		PreparedStatement stmt;
		try {
			stmt = connection
					.prepareStatement(insert_ponto);
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement(insert_ponto);
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	public Boolean saidaIntervalo(){
		String idUsuario = ContextoUsuario.getID();
		
		String insert_ponto = "insert into sisnoc2.ponto "
				+ "(seqBatida,"
				+ "idUsuario,"
				+ "datetime,"
				+ "tipo "
				+ ")values( "
				+ "1,"
				+ idUsuario+","
				+ "CURRENT_TIMESTAMP(),"
				+ "'saida_intervalo');";
		

		PreparedStatement stmt;
		try {
			stmt = connection
					.prepareStatement(insert_ponto);
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement(insert_ponto);
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	
	
		return true;
	}
	public Boolean entradaIntervalo(){
			
		String idUsuario = ContextoUsuario.getID();
		
		String insert_ponto = "insert into sisnoc2.ponto "
				+ "(seqBatida,"
				+ "idUsuario,"
				+ "datetime,"
				+ "tipo "
				+ ")values( "
				+ "1,"
				+ idUsuario+","
				+ "CURRENT_TIMESTAMP(),"
				+ "'entrada_intervalo');";

		PreparedStatement stmt;
		try {
			stmt = connection
					.prepareStatement(insert_ponto);
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement(insert_ponto);
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	
	
		return true;
	}
	public Boolean saida(){
		String idUsuario = ContextoUsuario.getID();
		
		String insert_ponto = "insert into sisnoc2.ponto "
				+ "(seqBatida,"
				+ "idUsuario,"
				+ "datetime,"
				+ "tipo "
				+ ")values( "
				+ "1,"
				+ idUsuario+","
				+ "CURRENT_TIMESTAMP(),"
				+ "'saida_jornada');";
		

		PreparedStatement stmt;
		try {
			stmt = connection
					.prepareStatement(insert_ponto);
			stmt.executeUpdate();
			
			stmt = connection.prepareStatement(insert_ponto);
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	
	
		return true;
	}
}
