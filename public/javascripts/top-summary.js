
 $(document).ready(function(){






   var colores_g = ["#3366cc", "#dc3912", "#ff9900", "#109618", "#990099", "#0099c6"]
   //var points = ["P1", "P2", "P3", "P4", "P5"]
   var points = []

   var arrGlb =[];
   var ctr =0;
   var margins = {
       top: 24,
       left: 100,
       right: 24,
       bottom: 24
   },
   legendPanel = {
       width: 400
   },
   width = 600 //- margins.left - margins.right - legendPanel.width,
   height = 300 //- margins.top - margins.bottom,
       dataset =JSON.parse($('#code').val()),

       series = dataset.map(function (d) {


           return d.name;
       }),
       dataset = dataset.map(function (d) {
           return d.data.map(function (o, i) {
               // Structure it so that your numeric
               // axis (the stacked amount) is y
               return {
                   y: o.count,
                   x: o.yVal
               };
           });
       }),
       stack = d3.layout.stack();

   stack(dataset);

   for(var i=1; i<= dataset.length; i++) {
   			   points.push("P" + i);
   }

   width = 100*dataset.length

   height = 50*dataset[0].length



   var dataset = dataset.map(function (group) {
       return group.map(function (d) {
           // Invert the x and y values, and y0 becomes x0
           return {
               x: d.y,
               y: d.x,
               x0: d.y0
           };
       });
   }),
       svg = d3.select('#mainArea')
           .append('svg')
           .attr('width', width + margins.left + margins.right + legendPanel.width)
           .attr('height', height+ margins.top + margins.bottom)
           .append('g')
           .attr('transform', 'translate(' + margins.left + ',' + margins.top + ')'),
       xMax = d3.max(dataset, function (group) {
           return d3.max(group, function (d) {
               return d.x + d.x0;
           });
       }),

       xScale =  d3.scale.ordinal()
           .domain(points)
           .rangeBands([0, width]),  // the rec will be place at the multplier of (0+width/pointh.length) ... such as 0 , 120 , 240, 360.. if the the width is 600 and size is 5


       yVals = dataset[0].map(function (d) {
           return d.y;
       }),
       _ = console.log(yVals),
       yScale = d3.scale.ordinal()
           .domain(yVals)
           .rangeRoundBands([0, height], .2),
       xAxis = d3.svg.axis()
           .scale(d3.scale.ordinal()
       .domain(points).rangeBands([0, width]))
           .orient('top'),
       yAxis = d3.svg.axis()
           .scale(yScale)
           .orient('left'),
       colours = d3.scale.category10(),
       groups = svg.selectAll('g')
           .data(dataset)
           .enter()
           .append('g')
           .style('fill', function (d, i) {
               var w = i+2

           return "#FF0000 ";
       }),
       rects = groups.selectAll('rect')
           .data(function (d) {

               return d;
       })
           .enter()
           .append('rect')
           .attr('x', function (d) {
   		var rows = yVals.length
   		 var numb = Math.floor(ctr/rows)
   		 var pointsSize = points.length
               ctr++;



           return xScale(points[numb]);
       })
           .attr('y', function (d, i) {
           return yScale(d.y);
       })
           .attr('height', function (d) {
           return yScale.rangeBand();
       })
           .attr('width', function (d) {
           //return d.x;
   		  if( d.x ==0)
   		    return 0
   		  else
   			return width/points.length-1
       })


       svg.append('g')
           .attr('class', 'axis')
           .attr('transform', 'translate(0,' + height - 200 + ')')
           .call(xAxis);

   	svg.append('g')
   		.attr('class', 'axis')
   		.call(yAxis);




 });