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
        <div class="panel-heading"><h3 id="chamados"><center><strong>Problemas</strong></center></h3></div>
	<div class="panel-body">
	<c:if test="${!empty problemasAbertos }">
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
			   <div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_rdm_execucao">
						<strong>Aberto</strong>
					 </a>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_execucao">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${problemasAbertos}" var="problemasAbertos">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${problemasAbertos.id}" target="_blank" >${problemasAbertos.chamado}</a></td>
										<td>${problemasAbertos.equipe}</td>
										<td>${problemasAbertos.titulo}</td>
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
                   </div>
			
			
			
			</div>
		</div>
		</c:if>
	
		<div class="row">

		<div class="col-md-3">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_rdm_planejamento">
						<strong>Em investigação</strong>
					 </a>
					<c:if test="${empty problemasInvestigacao}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty problemasInvestigacao}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_planejamento">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${problemasInvestigacao}" var="problemasInvestigacao">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${problemasInvestigacao.id}" target="_blank" >${problemasInvestigacao.chamado}</a></td>
										<td>${problemasInvestigacao.equipe}</td>
										<td>${problemasInvestigacao.titulo}</td>
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
						<strong>Causa identificada </strong>
					 </a>
					 					<c:if test="${empty problemasCausaIdentificada}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty problemasCausaIdentificada}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_validacao">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${problemasCausaIdentificada}" var="problemasCausaIdentificada">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${problemasCausaIdentificada.id}" target="_blank" >${problemasCausaIdentificada.chamado}</a></td>
										<td>${problemasCausaIdentificada.equipe}</td>
										<td>${problemasCausaIdentificada.titulo}</td>
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
						<strong>Solução em implatação</strong>
					 </a>
					 					<c:if test="${empty problemasSolucaoImplatacao}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty problemasSolucaoImplatacao}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_aprovada">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${problemasSolucaoImplatacao}" var="problemasSolucaoImplatacao">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${problemasSolucaoImplatacao.id}" target="_blank" >${problemasSolucaoImplatacao.chamado}</a></td>
										<td>${problemasSolucaoImplatacao.equipe}</td>
										<td>${problemasSolucaoImplatacao.titulo}</td>
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
						<strong>Em revisão</strong>
					 </a>
					 					<c:if test="${empty problemasEmRevisao}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty problemasEmRevisao}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_finalizada">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${problemasEmRevisao}" var="problemasEmRevisao">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=cr+SKIPLIST=1+QBE.EQ.id=${problemasEmRevisao.id}" target="_blank" >${problemasEmRevisao.chamado}</a></td>
										<td>${problemasEmRevisao.equipe}</td>
										<td>${problemasEmRevisao.titulo}</td>
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
					</c:if>						
					 
				
                   </div>
               </div>  
               
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