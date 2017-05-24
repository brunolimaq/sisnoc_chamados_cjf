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

	public String populaTipoLegivel(ResultSet rs) throws SQLException, ParseException {
		
		String tipo = rs.getString("tipo");
		
		if (tipo.equals("R")){
			return "Chamado";
		} else if (tipo.equals("I")){
			return "Incidente";
		} else if (tipo.equals("P")){
			return "Problema";
		}else {
			return "Não tratado";
		}
		
	}

	public String populaStatusDescricao(ResultSet rs) throws SQLException, ParseException {
		return rs.getString("statusDescricao");
	}

	public String populaRdm(ResultSet rs) throws SQLException, ParseException {
		return rs.getString("mudanca");
	}

	public String populaAgendamento(ResultSet rs) throws SQLException, ParseException{
		return rs.getString("agendamento");
	}

	public String populResponsavel(ResultSet rs) throws SQLException, ParseException {
		return rs.getString("responsavel");
	}

	public String populaDiasAtualizacao(ResultSet rs) throws SQLException, ParseException  {
		
		return rs.getString("diasatualizacao");
	}

	public String populaAtualizacao(ResultSet rs) throws SQLException, ParseException  {
		
		return rs.getString("atualizacao");
	}

	public String populaData_inicio(ResultSet rs) throws SQLException, ParseException  {
		
		return rs.getString("data_inicio");
	}

	public String populaData_retorno(ResultSet rs) throws SQLException, ParseException  {
		
		return rs.getString("data_retorno");
	}

	public String populaCCM(ResultSet rs)throws SQLException, ParseException {
		return rs.getString("ccm");
	}

	public String populaPrazo(ResultSet rs) throws SQLException,  ParseException  {
		
		return rs.getString("prazo");
	}

	public int populaReaberto(ResultSet rs)  throws SQLException,  ParseException {
		// TODO Auto-generated method stub
		return rs.getInt("reaberturas");
	}
	
	
	
	
	
	
}