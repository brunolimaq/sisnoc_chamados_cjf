package br.com.sisnoc.chamados.negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;


public class Popula {
	
	

//	ID, chamados, responsavel, grupo, descricao, titulo, status
	 
	public String populaID(ResultSet rs) throws SQLException, ParseException {
		 
		 return rs.getString("ID");
	}

	public String populaChamados(ResultSet rs) throws SQLException,  ParseException {
		
		String chamados = rs.getString("chamados");
		return chamados;
	}

	public String populaTitulo(ResultSet rs) throws SQLException,  ParseException {
		return rs.getString("titulo");
	}

	public String populaEquipe(ResultSet rs) throws SQLException,  ParseException {
		return rs.getString("equipe");
	}
	
	public String populaGrupo(ResultSet rs) throws SQLException,  ParseException {
		return rs.getString("grupo");
	}

	public String populaStatus(ResultSet rs) throws SQLException,  ParseException{
		//System.out.println(rs.getString("status"));
		String status = rs.getString("status");
		
		return status;
	}

	public int populaTime(ResultSet rs) throws SQLException,  ParseException{
		return rs.getInt("time");
	}

	public int populaEpoch(ResultSet rs) throws SQLException,  ParseException {
		
		return rs.getInt("epoch");
	}

	public String populaTipo(ResultSet rs) throws SQLException, ParseException {
		return rs.getString("tipo");

	}
}