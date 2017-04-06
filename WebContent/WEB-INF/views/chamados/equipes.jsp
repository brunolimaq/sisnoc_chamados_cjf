<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chamados ${equipe} </title>

  
  	 <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
  	 <link rel="stylesheet" type="text/css" href="resources/css/sisnoc.css" />
  	 <script src="resources/js/jquery-2.1.4.js"></script>
  	 
     
     	
 <script type="text/javascript"> 


         function timedRefresh(timeoutPeriod) {
            setTimeout("location.reload(true);", timeoutPeriod);
        }
         jQuery(document).ready(function () {
             timedRefresh(15000);
        });
       
 </script>  	 

</head>
<body>
<br>



  <nav  class="navbar navbar-inverse  navbar-fixed-top">
<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/sisnoc/listaChamados">Sisnoc Algar</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
          <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Equipes
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
			<li ><a href="/chamados/equipe_armazenamento">Analistas Storage <span class="badge"></span></a></li>
			<li ><a href="/chamados/equipe_app">Analistas Aplica��es <span class="badge"></span></a></li>
			<li ><a href="/chamados/equipe_Bd">Analistas Banco de Dados<span class="badge"></span></a></li>
            <li><a href="/chamados/equipe_corp">Analistas Servi�os Corporativos <span class="badge"></span></a></li>
            <li><a href="/chamados/equipe_rede">Analistas Redes <span class="badge"></span></a></li>
            <li><a href="/chamados/equipe_monit">Analistas Monitora��o <span class="badge"></span></a></li>
            <li><a href="/chamados/equipe_bkp">Analistas Backup <span class="badge"></span></a></li>
            <li><a href="/chamados/equipe_SO">Analistas Sistemas Operacionais <span class="badge"></span></a></li>
            <li><a href="/chamados/equipe_virt">Analistas Virtualiza��o <span class="badge"></span></a></li>
            <li><a href="/chamados/equipe_doc">Documentadores <span class="badge"></span></a></li>
            <li><a href="/chamados/monitoradores">Monitoradores <span class="badge"></span></a></li>
            <li><a href="/chamados/supervisor">Supervisores Datacenter <span class="badge"></span></a></li>
          </ul>
        </li>
        <li><a href="#">Problemas <span class="badge"></span></a></li>
        <li><a href="#">GMUD</a></li>
        <li><a href="#">Relat�rios</a></li>
        <li><a href="/chamados/pendencias">Pend�ncias</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right"> <!-- Via fazer  o menu a direita pra acessar minha conta. -->
					<li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> <!-- Bot�o que vai abrir o dropdown -->
							Minha Conta
							<span class="caret"></span> <!-- Coloca a 'setinha'para baixo de quando tem uma dropdown -->
						</a>
						<div class="dropdown-menu perfil"> <!-- Vai ser as op��es da dropdown -->
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

<br>	
<br>

<div class="panel panel-primary">
        <div class="panel-heading"><h3 id="chamados"><center><strong>Chamados em andamento - ${equipe}</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">

				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>Chamados </strong>
					 </a>
					<c:if test="${empty chamadosEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorr�ncia nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosEquipeAndamento}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descri��o</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosEquipeAndamento}" var="chamadosEquipeAndamento">	
									<tr  class="${chamadosEquipeAndamento.alerta}">
										<td>${chamadosEquipeAndamento.equipe}</td>
										<td>${chamadosEquipeAndamento.chamado}</td>
										<td>${chamadosEquipeAndamento.titulo}</td>
										<td>${chamadosEquipeAndamento.sla}
										</td>
											<c:if test="${chamadosEquipeAndamento.grupo == 'INFRA.Solicita��o.Aplica��o.Deploy de Aplica��o.Manuten��o corretiva'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosEquipeAndamento.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >15m</span>
												  </div>
												  <div class="progress-bar ${chamadosEquipeAndamento.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>30m</span>
												  </div>
												  <div class="progress-bar ${chamadosEquipeAndamento.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>1h</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosEquipeAndamento.grupo == 'INFRA.Solicita��o.Aplica��o.Deploy de Aplica��o.Manuten��o comum'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosEquipeAndamento.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >15m</span>
												  </div>
												  <div class="progress-bar ${chamadosEquipeAndamento.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>30m</span>
												  </div>
												  <div class="progress-bar ${chamadosEquipeAndamento.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>1</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosEquipeAndamento.grupo != 'INFRA.Solicita��o.Aplica��o.Deploy de Aplica��o.Manuten��o comum'}">	
												<c:if test="${chamadosEquipeAndamento.grupo != 'INFRA.Solicita��o.Aplica��o.Deploy de Aplica��o.Manuten��o corretiva'}">	
													<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosEquipeAndamento.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >2</span>
													  </div>
													  <div class="progress-bar ${chamadosEquipeAndamento.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>6</span>
													  </div>
													  <div class="progress-bar ${chamadosEquipeAndamento.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
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
               </div> <!-- fim DIV col-md6 do Chamados ROW -->
               
               				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_incidente_titulo">
						<strong>Incidentes </strong>
					 </a>
					<c:if test="${empty incidenteEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorr�ncia nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty incidenteEquipeAndamento}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_incidente">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descri��o</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${incidenteEquipeAndamento}" var="incidenteEquipeAndamento">	
									<tr  class="${incidenteEquipeAndamento.alerta}">
										<td>${incidenteEquipeAndamento.equipe}</td>
										<td>${incidenteEquipeAndamento.chamado}</td>
										<td>${incidenteEquipeAndamento.titulo}</td>
										<td>${incidenteEquipeAndamento.sla}
										</td>
										<td  width="15%" height="70%" style="padding:3px" >
										<div class="progress" style="height:30px" align="center">
										  <div class="progress-bar ${incidenteEquipeAndamento.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
										    <span >15m</span>
										  </div>
										  <div class="progress-bar ${incidenteEquipeAndamento.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
										    <span>45m</span>
										  </div>
										  <div class="progress-bar ${incidenteEquipeAndamento.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
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
               </div> <!-- fim DIV col-md4 do Chamados ROW -->
               
               
              </div> <!-- row -->
              </div><!-- painel body -->
              </div><!-- painel -->



<div class="panel panel-primary">
        <div class="panel-heading"><h3 id="chamados"><center><strong>Chamados Pend�ntes - ${equipe}</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">

				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>Chamados </strong>
					 </a>
					<c:if test="${empty chamadosEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorr�ncia nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosEquipePendente}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descri��o</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosEquipePendente}" var="chamadosEquipePendente">	
									<tr  class="${chamadosEquipePendente.alerta}">
										<td>${chamadosEquipePendente.equipe}</td>
										<td>${chamadosEquipePendente.chamado}</td>
										<td>${chamadosEquipePendente.titulo}</td>
										<td>${chamadosEquipePendente.sla}
										</td>
										
									
										<c:if test="${chamadosEquipePendente.grupo == 'INFRA.Solicita��o.Aplica��o.Deploy de Aplica��o.Manuten��o corretiva'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosEquipePendente.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >15m</span>
												  </div>
												  <div class="progress-bar ${chamadosEquipePendente.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>30m</span>
												  </div>
												  <div class="progress-bar ${chamadosEquipePendente.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>1h</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosEquipePendente.grupo == 'INFRA.Solicita��o.Aplica��o.Deploy de Aplica��o.Manuten��o comum'}">	
												<td  width="15%" height="70%" style="padding:3px" >
												<div class="progress" style="height:30px" align="center">
												  <div class="progress-bar ${chamadosEquipePendente.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
												    <span >15m</span>
												  </div>
												  <div class="progress-bar ${chamadosEquipePendente.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
												    <span>30m</span>
												  </div>
												  <div class="progress-bar ${chamadosEquipePendente.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
												    <span>1</span>
												  </div>
												</div>
												</td>
											</c:if>
											<c:if test="${chamadosEquipePendente.grupo != 'INFRA.Solicita��o.Aplica��o.Deploy de Aplica��o.Manuten��o comum'}">	
												<c:if test="${chamadosEquipePendente.grupo != 'INFRA.Solicita��o.Aplica��o.Deploy de Aplica��o.Manuten��o corretiva'}">	
													<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosEquipePendente.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >2</span>
													  </div>
													  <div class="progress-bar ${chamadosEquipePendente.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>6</span>
													  </div>
													  <div class="progress-bar ${chamadosEquipePendente.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
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
               </div> <!-- fim DIV col-md6 do Chamados ROW -->
               
               				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_incidente_titulo">
						<strong>Incidentes </strong>
					 </a>
					<c:if test="${empty incidenteEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorr�ncia nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty incidenteEquipePendente}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_incidente">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descri��o</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${incidenteEquipePendente}" var="incidenteEquipePendente">	
									<tr  class="${incidenteEquipePendente.alerta}">
										<td>${incidenteEquipePendente.equipe}</td>
										<td>${incidenteEquipePendente.chamado}</td>
										<td>${incidenteEquipePendente.titulo}</td>
										<td>${incidenteEquipePendente.sla}
										</td>
										<td  width="15%" height="70%" style="padding:3px" >
										<div class="progress" style="height:30px" align="center">
										  <div class="progress-bar ${incidenteEquipePendente.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
										    <span >15m</span>
										  </div>
										  <div class="progress-bar ${incidenteEquipePendente.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
										    <span>45m</span>
										  </div>
										  <div class="progress-bar ${incidenteEquipePendente.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
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
               </div> <!-- fim DIV col-md4 do Chamados ROW -->
               
               
              </div> <!-- row -->
              </div><!-- painel body -->
              </div><!-- painel -->

<div class="panel panel-primary">
        <div class="panel-heading"><h3 id="chamados"><center><strong>OS e Tarefas Internas em Andamento - ${equipe}</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">

				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>OS </strong>
					 </a>
					<c:if test="${empty osEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorr�ncia nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty osEquipeAndamento}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descri��o</strong></center></td>
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${osEquipeAndamento}" var="osEquipeAndamento">	
									<tr  class="${osEquipeAndamento.alerta}">
										<td>${osEquipeAndamento.equipe}</td>
										<td>${osEquipeAndamento.chamado}</td>
										<td>${osEquipeAndamento.titulo}</td>
										
											
										
									</tr>
								</c:forEach>
							</tbody>    
						</table>
					</c:if>
                   </div>
               </div> <!-- fim DIV col-md6 do Chamados ROW -->
               
               				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_incidente_titulo">
						<strong>Tarefas Internas </strong>
					 </a>
					<c:if test="${empty tarefaEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorr�ncia nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty tarefaEquipeAndamento}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_incidente">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descri��o</strong></center></td>
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${tarefaEquipeAndamento}" var="tarefaEquipeAndamento">	
									<tr  class="${tarefaEquipeAndamento.alerta}">
										<td>${tarefaEquipeAndamento.equipe}</td>
										<td>${tarefaEquipeAndamento.chamado}</td>
										<td>${tarefaEquipeAndamento.titulo}</td>
										
										
									</tr>
								</c:forEach>
							</tbody>    
						</table>
					</c:if>
                   </div>
               </div> <!-- fim DIV col-md4 do Chamados ROW -->
               
               
              </div> <!-- row -->
              </div><!-- painel body -->
              </div><!-- painel -->

<div class="panel panel-primary">
        <div class="panel-heading"><h3 id="chamados"><center><strong>OS e Tarefas Internas Pend�ntes - ${equipe}</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">

				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>OS </strong>
					 </a>
					<c:if test="${empty osEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorr�ncia nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty osEquipePendente}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descri��o</strong></center></td>
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${osEquipePendente}" var="osEquipePendente">	
									<tr  class="${osEquipePendente.alerta}">
										<td>${osEquipePendente.equipe}</td>
										<td>${osEquipePendente.chamado}</td>
										<td>${osEquipePendente.titulo}</td>
										
											
										
									</tr>
								</c:forEach>
							</tbody>    
						</table>
					</c:if>
                   </div>
               </div> <!-- fim DIV col-md6 do Chamados ROW -->
               
               				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_incidente_titulo">
						<strong>Tarefas Internas</strong>
					 </a>
					<c:if test="${empty tarefaEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorr�ncia nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty tarefaEquipePendente}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_incidente">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descri��o</strong></center></td>
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${tarefaEquipePendente}" var="tarefaEquipePendente">	
									<tr  class="${tarefaEquipePendente.alerta}">
										<td>${tarefaEquipePendente.equipe}</td>
										<td>${tarefaEquipePendente.chamado}</td>
										<td>${tarefaEquipePendente.titulo}</td>
										
										
									</tr>
								</c:forEach>
							</tbody>    
						</table>
					</c:if>
                   </div>
               </div> <!-- fim DIV col-md4 do Chamados ROW -->
               
               
              </div> <!-- row -->
              </div><!-- painel body -->
              </div><!-- painel -->
               
<script src="resources/js/jquery-2.1.4.js"></script>

<!-- smooth scrolling script -->
<script>
		$(function () {
		
		$('a[href^="#"]').click(function(event) {
			var id = $(this).attr("href");
			var offset = 20;
			var target = $(id).offset().top - offset;
		
		$('html, body').animate({scrollTop:target}, 4000);
			event.preventDefault();
		});
		
		});
</script>
<!-- end smooth scrolling script -->

<jsp:include page="footer.jsp"></jsp:include>

<script src="resources/js/jquery-2.1.4.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>