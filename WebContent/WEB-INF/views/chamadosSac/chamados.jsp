<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="chamadossac" class="br.com.sisnoc.chamados.modelo.Chamado"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chamados SAC</title>
  
  	 <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
     <script src="resources/js/jquery-2.1.4.js"></script>
     <script src="resources/js/bootstrap.min.js"></script>
     
     	
<script type="text/javascript">


        function timedRefresh(timeoutPeriod) {
            setTimeout("location.reload(true);", timeoutPeriod);
        }

        jQuery(document).ready(function () {
            timedRefresh(15000);
        });
       
    </script>

<style>
.navbar-default {
  background-color: #EAE6E6;
  border-color: #e7e7e7;
}

body {
font-size: 13px;
}

 .panel-heading {
      padding: 1px 0 0 0;
      border-bottom: 1px solid transparent;
      border-top-left-radius: 3px;
      border-top-right-radius: 3px;
    }    
        .app_linux {
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
            
        }
        
          .app_zabbix {
            color: white;
            background-color: #337AB7;
            border-color: #337AB7;
        }
        
        .Incidente {
            background-color:  #fcf8e3 !important; 
             
        }
        
		
		#slaid_0 {
		
			}
			
			
		#slaid_1 {
			  color: #732801;
			}
			
		#slaid_1 a {
			  color: #732801;
			}
			
	    #slaid_2 {
			  color: #8B3D0D;
			}
		#slaid_2 a {
			  color: #8B3D0D;
		}
			
	    #slaid_3 {
			  color: #FF3A00;
			}
		#slaid_3  a {
			color: #FF3A00;
  		}
			
	    #slaid_4 {
			color: #FF0000;
			border-color:  #FF0000;
  			}
  		
  		#slaid_4  a {
			color: #FF0000;
  		}
  			
  			
	    #slaid_5 {
  			color: #FFFFFF;
			background-color: #FF0000 !important;		
  			}
  		#slaid_5 a {
  			color: #FFFFFF;
  		}	
  		
  		#slaid_6 {
			  color: #FFFFFF;
			  background-color: #000000 !important;
		  }
		#slaid_6 a {
  			color: #FFFFFF;
  		}	
  		
  		
  		#slaid_10 {
  			color: #FFFFFF;
  			background-color: #517f49
  		}
  		#slaid_10 a {
  			color: #FFFFFF;
  		}
  		
  		#slaid_20{
  			color: #000000;
  			background-color: #fff900;
  			
  		}
  		#slaid_20 a {
  			color: #000000
  		}
  		
  		#slaid_30 {
  			color: #FFFFFF; 
  			background-color: #ff7800;
  		}
  		
  		#slaid_30 a {
  			color: #FFFFFF;
  		}
  		
  		#slaid_40 {
  			color: #FFFFFF; 
  			background-color: #ff7800;
  		}
  		
  		#slaid_40 a {
  			color: #FFFFFF;
  		}  		
  		
  		#slaid_50 {
  			color: #FFFFFF; 
  			background-color: #ff0000;
  		}
  		
  		#slaid_50 a {
  			color: #FFFFFF;
  		}
  		
  		#slaid_60 {
  			color: #FFFFFF; 
  			background-color: #000000;
  		}
  		
  		#slaid_60 a {
  			color: #FFFFFF;
  		}
  		
  		
  		
  		#od_padrao {
  			color: #337ab7;
			background-color: #E5E5E5 !important;
  			}
 
		#sladias_0 {
 				color: #333;
			  background-color: #fff;  		}
			  
		#sladias_0 a {
  			color: #333;
  		}
		#sladias_1 {
 			color: #333;
			  background-color: #fff;  			}
			  
		#sladias_1 a {
  			color: #333;
  		}
  				#sladias_2 {
 				color: #F08657;
			  background-color: #fff;  		}
			  
		#sladias_2 a {
  			color: #F08657;
  		}
  				#sladias_3 {
 				color: #F08657;
			  background-color: #fff;  		}
			  
		#sladias_3 a {
  			color: #F08657;
  		}
  				#sladias_4 {
 				color: #F08657;
			  background-color: #fff;  		}
			  
		#sladias_4 a {
  			color: #F08657;
  		}
  				#sladias_5 {
 				color: #FF4E00;
			  background-color: #fff;  		}
			  
		#sladias_5 a {
  			color: #FF4E00;
  		}
  				#sladias_6 {
 				color: #FF4E00;
			  background-color: #fff;  		}
			  
		#sladias_6 a {
  			color: #FF4E00;
  		}
  				#sladias_7 {
 				color: #F75252;
			  background-color: #fff;  		}
			  
		#sladias_7 a {
  			color: #F75252;
  		}
  				#sladias_8 {
 				color: #FF0000;
			  background-color: #fff;  		}
			  
		#sladias_8 a {
  			color: #FF0000;
  		}
  				#sladias_9 {
 				color: #FF0000;
			  background-color: #fff;  		}
			  
		#sladias_9 a {
  			color: #FF0000;
  		}  		
  
  	.filho { 
	display: none; 
	background-color: #FDFBC4;
    font-size: 11px;
    }
     .filho_td div td { 
     padding: 1px;
     font-size: 5px;
    }
	  #logo { 
       width: 20px;
       height: 20px;
	}
	
	 #logo_seta { 
    width: 20px;
    height: 20px;
    left: -33px;
    top: 14px;
    position: relative;
	}
	
	#texto {
	text-align: right;
	}

</style>

</head>

<body>
<div class="panel panel-primary">
        <div class="panel-heading"><h3 id="chamados"><center><strong>Chamados SAC</strong></center></h3></div>
        <div class="panel-body">
                   

         <div class="row">

                <div class="col-md-4">
                    <div class="list-group">
                        <a href="#chamados" class="list-group-item list-group-item-success ">
   <center><strong>Chamados Abertos</strong></center> 
  </a>
                   
                 <table class="table table-bordered table-hover">
                              <thead>
                              
                            <tr class="app_linux">
                                <td><center><strong>Nome</strong></center></td>
                                <td><center><strong>Chamado</strong></center></td>
                                <td><center><strong>Descrição</strong></center></td>
                                <td><center><strong>Tipo</strong></center></td>
                                <td><center><strong>Status</strong></center></td>
                                <td><center><strong>SLA</strong></center></td>
                            </tr>
                          </thead>
                           <tbody>
		                		<c:forEach items="${chamadosPainelMon}" var="chamadosPainelMon">	
										<tr   data-toggle="tooltip" data-placement="bottom" title="100" class="${chamadosPainelMon.sla2}" id="slaid_${chamadosPainelMon.sla2}">
											<td>${chamadosPainelMon.nome}</td>
												<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPainelMon.id}" target="_blank" >${chamadosPainelMon.chamado}</a>
												<c:forEach items="${chamadosFilhosCarinha}" var="chamadosFilhosCarinha" >
														<c:if test="${chamadosFilhosCarinha.chamado == chamadosPainelMon.chamado }">
																<img src="resources/images/filho.png" id="logo" alt=""/>
														</c:if>
												</c:forEach>
											</td>
											<td>${chamadosPainelMon.descricao}</td>
											<td >${chamadosPainelMon.tipo}</td>
											<td>${chamadosPainelMon.status}</td>
											<td>${chamadosPainelMon.sla}</td>
  										</tr>
  										<c:forEach items="${chamadosFilhos}" var="chamadosFilhos">	
  											<c:if test="${chamadosFilhos.chamado_Pai == chamadosPainelMon.chamado }">	
														<tr class="filho">
														<div>
															
															<td><img src="resources/images/setabaixo.png" id="logo" alt=""/> ${chamadosFilhos.nome}</td>
															<td ><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosFilhos.id}" target="_blank" >${chamadosFilhos.chamado}</a></td>
															<td >${chamadosFilhos.descricao}</td>
															<td >${chamadosFilhos.tipo}</td>
															<td >${chamadosFilhos.status}</td>
															<td >${chamadosFilhos.sla}</td>
															</div>
														</tr>
											</c:if>
										</c:forEach>
								</c:forEach>
                        </tbody>    
                    </table>
                   
                   
                    </div>
                </div>
                
                
                
                <div class="col-md-4">
                    <div class="list-group">
                        <a href="#chamados" class="list-group-item list-group-item-success ">
<center><strong>Solicitações</strong></center>  
  </a>
                   
    <table  id="chamados" class="table table-bordered table-hover">
                              <thead>
                            <tr class="app_linux">
                                <td><center><strong>Nome</strong></center></td>
                                <td><center><strong>Chamado</strong></center></td>
                                <td><center><strong>Descrição</strong></center></td>
                                <td><center><strong>Status</strong></center></td>
                                <td><center><strong>SLA</strong></center></td>
                            </tr>
                          </thead>
                           <tbody>
		                		<c:forEach items="${chamadosPainelSol}" var="chamadosPainelSol">	
										<tr   data-toggle="tooltip" data-placement="bottom" title="100" class="${chamadosPainelSol.sla2}" id="slaid_${chamadosPainelSol.sla2}">
											<td>${chamadosPainelSol.nome}</td>
												<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPainelSol.id}" target="_blank" >${chamadosPainelSol.chamado}</a>
												<c:forEach items="${chamadosFilhosCarinha}" var="chamadosFilhosCarinha" >
														<c:if test="${chamadosFilhosCarinha.chamado == chamadosPainelSol.chamado }">
																<img src="resources/images/filho.png" id="logo" alt=""/>
														</c:if>
												</c:forEach>
											</td>
											<td>${chamadosPainelSol.descricao}</td>
											<td>${chamadosPainelSol.status}</td>
											<td>${chamadosPainelSol.sla}</td>
  										</tr>
  										<c:forEach items="${chamadosFilhos}" var="chamadosFilhos">	
  											<c:if test="${chamadosFilhos.chamado_Pai == chamadosPainelSol.chamado }">	
														<tr class="filho">
														<div>
															
															<td><img src="resources/images/setabaixo.png" id="logo" alt=""/> ${chamadosFilhos.nome}</td>
															<td ><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosFilhos.id}" target="_blank" >${chamadosFilhos.chamado}</a></td>
															<td >${chamadosFilhos.descricao}</td>
															<td >${chamadosFilhos.status}</td>
															<td >${chamadosFilhos.sla}</td>
															</div>
														</tr>
											</c:if>
										</c:forEach>
								</c:forEach>
                        </tbody>                            
                    </table>
                   
                   
                    </div>
                </div>
                
                
          <div class="col-md-4">
                    <div class="list-group">
                        <a href="#chamados" class="list-group-item list-group-item-success ">
<center><strong>Incidentes</strong></center>  
  </a>
                   
    <table class="table table-bordered table-hover">
                              <thead>
                            <tr class="app_linux">
                                <td><center><strong>Nome</strong></center></td>
                                <td><center><strong>Chamado</strong></center></td>
                                <td><center><strong>Descrição</strong></center></td>
                                <td><center><strong>Status</strong></center></td>
                                <td><center><strong>SLA</strong></center></td>
                            </tr>
                          </thead>
                           <tbody>
		                		<c:forEach items="${chamadosPainelInc}" var="chamadosPainelInc">	
										<tr   data-toggle="tooltip" data-placement="bottom" title="100" class="${chamadosPainelInc.sla2}" id="slaid_${chamadosPainelInc.sla2}">
											<td>${chamadosPainelInc.nome}</td>
												<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPainelInc.id}" target="_blank" >${chamadosPainelInc.chamado}</a>
												<c:forEach items="${chamadosFilhosCarinha}" var="chamadosFilhosCarinha" >
														<c:if test="${chamadosFilhosCarinha.chamado == chamadosPainelInc.chamado }">
																<img src="resources/images/filho.png" id="logo" alt=""/>
														</c:if>
												</c:forEach>
											</td>
											<td>${chamadosPainelInc.descricao}</td>
											<td>${chamadosPainelInc.status}</td>
											<td>${chamadosPainelInc.sla}</td>
  										</tr>
  										<c:forEach items="${chamadosFilhos}" var="chamadosFilhos">	
  											<c:if test="${chamadosFilhos.chamado_Pai == chamadosPainelInc.chamado }">	
														<tr class="filho">
														<div>
															
															<td><img src="resources/images/setabaixo.png" id="logo" alt=""/> ${chamadosFilhos.nome}</td>
															<td ><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosFilhos.id}" target="_blank" >${chamadosFilhos.chamado}</a></td>
															<td >${chamadosFilhos.descricao}</td>
															<td >${chamadosFilhos.status}</td>
															<td >${chamadosFilhos.sla}</td>
															</div>
														</tr>
											</c:if>
										</c:forEach>
								</c:forEach>
                        </tbody>                            
                    </table>
                    </div>
                    
                    
                </div>


                
                
                
            </div>

          
            
            
            
        </div>
    </div>
    

        <!-- Fim Chamados CA -->


 
     <!-- Inicio Equipe APP Linux -->
     
        <div class="row">
                     
   <div class="panel panel-primary">
       <div class="panel-heading"><h3 id="aplicacao"><center><strong>Chamados com pendências</strong></center></h3></div>
        <div class="panel-body">
        
                     <div class="row">
                 
                               <div class="col-md-12">

               
                        <div class="list-group">
                        <a href="#chamados" class="list-group-item list-group-item-success  ">
                        	<center><strong>Incidentes / Solicitações <span class="badge">${countApp}</span></strong></center>  
  						</a>
  						
  
                    <table id="chamados" class="table table-bordered table-hover">
                              <thead>
                            <tr class="app_linux">
                                <td><center><strong>Nome</strong></center></td>
                                <td><center><strong>Chamado</strong></center></td>
                                <td><center><strong>Descrição</strong></center></td>
                                <td><center><strong>Categoria</strong></center></td>
                                <td><center><strong>Status</strong></center></td>
                                <td><center><strong>Aberto</strong></center></td>
                                <td><center><strong>SLA</strong></center></td>
                            </tr>
                          </thead>
                           <tbody>
		                		<c:forEach items="${chamadosSac}" var="chamadosSac">	
										<tr   data-toggle="tooltip" data-placement="bottom" title="100" class="${chamadosSac.sla2}" id="slaid_${chamadosSac.sla2}">
											<td>${chamadosSac.nome}</td>
												<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosSac.id}" target="_blank" >${chamadosSac.chamado}</a>
												<c:forEach items="${chamadosFilhosCarinha}" var="chamadosFilhosCarinha" >
														<c:if test="${chamadosFilhosCarinha.chamado == chamadosSac.chamado }">
																<img src="resources/images/filho.png" id="logo" alt=""/>
														</c:if>
												</c:forEach>
											</td>
											<td>${chamadosSac.descricao}</td>
											<td>${chamadosSac.categoria}</td>
											<td>${chamadosSac.status}</td>
											<td>${chamadosSac.dataInicio}</td>
											<td>${chamadosSac.sla}</td>
  										</tr>
  										<c:forEach items="${chamadosFilhos}" var="chamadosFilhos">	
  											<c:if test="${chamadosFilhos.chamado_Pai == chamadosSac.chamado }">	
														<tr class="filho">
														<div>
															
															<td><img src="resources/images/setabaixo.png" id="logo" alt=""/> ${chamadosFilhos.nome}</td>
															<td ><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosFilhos.id}" target="_blank" >${chamadosFilhos.chamado}</a></td>
															<td >${chamadosFilhos.descricao}</td>
															<td >${chamadosFilhos.categoria}</td>
															<td >${chamadosFilhos.status}</td>
															<td >${chamadosFilhos.dataInicio}</td>
															<td >${chamadosFilhos.sla}</td>
															</div>
														</tr>
											</c:if>
										</c:forEach>
								</c:forEach>
                        </tbody>
                    </table>
                    </div>
                 </div>
                       
    </div> <!-- Fim row-->
        
        
       </div>
    </div>
    
       <!-- FIM Equipe APP Linux -->
       
   
        
        
    </div>
    
       <!-- FIM Ordem de Serviço --> 
       

       
    
    </div>  <!--  Fim Row de todos os Paineis -->
<script src="resources/js/jquery-2.1.4.js"></script>
<script>
//Escondendo os tr com classe 'filho'
$("#chamados").find("tr.filho").hide();

//Exibindo os filhos da tr que foi clicada
$("#chamados tr:not(.filho)").click(
	function() {
		$(this).nextUntil(":not(.filho)").toggle();
	}
);
//SLA
	$("#slaid_0.0").ready(function() {
		$("#slaid_0.0").attr({title: "SLA dentro do prazo!!!"})
	});
	$("#slaid_1.1").ready(function() {
		$("#slaid_1.1").attr({title: "SLA menor que 4 horas!!!"})
	});
	$("#slaid_2.2").ready(function() {
		$("#slaid_2.2").attr({title: "SlA menor que 2 horas!!!"})
	});
	$("#slaid_3.3").ready(function() {
		$("#slaid_3.3").attr({title: "SLA menor que 1 hora!!!"})
	});
	$("#slaid_4.4").ready(function() {
		$("#slaid_4.4").attr({title: "SLA menor que 30 minutos!!!"})
	});
	$("#slaid_5.5").ready(function() {
		$("#slaid_5.5").attr({title: "SLA menor que 15 minutos!!!"})
	});
	$("#slaid_6.6").ready(function() {
		$("#slaid_6.6").attr({title: "SLA estourado!!!"})
	});

	//Title Personalizado 
//	var monitoracao = ${countPainelMon}
//	var solicitacao = ${countPainelSol}
//	var incidente = ${countPainelInc}
	
//	 $(document).ready(function() {
//	        document.title = ' (Inc ' + incidente + ')' + '(Sol ' + solicitacao + ')' + '(Mon ' + monitoracao + ')' + ' Chamados Algar';
//	    });
	 
	 //Alerta Dias Ordem de Serviço
	 
	 $("#sladias_0.0").ready(function() {
		$("#sladias_0.0").attr({title: "Status da Ordem de Serviço atualizado HOJE!!"})
	 });
	 $("#sladias_1.1").ready(function() {
			$("#sladias_1.1").attr({title: "Status da Ordem de Serviço foi atualizado ONTEM!!"})
		 });
	 $("#sladias_2.2").ready(function() {
			$("#sladias_2.2").attr({title: "Status da Ordem de Serviço foi atualizado a 2 dias!!"})
		 });
	 $("#sladias_3.3").ready(function() {
			$("#sladias_3.3").attr({title: "Status da Ordem de Serviço foi atualizado a 3 dias!!"})
		 });
	 $("#sladias_4.4").ready(function() {
			$("#sladias_4.4").attr({title: "Status da Ordem de Serviço foi atualizado a 4 dias!!"})
		 });
	 $("#sladias_5.5").ready(function() {
			$("#sladias_5.5").attr({title: "Status da Ordem de Serviço foi atualizado a 5 dias!!"})
		 });
	 $("#sladias_6.6").ready(function() {
			$("#sladias_6.6").attr({title: "Status da Ordem de Serviço foi atualizado a 6 dias!!"})
		 });
	 $("#sladias_7.7").ready(function() {
			$("#sladias_7.7").attr({title: "Status da Ordem de Serviço foi atualizado a 7 dias!!"})
		 });
	 $("#sladias_8.8").ready(function() {
			$("#sladias_8.8").attr({title: "Status da Ordem de Serviço foi atualizado a 8 dias!!"})
		 });
	 $("#sladias_9.9").ready(function() {
			$("#sladias_9.9").attr({title: "Status da Ordem de Serviço foi atualizado a 9 dias!!"})
		 });
	

	 
	 
</script>

<!-- smooth scrolling script -->
<script>
		$(function () {
		
		$('a[href^="#"]').click(function(event) {
			var id = $(this).attr("href");
			var offset = 20;
			var target = $(id).offset().top - offset;
		
		$('html, body').animate({scrollTop:target}, 2000);
			event.preventDefault();
		});
		
		});
</script>
<!-- end smooth scrolling script -->

</body>
</html>