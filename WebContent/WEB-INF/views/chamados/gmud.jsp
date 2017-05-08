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
        <div class="panel-heading"><h3 id="chamados"><center><strong>GMUD's</strong></center></h3></div>
	<div class="panel-body">
	<c:if test="${!empty chamadosRDMGeralExecucao }">
		<div class="row">
			<div class="col-md-offset-3 col-md-6">
			   <div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_rdm_execucao">
						<strong>Em execução</strong>
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
								<c:forEach items="${chamadosRDMGeralExecucao}" var="chamadosRDMGeralExecucao">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=chg+SKIPLIST=1+QBE.EQ.id=${chamadosRDMGeralExecucao.id}" target="_blank" >${chamadosRDMGeralExecucao.mudanca}</a></td>
										<td>${chamadosRDMGeralExecucao.responsavel}</td>
										<td>${chamadosRDMGeralExecucao.resumo}</td>
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
						<strong>Em planejamento</strong>
					 </a>
					<c:if test="${empty chamadosRDMGeralPlanejamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosRDMGeralPlanejamento}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_planejamento">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosRDMGeralPlanejamento}" var="chamadosRDMGeralPlanejamento">	
									<tr>

										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=chg+SKIPLIST=1+QBE.EQ.id=${chamadosRDMGeralPlanejamento.id}" target="_blank" >${chamadosRDMGeralPlanejamento.mudanca}</a>
										<c:if test="${chamadosRDMGeralPlanejamento.ccm == 1}">
											 <span class="glyphicon glyphicon-copyright-mark"></span>
										</c:if>											 
										</td>
										
										<td>${chamadosRDMGeralPlanejamento.responsavel}</td>
										<td>${chamadosRDMGeralPlanejamento.resumo}</td>
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
						<strong>Aguardando aprovação </strong>
					 </a>
					 					<c:if test="${empty chamadosRDMGeralValidacao}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosRDMGeralValidacao}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_validacao">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosRDMGeralValidacao}" var="chamadosRDMGeralValidacao">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=chg+SKIPLIST=1+QBE.EQ.id=${chamadosRDMGeralValidacao.id}" target="_blank" >${chamadosRDMGeralValidacao.mudanca}</a>
										<c:if test="${chamadosRDMGeralValidacao.ccm == 1}">
											 <span class="glyphicon glyphicon-copyright-mark"></span>
										</c:if>											 
										</td>
										<td>${chamadosRDMGeralValidacao.responsavel}</td>
										<td>${chamadosRDMGeralValidacao.resumo}</td>
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
						<strong>Aprovadas</strong>
					 </a>
					 					<c:if test="${empty chamadosRDMGeralAprovada}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosRDMGeralAprovada}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_aprovada">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Agendamento</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosRDMGeralAprovada}" var="chamadosRDMGeralAprovada">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=chg+SKIPLIST=1+QBE.EQ.id=${chamadosRDMGeralAprovada.id}" target="_blank" >${chamadosRDMGeralAprovada.mudanca}</a>
										</td>
										<td>${chamadosRDMGeralAprovada.responsavel}</td>
										<td>${chamadosRDMGeralAprovada.resumo}</td>
										<td>${chamadosRDMGeralAprovada.agendamento}</td>
										
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
						<strong>Finalizadas</strong>
					 </a>
					 					<c:if test="${empty chamadosRDMGeralOutros}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosRDMGeralOutros}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_rdm_finalizada">
									<td><center><strong>Número</strong></center></td>
									<td><center><strong>Responsável</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>Agendamento</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosRDMGeralOutros}" var="chamadosRDMGeralOutros">	
									<tr>
										<td><a href="http://sacsti/CAisd/pdmweb.exe?OP=SEARCH+FACTORY=chg+SKIPLIST=1+QBE.EQ.id=${chamadosRDMGeralOutros.id}" target="_blank" >${chamadosRDMGeralOutros.mudanca}</a>
										<td>${chamadosRDMGeralOutros.responsavel}</td>
										<td>${chamadosRDMGeralOutros.resumo}</td>
										<td>${chamadosRDMGeralOutros.agendamento}</td>
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