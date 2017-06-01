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
        <div class="panel-heading"><h3 id="chamados"><center><strong>Pendências</strong></center></h3></div>
	<div class="panel-body">
	
		<div class="row">

		<div class="col-md-3">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_rdm_planejamento">
						<strong>Pendente informação do solicitante</strong>
					 </a>
					<c:if test="${empty chamadosPendenteSolicitante}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPendenteSolicitante}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_planejamento">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosPendenteSolicitante}" var="chamadosPendenteSolicitante">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPendenteSolicitante.id}" target="_blank" >${chamadosPendenteSolicitante.chamado}</a></td>
										<td>${chamadosPendenteSolicitante.equipe}</td>
										<td>${chamadosPendenteSolicitante.titulo}</td>
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
					</c:if>						
                   </div>
			</div>
               
               	<div class="col-md-3">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_rdm_validacao">
						<strong>Pendente de fornecedor </strong>
					 </a>
					 					<c:if test="${empty chamadosPendenteFornecedor}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPendenteFornecedor}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_validacao">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosPendenteFornecedor}" var="chamadosPendenteFornecedor">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPendenteFornecedor.id}" target="_blank" >${chamadosPendenteFornecedor.chamado}</a></td>
										<td>${chamadosPendenteFornecedor.equipe}</td>
										<td>${chamadosPendenteFornecedor.titulo}</td>
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
					</c:if>						
					 
				
                   </div>
               </div>
               
                <div class="col-md-3">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_rdm_aprovada">
						<strong>Pendente de chamado filho</strong>
					 </a>
					 					<c:if test="${empty chamadosPendenteFilho}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPendenteFilho}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_aprovada">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosPendenteFilho}" var="chamadosPendenteFilho">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPendenteFilho.id}" target="_blank" >${chamadosPendenteFilho.chamado}</a></td>
										<td>${chamadosPendenteFilho.equipe}</td>
										<td>${chamadosPendenteFilho.titulo}</td>
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
					</c:if>						
					 
				
                   </div>
               </div>  
               
               <div class="col-md-3">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_rdm_finalizada">
						<strong>Pendente janela de mudança</strong>
					 </a>
					 					<c:if test="${empty chamadosPendenteMudanca}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPendenteMudanca}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_finalizada">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosPendenteMudanca}" var="chamadosPendenteMudanca">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosPendenteMudanca.id}" target="_blank" >${chamadosPendenteMudanca.chamado}</a></td>
										<td>${chamadosPendenteMudanca.equipe}</td>
										<td>${chamadosPendenteMudanca.titulo}</td>
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
					</c:if>						
					 
				
                   </div>
               </div>  
               
               </div>
               
               <div class="row">

    	<div class="col-md-6">
               	
               	<div class="list-group ">
					<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Chamados violados </strong>
					 </a>
					 
					 	<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Categoria</strong></center></td>
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Ações</strong></center></td>
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${chamadosViolados}" var="chamadosViolados">	
									
									<c:if test="${chamadosViolados.sla == 'Violado' }">
									<tr >
									    <td>${chamadosViolados.tipoLegivel}</td>
										<td>${chamadosViolados.equipe}</td>
									    <td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosViolados.id}" target="_blank" >${chamadosViolados.chamado}</a></td>
										<td>${chamadosViolados.titulo}</td>
										<td>${chamadosViolados.statusDescricao}</td>
									</tr>
								</c:if>
								</c:forEach>
							</tbody>    
						</table>
					                   </div>
					                   </div>
					
    				<div class="col-md-6">
    				               	<div class="list-group ">
    				
					<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Chamados reabertos </strong>
					 </a>
					 
					 
					  	<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Categoria</strong></center></td>
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Ações</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosReabertos}" var="chamadosReabertos">	
								<c:if test="${chamadosReabertos.reaberto > '1' }">
								
									<tr>
										<td>${chamadosReabertos.tipoLegivel}</td>
										<td>${chamadosReabertos.equipe}</td>
									   <td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${chamadosReabertos.id}" target="_blank" >${chamadosReabertos.chamado}</a></td>
										<td>${chamadosReabertos.titulo}</td>
										<td></td>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>    
						</table>
						</div>
                   
                   
               
               
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