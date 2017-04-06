<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

    

  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>SISNOC - Chamados Algar</title>

  		 <script src="resources/js/jquery-2.1.4.js"></script>
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/highcharts-more.js"></script>
		
		<script src="https://code.highcharts.com/modules/solid-gauge.js"></script>
		<script src="resources/js/sisnoc_graficos_metas.js"></script>
		


<style type="text/css">

.highcharts-yaxis-grid .highcharts-grid-line {
	display: none;
}

</style>


</head>
<body>


<div style="width: 600px; height: 400px; margin: 0 auto">
    <div id="gauge-sla2" style="width: 300px; height: 200px; float: left"></div>
</div>

<div style="width: 600px; height: 400px; margin: 0 auto">
    <div id="gauge-sla4" style="width: 300px; height: 200px; float: left"></div>
</div>


</body>

</html>
