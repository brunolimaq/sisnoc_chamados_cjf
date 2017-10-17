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
        <div class="panel-heading"><h3 id="chamados"><center><strong>Chamados VIP's</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-6">
			   <div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Em andamento/Com Pendências</strong>
					 </a>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Categoria</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>Afetado</strong></center></td>
									<td><center><strong>Relator</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaVipNaoEncerrados}" var="listaVipNaoEncerrados">	
									<tr>
										<td>${listaVipNaoEncerrados.chamado}</td>
										<td>${listaVipNaoEncerrados.equipe}</td>
										<td>${listaVipNaoEncerrados.descricao}</td>
										<td>${listaVipNaoEncerrados.categoria}</td>
										<td>${listaVipNaoEncerrados.statusDescricao}</td>
										<td>${listaVipNaoEncerrados.afetado}</td>
										<td>${listaVipNaoEncerrados.relator}</td>
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
                   </div>
			
			
			
			</div>
			
			
			<div class="col-md-6">
			   <div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_rdm_execucao">
						<strong>Últimos 30 - Encerrados</strong>
					 </a>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_execucao">
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Equipe</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Categoria</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									<td><center><strong>Afetado</strong></center></td>
									<td><center><strong>Relator</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaVipEncerrados}" var="listaVipEncerrados">	
									<tr>
										<td>${listaVipEncerrados.chamado}</td>
										<td>${listaVipEncerrados.equipe}</td>
										<td>${listaVipEncerrados.descricao}</td>
										<td>${listaVipEncerrados.categoria}</td>
										<td>${listaVipEncerrados.statusDescricao}</td>
										<td>${listaVipEncerrados.afetado}</td>
										<td>${listaVipEncerrados.relator}</td>
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
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