var dataset = [];

function fun(){

var r = 1;

dataset = [];
var rows = $("#noVar").val();
   var cols = $("#noPoints").val();
  for(var i= 1; i<= rows; i++)
  {
     if($('#sessionName' + i).val() == ''  ||  $('#acronym' + i).val() == '' || $('#sessionType' + i).val() == '' || $('#arm' + i).val() == '' )
        r = 0;

        var object1= { sessionName: $('#sessionName' + i).val() , acronym: $('#acronym' + i).val(), sessionType: $('#sessionType' + i).val(), arm: $('#arm' + i).val() }
        dataset.push(object1)

  }


 $("#code").val(JSON.stringify(dataset))


 return r;


}

$(document).ready(function(){

 function drawTable (rows, cols)
 {
     $('#head').empty();


     $('#head').append("<th data-field=\"extension\" data-sortable=\"true\"> Session Name </th> \
     <th data-field=\"extension\" data-sortable=\"true\"> Acronym </th> \
     <th data-field=\"extension\" data-sortable=\"true\"> Session Type </th> \
     <th data-field=\"extension\" data-sortable=\"true\"> Treatment Arms </th>")


      var arms = "<option id=\"select_1\" value=0 > Crossover </option> "

      for(var a= 1; a<=cols; a++)
      {
      //var value = a-1;
        arms = arms + " <option id=\"select_1\" value=" + a + "> Arm"+ a +"</option> "
      }

      //alert(arms);

    $('#tableBody').empty();
     for(var i =1; i<= rows; i++){
           var rowName = "rowsNo" + i;
           var varName = "varName" + i
           $('#' + rowName).empty();
          var tt = $('#tableBody').append ("<tr class=\"table_row_odd table_row_first\" id=\""+  rowName +"\"> </tr> ")
           $('#' + rowName).append( "<td> <input style = \"height:50px; border-color: #778899;\" type=\"text\" height=\"90\" class=\"form-control\" id = \""+ "sessionName" + i + "\" required>  </td> ")
          for(var j =1; j<= 1; j++){
          var pointName = "point" + i + j
          $('#' + rowName).append( "<td> <input style = \"height:50px; border-color: #778899;\" type=\"text\" class=\"form-control\" id = \""+ "acronym" + i + "\" required>  </td> <td> \
                    <select  style = \"margin-top: 10px; border-color: #778899;\" id=\"" + "sessionType" + i+ "\" name=\"dataType\" value=\"1\"> \
                        <option id=\"select_1\" value=1 > BaseLine</option> \
                        <option id=\"select_1\" value=2 > Intervension </option> \
    					<option id=\"select_1\" value=3 > Other </option> \
                     </select> </td>  \
    				 <td> \
                    <select  style = \"margin-top: 10px; border-color: #778899;\" id=\"" + "arm" + i + "\" name=\"dataType\" value=\"1\"> \"" + arms + "</select> </td>")

          }
      }
 }


  $("#noPoints").change(function() {
  var rows = $("#noVar").val();
  var cols = $(this).val();
  var inner = "";

drawTable (rows, cols)




});


  $("#noVar").change(function() {
  var rows = $(this).val();
  var cols = $("#noPoints").val();
  var inner = "";

  drawTable (rows, cols)
});




$( "#target" ).submit(function( event ) {



var rows = $("#noVar").val();
   var cols = $("#noPoints").val();
  var dataset = [];

  for(var i= 1; i<= rows; i++)
  {

        var object1= { sessionName: $('#sessionName' + i).val() , acronym: $('#acronym' + i).val(), sessionType: $('#sessionType' + i).val(), arm: $('#arm' + i).val() }
        dataset.push(object1)

  }
alert(JSON.stringify(dataset));

$("#code").val(JSON.stringify(dataset))


});
		!function ($) {
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){
				$(this).find('em:first').toggleClass("glyphicon-minus");
			});
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})

});