
$(document).ready(function(){


var arryArmsName = ["cg", "i1", "i2"]

//noExp


/*$("#noExp").keypress(function() {
   $(this).trigger("change");
});*/
$("#noExp").change(function() {

  $("#allTable").empty();
  var noOfExperiments  = $("#noExp").val();
  for(var i =1; i<= noOfExperiments; i++){
      var noVar = "noVar" + i;
      var noArms = "noArms"  + i;
      var head  =  "head" + i
      var tableName = "tableBody" + i;
      var ExperimentTypeName = "experimentType" + i;


      /*$('#allTable').append (" <label> (" + i  + ") Experiment type:</label> <td> <select class=\"select_field\"  iter=\""+  i + "\"id=\"" +  ExperimentTypeName + "\" name=\"study_type\"  style=\"width: 300px\"> <option id=\"op1\" value=2  selected =\"selected\"> Parallel Group </option> <option id=\"op1\" value=1  selected =\"selected\"> Crossover </option> </select> </td> \
       <label>How many groups your study has? </label>   <input type=\"number\" iter=\""+  i + "\"class=\"form-control\" name=\"min\" value =\"0\" id = \"" + noArms +"\" disabled> \
       <label>How many unique sessions your study has? </label>  <input type=\"number\" iter=\""+  i + "\"class=\"form-control\"  name=\"pName\" id = \"" +  noVar + "\" required>") */

     /*$('#allTable').append ("<table class = \"sessionsList\" data-toggle=\"table\"  data-show-refresh=\"true\" data-show-toggle=\"true\" data-show-columns=\"true\" data-search=\"true\" data-select-item-name=\"toolbar1\" data-pagination=\"true\" data-sort-name=\"name\" data-sort-order=\"desc\"> <thead> \
                         <tr id =\"" + head  + "\"> </tr> </thead> <tbody  id=\"" + tableName + "\"> </tbody> </table> <br>") */

      $('#allTable').append ("<table cellspacing=\"0\" class=\"data_table\"> <tr><td> (" + i  + ") Experiment type :</td> <td> <select style=\"height: 50px; border-color: #778899;\" class=\"select_field\"  iter=\""+  i + "\"id=\"" +  ExperimentTypeName + "\" name=\"study_type\" > <option id=\"op1\" value=2  selected =\"selected\"> Parallel Group </option> <option id=\"op1\" value=1  selected =\"selected\"> Crossover </option> </select> </td> </tr> <tr> <td align=\"center\" valign=\"middle\">\
       Number of groups :  </td> <td id = \"kareem\" ><input style=\"height: 50px; border-color: #778899;\" type=\"number\" min=\"0\" iter=\""+  i + "\"class=\"form-control\" name=\"min\" value =\"0\" id = \"" + noArms +"\" disabled> </td></tr><br> <tr><td valign=\"center\">\
       Number of sessions :  </td>  <td id = \"kareem\"> <input style=\"height: 50px; border-color: #778899;\" type=\"number\" min=\"0\" iter=\""+  i + "\"class=\"form-control\"  name=\"pName\" id = \"" +  noVar + "\" required> </td> </tr></table>")

      $('#allTable').append ("<table class = \"sessionsList\" data-toggle=\"table\"  data-show-refresh=\"true\" data-show-toggle=\"true\" data-show-columns=\"true\" data-search=\"true\" data-select-item-name=\"toolbar1\" data-pagination=\"true\" data-sort-name=\"name\" data-sort-order=\"desc\"> <thead> \
                               <tr id =\"" + head  + "\"> </tr> </thead> <tbody  id=\"" + tableName + "\"> </tbody> </table> <br> <hr>")


     var nameVar =  "#" + noVar

         $("#" + noArms).change(function() {  // when user enter the number of arms or groups we create input texts for them

          var iter = $(this).attr('iter')
          var nameVar =  "#noVar" + iter;


         var armsHeadName =  "noArms" + iter + "head"
         var armsTableName = "noArms" + iter +"tableBody"

          $("#" + armsTableName).empty();
          $("#" + armsHeadName).empty();

          $(this).after("<table class = \"sessionsList\" data-toggle=\"table\"  data-show-refresh=\"true\" data-show-toggle=\"true\" data-show-columns=\"true\" data-search=\"true\" data-select-item-name=\"toolbar1\" data-pagination=\"true\" data-sort-name=\"name\" data-sort-order=\"desc\"> <thead> \
                                <tr id =\"" + armsHeadName  + "\"> </tr> </thead> <tbody  id=\"" + armsTableName + "\"> </tbody> </table> <br>")

          $("#" + armsHeadName).append("<th data-field=\"extension\" data-sortable=\"true\">  </th>")

          for(var a= 1; a<=$(this).val(); a++)
           {
             var armName = "noArms" + iter + a
             var name = "Group " + a
             $("#" + armsTableName).append ("<tr class=\"table_row_odd table_row_first\" id=\""+  "jj" +"\"><td> Group name : </td> <td> <input type=\"text\" height=\"90\" class=\"form-control\" id = \""+ armName + "\" required value=\""+ name +"\">  </td> </tr> ")

             $("#" + armName).change(function() {
               // arryArmsName[arryArmsName.length] = $(this).val()

                $(nameVar).trigger("change");
             });
           }


          $(nameVar).trigger("change");



        });


        $("#" + ExperimentTypeName).change(function() {  // this trigger when we change the type of experiment betwen cross over and parallel group


                var iter = $(this).attr('iter')
                var experType = $(this).val();
                       if(experType ==1){

                              $("#noArms"+ iter).prop('disabled', 'disabled');
                           }
                       else
                       {
                           $("#noArms"+ iter).prop('disabled', false);
                       }

        });



    $(nameVar).change(function(){

         var rows = $(this).val()


         var iter = $(this).attr('iter')
         var experType =  $("#experimentType" + iter).val();


          if(experType ==1){

                 $("#noArms"+ + iter).prop('disabled', 'disabled');
          }



         var locaPontsName = "#noArms" + iter


         var locaHeadName = "#head" + iter
         var locaBodyTableName = "#tableBody" + iter
         var thisName = "noVar" + iter

         //alert(rows +  " -- "  + nameVar  +  "----"  +  iter);

         var cols =  $(locaPontsName).val()


         $(locaHeadName).empty();

         if(rows !=""){

            $(locaHeadName).append("<th data-field=\"extension\" data-sortable=\"true\"> Session Name </th> \
            <th data-field=\"extension\" data-sortable=\"true\"> Acronym </th> \
            <th data-field=\"extension\" data-sortable=\"true\"> Session Type </th> \
            <th data-field=\"extension\" data-sortable=\"true\"> Arms </th> \
            <th data-field=\"extension\" data-sortable=\"true\"> Order </th>")

         }




            var arms = "<option id=\"select_1\" value=0 > All </option> "



            for(var a= 1; a<=cols; a++)
            {
            //var value = a-1;

            var name = $(locaPontsName+a).val()
              //arms = arms + " <option id=\"select_1\" value=" + a + "> Arm"+ a +"</option> "

              arms = arms + " <option id=\"select_1\" value=" + a + ">"+ name +"</option> "
            }

          $(locaBodyTableName).empty();
           for(var r =1; r<= rows; r++){
               var sessionName = "sessionName" + iter + r
               var acronymName = "acronym" + iter + r
               var sessionTypeName = "sessionType" + iter +r
               var randomizationName = "rand" + iter +r
               var armsName = "arm" + iter + r

                 var rowName = thisName + r;
                 $('#' + rowName).empty();
                var tt = $(locaBodyTableName).append ("<tr class=\"table_row_odd table_row_first\" id=\""+  rowName +"\"> </tr> ")
                 $('#' + rowName).append( "<td> <input  style = \"height:50px; border-color: #778899;\" type=\"text\" height=\"90\" class=\"form-control\" id = \""+ sessionName + "\" required>  </td> ")
                for(var j =1; j<= 1; j++){
                var pointName = "2point" + r + j
                $('#' + rowName).append( "<td> <input style = \"height:50px; border-color: #778899;\" type=\"text\" class=\"form-control\" id = \""+ acronymName + "\" required>  </td> <td> \
                          <select style = \"margin-top: 10px; border-color: #778899;\" id=\"" + sessionTypeName+ "\" name=\"dataType\" value=\"1\"> \
                              <option id=\"select_1\" value=0 > BaseLine</option> \
                              <option id=\"select_1\" value=1 > Cross BaseLine</option> \
                              <option id=\"select_1\" value=2 > Cross Intervention </option> \
                              <option id=\"select_1\" value=4 > Intra Intervention </option> \
          					<option id=\"select_1\" value=3 > Other </option> \
                           </select> </td> <td> <select  style = \"margin-top: 10px; border-color: #778899;\" id=\"" +armsName+ "\" name=\"dataType\" value=\"1\"> \"" + arms + "</select> </td> <td> \
                           <select style = \"margin-top: 10px; border-color: #778899;\" id=\"" + randomizationName+ "\" name=\"dataType\" value=\"1\"> \
                                                         <option id=\"select_1\" value=1 > Randomized </option> \
                                                         <option id=\"select_1\" value=2 > Fixed </option> \
                                                      </select> </td>")

                            if(experType ==1){

                                         $("#" + armsName).prop('disabled', 'disabled');
                                }
                }
            }

    });

  }



 // drawTable (rows, cols)
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