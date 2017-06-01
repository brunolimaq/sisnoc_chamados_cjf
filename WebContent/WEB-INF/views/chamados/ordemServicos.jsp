<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chamados ${equipe} </title>

  
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/index.css">
	<link rel="stylesheet" type="text/css" href="resources/css/sisnoc.css" />
	
	<script src="resources/js/jquery-3.1.1.min.js"></script>
	<script src="resources/js/highcharts.js"></script>
	<script src="resources/js/highcharts-more.js"></script>
	<script src="resources/js/solid-gauge.js"></script>
  	 
     
     	
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



<c:import url="menu.jsp"></c:import>


<div class="panel panel-primary">
        <div class="panel-heading"><h3 id="chamados"><center><strong>Ordens de Serviços</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">

				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>Minhas Ordem de serviços em andamento </strong>
					 </a>
					<c:if test="${empty chamadosOSPessoal}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta filaaaa!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosOSPessoal}">
					 
					 	<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>Prazo</strong></center></td>
									<td><center><strong>Sem Atualização</strong></center></td>
								</tr>
							</thead>
							<tbody>
		  						                   
							<c:forEach items="${chamadosOSPessoal}" var="chamadosOSPessoal">
										
<%-- 									<tr class="${chamadosOSGeral.alerta}" > --%>

										<tr class="SemAlerta" > 

										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosOSPessoal.id}" target="_blank" >${chamadosOSPessoal.chamado}</a></td>
										<td>${chamadosOSPessoal.titulo}</td>
										
										<c:if test="${chamadosOSPessoal.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosOSPessoal.flagFilho == 1}">
												<td><img src="resources/images/filho.png" id="logo"></img> atendido</td>
											</c:if>	
										</c:if>	
										
											<c:if test="${chamadosOSPessoal.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosOSPessoal.flagFilho == 0}">
												<td>${chamadosOSPessoal.statusDescricao}</td>
											</c:if>	
										</c:if>						
													 
										
										<c:if test="${chamadosOSPessoal.statusDescricao != 'Aberto chamado filho'}">
											<td>${chamadosOSPessoal.statusDescricao}</td>
										</c:if>
										
										<td>${chamadosOSPessoal.prazo}</td>
										
																		
											
											<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosOSPessoal.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >1d</span>
													  </div>
													  <div class="progress-bar ${chamadosOSPessoal.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>2d</span>
													  </div>
													  <div class="progress-bar ${chamadosOSPessoal.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>3d</span>
													  </div>
													</div>
													</td>
									</tr>
								
							</c:forEach>
						</tbody>    
					</table>
						
					</c:if>
                   </div>
               </div> <!-- fim DIV col-md6 do Chamados ROW -->
               
               				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_chamados_titulo">
						<strong>Minhas Tarefas internas em andamento </strong>
						
					 </a>
					 					<c:if test="${empty chamadosTarefaPessoal}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosTarefaPessoal}">
					 
					 <table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>Prazo</strong></center></td>
									<td><center><strong>Sem Atualização</strong></center></td>
								</tr>
							</thead>
							<tbody>
		  						                   
							<c:forEach items="${chamadosTarefaPessoal}" var="chamadosTarefaPessoal">
										
<%-- 									<tr class="${chamadosOSGeral.alerta}" > --%>

										<tr class="SemAlerta" > 

										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosTarefaPessoal.id}" target="_blank" >${chamadosTarefaPessoal.chamado}</a></td>
										<td>${chamadosTarefaPessoal.titulo}</td>
										
										<c:if test="${chamadosTarefaPessoal.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosTarefaPessoal.flagFilho == 1}">
												<td><img src="resources/images/filho.png" id="logo"></img> atendido</td>
											</c:if>	
										</c:if>	
										
											<c:if test="${chamadosTarefaPessoal.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosTarefaPessoal.flagFilho == 0}">
												<td>${chamadosTarefaPessoal.statusDescricao}</td>
											</c:if>	
										</c:if>						
													 
										
										<c:if test="${chamadosTarefaPessoal.statusDescricao != 'Aberto chamado filho'}">
											<td>${chamadosTarefaPessoal.statusDescricao}</td>
										</c:if>
										
										<td>${chamadosTarefaPessoal.prazo}</td>
										
																		
											
											<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosTarefaPessoal.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >1d</span>
													  </div>
													  <div class="progress-bar ${chamadosTarefaPessoal.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>2d</span>
													  </div>
													  <div class="progress-bar ${chamadosTarefaPessoal.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>3d</span>
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
              
              <div class="row">
           	<div class="col-md-6 clearfix">
				<div class="list-group ">
					<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Ordem de Serviço em andamento do(s) meu(s) grupos</strong>
					</a>
					<c:if test="${empty chamadosOSGeralGrupo}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosOSGeralGrupo}">
           				
           				<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>Prazo</strong></center></td>
									<td><center><strong>Sem Atualização</strong></center></td>
								</tr>
							</thead>
							<tbody>
		  						                   
							<c:forEach items="${chamadosOSGeralGrupo}" var="chamadosOSGeralGrupo">
										
<%-- 									<tr class="${chamadosOSGeral.alerta}" > --%>

										<tr class="SemAlerta" > 

										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosOSGeralGrupo.id}" target="_blank" >${chamadosOSGeralGrupo.chamado}</a></td>
										<td>${chamadosOSGeralGrupo.responsavel}</td>
										<td>${chamadosOSGeralGrupo.titulo}</td>
										
										<c:if test="${chamadosOSGeralGrupo.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosOSGeralGrupo.flagFilho == 1}">
												<td><img src="resources/images/filho.png" id="logo"></img> atendido</td>
											</c:if>	
										</c:if>	
										
											<c:if test="${chamadosOSGeralGrupo.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosOSGeralGrupo.flagFilho == 0}">
												<td>${chamadosOSGeralGrupo.statusDescricao}</td>
											</c:if>	
										</c:if>						
													 
										
										<c:if test="${chamadosOSGeralGrupo.statusDescricao != 'Aberto chamado filho'}">
											<td>${chamadosOSGeralGrupo.statusDescricao}</td>
										</c:if>
										
										<td>${chamadosOSGeralGrupo.prazo}</td>
										
																		
											
											<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosOSGeralGrupo.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >1d</span>
													  </div>
													  <div class="progress-bar ${chamadosOSGeralGrupo.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>2d</span>
													  </div>
													  <div class="progress-bar ${chamadosOSGeralGrupo.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>3d</span>
													  </div>
													</div>
													</td>
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
                   	<a href="#chamados" class="list-group-item active" id="painel_noc_titulo"> 
						<strong>Tarefas Internas em andamento do meu(s) grupos</strong>
					 </a>
					<c:if test="${empty chamadosTarefaGeralGrupo}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosTarefaGeralGrupo}">
					 
					  <table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>Prazo</strong></center></td>
									<td><center><strong>Sem Atualização</strong></center></td>
								</tr>
							</thead>
							<tbody>
		  						                   
							<c:forEach items="${chamadosTarefaGeralGrupo}" var="chamadosTarefaGeralGrupo">
										
<%-- 									<tr class="${chamadosOSGeral.alerta}" > --%>

										<tr class="SemAlerta" > 

										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosTarefaGeralGrupo.id}" target="_blank" >${chamadosTarefaGeralGrupo.chamado}</a></td>
										<td>${chamadosTarefaGeralGrupo.responsavel}</td>
										<td>${chamadosTarefaGeralGrupo.titulo}</td>
										
										<c:if test="${chamadosTarefaGeralGrupo.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosTarefaGeralGrupo.flagFilho == 1}">
												<td><img src="resources/images/filho.png" id="logo"></img> atendido</td>
											</c:if>	
										</c:if>	
										
											<c:if test="${chamadosTarefaGeralGrupo.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosTarefaGeralGrupo.flagFilho == 0}">
												<td>${chamadosTarefaGeralGrupo.statusDescricao}</td>
											</c:if>	
										</c:if>						
													 
										
										<c:if test="${chamadosTarefaGeralGrupo.statusDescricao != 'Aberto chamado filho'}">
											<td>${chamadosTarefaGeralGrupo.statusDescricao}</td>
										</c:if>
										
										<td>${chamadosTarefaGeralGrupo.prazo}</td>
										
																		
											
											<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosTarefaGeralGrupo.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >1d</span>
													  </div>
													  <div class="progress-bar ${chamadosTarefaGeralGrupo.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>2d</span>
													  </div>
													  <div class="progress-bar ${chamadosTarefaGeralGrupo.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>3d</span>
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
			</div> <!-- Fechamento ROL 01 -->
              
				<div class="row">
              
              	<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_incidente_titulo">
						<strong>Ordem de Serviços pendentes</strong>
					 </a>
					<c:if test="${empty chamadosOSEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosOSEquipePendente}">
					 
					 <table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>Prazo</strong></center></td>
									<td><center><strong>Sem Atualização</strong></center></td>
								</tr>
							</thead>
							<tbody>
		  						                   
							<c:forEach items="${chamadosOSEquipePendente}" var="chamadosOSEquipePendente">
										
<%-- 									<tr class="${chamadosOSGeral.alerta}" > --%>

										<tr class="SemAlerta" > 

										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosOSEquipePendente.id}" target="_blank" >${chamadosOSEquipePendente.chamado}</a></td>
										<td>${chamadosOSEquipePendente.responsavel}</td>
										<td>${chamadosOSEquipePendente.titulo}</td>
										
										<td>${chamadosOSEquipePendente.statusDescricao}</td>
										<td>${chamadosOSEquipePendente.prazo}</td>
										
																		
											
											<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosOSEquipePendente.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >1d</span>
													  </div>
													  <div class="progress-bar ${chamadosOSEquipePendente.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>2d</span>
													  </div>
													  <div class="progress-bar ${chamadosOSEquipePendente.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>3d</span>
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
                             	<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_incidente_titulo">
						<strong>Tarefas Internas pendentes</strong>
					 </a>
					<c:if test="${empty chamadosTarefaEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosTarefaEquipePendente}">
					 
					 <table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>Prazo</strong></center></td>
									<td><center><strong>Sem Atualização</strong></center></td>
								</tr>
							</thead>
							<tbody>
		  						                   
							<c:forEach items="${chamadosTarefaEquipePendente}" var="chamadosTarefaEquipePendente">
										
<%-- 									<tr class="${chamadosOSGeral.alerta}" > --%>

										<tr class="SemAlerta" > 

										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosTarefaEquipePendente.id}" target="_blank" >${chamadosTarefaEquipePendente.chamado}</a></td>
										<td>${chamadosTarefaEquipePendente.responsavel}</td>
										<td>${chamadosTarefaEquipePendente.titulo}</td>
										
										<c:if test="${chamadosTarefaEquipePendente.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosTarefaEquipePendente.flagFilho == 1}">
												<td><img src="resources/images/filho.png" id="logo"></img> atendido</td>
											</c:if>	
										</c:if>	
										
											<c:if test="${chamadosTarefaEquipePendente.statusDescricao == 'Aberto chamado filho'}">
											<c:if test="${chamadosTarefaEquipePendente.flagFilho == 0}">
												<td>${chamadosTarefaEquipePendente.statusDescricao}</td>
											</c:if>	
										</c:if>						
													 
										
										<c:if test="${chamadosTarefaEquipePendente.statusDescricao != 'Aberto chamado filho'}">
											<td>${chamadosTarefaEquipePendente.statusDescricao}</td>
										</c:if>
										
										<td>${chamadosTarefaEquipePendente.prazo}</td>
										
																		
											
											<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosTarefaEquipePendente.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >1d</span>
													  </div>
													  <div class="progress-bar ${chamadosTarefaEquipePendente.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>2d</span>
													  </div>
													  <div class="progress-bar ${chamadosTarefaEquipePendente.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>3d</span>
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
               
               
               </div>
              
              
              
              
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