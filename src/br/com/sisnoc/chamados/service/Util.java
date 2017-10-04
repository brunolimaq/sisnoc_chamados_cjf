package br.com.sisnoc.chamados.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

	
	public String horaAtual(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		java.util.Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
		String dataFormatada = sdf.format(hora);
		
		return dataFormatada;
		
	}
}
