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
	Highcharts.chart('container', {
	    chart: {
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
	    series: [{
	        name: 'Quantidade',
	        data: [
	            { name: 'Chamados', y: 56.33 },
	            {
	                name: 'Tarefas Internas',
	                y: 0.2,
	                sliced: true,
	                selected: true
	            },
	            { name: 'RDM', y: 10.38 },
	            { name: 'Problemas', y: 4.77 }, { name: 'Tarefas Internas', y: 0.91 },
	            { name: 'Incidentes', y: 24.9 }
	        ]
	    }]
	});
	
	
	

 }); 




</script>
</head>
<body>


<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>


</body>

</html>
