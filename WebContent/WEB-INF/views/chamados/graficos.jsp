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
		<script src="https://code.highcharts.com/highcharts-more.js"></script>
		
		<script src="https://code.highcharts.com/modules/solid-gauge.js"></script>


<style type="text/css">

.highcharts-yaxis-grid .highcharts-grid-line {
	display: none;
}

</style>


<script type="text/javascript">




// div conformidade

$( document ).ready(function() {    

			var gaugeOptions = {
			
			    chart: {
			        type: 'solidgauge',
                    renderTo: 'container-speed'

			    },
			
			    title: null,
			
			    pane: {
			        center: ['50%', '85%'],
			        size: '140%',
			        startAngle: -90,
			        endAngle: 90,
			        background: {
			            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
			            innerRadius: '60%',
			            outerRadius: '100%',
			            shape: 'arc'
			        }
			    },
			
			    tooltip: {
			        enabled: false
			    },
			
			    // the value axis
			    yAxis: {
			        stops: [
			            [0.1, '#DF5353'], // green
			            [0.5, '#DDDF0D'], // yellow
			            [0.9, '#55BF3B'] // red
			        ],
			        lineWidth: 0,
			        minorTickInterval: null,
			        tickAmount: 2,
			        title: {
			            y: -70
			        },
			        labels: {
			            y: 16
			        }
			    },
			
			    plotOptions: {
			        solidgauge: {
			            dataLabels: {
			                y: 5,
			                borderWidth: 0, 
			                useHTML: true
			            }
			        }
			    },
			    yAxis: {
			        min: 0,
			        max: 100,
			        title: {
			            text: 'Meta 4/hs'
			        }
			    },
			
			    credits: {
			        enabled: false
			    },
			
			    series: [{}]
			};

// The speed gauge
// 		var chartSpeed = Highcharts.chart('container-speed', Highcharts.merge(gaugeOptions, {
		   
		
// 		}));

	$.getJSON("http://localhost:8080/chamados/graficos/metasIndividualDuasHoras", function(data) {
	  
		gaugeOptions.series = [{
		      name: 'Chamados',
			  data: [data.dados.meta2],
		      dataLabels: {
		          format: '<div style="text-align:center"><span style="font-size:25px;color:' +
		              ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
		                 '<span style="font-size:12px;color:silver">km/h</span></div>'
		      },
		      tooltip: {
		          valueSuffix: ' km/h'
		      }
		
			}];
 		    var chart = new Highcharts.Chart(gaugeOptions);
		});

 }); 




</script>
</head>
<body>


<div style="width: 600px; height: 400px; margin: 0 auto">
    <div id="container-speed" style="width: 300px; height: 200px; float: left"></div>
</div>


</body>

</html>
