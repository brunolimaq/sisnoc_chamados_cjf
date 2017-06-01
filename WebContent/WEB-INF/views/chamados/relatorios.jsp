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
	<link rel="stylesheet" type="text/css" href="resources/css/font-awesome/css/font-awesome.min.css" />

	<script src="resources/js/jquery-3.1.1.min.js"></script>
	<script src="resources/js/highcharts.js"></script>
	<script src="resources/js/highcharts-more.js"></script>
	<script src="resources/js/solid-gauge.js"></script>
	
  	 
<style type="text/css">

a {
color: red;
}

</style>
     	
 <script type="text/javascript"> 

//  $(document).ready(function(){
// 	   $("#equipe").click(function(){
// 		   if ($("#equipe").attr("checked")){
			   
// 			   $("#listaEquipes").css("display", "none");
			   
// 			}else{
// 			   $("#listaEquipes").css("display", "block");
// 			}

// 	   });
	   
	   
// 	   $("#usuario").click(function(){
// 		   if ($("#equipe").attr("checked")){
			   
// 			   $("#listaUsuarios").css("display", "none");
			   
// 			}else{
// 			   $("#listaUsuarios").css("display", "block");
// 			}

// 	   });

// 	});

alteraDiv = function (){
    if($('#opcao').val() == 'rel_reabertos'){
   
        $("#listaUsuarios").hide();
        $("#listaEquipes").hide();
        $("#listaPor").hide();
        $("#listaMes").hide();
        $("#listaAno").hide();
    }
    
    if($('#opcao').val() == 'rel_chamados'){

    	$("#listaUsuarios").hide();
        $("#listaEquipes").hide();
        $("#listaPor").hide();
        $("#listaMes").show();
        $("#listaAno").show();    
     }
    
    if($('#opcao').val() == 'vol_requisicoes'){

    	$("#listaUsuarios").hide();
        $("#listaEquipes").hide();
        $("#listaPor").hide();
        $("#listaMes").hide();
        $("#listaAno").hide();    
     }
    
}

       
 </script>  	 

</head>
<body>





<c:import url="menu.jsp"></c:import>









				<div class="panel panel-primary">
      			  <div class="panel-heading"><h3 id="chamados"><center><strong>Relat�rios</strong></center></h3></div>
					<div class="panel-body">

              
             <div class="row">

				
					<div class="col-md-12">
					
					
					
						<form class="form-horizontal" action="/chamados/relatoriosDinamicos/" method="GET" target="_blank">
							<br/>
							<br/>
							
						<div class="form-group">
						    <div class="col-md-4">
						    <label for="relatorios">Relat�rio:</label>
								<select class="form-control" name="opcao" id="opcao" onchange="alteraDiv()" >
								  <option id="rel_reabertos" value="rel_reabertos">Chamados Reabertos</option>
								  <option id="rel_chamados" value="rel_chamados">Chamados</option>
								  <option id="vol_requisicoes" value="vol_requisicoes">Volumetria - Chamados</option>
								</select>
					    	</div>
						  </div>	
						<div class="form-group"  style="display: none;" id="listaPor" >
						    <div class="col-md-2">
   						    <label for="opcao">Relat�rio por:</label>
								<select class="form-control"  >
								    <option value="usuario">Usu�rios</option>
								    <option value="equipe">Equipes</option>
								</select>
							</div>
						</div>
						
						<div class="form-group" style="display: none;" id="listaEquipes">
						    <div class="col-md-3">
						    <label for="relatorios">Equipes:</label>
								<select class="form-control">
								  <option>Aplica��o</option>
								  <option>Bando de Dados</option>
								  <option>Redes</option>
								  <option>Virtualiza��o</option>
								  <option>O.S Windows</option>
								  <option>O.S Linux</option>
								</select>
						  </div>	 
						  </div> 
						<div class="form-group"   style="display: none;" id="listaUsuarios" >
						    <div class="col-md-3">
						    <label for="relatorios">Usu�rios:</label>
								<select class="form-control" >
								  <option>Jayro Roeder</option>
								  <option>Bruno Queiroz</option>
								  <option>Guilherme Barros</option>
								  <option>Walison Morales</option>
								</select>
					    	</div>
						  </div>	  
						  
						  
						  <div class="form-group"  style="display: none;" id="listaMes" >
						    <div class="col-md-2">
							    <label for="periodo">M�s:</label>
								<select class="form-control" id="mes" name="mes">
								  <option value="1">Janeiro</option>
								  <option value="2">Feveiro</option>
								  <option value="3">Mar�o</option>
								  <option value="4">Abril</option>
								  <option value="5">Maio</option>
								  <option value="6">Junho</option>
								  <option value="7">Julho</option>
								  <option value="8">Agosto</option>								  								  
								  <option value="9">Setembro</option>
								  <option value="10">Outubro</option>
								  <option value="11">Novembro</option>
								  <option value="12">Dezembro</option>								  
								</select>
							</div>
						</div>		
						<div class="form-group"  style="display: none;" id="listaAno" >
						    <div class="col-md-2">
							    <label for="periodoAte">Ano:</label>
								<select class="form-control" id="ano" name="ano">
								  <option value="2017">2017</option>
								  <option value="2016">2016</option>
								  <option value="2015">2015</option>
								  <option value="2014">2014</option>
								</select>
							</div>
						</div>						  
										  
  
						  	  
						  <div class="form-group">
						    <div class="col-md-2">
								  <button type="submit" class="btn btn-primary">Gerar</button>
						    </div>
						  </div>
						</form>
						
						
	             	</div>
	             	
<!-- 	             	<br/> -->
<!-- 	             	<br/> -->
<!-- 	             	<div class="col-md-offset-3 col-md-6"> -->
             	
<!-- 			             <div class="list-group "> -->
<!-- 							<a href="#relatorios_fixos" class="list-group-item active" id="painel_noc_titulo"> -->
<!-- 								<strong>Relat�rios Padr�o</strong> -->
<!-- 							</a> -->
			             
			             
<!-- 			           				<table class="table table-bordered col-md-3"> -->
<!-- 										<thead> -->
<!-- 											<tr class="painel_noc"> -->
<%-- 												<td><center><strong>Descri��o</strong></center></td> --%>
<%-- 												<td><center><strong>Por Equipe?</strong></center></td> --%>
<%-- 												<td><center><strong>Por Usu�rio?</strong></center></td> --%>
<%-- 												<td><center><strong>Di�rio?</strong></center></td> --%>
<%-- 												<td><center><strong>Semana?</strong></center></td> --%>
<%-- 												<td><center><strong>Mensal?</strong></center></td> --%>
<%-- 												<td><center><strong>A��es</strong></center></td> --%>
												
<!-- 											</tr> -->
<!-- 										</thead> -->
<!-- 										<tbody> -->
												
<%-- 													<tr  class="${chamadosPainelEquipe.alerta}"> --%>
<!-- 													<td>Volumetria de chamados por equipe</td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><a href="/chamados/relatorio_volumetria"><i class="fa fa-file-pdf-o fa-2x" aria-hidden="true"></i></a></td> -->
<!-- 													</tr> -->
													
													
<%-- 													<tr  class="${chamadosPainelEquipe.alerta}"> --%>
<!-- 													<td>Chamados do usu�rio por tipo de requisi��o</td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><a href="/chamados/relatorio_usuario_tipo"><i class="fa fa-file-pdf-o fa-2x" aria-hidden="true"></i></a></td> -->
													
<%-- 													<tr  class="${chamadosPainelEquipe.alerta}"> --%>
<!-- 													<td>Chamados das Equipes por tipo de requisi��o</td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><a href="/chamados/relatorio_grupo_tipo"><i class="fa fa-file-pdf-o fa-2x" aria-hidden="true"></i></a></td> -->
													
<%-- 													<tr  class="${chamadosPainelEquipe.alerta}"> --%>
<!-- 													<td>Requisi��es mensais por semana</td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><a href="/chamados/relatorio_mensal_semanal"><i class="fa fa-file-pdf-o fa-2x" aria-hidden="true"></i></a></td> -->
<!-- 													</tr> -->
												
<!-- 										</tbody>     -->
<!-- 									</table> -->
									
						
<!-- 					</div> -->
<!-- 				</div> -->
	             		
	             	
             	</div>
            </div>
              
              </div><!-- painel body -->



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