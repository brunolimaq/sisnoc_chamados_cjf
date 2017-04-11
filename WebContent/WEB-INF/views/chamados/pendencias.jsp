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
        <div class="panel-heading"><h3 id="chamados"><center><strong>Minhas Requisições Pendentes</strong></center></h3></div>
	<div class="panel-body">
		<div class="row">

				<div class="col-md-6">
               	<div class="list-group ">
                   	<a href="#chamados" class="list-group-item active" id="painel_chamados_titulo">
						<strong>Chamados </strong>
					 </a>
					<c:if test="${empty chamadosPainelPessoalPendencias}">
						<div class="alert alert-success" role="alert"><strong>Nenhuma ocorrência nesta fila!</strong></div>

  					</c:if>
  					<c:if test="${!empty chamadosPainelPessoalPendencias}">
					 
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="painel_chamados">
									<td><center><strong>Categoria</strong></center></td>
									<td><center><strong>Chamado</strong></center></td>
									<td><center><strong>Descrição</strong></center></td>
									<td><center><strong>SLA</strong></center></td>
									<td><center><strong>Meta</strong></center></td>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chamadosPainelPessoalPendencias}" var="chamadosPainelPessoalPendencias">	
									<tr  class="${chamadosPainelPessoalPendencias.alerta}">
										<td>${chamadosPainelPessoalPendencias.tipoLegivel}</td>
										<td>${chamadosPainelPessoalPendencias.chamado}</td>
										<td>${chamadosPainelPessoalPendencias.titulo}</td>
										<td>${chamadosPainelPessoalPendencias.sla}
										
										<c:if test="${chamadosPainelPessoalPendencias.tipoLegivel == 'Incidente'}">	
										<td  width="15%" height="70%" style="padding:3px" >
										<div class="progress" style="height:30px" align="center">
										  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
										    <span >15m</span>
										  </div>
										  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
										    <span>45m</span>
										  </div>
										  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
										    <span>1h</span>
										  </div>
										</div>
										</td>
										</c:if>
										
										<c:if test="${chamadosPainelPessoalPendencias.tipoLegivel == 'Chamado'}">
										
											<c:if test="${chamadosPainelPessoalPendencias.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
													<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >15m</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>30m</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>1h</span>
													  </div>
													</div>
													</td>
												</c:if>
												<c:if test="${chamadosPainelPessoalPendencias.grupo == 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
													<td  width="15%" height="70%" style="padding:3px" >
													<div class="progress" style="height:30px" align="center">
													  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
													    <span >45m</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
													    <span>1h30m</span>
													  </div>
													  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
													    <span>2h</span>
													  </div>
													</div>
													</td>
												</c:if>
												<c:if test="${chamadosPainelPessoalPendencias.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção comum'}">	
													<c:if test="${chamadosPainelPessoalPendencias.grupo != 'INFRA.Solicitação.Aplicação.Deploy de Aplicação.Manutenção corretiva'}">	
														<td  width="15%" height="70%" style="padding:3px" >
														<div class="progress" style="height:30px" align="center">
														  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_2}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 2 horas">
														    <span >2h</span>
														  </div>
														  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_6}" style="width: 33%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 6 horas">
														    <span>4h</span>
														  </div>
														  <div class="progress-bar ${chamadosPainelPessoalPendencias.meta_24}" style="width: 34%" class="sr-only" data-toggle="tooltip" data-placement="bottom" title="Meta de 24 horas">
														    <span>6h</span>
														  </div>
														</div>
														</td>
													</c:if>
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
						<strong>Índices </strong>
						Em construção..
					 </a>
				
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