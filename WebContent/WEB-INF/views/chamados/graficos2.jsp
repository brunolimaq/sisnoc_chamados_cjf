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

  		 <script src="../resources/js/jquery-2.1.4.js"></script>
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		
		<script src="https://code.highcharts.com/modules/solid-gauge.js"></script>


<style type="text/css">

.highcharts-yaxis-grid .highcharts-grid-line {
	display: none;
}

</style>


<script type="text/javascript">




// div conformidade

$( document ).ready(function() {    


	Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
	    return {
	        radialGradient: {
	            cx: 0.5,
	            cy: 0.3,
	            r: 0.7
	        },
	        stops: [
	            [0, color],
	            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
	        ]
	    };
	});

	// Build the chart
	var graficoPizza = {
	    chart: {
            renderTo: 'graficoPizza',
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    title: { 
	    text: '.'},
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                style: {
	                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                },
	                connectorColor: 'silver'
	            }
	        }
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{ }]
	};
	
	
	$.getJSON("http://localhost:8080/chamados/graficos/teste", function(data) {
		 
		var chamados = data.dados.chamados.pop();
		$('#teste').text(teste)
		graficoPizza.series = [{
	        name: 'Brands',
	        data: [
				{
				    name: 'Chrome',
				    y: 10,
				    sliced: true,
				    selected: true
				},
	            {name: 'Microsoft Internet Explorer', y: teste},
	            {name: 'Firefox', y: 40}
	        ]
	    }];
			   var chart = new Highcharts.Chart(graficoPizza);
		});

	
	

 }); 




</script>
</head>
<body>


<div id="graficoPizza" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>

<h1 id="teste"></h1>
</body>

</html>