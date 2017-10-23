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
<div class="panel-heading"><h3 id="chamados"><center><strong>Perfil</strong></center></h3></div>
	<div class="panel-body">
	
			<form action="/chamados/cadastrarUsuario" method="GET">
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">

		
		 
		 
		  <div class="form-group">
		    <label for="usuario">Nome Completo</label>
		    <input type="text" class="form-control" id="nome"  value="${nome}" name="nome">
		  </div>
		 
	      <div class="form-group">
		    <label for="usuario">Login</label>
		    <input type="text" class="form-control" id="login"  value="${nome}" name="login">
		  </div>
		  
		  <div class="form-group">
		    <label for="usuario">E-mail</label>
		    <input type="email" class="form-control" id="email"  value="${nome}" name="email">
		  </div>
		  
		   <div class="form-group">
		    <label for="senhaUsuario">Senha</label>
		    <input type="password" class="form-control" id="senhaUsuario" placeholder="Digite uma senha" name="senhaUsuario">
		  </div>
		  <div class="form-group">
		    <label for="senhaUsuarioValidacao">Repetir senha</label>
		    <input type="password" class="form-control" id="senhaValidacao" placeholder="Digitar novamente a senha" name="senhaValidacao">
		  </div>
		 </div>
		  
		 <div class="col-md-3">
		  
		  
		  
		  <div class="form-group">
		    <label for="grupos">Equipes</label>
			<select id="grupos" multiple class="form-control" name="equipes">
			<c:forEach items="${equipes}" var="equipesList">	
			  <option value="${equipesList.value}">${equipesList.key}</option>
			 </c:forEach>
			</select>
		  </div>
		  
		  <div class="form-group">
		    <label for="grupos">Gerências</label>
			<select id="grupos" class="form-control" name="gerencia">
			<c:forEach items="${gerencia}" var="gerenciaList">	
			  <option value="${gerenciaList.value}">${gerenciaList.key}</option>
			 </c:forEach>
			</select>
		  </div>
		  
		    <div class="form-group">
		    <label for="grupos">Permissões</label>
			<select id="grupos" multiple class="form-control" name="permissoes">
			<c:forEach items="${permissoes}" var="permissoesList">	
			  <option value="${permissoesList.value}">${permissoesList.key}</option>
			 </c:forEach>
			</select>
		  </div>
		  </div>
		</div>
		  <button type="submit" class="btn btn-primary">Cadastrar</button>
		  		</form>
	</div>
</div> 
<jsp:include page="footer.jsp"></jsp:include>

	<script src="resources/js/jquery-2.2.4.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/main.js"></script> 

</body>
</html>
