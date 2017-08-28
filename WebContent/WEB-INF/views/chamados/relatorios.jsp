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
        $("#listaTipo").hide();
        $("#responsavel").hide();
    }
    
    if($('#opcao').val() == 'rel_chamados'){

    	$("#listaUsuarios").hide();
        $("#listaEquipes").hide();
        $("#listaPor").hide();
        $("#listaMes").show();
        $("#listaAno").show();
        $("#listaTipo").show();
        $("#responsavel").hide();
     }
    
    if($('#opcao').val() == 'vol_requisicoes'){

    	$("#listaUsuarios").hide();
        $("#listaEquipes").hide();
        $("#listaPor").hide();
        $("#listaMes").hide();
        $("#listaAno").hide();
        $("#listaTipo").hide();
        $("#responsavel").hide();
     }
    
    if($('#opcao').val() == 'rel_Aberto7Dias'){

    	$("#listaUsuarios").hide();
        $("#listaEquipes").hide();
        $("#listaPor").hide();
        $("#listaMes").hide();
        $("#listaAno").hide();
        $("#listaTipo").hide();
        $("#responsavel").show();
        
     }
    
    if($('#opcao').val() == 'rel_satisfacao'){


    	$("#listaUsuarios").hide();
        $("#listaEquipes").hide();
        $("#listaPor").hide();
        $("#listaMes").show();
        $("#listaAno").show();
        $("#listaTipo").hide();
        $("#responsavel").hide();
        
     }
    
}
       
 </script>  	 

</head>
<body>





<c:import url="menu.jsp"></c:import>









				<div class="panel panel-primary">
      			  <div class="panel-heading"><h3 id="chamados"><center><strong>Relatórios</strong></center></h3></div>
					<div class="panel-body">

              
             <div class="row">

				
					<div class="col-md-12">
					
					
					
						<form class="form-horizontal" action="/chamados/relatoriosDinamicos/" method="GET" target="_blank">
							<br/>
							<br/>
							
						<div class="form-group">
						    <div class="col-md-4">
						    <label for="relatorios">Relatório:</label>
								<select class="form-control" name="opcao" id="opcao" onchange="alteraDiv()" >
								  <option id="rel_reabertos" value="rel_reabertos">Chamados Reabertos</option>
								  <option id="rel_chamados" value="rel_chamados">Chamados</option>
								  <option id="vol_requisicoes" value="vol_requisicoes">Volumetria - Chamados</option>
								  <option id="rel_Aberto7Dias" value="rel_Aberto7Dias">Chamados com pendências superior a uma semana</option>
								  <option id="rel_satisfacao" value="rel_satisfacao">Satisfação por equipe - Atendimentos</option>
								</select>
					    	</div>
						  </div>	
						<div class="form-group"  style="display: none;" id="listaPor" >
						    <div class="col-md-2">
   						    <label for="opcao">Relatório por:</label>
								<select class="form-control"  >
								    <option value="usuario">Usuários</option>
								    <option value="equipe">Equipes</option>
								</select>
							</div>
						</div>
						
						<div class="form-group" style="display: none;" id="listaEquipes">
						    <div class="col-md-3">
						    <label for="relatorios">Equipes:</label>
								<select class="form-control">
								  <option>Aplicação</option>
								  <option>Bando de Dados</option>
								  <option>Redes</option>
								  <option>Virtualização</option>
								  <option>O.S Windows</option>
								  <option>O.S Linux</option>
								</select>
						  </div>	 
						  </div> 
						<div class="form-group"   style="display: none;" id="responsavel" >
						    <div class="col-md-3">
						    <label for="responsavel">Responsável:</label>
								<select class="form-control"   id="responsavel" name="responsavel">
									<option value="TODOS">Todos</option>
									<option value="alex.oliveira">Alex Italo</option>
									<option value="bruno.luiz">Bruno Luiz</option>
									<option value="israel.silva">Israel Lara</option>
									<option value="weslei">Weslei Ferreira</option>
									<option value="jefferson.sousa">Jefferson de Sousa</option>
									<option value="fabiano.oliveira">Fabiano Pereira</option>
									<option value="bruno.queiroz">Bruno Lima</option>
									<option value="walison.morales">Walison dos Santos</option>
									<option value="glauber.estacio">Glauber Estacio</option>
									<option value="guilherme">Guilherme Moreira</option>
									<option value="caio.cardoso">Caio Cardoso</option>
									<option value="antonio.junior">Antonio N. Machado</option>
									<option value="jayro.roeder">Jayro Roeder</option>
									<option value="jose.alves">Jose Alessandro</option>
									<option value="lucas.cavalcante">Lucas Ernesto</option>
									<option value="renan.silva">Renan Oscar</option>
									<option value="adriana">Adriana Morais</option>
									<option value="joao.sardinha">João Victor </option>
									<option value="fernando.suzuki">Fernando Suzuki</option>
									<option value="felipe.pereira">Felipe Pereira</option>
									<option value="bento.junior">Bento Junior</option>								  
								</select>
					    	</div>
						  </div>	  
						  
						  
						  <div class="form-group"  style="display: none;" id="listaMes" >
						    <div class="col-md-2">
							    <label for="periodo">Mês:</label>
								<select class="form-control" id="mes" name="mes">
								  <option value="1">Janeiro</option>
								  <option value="2">Feveiro</option>
								  <option value="3">Março</option>
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
								
						<div class="form-group"  style="display: none;" id="listaTipo" >
						    <div class="col-md-2">
							    <label for="tipo">Tipo chamado:</label>
								<select class="form-control" id="tipo" name="tipo">
								  <option value="TODOS">Todos</option>
								  <option value="Incidente">Incidente</option>
								  <option value="Orden de Servico">Ordem de Serviço</option>
								  <option value="Chamado">Chamado</option>
								  <option value="Problema">Problema</option>
								  <option value="Tarefa Interna">Tarefa Interna</option>
								  
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
<!-- 								<strong>Relatórios Padrão</strong> -->
<!-- 							</a> -->
			             
			             
<!-- 			           				<table class="table table-bordered col-md-3"> -->
<!-- 										<thead> -->
<!-- 											<tr class="painel_noc"> -->
<%-- 												<td><center><strong>Descrição</strong></center></td> --%>
<%-- 												<td><center><strong>Por Equipe?</strong></center></td> --%>
<%-- 												<td><center><strong>Por Usuário?</strong></center></td> --%>
<%-- 												<td><center><strong>Diário?</strong></center></td> --%>
<%-- 												<td><center><strong>Semana?</strong></center></td> --%>
<%-- 												<td><center><strong>Mensal?</strong></center></td> --%>
<%-- 												<td><center><strong>Ações</strong></center></td> --%>
												
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
<!-- 													<td>Chamados do usuário por tipo de requisição</td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><a href="/chamados/relatorio_usuario_tipo"><i class="fa fa-file-pdf-o fa-2x" aria-hidden="true"></i></a></td> -->
													
<%-- 													<tr  class="${chamadosPainelEquipe.alerta}"> --%>
<!-- 													<td>Chamados das Equipes por tipo de requisição</td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><i class="fa fa-check-square-o fa-2x" aria-hidden="true"></i></td> -->
<!-- 													<td><a href="/chamados/relatorio_grupo_tipo"><i class="fa fa-file-pdf-o fa-2x" aria-hidden="true"></i></a></td> -->
													
<%-- 													<tr  class="${chamadosPainelEquipe.alerta}"> --%>
<!-- 													<td>Requisições mensais por semana</td> -->
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