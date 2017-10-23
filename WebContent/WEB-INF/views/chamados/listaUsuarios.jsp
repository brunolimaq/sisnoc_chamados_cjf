<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>

<html>
	
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>

  <title>SISNOC</title>

	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/index.css">
	<link rel="stylesheet" type="text/css" href="resources/css/sisnoc.css" />
	<link rel="stylesheet" type="text/css" href="resources/icons/fontawesome/css/font-awesome.min.css" />
	
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-more.js"></script>
	<script src="https://code.highcharts.com/modules/solid-gauge.js"></script>
	

	
	

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  

  
</head>

<body>

<c:import url="menu.jsp"></c:import>
	
<div class="panel panel-primary">
	<div class="panel-body">
	

	<div class="row">
			  <div class="col-md-offset-2 col-md-8">
		   <div class="list-group ">
		   		<c:if test="${!empty mensagem}">
				<div class="alert alert-danger" role="alert">${mensagem}</div>
				</c:if>
				<c:if test="${!empty mensagemSucesso}">
					<div class="alert alert-success" role="alert">${mensagemSucesso}</div>
				</c:if>
			
                   	<a href="#chamados" class="list-group-item active" id="painel_noc_titulo">
						<strong>Lista de Usuarios </strong>
					 </a>
						<table class="table table-bordered table-hover ">
							<thead>
								<tr class="painel_noc">
									<td><center><strong>ID</strong></center></td>
									<td><center><strong>Nome Usuario</strong></center></td>
									<td><center><strong>Login</strong></center></td>
									<td><center><strong>E-mail</strong></center></td>
									<td><center><strong>Opções</strong></center></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaUsuarios}" var="listaUsuarios">	
									<tr>
										<td>${listaUsuarios.id}</td>
										<td>${listaUsuarios.nome}</td>
										<td>${listaUsuarios.login}</td>
										<td>${listaUsuarios.email}</td>
									    <td><a href="/chamados/visualizarUsuario?id=${listaUsuarios.id}" target="_blank" ><i class="fa fa-search fa-2x" aria-hidden="true"> </i></a>  
									        <a href="/chamados/editarUsuario?id=${listaUsuarios.id}" target="_blank" ><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"> </i></a> 
									        <a href="/chamados/excluirUsuario?id=${listaUsuarios.id}" target="_blank" ><i class="fa fa-times fa-2x text-danger" aria-hidden="true"> </i></a>
									   </td>	
									</tr>
									
								</c:forEach>
							</tbody>    
						</table>
                   </div>
			</div>
		</div>
	</div>
</div> 
<jsp:include page="footer.jsp"></jsp:include>

	<script src="resources/js/jquery-2.2.4.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/main.js"></script> 

</body>
</html>
