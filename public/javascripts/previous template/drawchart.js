function drawStuff(fileName) {

alert('hello');
    var x = "RI_S004-001.Q_motion";
    var jsonData = $.ajax({
                         type: 'GET',
                         url: '/test',
                         dataType:"json",
                         data: "fileName=" + x,
                         async: false
                         }).responseText;
     // show that data that is recieved from method test as json, it has to be col and row
     var data = new google.visualization.DataTable(jsonData);

    /*var data = new google.visualization.DataTable(
                   {
                     cols: [{id: 'task', label: 'Task', type: 'string'},
                              {id: 'hours', label: 'Hours per Day', type: 'number'}],
                     rows: [{c:[{v: 'Work'}, {v: 11}]},
                            {c:[{v: 'Eat'}, {v: 2}]},
                            {c:[{v: 'Commute'}, {v: 2}]},
                            {c:[{v: 'Watch TV'}, {v:2}]},
                            {c:[{v: 'Sleep'}, {v:7, f:'7.000'}]}
                           ]
                   },
                 0.6
              );
     */

    /*var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    data.addRows([
        ['Mushrooms', 3],
        ['Onion', 1],
        ['Olives', 1],
        ['Zucchini', 3],
        ['Pepperoni', 2]
    ]);
    */
    //set the chart options

    /*var options = {
        title: 'Pizza Consumed',
        width: 400,
        height: 500
    };*/

     var options = {
              title: 'Q-Sensor Singal',
               animation: {
                      duration: 1000,
                      easing: 'out'
                    },
              backgroundColor: { fill:'transparent' }
      };

    //instantiate and draw our chart, passing in the options
    var chart = new google.visualization.LineChart(document.querySelector('#chart1'));
    chart.draw(data, options);



   /*var addButton = document.getElementById('bt2');

     addButton.onclick = function() {

    data.addColumn('number', 'Topping');
    for (var i = 0; i < data.getNumberOfRows(); ++i) {
              var num = Math.floor(Math.random() * 1000);
              data.setValue(i, 2, num);
            }
    chart.draw(data, options);
    };*/

     google.visualization.events.addListener(chart, 'select', function() {
        var row = chart.getSelection()[0].row;
        //document.getElementById("koko").src = pic;
        //alert('You selected ' + data.getValue(row, 0) );
        //var video = $('#kid')[0];
        var video2 = $('#kid0')[0];
        //video.currentTime = data.getValue(row, 0);
        video2.currentTime = data.getValue(row, 0);

        //video.play();
        video2.play();


      });
}
$(document).ready(function () {
     /*$("#showvideo").click(function(){
         $("#videoboard").toggle();
         if($("#showvideo").html()== 'Show Videos')
              {$("#showvideo").html('Hide Videos');}
          else
             {$("#showvideo").html('Show Videos');}
       });
     */
    //$("#S001BL li").not('.emptyMessage').click(function() {
    $("#signal li").not('.emptyMessage').click(function() {
    //$("#signal").find("li").not('.emptyMessage').click(function() {
      google.load('visualization', '1.0', { 'packages': ['corechart'], callback: drawStuff });
      //$("#kid0").load("assets/images/v.mp4");
        // $("#S001").toggle();
       //  $('#kid0').html(' <source src= "assets/images/v.mp4" type="video/mp4">');
});


});