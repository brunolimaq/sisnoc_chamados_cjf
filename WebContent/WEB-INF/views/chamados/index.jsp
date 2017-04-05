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


<script src="https://code.highcharts.com/stock/highstock.js"></script>
<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
    
    <script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>

<script src="https://code.highcharts.com/modules/solid-gauge.js"></script>

    <script type="text/javascript">
   
    
    var gaugeOptions = {

    	    chart: {
    	        type: 'solidgauge'
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
    	            [0.1, '#55BF3B'], // green
    	            [0.5, '#DDDF0D'], // yellow
    	            [0.9, '#DF5353'] // red
    	        ],
    	        lineWidth: 0,
    	        minorTickInterval: null,
    	        tickAmount: 2,
    	        title: {
    	            y: -70
    	        },
    	        labels: {
    	            y: 16
    	        }
    	    },

    	    plotOptions: {
    	        solidgauge: {
    	            dataLabels: {
    	                y: 5,
    	                borderWidth: 0,
    	                useHTML: true
    	            }
    	        }
    	    }
    	};

    	// The speed gauge
    	var chartSpeed = Highcharts.chart('container-speed', Highcharts.merge(gaugeOptions, {
    	    yAxis: {
    	        min: 0,
    	        max: 200,
    	        title: {
    	            text: 'Speed'
    	        }
    	    },

    	    credits: {
    	        enabled: false
    	    },

    	    series: [{
    	        name: 'Speed',
    	        data: [80],
    	        dataLabels: {
    	            format: '<div style="text-align:center"><span style="font-size:25px;color:' +
    	                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
    	                   '<span style="font-size:12px;color:silver">km/h</span></div>'
    	        },
    	        tooltip: {
    	            valueSuffix: ' km/h'
    	        }
    	    }]

    	}));

    	// The RPM gauge
    	var chartRpm = Highcharts.chart('container-rpm', Highcharts.merge(gaugeOptions, {
    	    yAxis: {
    	        min: 0,
    	        max: 5,
    	        title: {
    	            text: 'RPM'
    	        }
    	    },

    	    series: [{
    	        name: 'RPM',
    	        data: [1],
    	        dataLabels: {
    	            format: '<div style="text-align:center"><span style="font-size:25px;color:' +
    	                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y:.1f}</span><br/>' +
    	                   '<span style="font-size:12px;color:silver">* 1000 / min</span></div>'
    	        },
    	        tooltip: {
    	            valueSuffix: ' revolutions/min'
    	        }
    	    }]

    	}));

    	// Bring life to the dials
    	setInterval(function () {
    	    // Speed
    	    var point,
    	        newVal,
    	        inc;

    	    if (chartSpeed) {
    	        point = chartSpeed.series[0].points[0];
    	        inc = Math.round((Math.random() - 0.5) * 100);
    	        newVal = point.y + inc;

    	        if (newVal < 0 || newVal > 200) {
    	            newVal = point.y - inc;
    	        }

    	        point.update(newVal);
    	    }

    	    // RPM
    	    if (chartRpm) {
    	        point = chartRpm.series[0].points[0];
    	        inc = Math.random() - 0.5;
    	        newVal = point.y + inc;

    	        if (newVal < 0 || newVal > 5) {
    	            newVal = point.y - inc;
    	        }

    	        point.update(newVal);
    	    }
    	}, 2000);
</script>

	
	

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  
</head>

<body class="aw-layout-simple-page">
	<nav class="navbar navbar-inverse navbar-static-top"> <!-- "navbar" é a barra de navegação e "inverse" deixará preta. "static-top" deixará no top. -->
		<div class="container"> <!-- Um container dentro da barra de navegação--> 
			<div class="navbar-header"> <!-- Cabeçalho da barra de navegação -->
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu">
					<span class="icon-bar"></span> <!-- Esses 3 span váo criar aquelas 3 barrinhas pra clicar em cima e expandir a barra -->
					<span class="icon-bar"></span>
					<span class="icon-bar"></span> 
				</button> <!-- Esse botão vai fazer a barra de navegação expandir qnd tiver em tela menor, foi chamado pelo id do menu lá na class="collapse navbar-collpase"-->
     			 <a class="navbar-brand" href="/chamados/listaChamados">Sisnoc Algar</a>
			</div>
			<div class="collapse navbar-collapse" id="menu"> <!-- Essa div é para ativar a responsividade da barra de navegação para quando diminui a tela de exibição -->
				<ul class="nav navbar-nav">
			          <li class="dropdown">
			          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Equipes
			          <span class="caret"></span></a>
			          <ul class="dropdown-menu">
						<li ><a href="/chamados/equipe_armazenamento">Analistas Storage <span class="badge"></span></a></li>
						<li ><a href="/chamados/equipe_app">Analistas Aplicações <span class="badge"></span></a></li>
						<li ><a href="/chamados/equipe_Bd">Analistas Banco de Dados<span class="badge"></span></a></li>
			            <li><a href="/chamados/equipe_corp">Analistas Serviços Corporativos <span class="badge"></span></a></li>
			            <li><a href="/chamados/equipe_rede">Analistas Redes <span class="badge"></span></a></li>
			            <li><a href="/chamados/equipe_monit">Analistas Monitoração <span class="badge"></span></a></li>
			            <li><a href="/chamados/equipe_bkp">Analistas Backup <span class="badge"></span></a></li>
			            <li><a href="/chamados/equipe_SO">Analistas Sistemas Operacionais <span class="badge"></span></a></li>
			            <li><a href="/chamados/equipe_virt">Analistas Virtualização <span class="badge"></span></a></li>
			            <li><a href="/chamados/equipe_doc">Documentadores <span class="badge"></span></a></li>
			            <li><a href="/chamados/monitoradores">Monitoradores <span class="badge"></span></a></li>
			            <li><a href="/chamados/supervisor">Supervisores Datacenter <span class="badge"></span></a></li>
			          </ul>
			        </li>					
       				 <li><a href="#">Problemas</a></li>
					<li><a href="#">GMUD</a></li>
					<li><a href="#">Relatórios</a></li>
					<li><a href="/chamados/pendencias">Pendências</a></li>	
				</ul>
				<ul class="nav navbar-nav navbar-right"> <!-- Via fazer  o menu a direita pra acessar minha conta. -->
					<li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <!-- Botão que vai abrir o dropdown -->
							Minha Conta
							<span class="caret"></span> <!-- Coloca a 'setinha'para baixo de quando tem uma dropdown -->
						</a>
						<div class="dropdown-menu perfil"> <!-- Vai ser as opções da dropdown -->
							<div class="col-sm-4 hidden-xs"> <!-- Esse hidden-xs quer dizer que o avatar vai ficar escondido quando for em display de celular-->
								<img class="img-responsive img-rounded" src="http://api.adorable.io/avatars/100/watchuru.png"> <!-- Isso vai criar um avatar aleatorio nessa dropdown a primeira classe vai tornar responsiva e a segunda estiliza o formato-->
								
							</div>
							<ul class="list-unstyled col-sm-8"> <!-- essa classe tira o estilo dos itens, a bolinha no caso-->
								<li><sec:authentication property="principal.username"/></li>
								<li><a href="">Alterar Perfil</a></li>
								<li><a href="/chamados/logout">Sair</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>	
	</nav>
	
	
	
	
<div class="panel panel-primary">
	<div class="panel-body">
	
	<div class="row">
			  <div class="col-md-6">
				<div class="list-group ">
					<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Meus chamados em andamento</strong>
					</a>
					<c:if test="${empty incidentesPainelNoc}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty incidentesPainelNoc}">
		
					
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>META</strong></center></td>
								</tr>
							</thead>
							<tbody>
		  						                   
							<c:forEach items="${incidentesPainelNoc}" var="incidentesPainelNoc">
								
									<tr class="${incidentesPainelNoc.alerta}" >
										<td>${incidentesPainelNoc.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${incidentesPainelNoc.id}" target="_blank" >${incidentesPainelNoc.chamado}</a></td>
										<td>${incidentesPainelNoc.titulo}</td>
										<td>${incidentesPainelNoc.sla}</td>
										<td  width="15%" height="70%" style="padding:3px" >
										<div class="progress" style="height:30px" align="center">
										  <div class="progress-bar ${incidentesPainelNoc.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
										    <span >15m</span>
										  </div>
										  <div class="progress-bar ${incidentesPainelNoc.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
										    <span>45m</span>
										  </div>
										  <div class="progress-bar ${incidentesPainelNoc.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
										    <span>1h</span>
										  </div>
										</div>
										</td>
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
<div style="width: 600px; height: 400px; margin: 0 auto">
    <div id="container-speed" style="width: 300px; height: 200px; float: left"></div>
    <div id="container-rpm" style="width: 300px; height: 200px; float: left"></div>
</div>
					
                   </div>
               </div> <!-- fim DIV col-md4 do Incidentes ROW -->
               
               </div>
	
		<div class="row">
           	<div class="col-md-6 clearfix">
				<div class="list-group ">
					<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Chamados em andamento do meu(s) grupos</strong>
					</a>
					<c:if test="${empty chamadosPainelNoc}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPainelNoc}">
           	
						<table class="table table-bordered table-hover col-md-3">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosPainelNoc}" var="chamadosPainelNoc">
									
										<tr  class="${chamadosPainelNoc.alerta}">
										<td>${chamadosPainelNoc.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPainelNoc.id}" target="_blank" >${chamadosPainelNoc.chamado}</a></td>
										<td>${chamadosPainelNoc.titulo}</td>
										<td>${chamadosPainelNoc.sla}
										</td>
										
										<c:if test="${chamadosPainelNoc.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosPainelNoc.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >15m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelNoc.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>30m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelNoc.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>1h</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosPainelNoc.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosPainelNoc.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >15m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelNoc.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>30m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelNoc.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>1</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosPainelNoc.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<c:if test="${chamadosPainelNoc.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
													<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosPainelNoc.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >2</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelNoc.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>6</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelNoc.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>24</span>
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
						<strong>GMUD's do dia</strong>
					 </a>
					<c:if test="${empty chamadosPainelChamados}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPainelChamados}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosPainelChamados}" var="chamadosPainelChamados">	
									<tr  class="${chamadosPainelChamados.alerta}">
										<td>${chamadosPainelChamados.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPainelChamados.id}" target="_blank" >${chamadosPainelChamados.chamado}</a></td>
										<td>${chamadosPainelChamados.titulo}</td>
										<td>${chamadosPainelChamados.sla}
										</td>
										
										<c:if test="${chamadosPainelChamados.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosPainelChamados.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >15m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelChamados.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>30m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelChamados.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>1h</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosPainelChamados.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosPainelChamados.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >45m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelChamados.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>1h30m</span>
												  </div>
												  <div class="progress-bar ${chamadosPainelChamados.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>2h</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosPainelChamados.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<c:if test="${chamadosPainelChamados.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
													<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosPainelChamados.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >2h</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelChamados.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>4h</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelChamados.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
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
               </div> <!-- fim DIV col-md4 do Chamados ROW -->
			</div> <!-- Fechamento ROL 01 -->
			
			
			
               
               
               
               </div><!-- fim DIV dos Paineis NOC, Chamados e Incidentes -->
           </div> <!-- fim DIV Painel Geral -->

	<script src="resources/js/jquery-2.2.4.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/main.js"></script> 

</body>
</html>
