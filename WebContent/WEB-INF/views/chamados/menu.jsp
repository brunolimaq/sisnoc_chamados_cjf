
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

  <nav  class="navbar navbar-inverse  navbar-fixed-top">
<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/chamados/listaChamados">Sisnoc Algar</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li><a href="/chamados/">Painel</a></li>
        <li><a href="/chamados/pendencias">Pendências</a></li>
        <li><a href="/chamados/gmud">GMUD</a></li>
<!--         <li><a href="#">Problemas <span class="badge"></span></a></li> -->
<!--         <li><a href="#">GMUD</a></li> -->
<!--         <li><a href="#">Relatórios</a></li> -->
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
            <li><a href="/chamados/equipe_virt">Analistas Virtualização <span class="badge"></span></a></li>
            <li><a href="/chamados/equipe_doc">Documentadores <span class="badge"></span></a></li>
            <li><a href="/chamados/monitoradores">Monitoradores <span class="badge"></span></a></li>
            <li><a href="/chamados/supervisor">Supervisores Datacenter <span class="badge"></span></a></li>
          </ul>
        </li>
        

        
      </ul>
      <ul class="nav navbar-nav navbar-right"> 
					<li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							Minha Conta
							<span class="caret"></span> 
						</a>
						<div class="dropdown-menu perfil">
							<div class="col-sm-4 hidden-xs">
								<img class="img-responsive img-rounded" src="http://api.adorable.io/avatars/100/watchuru.png"> 
								
							</div>
							<ul class="list-unstyled col-sm-8"> 
								<li><sec:authentication property="principal.username"/></li>
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
	
<input type="hidden" id="input-urlRaiz"  value="http://10.0.21.158:8080/chamados/">