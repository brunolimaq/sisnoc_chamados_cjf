<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:useBean id="chamadosteste" class="br.com.sisnoc.chamados.modelo.Chamado"/>

<!DOCTYPE html>

<html>
	
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>

  <title>SISNOC</title>

	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/index.css">
	<link rel="stylesheet" type="text/css" href="resources/css/sisnoc.css" />
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-more.js"></script>
	<script src="https://code.highcharts.com/modules/solid-gauge.js"></script>
	

	
	

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  
  <script type="text/javascript">
  //Metas de 2 e 4 horas

  $( document ).ready(function() {
	  

	  
	
		var gaugeSLA2 = {
			
			    chart: {
			        type: 'solidgauge',
                    renderTo: 'gauge-sla2'

			    },
			
			    title: null,
			
			    pane: {
			        center: ['50%', '85%'],
			        size: '140%',
			        startAngle: -90,
			        endAngle: 90,
			        background: {
			            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
			            innerRadius: '60%',
			            outerRadius: '100%',
			            shape: 'arc'
			        }
			    },
			
			    tooltip: {
			        enabled: false

			    },
			
			    // the value axis
			    yAxis: {
			        stops: [
			            [0.70, '#DF5353'], // red
			            [0.74, '#DF5353'], // red
			            [0.75, '#DDDF0D'], // yellow
			            [0.85, '#DDDF0D'], // yellow
			            [0.86, '#55BF3B'] // green
			        ],
			        lineWidth: 0,
			        minorTickInterval: null,
			        tickAmount: 2,
			        title: {
			            y: -50,
			            text: 'Meta 2 horas'
			        },
			        labels: {
			            y: 16
			        },
			        min: 0,
			        max: 100,

			    },
			
			    plotOptions: {
			        solidgauge: {
			            dataLabels: {
			                y: 5,
			                borderWidth: 0,
			                useHTML: true
			            }
			        }
			    },
			    credits: {
			        enabled: false
			    },
			
			    series: [{}]
			};
			
var gaugeSLA4 = {
		
	    chart: {
	        type: 'solidgauge',
            renderTo: 'gauge-sla4'

	    },
	
	    title: null,
	
	    pane: {
	        center: ['50%', '85%'],
	        size: '140%',
	        startAngle: -90,
	        endAngle: 90,
	        background: {
	            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
	            innerRadius: '60%',
	            outerRadius: '100%',
	            shape: 'arc'
	        }
	    },
	
	    tooltip: {
	        enabled: false

	    },
	
	    // the value axis
	    yAxis: {
	        stops: [
	            [0.8, '#DF5353'], // red
	            [0.85, '#DDDF0D'], // yellow
	            [0.9, '#55BF3B'] // green
	        ],
	        lineWidth: 0,
	        minorTickInterval: null,
	        tickAmount: 2,
	        title: {
	            y: -50,
	            text: 'Meta 4 horas'
	        },
	        labels: {
	            y: 16
	        },
	        min: 0,
	        max: 100,

	    },
	
	    plotOptions: {
	        solidgauge: {
	            dataLabels: {
	                y: 5,
	                borderWidth: 0,
	                useHTML: true
	            }
	        }
	    },
	    credits: {
	        enabled: false
	    },
	
	    series: [{}]
	};



	$.getJSON($("#input-urlRaiz").val()+"graficos/metasIndividual", function(data) {
	  
		gaugeSLA2.series = [{
			  data: [data.dados.meta2],
		      dataLabels: {
		          format: '<div style="text-align:center"><span style="font-size:18px;color:' +
		              ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
		                 '<span style="font-size:9px;color:silver">% mês</span></div>'
		      }
		
			}];
 		    var chart = new Highcharts.Chart(gaugeSLA2);


 		   gaugeSLA4.series = [{
 			  data: [data.dados.meta4],
 		      dataLabels: {
 		          format: '<div style="text-align:center"><span style="font-size:18px;color:' +
 		              ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
 		                 '<span style="font-size:9px;color:silver">% mês</span></div>'
 		      }

 			}];
 			    var chart = new Highcharts.Chart(gaugeSLA4);
 			    
 		   var violados = data.dados.violados;
 		   $("#violados").text(violados);
 		   var requisicoesMes = data.dados.requisicoesMes;
 		   $("#requisicoesMes").text(requisicoesMes);
 		   var reabertosMes = data.dados.reabertosMes;
		   $("#reabertosMes").text(reabertosMes);
 		   var pendencias = data.dados.pendencias;
		   $("#pendencias").text(pendencias);


		});
});
  
  

  $( document ).ready(function() {   
	 


  	Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
  	    return {
  	        radialGradient: {
  	            cx: 0.5,
  	            cy: 0.3,
  	            r: 0.7
  	        },
  	        stops: [
  	            [0, color],
  	            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
  	        ]
  	    };
  	});

  	// Build the chart
  	var graficoPizza = {
  	    chart: {
              renderTo: 'graficoPizza',
  	        plotBackgroundColor: null,
  	        plotBorderWidth: null,
  	        plotShadow: false,
  	        type: 'pie'
  	    },
  	    tooltip: {
  	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
  	    },
  	    title: { 
  	    text: '.'},
  	    plotOptions: {
  	        pie: {
  	            allowPointSelect: true,
  	            cursor: 'pointer',
  	            dataLabels: {
  	                enabled: true,
  	                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
  	                style: {
  	                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
  	                },
  	                connectorColor: 'silver'
  	            }
  	        }
  	    },
  	    credits: {
  	        enabled: false
  	    },
  	    series: [{ }]
  	};

 
  	$.getJSON($("#input-urlRaiz").val()+"graficos/metasIndividual", function(data) {
  		 
  		var chamados = data.dados.chamadosMes.pop();
  		var incidentes = data.dados.incidentesMes.pop();

  		
  		graficoPizza.series = [{
  	        name: 'Brands',
  	        data: [
  				{
  				    name: 'Chamados',
  				    y: chamados,
  				    sliced: true,
  				    selected: true
  				},
  	            {name: 'Incidentes', y: incidentes, color: 'red'}

  	        ]
  	    }];
  			   var chart = new Highcharts.Chart(graficoPizza);
  		});


  	

   }); 
  
  </script>
  <script type="text/javascript"> 


          function timedRefresh(timeoutPeriod) {
             setTimeout("location.reload(true);", timeoutPeriod);
         }
          jQuery(document).ready(function () {
              timedRefresh(15000);
         });
       
  </script> 
  
  

<!--   <script>  
  
   $( document ).ready(function play() {   

	   audio = document.getElementById('audio'); 	  	 
	   audio.play();

	   });
  
  
  </script> -->
  
  
</head>

<body>

<c:import url="menu.jsp"></c:import>
	
<div class="panel panel-primary">
	<div class="panel-body">
	
	<div class="row">
			  <div class="col-md-6">
				<div class="list-group ">
					<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Meus chamados em andamento</strong>
					</a>
					<c:if test="${empty chamadosPainelPessoal}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPainelPessoal}">
  					
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Categoria</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>META</strong></center></td>
								</tr>
							</thead>
							<tbody>
		  						                   
							<c:forEach items="${chamadosPainelPessoal}" var="chamadosPainelPessoal">
										
									<tr class="${chamadosPainelPessoal.alerta}" >
									<c:if test="${chamadosPainelPessoal.tipoLegivel == 'Incidente'}">	
										<td id="painel_incidente_titulo">${chamadosPainelPessoal.tipoLegivel}</td>
									<audio id="audio">
   										<source src="resources/audio/alertaIC.mp3" type="audio/mp3" />
									</audio>	
										
									</c:if>
									<c:if test="${chamadosPainelPessoal.tipoLegivel == 'Chamado'}">
										<td>${chamadosPainelPessoal.tipoLegivel}</td>
									</c:if>		
											
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPainelPessoal.id}" target="_blank" >${chamadosPainelPessoal.chamado}</a></td>
										<td>${chamadosPainelPessoal.titulo}</td>
										
										<c:if test="${chamadosPainelPessoal.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosPainelPessoal.flagFilho == 1}">
												<td><img src="resources/images/filho.png" id="logo"></img> atendido</td>
											</c:if>	
										</c:if>	
										
											<c:if test="${chamadosPainelPessoal.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosPainelPessoal.flagFilho == 0}">
												<td>${chamadosPainelPessoal.statusDescricao}</td>
											</c:if>	
										</c:if>						
													 
										
										<c:if test="${chamadosPainelPessoal.statusDescricao != 'Aberto chamado filho'}">
											<td>${chamadosPainelPessoal.statusDescricao}</td>
										</c:if>
										
										<td>${chamadosPainelPessoal.sla}</td>
										
										<c:if test="${chamadosPainelPessoal.tipoLegivel == 'Incidente'}">	
										<td  width="15%" height="70%" style="padding:3px" >
										<div class="progress" style="height:30px" align="center">
										  <div class="progress-bar ${chamadosPainelPessoal.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
										    <span >15m</span>
										  </div>
										  <div class="progress-bar ${chamadosPainelPessoal.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
										    <span>45m</span>
										  </div>
										  <div class="progress-bar ${chamadosPainelPessoal.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
										    <span>1h</span>
										  </div>
										</div>
										</td>
										</c:if>
										<c:if test="${chamadosPainelPessoal.tipoLegivel == 'Chamado'}">
										
																
										<c:if test="${chamadosPainelPessoal.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosPainelPessoal.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >15m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelPessoal.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>30m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelPessoal.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>1h</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosPainelPessoal.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosPainelPessoal.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >45m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelPessoal.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>1h30m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelPessoal.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>2h</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosPainelPessoal.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<c:if test="${chamadosPainelPessoal.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
													<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosPainelPessoal.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >2h</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelPessoal.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>4h</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelPessoal.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>6h</span>
													  </div>
													</div>
													</td>
												</c:if>
											</c:if>
											
											</c:if>
									</tr>
								
							</c:forEach>
						</tbody>    
					</table>
				</c:if>
		  		
				</div>
				</div>
               <div class="col-md-6">
               	<div class="list-group">
                   	<a href="#chamados" class="list-group-item active "  id="painel_chamados_titulo">
						<strong>Meus índices</strong>
					 </a>
						<div class="row">
					  		<br/>
					  		<div class="col-md-6">
								<div id="graficoPizza" style="min-width: 300px; height: 300px; max-width: 450px; "></div>
							</div>
					  		
					 		<div class="col-sm-3">
								 <div class="panel panel-default" >
								  <div class="panel-heading">Violados</div>
								  <div class="panel-body paineis_indices" >
								    <label class="letras" id="violados">0</label>
								  </div>
								</div>
								<div class="panel panel-default">
								  <div class="panel-heading">Pendências</div>
								  <div class="panel-body paineis_indices">
								    <label class="letras" id="pendencias">0</label>
								  </div>
								</div>
						 		<div id="gauge-sla2" style="width: 200px; height: 134px;" ></div>
							</div>
							<div class="col-sm-3">
								 <div class="panel panel-default">
								  <div class="panel-heading">Requisições Mês</div>
								  <div class="panel-body paineis_indices">
								    <label class="letras" id="requisicoesMes">0</label>
								  </div>
								</div>
								<div class="panel panel-default">
								  <div class="panel-heading">Chamados Reaberto</div>
								  <div class="panel-body paineis_indices">
								    <label class="letras" id="reabertosMes">0</label>
								  </div>
								</div>
								<div id="gauge-sla4" style="width: 200px; height: 134px;"></div>
							</div>
							
					 
<!-- 						 	<div class="col-md-offset-6 col-md-3"> -->
<!-- 						 		<div id="gauge-sla2" style="width: 300px; height: 134px;" ></div> -->
<!-- 					 	 	</div> -->
<!-- 						 	<div class="col-md-3"> -->
<!-- 								<div id="gauge-sla4" style="width: 300px; height: 134px;"></div> -->
<!-- 							</div> -->
					
						 	
						 </div>
					 </div>
                   </div>
               </div> <!-- fim DIV col-md4 do Incidentes ROW -->
               
               </div>
		<div class="panel-body">
	
		<div class="row">
           	<div class="col-md-6 clearfix">
				<div class="list-group ">
					<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Chamados em andamento do meu(s) grupos</strong>
					</a>
					<c:if test="${empty chamadosPainelEquipe}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPainelEquipe}">
           	
						<table class="table table-bordered table-hover col-md-3">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosPainelEquipe}" var="chamadosPainelEquipe">
									
										<tr  class="${chamadosPainelEquipe.alerta}">
										<td>${chamadosPainelEquipe.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPainelEquipe.id}" target="_blank" >${chamadosPainelEquipe.chamado}</a></td>
										<td>${chamadosPainelEquipe.titulo}</td>
										<td>${chamadosPainelEquipe.statusDescricao}</td>
										<td>${chamadosPainelEquipe.sla}
										</td>
										
										<c:if test="${chamadosPainelEquipe.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosPainelEquipe.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >15m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelEquipe.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>30m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelEquipe.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>1h</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosPainelEquipe.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosPainelEquipe.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >45m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelEquipe.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>1h30m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelEquipe.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>2h</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosPainelEquipe.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<c:if test="${chamadosPainelEquipe.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
													<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosPainelEquipe.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >2h</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelEquipe.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>4h</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelEquipe.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>6h</span>
													  </div>
													</div>
													</td>
												</c:if>
											</c:if>
											
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
					</c:if>
				</div>
				<div><br></div>
				</div> <!-- fim DIV col-md4 do NOC ROW -->
				
				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>RDM's do meu(s) grupos</strong>
					 </a>
					<c:if test="${empty chamadosRDMPessoal}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosRDMPessoal}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>Agendamento</strong></center></td>
								</tr>
							</thead>
							
					
							
							<tbody>
								<c:forEach items="${chamadosRDMPessoal}" var="chamadosRDMPessoal">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=chg+SKIPLIST=1+QBE.EQ.id=${chamadosRDMPessoal.id}" target="_blank" >${chamadosRDMPessoal.mudanca}</a></td>
										<td>${chamadosRDMPessoal.responsavel}</td>
										<td>${chamadosRDMPessoal.resumo}</td>
										<c:if test="${chamadosRDMPessoal.statusDescricao == 'Em execução'}">	
											<td id="painel_rdm_execucao">${chamadosRDMPessoal.statusDescricao}</td>
										</c:if>
										<c:if test="${chamadosRDMPessoal.statusDescricao != 'Em execução'}">	
											<td>${chamadosRDMPessoal.statusDescricao}</td>
										</c:if>
										<td>${chamadosRDMPessoal.agendamento}</td>
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
					</c:if>						
                   </div>
               </div> <!-- fim DIV col-md4 do Chamados ROW -->
			</div> <!-- Fechamento ROL 01 -->
			
			
			
               
               
               
               </div><!-- fim DIV dos Paineis NOC, Chamados e Incidentes -->
           </div> <!-- fim DIV Painel Geral -->
<jsp:include page="footer.jsp"></jsp:include>

	<script src="resources/js/jquery-2.2.4.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/main.js"></script> 

</body>
</html>
