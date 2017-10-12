package br.com.sisnoc.chamados.dao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class teste {
	
	
	public static void main(String[] args) throws ParseException {
		
		
			
			String valor1 = "01/10/2014";
			String valor2 = "10/10/2014";
        
        //long periodo;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dataI = new Date(format.parse(valor1).getTime()); 
        Date dataF = new Date(format.parse(valor2).getTime());
        

		   Calendar dInicial = Calendar.getInstance(); 
	        dInicial.setTime(dataI);
	        Calendar dFinal = Calendar.getInstance();
	        dFinal.setTime(dataF);
	        int count = 0;    
	        while (dInicial.get(Calendar.DAY_OF_MONTH) != dFinal.get(Calendar.DAY_OF_MONTH)){    
	        	   dInicial.add(Calendar.DAY_OF_MONTH, 1);    
	            count ++;    
	        }
        
	        System.out.println("Dias de diferença são: " + count);    

	}
}