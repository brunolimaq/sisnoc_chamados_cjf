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



<c:import url="menu.jsp"></c:import>


<div class="panel panel-primary">
        <div class="panel-heading"><h3 id="chamados"><center><strong>Requisições dos(as) ${equipe}</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">
		<!-- chamados -->
		<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>Chamados em andamento </strong>
					 </a>
					<c:if test="${empty chamadosEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosEquipeAndamento}">
					 
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
								<c:forEach items="${chamadosEquipeAndamento}" var="chamadosEquipeAndamento">	
									<tr  class="${chamadosEquipeAndamento.alerta}">
										<td>${chamadosEquipeAndamento.equipe}</td>
  									   <td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosEquipeAndamento.id}" target="_blank" >${chamadosEquipeAndamento.chamado}</a></td>
										<td>${chamadosEquipeAndamento.titulo}</td>
										<td>${chamadosEquipeAndamento.sla}
										</td>
											<c:if test="${chamadosEquipeAndamento.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
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
											<c:if test="${chamadosEquipeAndamento.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
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
											<c:if test="${chamadosEquipeAndamento.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<c:if test="${chamadosEquipeAndamento.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
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
                   	<a href="#chamados" class="list-group-item active" id="painel_incidente_titulo">
						<strong>Chamados Pendentes </strong>
					 </a>
					<c:if test="${empty chamadosEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosEquipePendente}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_incidente">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosEquipePendente}" var="chamadosEquipePendente">	
									<tr  class="${chamadosEquipePendente.alerta}">
										<td>${chamadosEquipePendente.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosEquipePendente.id}" target="_blank" >${chamadosEquipePendente.chamado}</a></td>
										<td>${chamadosEquipePendente.titulo}</td>
										<td>${chamadosEquipePendente.sla}
										</td>
										<c:if test="${chamadosEquipePendente.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
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
											<c:if test="${chamadosEquipePendente.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
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
											<c:if test="${chamadosEquipePendente.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
												<c:if test="${chamadosEquipePendente.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
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
               
               

		</div>


		<div class="row">
		<!-- Incidentes -->
		      	<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" "id="painel_chamados_titulo">
						<strong>Incidentes em andamento</strong>
					 </a>
					<c:if test="${empty incidenteEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty incidenteEquipeAndamento}">
					 
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
								<c:forEach items="${incidenteEquipeAndamento}" var="incidenteEquipeAndamento">	
									<tr  class="${incidenteEquipeAndamento.alerta}">
										<td>${incidenteEquipeAndamento.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${incidenteEquipeAndamento.id}" target="_blank" >${incidenteEquipeAndamento.chamado}</a></td>
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

				
               
				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_incidente_titulo">
						<strong>Incidentes Pendentes </strong>
					 </a>
					<c:if test="${empty incidenteEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty incidenteEquipePendente}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_incidente">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${incidenteEquipePendente}" var="incidenteEquipePendente">	
									<tr  class="${incidenteEquipePendente.alerta}">
										<td>${incidenteEquipePendente.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${incidenteEquipePendente.id}" target="_blank" >${incidenteEquipePendente.chamado}</a></td>
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
      </div>
      <div class="row">
               <!-- Ordem de serviços -->
				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>Ordem de Serviço em andamento </strong>
					 </a>
					<c:if test="${empty osEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty osEquipeAndamento}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${osEquipeAndamento}" var="osEquipeAndamento">	
									<tr  class="${osEquipeAndamento.alerta}">
										<td>${osEquipeAndamento.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${osEquipeAndamento.id}" target="_blank" >${osEquipeAndamento.chamado}</a></td>
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
                   	<a href="#chamados" class="list-group-item active" id="painel_incidente_titulo">
						<strong>Ordem de serviço pendentes </strong>
					 </a>
					<c:if test="${empty osEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty osEquipePendente}">
					 
						<table class="table table-bordered table-hover">
							<thead>
							<tr class="painel_incidente">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${osEquipePendente}" var="osEquipePendente">	
									<tr  class="${osEquipePendente.alerta}">
										<td>${osEquipePendente.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${osEquipePendente.id}" target="_blank" >${osEquipePendente.chamado}</a></td>
										<td>${osEquipePendente.titulo}</td>
										
											
										
									</tr>
								</c:forEach>
							</tbody>    
						</table>
					</c:if>
                   </div>
               </div> <!-- fim DIV col-md6 do Chamados ROW -->
		</div>
		               
              <div class="row">
              
               <!-- Tarefas Internas -->
               
                <div class="col-md-6">
               	<div class="list-group ">
               	
                   	<a href="#chamados" class="list-group-item active" " id="painel_chamados_titulo">
						<strong>Tarefas Internas em andamento </strong>
					 </a>
					<c:if test="${empty tarefaEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty tarefaEquipeAndamento}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${tarefaEquipeAndamento}" var="tarefaEquipeAndamento">	
									<tr  class="${tarefaEquipeAndamento.alerta}">
										<td>${tarefaEquipeAndamento.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${osEquipePendente.id}" target="_blank" >${osEquipePendente.chamado}</a></td>
										<td>${tarefaEquipeAndamento.titulo}</td>
										
										
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
					<c:if test="${empty tarefaEquipePendente}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty tarefaEquipePendente}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_incidente">
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${tarefaEquipePendente}" var="tarefaEquipePendente">	
									<tr  class="${tarefaEquipePendente.alerta}">
										<td>${tarefaEquipePendente.equipe}</td>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${tarefaEquipePendente.id}" target="_blank" >${tarefaEquipePendente.chamado}</a></td>
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