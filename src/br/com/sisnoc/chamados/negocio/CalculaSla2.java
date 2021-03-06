package br.com.sisnoc.chamados.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import br.com.sisnoc.chamados.modelo.Chamado;

public class CalculaSla2 {

	public static String acumuladoParaHoraLegivel(Integer acumulado){
		
		int segundos = acumulado; 
		int segundo = segundos % 60; 
		int minutos = segundos / 60; 
		int minuto = minutos % 60; 
		int hora = minutos / 60; 
		String hms = String.format ("%02d:%02d:%02d", hora, minuto, segundo); 
		//System.out.println (hms); 
		
		return hms;
		
	}
	
	public static Integer getSlaInicial(Chamado chamado){
		
		if(chamado.getEquipe().equals("Documentadores")){
			
			return 900;
			
			
		}if(chamado.getEquipe().equals("Analistas Aplicações")){
			
			return 800;
			
			
		}else if(chamado.getEquipe().equals("Analistas Banco de Dados")){
			
			return 800;
			
			
		}else if(chamado.getEquipe().equals("Analistas Redes")){
			
			return 1300;
			
			
		}else if(chamado.getEquipe().equals("Analistas Backup")){
			
			return 1300;
			
			
		}else{
			
			return 1200;
			
		}
	}
	
	
	public static Integer getSlaFinal(Chamado chamado){
		
		if(chamado.getEquipe().equals("Documentadores")){
			
			return 1900;
			
		}if(chamado.getEquipe().equals("Analistas Aplicações")){
			
			return 2100;
			
		}else if(chamado.getEquipe().equals("Analistas Banco de Dados")){
			
			return 2100;
			
		}else if(chamado.getEquipe().equals("Analistas Redes")){
			
			return 2200;
			
		}else if(chamado.getEquipe().equals("Analistas Backup")){
			
			return 2200;
			
		}else{
			
			return 2100;
		}
	}
	
	public static Chamado SlaCjf(ArrayList<Chamado> log){
		
		String num_chamado = "";
		Integer tempoInicial = 0;
		Integer tempoParada = 0;
		Integer tempoAcumulado = 0;
		Integer slaInicial = 0;
		Integer slaFinal = 0;
		
		Chamado ultimoLog = new Chamado();
		
		
		ArrayList<Chamado> listaChamados = new ArrayList<Chamado>();
		
		for (Chamado logChamado : log) {
			
			
			logChamado.setStatusReal(logChamado.getStatus());
			logChamado.setReaberto(0);
			if(logChamado.getStatus().equals("INIT") || logChamado.getStatus().equals("SLARESUME")){
				logChamado.setStatus("resume");
			}else if(logChamado.getStatus().equals("SLADELAY")  || logChamado.getStatus().equals("RE")){
				logChamado.setStatus("stop");
			}
				slaInicial = getSlaInicial(logChamado);
				slaFinal = getSlaFinal(logChamado);
				
			
			//System.out.println(logChamado.getChamado()+" - "+logChamado.getEquipe());
			//System.out.println("inicial: "+slaInicial);
			//System.out.println("Final: "+slaFinal);
			
			if (logChamado.getStatus().equals("resume")){
				
				tempoInicial = logChamado.getTime();
			}else if (logChamado.getStatus().equals("stop")){
				tempoAcumulado = tempoAcumulado + calculaAcumulado(slaInicial, slaFinal, (long)tempoInicial, (long)logChamado.getTime(), 28800,logChamado.getTipo(),logChamado.getGrupo());
			}
			
			ultimoLog = logChamado;
		}
		
		
		if(ultimoLog.getStatus().equals("resume")){
			Integer epoch = ultimoLog.getEpoch();
			
			tempoAcumulado = tempoAcumulado + calculaAcumulado(slaInicial, slaFinal, (long)tempoInicial, (long) epoch, 28800, ultimoLog.getTipo(),ultimoLog.getGrupo());
			
			ultimoLog.setSla(""+tempoAcumulado);
			Chamado validado = new Chamado();
			validado = calculaSLARestante(tempoAcumulado, ultimoLog);
			return validado;
			
			
			
			
		}else if(ultimoLog.getStatus().equals("stop")){
			
			
			ultimoLog.setSla(""+tempoAcumulado);
			Chamado validado = new Chamado();
			validado = calculaSLARestante(tempoAcumulado, ultimoLog);
			return validado;

		}
		
		
		
		return null;
	}

	public static Integer calculaAcumulado(Integer slaIni, Integer slaFim, Long tempoInicial, Long tempoFinal, Integer slaMax, String tipo, String grupo) {
		
		//System.out.println(tipo);
		if (tipo == "I") {
				return (int) (tempoFinal - tempoInicial);
		}else if (tipo == "P" || grupo.equals("INFRA.Ordem de Servico") || grupo.equals("Infra.Tarefas Internas")){
				return 1;
		}
		
		
		
		
		Integer testa;
		
		Calendar cInicio = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		Calendar cFim = Calendar.getInstance(TimeZone.getTimeZone("UTC"));


		
		cInicio.setTimeInMillis(tempoInicial*1000);
		cFim.setTimeInMillis(tempoFinal*1000);
		
		Integer tempoInicio = Integer.parseInt(cInicio.get(Calendar.HOUR_OF_DAY)+""+cInicio.get(Calendar.MINUTE));
		Integer tempoFim = Integer.parseInt(cFim.get(Calendar.HOUR_OF_DAY)+""+cFim.get(Calendar.MINUTE));
		
		testa = Integer.parseInt(cInicio.get(Calendar.HOUR_OF_DAY)+"");
		
		if(testa.toString().length() > 1){
			if(tempoInicio.toString().length() == 3){
				tempoInicio = Integer.parseInt(tempoInicio+"0");
			}
		}
		
		testa = Integer.parseInt(cFim.get(Calendar.HOUR_OF_DAY)+"");
		
		if(testa.toString().length() > 1){
			if(tempoFim.toString().length() == 3){
				tempoFim = Integer.parseInt(tempoFim+"0");
			}
		}
		
		
		Integer dwInicio = cInicio.get(Calendar.DAY_OF_WEEK);
		Integer dwFim = cFim.get(Calendar.DAY_OF_WEEK);
		
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
//		System.out.println("\n\n||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
//		System.out.println("Parametros");
//		System.out.println("Inicio SLA: "+slaIni);
//		System.out.println("Fim SLA: "+slaFim);
//		System.out.println("Inicio Periodo: "+tempoInicio);
//		System.out.println("Fim Periodo: "+tempoFim);
//		//System.out.println("Inicio Periodo: "+sdf.format(cInicio.getTime()));
//		//System.out.println("Fim Periodo: "+sdf.format(cFim.getTime()));
//		System.out.println("teste: "+(int) (tempoFinal - tempoInicial));
		

		
		//criado e atendido na mesma madrugada, dias diferentes
		if(countDays(cInicio,cFim) == 1){
			
			//System.out.println(tempoInicio+">"+slaFim);
			if(tempoInicio> slaFim){
				//System.out.println(tempoFim+"<"+slaIni);
				if(tempoFim< slaIni){
						
						return 0;
						
				}
			}
		}
			
		
		//criado e atendido na mesma noite, mesmo dia
		
		if(countDays(cInicio,cFim) == 0){
			
			//System.out.println(tempoInicio+">"+slaFim);
			if(tempoInicio> slaFim){
				//System.out.println(tempoFim+"<"+slaIni);
				if(tempoFim> slaFim){
						return 0;
						
				}
			}
		}
		//criado e atendido na mesma manhã, mesmo dia
		
		if(countDays(cInicio,cFim) == 0){
			
			//System.out.println(tempoInicio+">"+slaFim);
			if(tempoInicio < slaIni){
				//System.out.println(tempoFim+"<"+slaIni);
				if(tempoFim < slaIni){
						return 0;
						
				}
			}
		}
		
		
		//criado e atendido entre sexta cInicio - dwInicio e segunda cFim - dwFim
		
		if((dwInicio.equals(6) && tempoInicio >= slaFim) || dwInicio.equals(7) || dwInicio.equals(1)){
			
			if((dwFim.equals(2) && dwFim <= slaIni) || dwFim.equals(7) || dwFim.equals(1)){
				
				if(countDays(cInicio,cFim) <= 3){
					return 0;
				}
			}
		}

		//tempoInicial   ((long)1480715149)
		//tempoFinal
		
		//cInicio.setTimeInMillis(tempoInicial*1000);
		//cFim.setTimeInMillis(tempoFinal*1000);
		Integer ti = (int) (long) tempoInicial;
		Integer tf = (int) (long) tempoFinal;
		Integer stgTempo;
		
		Integer stgDw = cInicio.get(Calendar.DAY_OF_WEEK);
				
		Calendar stgInicio = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		
		Integer acum = 0;
		Integer varifica = 0;
		Integer varifica2 = 0;
//		System.out.println("inicio" + ti);
//		System.out.println("Fim" + tf);
		while(ti <= tf){
			ti++;
			varifica++;
			stgInicio.setTimeInMillis((long) ti*1000);
			stgTempo = Integer.parseInt(stgInicio.get(Calendar.HOUR_OF_DAY)+""+stgInicio.get(Calendar.MINUTE));
			stgDw = stgInicio.get(Calendar.DAY_OF_WEEK);
			testa = Integer.parseInt(stgInicio.get(Calendar.HOUR_OF_DAY)+"");
			
			if(testa.toString().length() > 1){
				if(stgTempo.toString().length() == 3){
					stgTempo = Integer.parseInt(stgTempo+"0");
				}
			}
			
			//System.out.println();
			if(stgTempo >= slaIni && stgTempo <= slaFim){
				varifica2++;
				
				if(stgDw != 1 && stgDw != 7){
					acum++;
					
					
						if(stgTempo >= slaMax){ //acumulado mais que 8 horas
							//System.out.println("Acumulou "+acum+" Segundos x");
							return acum;
						}
					
					
				}
			}
		}
		//System.out.println("Acumulou "+acum+" Segundos, verificados "+varifica+" verificados2 "+varifica2);
		return acum;	
		}

	//diferença de dias entre duas datas
	
	public static Chamado CalculaMetaOS(Integer dias, Chamado ordenServico){
		
		
		// Metas
		if(dias < 1){ //menor que 7
			ordenServico.setMeta_2("progress-bar-success");
			ordenServico.setMeta_6("progress-bar-success");
			ordenServico.setMeta_24("progress-bar-success");
			
		}else if(dias < 2){ //menor que 15 
			
			ordenServico.setMeta_2("progress-bar-danger");
			ordenServico.setMeta_6("progress-bar-success");
			ordenServico.setMeta_24("progress-bar-success");
			
		}else if(dias < 3){ // menor que 22 
			
			ordenServico.setMeta_2("progress-bar-danger");
			ordenServico.setMeta_6("progress-bar-danger");
			ordenServico.setMeta_24("progress-bar-success");
			
		}else {
			
			ordenServico.setMeta_2("progress-bar-danger");
			ordenServico.setMeta_6("progress-bar-danger");
			ordenServico.setMeta_24("progress-bar-danger");
			
		}
		
		ordenServico.setAlerta("SemAlerta");
		
		return ordenServico;
		  
	}
	
	public static int countDays(Calendar from, Calendar to) {
	    
		Calendar f = Calendar.getInstance();
		Calendar t = Calendar.getInstance();
		f = new GregorianCalendar(from.get(Calendar.YEAR),from.get(Calendar.MONTH),from.get(Calendar.DAY_OF_MONTH),0,0,0);
		t = new GregorianCalendar(to.get(Calendar.YEAR),to.get(Calendar.MONTH),to.get(Calendar.DAY_OF_MONTH),0,0,0);
		

	    int nbJours = 0;
	    for (Calendar c = f ; c.before(t) ; c.add(Calendar.DATE, +1))
	    {
	        nbJours++;
	    }
	    for (Calendar c = f ; c.after(t) ; c.add(Calendar.DATE, -1))
	    {
	        nbJours--;
	    }
	    return nbJours;
	    }



	private static Chamado calculaSLARestante(Integer tempoAcumulado, Chamado validado) {
		int sla = 0;
//		System.out.println("##########################################");
//		System.out.println("tempoAcumulado: "+acumuladoParaHoraLegivel(tempoAcumulado));
		System.out.println(validado.getTipo());
		if(validado.getTipo().equals("I")){
			System.out.println("t1");
			sla = 3600 - tempoAcumulado; //1 hora
			
		}else if (validado.getGrupo().equals("INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum")){
			System.out.println("t2");
			sla = 7200 - tempoAcumulado; //2 horas
			
		}else if (validado.getGrupo().equals("INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva")){
			System.out.println("t3");
			sla = 3600 - tempoAcumulado; //1 hora
			
		}else{
			System.out.println("t4");
			sla = 28800 - tempoAcumulado; //8 horas

		} 
		
		//System.out.println("sla: "+sla);
		//System.out.println("##########################################");
		 if(validado.getTipo().equals("I")){
			// Metas
				if(tempoAcumulado < 450){ //menor que 7
					validado.setMeta_2("progress-bar-success");
					validado.setMeta_6("progress-bar-success");
					validado.setMeta_24("progress-bar-success");
					
				}else if(tempoAcumulado < 900){ //menor que 15 
					
					validado.setMeta_2("progress-bar-warning");
					validado.setMeta_6("progress-bar-success");
					validado.setMeta_24("progress-bar-success");
					
				}else if(tempoAcumulado < 1350){ // menor que 22 
					
					validado.setMeta_2("progress-bar-danger");
					validado.setMeta_6("progress-bar-warning");
					validado.setMeta_24("progress-bar-success");
					
				}else if(tempoAcumulado < 2700){ // menor que 45
					
					validado.setMeta_2("progress-bar-danger");
					validado.setMeta_6("progress-bar-warning");
					validado.setMeta_24("progress-bar-success");
				}else if(tempoAcumulado < 3600){ // menor que 1h
					
					validado.setMeta_2("progress-bar-danger");
					validado.setMeta_6("progress-bar-danger");
					validado.setMeta_24("progress-bar-warning");
				}else {// estourou todas as metas
					
					validado.setMeta_2("progress-bar-danger");
					validado.setMeta_6("progress-bar-danger");
					validado.setMeta_24("progress-bar-danger");
					
				}  
		}
			else if(validado.getGrupo().equals("INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva")){
				// Metas
				if(tempoAcumulado < 450){ //menor que 7
					validado.setMeta_2("progress-bar-success");
					validado.setMeta_6("progress-bar-success");
					validado.setMeta_24("progress-bar-success");
					
				}else if(tempoAcumulado < 900){ //menor que 15 
					
					validado.setMeta_2("progress-bar-warning");
					validado.setMeta_6("progress-bar-success");
					validado.setMeta_24("progress-bar-success");
					
				}else if(tempoAcumulado < 1350){ // menor que 22 
					
					validado.setMeta_2("progress-bar-danger");
					validado.setMeta_6("progress-bar-warning");
					validado.setMeta_24("progress-bar-success");
					
				}else if(tempoAcumulado < 2700){ // menor que 45
					
					validado.setMeta_2("progress-bar-danger");
					validado.setMeta_6("progress-bar-danger");
					validado.setMeta_24("progress-bar-success");
				}else if(tempoAcumulado < 3600){ // menor que 1h
					
					validado.setMeta_2("progress-bar-danger");
					validado.setMeta_6("progress-bar-danger");
					validado.setMeta_24("progress-bar-warning");
				}else {// estourou todas as metas
					
					validado.setMeta_2("progress-bar-danger");
					validado.setMeta_6("progress-bar-danger");
					validado.setMeta_24("progress-bar-danger");
					
				}  
			
		}else if(validado.getGrupo().equals("INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum")){
			// Metas
			if(tempoAcumulado < 900){ //menor que 15 
				validado.setMeta_2("progress-bar-success");
				validado.setMeta_6("progress-bar-success");
				validado.setMeta_24("progress-bar-success");
				
			}else if(tempoAcumulado < 1800){ //menor que 30
				
				validado.setMeta_2("progress-bar-warning");
				validado.setMeta_6("progress-bar-success");
				validado.setMeta_24("progress-bar-success");
				
			}else if(tempoAcumulado < 2700){ // menor que 45
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-success");
				validado.setMeta_24("progress-bar-success");
			}else if(tempoAcumulado < 3600){ // menor que 1h
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-warning");
				validado.setMeta_24("progress-bar-success");
			}
			else if(tempoAcumulado < 5400){ // Menor que 1h 30
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-danger");
				validado.setMeta_24("progress-bar-success");
				
			}else if(tempoAcumulado < 7200){ // Menor que 2h
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-danger");
				validado.setMeta_24("progress-bar-warning");
				
			}else {// estourou todas as metas
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-danger");
				validado.setMeta_24("progress-bar-danger");
				
			}
		}else {
			// Metas
			if(tempoAcumulado < 3600){ //menor que 1 
				validado.setMeta_2("progress-bar-success");
				validado.setMeta_6("progress-bar-success");
				validado.setMeta_24("progress-bar-success");
				
			}else if(tempoAcumulado < 7200){ //menor que 2 horas 
				
				validado.setMeta_2("progress-bar-warning");
				validado.setMeta_6("progress-bar-success");
				validado.setMeta_24("progress-bar-success");
				
			}else if(tempoAcumulado < 10800){ // menor que 3 horas
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-success");
				validado.setMeta_24("progress-bar-success");
			}else if(tempoAcumulado < 14400){ // menor que 4h 
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-warning");
				validado.setMeta_24("progress-bar-success");
			}
			else if(tempoAcumulado < 18000){ // Menor que 5h
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-danger");
				validado.setMeta_24("progress-bar-success");
				
			}else if(tempoAcumulado < 21600){ // menor que 6h
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-danger");
				validado.setMeta_24("progress-bar-warning");
				
			}else {// estourou todas as metas
				
				validado.setMeta_2("progress-bar-danger");
				validado.setMeta_6("progress-bar-danger");
				validado.setMeta_24("progress-bar-danger");
				
			}
		}
		
		
		// Alertas
		if(sla <= 28800 && sla > 21600){ 
			
			validado.setAlerta("entre8-6"); //Alterar letra para  Marrom claro #A1887F

			 
		}else if(sla <= 21600 && sla > 14400){
			
			validado.setAlerta("entre6-4"); //Alterar letra vermelha escura #B71C1C
			
		}else if(sla <= 14400 && sla > 7200){
			
			validado.setAlerta("entre4-2"); // Fundo #FFEE58 e letra presta
			
		}else if(sla <= 7200 && sla > 3600){
			
			validado.setAlerta("entre2-1"); // Fundo #FFEE58 e letra presta
			
		}else if(sla <= 3600 && sla > 1800){ 
			
			validado.setAlerta("entre1-30m"); //Fundo #FF6D00 Letra branca
			
		}else if(sla <= 1800 && sla > 900){
			
			validado.setAlerta("entre30m-15m"); //Fundo vermelho  #F44336 e letra branca
			
		}else if(sla <= 900 && sla > 0){
			
			validado.setAlerta("menor15m"); //Fundo vermelho #F44336 e letra branca
			
		}else {
			
			validado.setAlerta("SlaViolado"); //Fundo Preto letra branca
			
		}
		
		if (validado.getTipo().equals("P")  ){
			validado.setSla("N/A");
			validado.setAlerta("SemAlerta");
			
		} else if (validado.getGrupo().equals("INFRA.Ordem de Servico")){
			validado.setSla("N/A");
			validado.setAlerta("SemAlerta");
			validado.setTipoLegivel("OS");			
		} else 	if (sla < 0){
			validado.setSla("Violado");
			validado.setSla2(tempoAcumulado);

		} else {
			validado.setSla(acumuladoParaHoraLegivel(sla));
			validado.setSla2(tempoAcumulado);
		}
		
		//System.out.println(validado);
		//System.out.println("1");
		return validado;
	}
	
	
	
	
}
