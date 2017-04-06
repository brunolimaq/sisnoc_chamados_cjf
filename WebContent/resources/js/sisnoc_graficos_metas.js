$( document ).ready(function() {
	
		var gaugeSLA2 = {
			
			    chart: {
			        type: 'solidgauge',
                    renderTo: 'gauge-sla2'

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
			        enabled: true

			    },
			
			    // the value axis
			    yAxis: {
			        stops: [
			            [0.8, '#DF5353'], // red
			            [0.84, '#DF5353'], // red
			            [0.85, '#DDDF0D'], // yellow
			            [0.89, '#DDDF0D'], // yellow
			            [0.9, '#55BF3B'] // green
			        ],
			        lineWidth: 0,
			        minorTickInterval: null,
			        tickAmount: 2,
			        title: {
			            y: -35,
			            text: 'Meta 2 horas'
			        },
			        labels: {
			            y: 16
			        },
			        min: 0,
			        max: 100,

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
			    credits: {
			        enabled: false
			    },
			
			    series: [{}]
			};
			
var gaugeSLA4 = {
		
	    chart: {
	        type: 'solidgauge',
            renderTo: 'gauge-sla4'

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
	        enabled: true

	    },
	
	    // the value axis
	    yAxis: {
	        stops: [
	            [0.8, '#DF5353'], // red
	            [0.85, '#DDDF0D'], // yellow
	            [0.9, '#55BF3B'] // green
	        ],
	        lineWidth: 0,
	        minorTickInterval: null,
	        tickAmount: 2,
	        title: {
	            y: -35,
	            text: 'Meta 4 horas'
	        },
	        labels: {
	            y: 16
	        },
	        min: 0,
	        max: 100,

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
	    credits: {
	        enabled: false
	    },
	
	    series: [{}]
	};



	$.getJSON("http://localhost:8080/chamados/graficos/metasIndividual", function(data) {
	  
		gaugeSLA2.series = [{
		      name: 'Meta 2h',
			  data: [data.dados.meta2],
		      dataLabels: {
		          format: '<div style="text-align:center"><span style="font-size:15px;color:' +
		              ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
		                 '<span style="font-size:7px;color:silver">% mês</span></div>'
		      },
		      tooltip: {
		          valueSuffix: ' % mês'
		      }
		
			}];
 		    var chart = new Highcharts.Chart(gaugeSLA2);
 		    
 		   gaugeSLA4.series = [{
 		      name: 'Meta 4h',
 			  data: [data.dados.meta4],
 		      dataLabels: {
 		          format: '<div style="text-align:center"><span style="font-size:15px;color:' +
 		              ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
 		                 '<span style="font-size:7px;color:silver">% mês</span></div>'
 		      },
 		      tooltip: {
 		          valueSuffix: ' % mês'
 		      }

 			}];
 			    var chart = new Highcharts.Chart(gaugeSLA4);
		});
});