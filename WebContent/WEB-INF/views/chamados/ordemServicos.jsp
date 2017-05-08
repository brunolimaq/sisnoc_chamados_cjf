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
        <div class="panel-heading"><h3 id="chamados"><center><strong>Ordens de Serviços</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">

				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>Minhas Ordem de serviços em andamento </strong>
					 </a>
					<c:if test="${empty chamadosPainelPessoalPendencias}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPainelPessoalPendencias}">
					 
						
					</c:if>
                   </div>
               </div> <!-- fim DIV col-md6 do Chamados ROW -->
               
               				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_chamados_titulo">
						<strong>Minhas Tarefas internas em andamento </strong>
						
					 </a>
					 					<c:if test="${empty chamadosPainelPessoalPendencias}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPainelPessoalPendencias}">
					 
						
					</c:if>
					 
				
                   </div>
               </div> <!-- fim DIV col-md4 do Chamados ROW -->
               
               
              </div> <!-- row -->
              
              <div class="row">
           	<div class="col-md-6 clearfix">
				<div class="list-group ">
					<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Ordem de Serviço em andamento do meu(s) grupos</strong>
					</a>
					<c:if test="${empty chamadosPainelEquipe}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPainelEquipe}">
           	

					</c:if>
				</div>
				<div><br></div>
				</div> <!-- fim DIV col-md4 do NOC ROW -->
				
				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_noc_titulo"> 
						<strong>Tarefas Internas em andamento do meu(s) grupos</strong>
					 </a>
					<c:if test="${empty chamadosRDMPessoal}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosRDMPessoal}">
					 
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
					<c:if test="${empty incidenteEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty incidenteEquipeAndamento}">
					 

					</c:if>
                   </div>
               </div> <!-- fim DIV col-md4 do Chamados ROW -->
                             	<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" " id="painel_incidente_titulo">
						<strong>Tarefas Internas pendentes</strong>
					 </a>
					<c:if test="${empty incidenteEquipeAndamento}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty incidenteEquipeAndamento}">
					 

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