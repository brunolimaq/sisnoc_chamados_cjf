<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<sec:authentication var="principal" property="principal" />

  <nav  class="navbar navbar-inverse navbar-fixed-top">
<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/chamados/listaChamados">Sisnoc Algar</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
		 	<c:if test="${principal.usuario.gerencia == 'SAC'}">
		     	<li><a href="/chamados/pendencias">Pendências <span class="badge">${countPendencias}</span></a></li>
			</c:if>

		 	<c:if test="${principal.usuario.gerencia != 'SAC'}">


		
		 	<li><a href="/chamados/painel">Painel</a></li>
		     <li><a href="/chamados/pendencias">Pendências</a></li>
	        
	        <li><a href="/chamados/gmud">RDM</a></li>
	        <li><a href="/chamados/ordemServicos">Ordens de Serviço</a></li>
	        <li><a href="/chamados/problemas">Problemas</a></li>
	        <li><a href="/chamados/url">URLs APPs</a></li>
	       <%-- <li><a href="/chamados/vip">VIP</a></li> --%>
	        <li><a href="/chamados/relatorios">Relatorios</a></li>
 			<%--<li class="dropdown">
	          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Usuários
	          <span class="caret"></span></a>
	          <ul class="dropdown-menu">
				<li ><a href="/chamados/listaUsuarios">Listar<span class="badge"></span></a></li>
				<li ><a href="/chamados/cadastroUsuario">Cadastrar<span class="badge"></span></a></li>	
			   </ul>
	        </li>--%>
		        
	        <li class="dropdown">
	          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Equipes
	          <span class="caret"></span></a>
	          <ul class="dropdown-menu">
				<li ><a href="/chamados/equipe_armazenamento">Analistas Storage <span class="badge"></span></a></li>
				<li ><a href="/chamados/equipe_app">Analistas Aplicações <span class="badge"></span></a></li>
				<li ><a href="/chamados/equipe_Bd">Analistas Banco de Dados<span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_corp">Analistas Serviços Corporativos <span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_rede">Analistas Redes <span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_monit">Analistas Monitoração <span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_bkp">Analistas Backup <span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_SO">Analistas Sistemas Operacionais <span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_SO_LINUX">Analistas Linux <span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_SO_WIN">Analistas Windows <span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_SO_AIX">Analistas AIX <span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_virt">Analistas Virtualização <span class="badge"></span></a></li>
	            <li><a href="/chamados/equipe_doc">Documentadores <span class="badge"></span></a></li>
	            <li><a href="/chamados/monitoradores">Monitoradores <span class="badge"></span></a></li>
	            <li><a href="/chamados/supervisor">Supervisores Datacenter <span class="badge"></span></a></li>
	          </ul>
	        </li>
		
		
  		</c:if>
  	
	    
	      </ul>
   		
      
      <ul class="nav navbar-nav navbar-right"> 
					<li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							Minha Conta
							<span class="caret"></span> 
						</a>
						<div class="dropdown-menu perfil">
							<div class="col-sm-4 hidden-xs">
								<img class="img-responsive img-rounded" src="resources/images/logoalgar.png"> 
								
							</div>
							<ul class="list-unstyled col-sm-8"> 
<%-- 								<li><sec:authentication property="principal.username"/></li> --%>
								<li>${principal.username}</li>
								<li><a href="/chamados/perfil">Alterar Perfil</a></li>
								<li><a href="/chamados/logout">Sair</a></li>
							</ul>
						</div>
					</li>
		</ul>
      
    </div>
  </div>
</nav>
<br/>
<br/>
	
<input type="hidden" id="input-urlRaiz"  value="/chamados/">