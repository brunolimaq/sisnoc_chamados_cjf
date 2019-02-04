<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de URLs das Aplicações </title>

  
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
        <div class="panel-heading"><h3 id="chamados"><center><strong>Status das URLs</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-4">
			   <div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_incidente_titulo">
						<strong>URLs Produção</strong>
					 </a>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_incidente">
									<td><center><strong>URL</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${UrlsProducao}" var="UrlsProducao">	
									<tr>
										<td><a href="${UrlsProducao.url}"  target="_blank" >${UrlsProducao.url}</td>
										<td class="${UrlsProducao.resultado}">${UrlsProducao.resultado}</td>
										
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
                   </div>

			</div>
			
			
			<div class="col-md-4">
			   <div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>URLs Homologação</strong>
					 </a>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>URL</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${UrlsHomolocacao}" var="UrlsHomolocacao">	
									<tr >
										<td><a href="${UrlsHomolocacao.url}"  target="_blank" >${UrlsHomolocacao.url}</td>
										<td class="${UrlsHomolocacao.resultado}">${UrlsHomolocacao.resultado}</td>
										
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
                   </div>
			
			
			
			</div>
			
			<div class="col-md-4">
			   <div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>URLs Desenvolvimento</strong>
					 </a>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>URL</strong></center></td>
									<td><center><strong>Status</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${UrlsDesenvolvimento}" var="UrlsDesenvolvimento">	
									<tr>
										<td><a href="${UrlsDesenvolvimento.url}"  target="_blank" >${UrlsDesenvolvimento.url}</td>
										<td class="${UrlsDesenvolvimento.resultado}">${UrlsDesenvolvimento.resultado}</td>
										
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