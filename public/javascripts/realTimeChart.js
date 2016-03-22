window.onload = function () {

      google.load('visualization', '1', { 'packages': ['corechart', 'bar'], callback: intermediate});
       //alert($('#folder_id').val());
		var dps = []; // dataPoints

		var chart = new CanvasJS.Chart("chartContainer",{
			title :{
				text: "Live Random Data"
			},
			data: [{
				type: "line",
				dataPoints: dps
			}]
		});

		var xVal = 0;
		var yVal = 100;
		var updateInterval = 5000;
		var dataLength = 50000; // number of dataPoints visible at any point

		var updateChart = function (count) {

		drawStuff_temp1() ;
		/*	count = count || 1;
			// count is number of times loop runs to generate random dataPoints.

			for (var j = 0; j < count; j++) {
				yVal = yVal +  Math.round(5 + Math.random() *(-5-5));
				dps.push({
					x: xVal,
					y: yVal
				});
				xVal++;
			};
			if (dps.length > dataLength)
			{
				dps.shift();
			}

			chart.render();
			*/
		};

		// generates first set of dataPoints
		updateChart(dataLength);

		// update chart after specified time.
		setInterval(function(){updateChart()}, updateInterval);
}

function intermediate ()
     {
        //alert("yes");
     }
function drawStuff_temp1() {



    var jsonData;
     $.ajax({
                         type: 'GET',
                         url: '/getRealTimeChart',
                         dataType:"json",
                         data: "fileId=" + $('#folder_id').val() ,
                         async: true,
                          beforeSend: function() { $('#loading').show(); },
                          complete: function() { $('#loading').hide(); },
                          success:function(result) {

                          jsonData = result;

                          var data = new google.visualization.DataTable(jsonData);

                           var chart = new google.visualization.LineChart(document.getElementById("chart"));
                           var options = {
                               curveType: 'function',
                               'height':300
                                          };
                           chart.draw(data,options );

                         if( !jQuery.isEmptyObject (jsonData))
                           {
                                                 // $("#phychoTop" ).show();
								jQuery.each(jsonData, function(key, val) {
								if(key = "rows") {
									jQuery.each(val, function(key2, val2) {
										jQuery.each(val2, function(key3, val3) {
										  jQuery.each(val3, function(key4, val4) {
											 jQuery.each(val4, function(key5, val5) {
											    // alert(val5);
											});
										   });
										});

										  });
									   }



								});
                          }

                          }
                   });
     }
