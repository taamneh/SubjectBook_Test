$(document).ready(function(){



/*jQuery('.tabs .tab-links a').on('click', function(e)  {
        var currentAttrValue = jQuery(this).attr('href');

        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).show().siblings().hide();

        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');

        e.preventDefault();
    });*/
   //$(".chart").each(function(index, value) {
 $('.tabs .tab-links a').click(function() {

      //alert('1111');
      var currentAttrValue = $(this).attr('href');
      //alert(currentAttrValue);

              // Show/Hide Tabs

             //$('.tabs ' + currentAttrValue).show().siblings().hide();
             $('.tabs ' + currentAttrValue).show().siblings().hide();

              // Change/remove current tab to active
              $(this).parent('li').addClass('active').siblings().removeClass('active');



               var subject= $(this).attr('subject');
               var session = $(this).attr('session');
               var name = session;

               google.load('visualization', '1.0', { 'packages': ['corechart'], callback: intermediate});
               e.preventDefault();

              var videoDiv ="#"+name;
                 function intermediate ()
                  {
                     drawStuff(session, subject , name);
                     showVideo(name);

                  }
                 $(videoDiv).toggle();
            });

});
function drawStuff(task, subject, chartDestination) {

    //alert('hello');
    var x = "RI_S004-001.Q_motion";
    var jsonData = $.ajax({
                         type: 'GET',
                         url: '/test',
                         dataType:"json",
                         data: "task=" + task + "&subject=" + subject,
                         async: false
                         }).responseText;
     // show that data that is recieved from method test as json, it has to be col and row
     var data = new google.visualization.DataTable(jsonData);

     var options = {
              title: 'Q-Sensor Singal',
               animation: {
                      duration: 1000,
                      easing: 'out'
                    },
              backgroundColor: { fill:'transparent' }

      };

    //instantiate and draw our chart, passing in the options
    var chartPlace = "#chart" + chartDestination;
    //alert(chartPlace);
    var chart = new google.visualization.LineChart(document.querySelector(chartPlace));
    chart.draw(data, options);


     var videoName = "#video" + chartDestination;
     //alert(videoName);
     google.visualization.events.addListener(chart, 'select', function() {
        var row = chart.getSelection()[0].row;
        var video2 = $(videoName)[0];
        video2.currentTime = data.getValue(row, 0);
        video2.play();
      });
}

function showVideo(num) {

   var videoButton = "#showvideo"+num;
   var videoBoard = "#videoboard" + num;
   $(videoButton).click(function(){
                //$(videoBoard).toggle();
             if($(videoButton).html()== 'Show Videos')
                {$(videoButton).html('Hide Videos');
                 $(videoBoard).slideDown();}
             else
                 {$(videoButton).html('Show Videos');
                    $(videoBoard).slideUp();
                 }
                     });
             $('#video1').html(' <source src= "assets/images/v.mp4" type="video/mp4">');
}