<!DOCTYPE html>
<html lang="pt" xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>

  <title>SISNOC</title>

  	<link rel="stylesheet" type="text/css" href="resources/layout/stylesheets/vendors.min.css"/>
	<link rel="stylesheet" type="text/css" href="resources/layout/stylesheets/algaworks.min.css"/>
	<link rel="stylesheet" type="text/css" href="resources/layout/stylesheets/application.css"/>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body class="aw-layout-simple-page">
  <div class="aw-layout-simple-page__container">

<form th:action="@{/login}" method="POST">
	<div class="aw-simple-panel">
<!-- 		<img alt="AlgaWorks" src="resources/images/logo-gray.png"/> -->
		<h1>SISNOC</h1>
		
		<div class="aw-simple-panel__message">
			Por favor, fa�a o login.
		</div>
		
<!-- 		<div class="aw-simple-panel__message  is-error"> -->
<!-- 			O e-mail e/ou senha n�o conferem -->
<!-- 		</div> -->
		
		<div class="aw-simple-panel__box">
			<div class="form-group  has-feedback">
				<input type="text" class="form-control  input-lg" placeholder="Seu login" autofocus="autofocus" name="username"/>
				<span class="glyphicon  glyphicon-envelope  form-control-feedback"></span>
			</div>
			
			<div class="form-group  has-feedback">
				<input type="password" class="form-control  input-lg" placeholder="Sua senha" name="password"/>
				<span class="glyphicon  glyphicon-lock  form-control-feedback" ></span>
			</div>
			<div class="form-group">
				<button type="submit" class="btn  btn-primary  btn-lg  aw-btn-full-width">Entrar</button>
			</div>
			
		</div>
	</div>
</form>

  </div>

<script th:src="resources/layout/javascripts/vendors.min.js}"></script>
<script th:src="resources/layout/javascripts/algaworks.min.js}"></script>
</body>
</html>
