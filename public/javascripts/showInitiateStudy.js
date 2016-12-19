
$(document).ready(function(){

// this is to prvent submitting the form by hitting enter
 $(window).keydown(function(event){
    if(event.keyCode == 13) {
      event.preventDefault();
      return false;
    }
  });


var dataset = [];

function convertToJOSN(){

        var noOfExperiments  = $("#noExp").val();

        var globalArmNum =1

        var r = 1;
        dataset = [];

         for(var expr= 1; expr<= noOfExperiments; expr++) {

         var experimentType = $("#experimentType" + expr).val()
         //alert(experimentType);

         var middleObj = [];
         var armsObj = []
            //alert($("#experimentType" + expr).val())

               var rows = $("#noVar" + expr).val();

               if(experimentType ==1) {
                   for(var i= 1; i<= rows; i++)
                     {
                        if($('#sessionName' + expr + i).val() == ''  ||  $('#acronym'+ expr + i).val() == '' || $('#sessionType'+ expr + i).val() == '' )
                           r = 0;

                           var object1= { sessionName: $('#sessionName' + expr + i).val() , acronym: $('#acronym' + expr + i).val(), sessionType: $('#sessionType' + expr + i).val(), arm: $('#arm' + expr + i).val(), fixed: $('#rand' + expr + i).val() }
                           //dataset.push(object1)
                          middleObj.push(object1)

                     }
               }
               else // if it is paralle group
               {
                 for(var i= 1; i<= rows; i++)
                      {
                         if($('#sessionName' + expr + i).val() == ''  ||  $('#acronym'+ expr + i).val() == '' || $('#sessionType'+ expr + i).val() == '' )
                            r = 0;

                            var object1= { sessionName: $('#sessionName' + expr + i).val() , acronym: $('#acronym' + expr + i).val(), sessionType: $('#sessionType' + expr + i).val(), arm: $('#arm' + expr + i).val(), fixed: $('#rand' + expr + i).val() }
                            globalArmNum = globalArmNum +1;
                            //dataset.push(object1)
                            middleObj.push(object1)

                      }

                      var noOfArms =  $("#noArms" + expr).val()
                      for(var j=1; j<= noOfArms; j++)
                      {


                        var objTemp = {nameOfArm : $('#noArms' + expr + j).val()}
                         armsObj.push(objTemp)
                      }


               }



                dataset.push({"ExperimentType" :  $("#experimentType" + expr).val(), "dataItems" : middleObj, "arms" : armsObj})

               //type : "#experimentType" + expr).val()
         }



         $("#code").val(JSON.stringify(dataset))

          // alert(JSON.stringify(dataset))
         return r;


}

// slaha aldeeee
var studyParameters = {}

var explOptions = [];
var explOptionsNames = [];
var respOptions = [];
var respOptionsNames = []

var subjectLevelPy = [];
var subjectLevelPostPy = [];
var sessionLevelPy = [];

var blockingVar = [];
var blockingVarNames = [];

var primaryNextButton = null;
var firstNextButton = null;
var secondNextButton = null;
var thirdNextButton = null;
var fourthNextButton = null;

//obj["property"] = 5;

//alert(JSON.stringify(obj))


// wsUri declared in the html file becuase it is generated on the server side
var websocket = new WebSocket(wsUri);
websocket.onmessage = function(evt) { onMessage(evt) };
websocket.onopen = function(evt) { onOpen(evt) };

var messageNo = 1;
var subjectOk =false;

 var resBl = null;
 var resEx = null;
 var resRe = null;

  function onbaslinechange(){
   for(var i =0; i< resBl.length; i++)
            {
               var num = i+1;

               var id = "#bl" + num

              if($(id).is(':checked'))
              {
                 //alert("here  "  + resBl[i]);
                 studyParameters["baseLineFolder"] = resBl[i]
              }
            }
 }
  function onExpChange(){
    for(var i =1; i< resEx.length; i++)
             {
                var num = i;
                var id = "#expl" + num
               if($(id).is(':checked'))
               {
                  //alert("expl  "  + resEx[i]);
                  studyParameters["explanColNo"] = i
               }
             }
  }

  function onRespChange(){
      for(var i =1; i< resRe.length; i++)
               {
                  var num = i;
                  var id = "#resp" + num
                 if($(id).is(':checked'))
                 {
                   // alert("resp  "  + resRe[i]);
                    studyParameters["respColNo"] = i
                 }
               }
    }

// This function will be called every time a message arrive from the server
  var doneSubject =0;
  function onMessage(evt)
  {
    doneSubject = doneSubject +1
    var incr = Math.round(doneSubject * (100 / document.getElementById("numSubj").value));
    incr = incr + "%"
    $("#progressFileCreating").width(incr)
    //$("#progressFileCreating").text(incr)
    $("#progressFileCreating").html("<h3 style= \"color : white;\">" + incr + "</h3>")
    $("#textProgress").text(evt.data)

     if(doneSubject == document.getElementById("numSubj").value)
         $('#sb').trigger('click');

  }
  function onOpen(evt)
  {
        websocket.send("FILESTRUCTURE");
  }

  function doSend(message)
  {
    websocket.send(message);
  }





    function init()
    {
      // alert('caling init')
       websocket.send("FILESTRUCTURE");
    }

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
//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches


function selectionLabels(arr){

   var str = ""
   for(var i =0; i< arr.length; i++ )
   {
       str = str + "<span class=\"label label-success\">" + arr[i] + "</span>  "
   }

  return str;

}

$( '.dropdown-menu .small2' ).on( 'click', function( event ) {

   var $target = $( event.currentTarget ),
       val = $target.attr( 'data-value' ),
        name = $target.attr( 'name' ),
       $inp = $target.find( 'input' ),
       idx;

   if ( ( idx = respOptions.indexOf( val ) ) > -1 ) {
      respOptions.splice( idx, 1 );
        respOptionsNames.splice(idx, 1)
            $("#selectedSecRes").empty()
            $("#respSndVar").after("<p id =\"selectedSecRes\">" + selectionLabels(respOptionsNames) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
   } else {
      respOptions.push( val );
      respOptionsNames.push(name)
           $("#selectedSecRes").empty()
           $("#respSndVar").after("<p id =\"selectedSecRes\">" + selectionLabels(respOptionsNames) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
   }

   $( event.target ).blur();

   console.log( respOptions );
   return false;
});


$("#mdl1Finish").on( 'click', function( event ) {
   alert("what is going on guyes")

   $("#myModal1").hide();
});


$( '.dropdown-menu .small1' ).on( 'click', function( event ) {

   var $target = $( event.currentTarget ),
       val = $target.attr( 'data-value' ),
       name = $target.attr( 'name' ),
       $inp = $target.find( 'input' ),
       idx;

   if ( ( idx = explOptions.indexOf( val ) ) > -1 ) {
        explOptions.splice( idx, 1 );
        explOptionsNames.splice(idx, 1)
        $("#selectedSec").empty()
       $("#explantorySdnVar").after("<p id =\"selectedSec\">" + selectionLabels(explOptionsNames) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
   } else {
      explOptionsNames.push(name)
      explOptions.push( val );
      $("#selectedSec").empty()
       $("#explantorySdnVar").after("<p id =\"selectedSec\">" + selectionLabels(explOptionsNames) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
   }

   $( event.target ).blur();

   console.log( explOptions );
   return false;
});


$( '.dropdown-menu .blockingVar' ).on( 'click', function( event ) {

   var $target = $( event.currentTarget ),
       val = $target.attr( 'data-value' ),
       name =  $target.attr( 'name' ),
       $inp = $target.find( 'input' ),
       idx;


   if ( ( idx = blockingVar.indexOf( val ) ) > -1 ) {
      blockingVar.splice( idx, 1 );
      blockingVarNames.splice( idx, 1)
      $("#blocingVarArea").empty()
       $("#blockingVarBtn").after("<p id =\"blocingVarArea\">" +  selectionLabels(blockingVarNames) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
   } else {

      blockingVar.push( val );
        blockingVarNames.push ( name )
        $("#blocingVarArea").empty()
        $("#blockingVarBtn").after("<p id =\"blocingVarArea\">" + selectionLabels(blockingVarNames) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', true ) }, 0);

   }

   $( event.target ).blur();

   console.log( blockingVar );
   return false;
});




$( '.dropdown-menu .subjectLevel' ).on( 'click', function( event ) {

   var $target = $( event.currentTarget ),
       val = $target.attr( 'data-value' ),
       $inp = $target.find( 'input' ),
       idx;

   if ( ( idx = subjectLevelPy.indexOf( val ) ) > -1 ) {
      subjectLevelPy.splice( idx, 1 );
        $("#selectedsubjectLevelPy").empty()
        $("#subjLevelBtn").after("<p id =\"selectedsubjectLevelPy\">" + selectionLabels(subjectLevelPy) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
   } else {
      subjectLevelPy.push( val );
       $("#selectedsubjectLevelPy").empty()
        $("#subjLevelBtn").after("<p id =\"selectedsubjectLevelPy\">" + selectionLabels(subjectLevelPy) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
   }

   $(event.target).blur();

   console.log(subjectLevelPy);
   return false;
});


$( '.dropdown-menu .subjectLevelPost' ).on( 'click', function( event ) {

   var $target = $( event.currentTarget ),
       val = $target.attr( 'data-value' ),
       $inp = $target.find( 'input' ),
       idx;

   if ( ( idx = subjectLevelPostPy.indexOf( val ) ) > -1 ) {
      subjectLevelPostPy.splice( idx, 1 );
        $("#selectedsubjectLevelPostPy").empty()
        $("#subjLevelPostBtn").after("<p id =\"selectedsubjectLevelPostPy\">" + selectionLabels(subjectLevelPostPy) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
   } else {
      subjectLevelPostPy.push( val );
       $("#selectedsubjectLevelPostPy").empty()
        $("#subjLevelPostBtn").after("<p id =\"selectedsubjectLevelPostPy\">" + selectionLabels(subjectLevelPostPy) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
   }

   $(event.target).blur();

   console.log(subjectLevelPostPy);
   return false;
});




$( '.dropdown-menu .sessionLevel' ).on( 'click', function( event ) {

   var $target = $( event.currentTarget ),
       val = $target.attr( 'data-value' ),
       $inp = $target.find( 'input' ),
       idx;

   if ( ( idx = sessionLevelPy.indexOf( val ) ) > -1 ) {
      sessionLevelPy.splice( idx, 1 );
       $("#selectedsessionLevelPy").empty()
         $("#sessLevelBtn").after("<p id =\"selectedsessionLevelPy\">" + selectionLabels(sessionLevelPy) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
   } else {
      sessionLevelPy.push( val );
        $("#selectedsessionLevelPy").empty()
         $("#sessLevelBtn").after("<p id =\"selectedsessionLevelPy\">" + selectionLabels(sessionLevelPy) + "</p>")
      setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
   }

   $( event.target ).blur();

   console.log( sessionLevelPy );
   return false;
});



$("form" ).submit(function( event ) {


  /* studyParameters["covarite"] = $("#cov" ).val()

   if (dataset.length == 0)
      studyParameters["descriptor"] = null;
   else
       studyParameters["descriptor"] = dataset
   //doSend($("#cov" ).val());
   doSend(JSON.stringify(studyParameters));
   alert( "Your study is being created. Please check back after few minutes" );
  $("#loading").show()
  //event.preventDefault();

  */
    });




$(".next0").click(function(){

     // to build the descriptor

     if(document.getElementById("noExp").value == "")
              alert("Please enter the number of experiments")
    else {
    var flg = 1;
    $('input[id^="noVar"]').each(function() {
            if($(this).val() == ""){
                  alert("Please enter the number of sessions that you have in you study")
                  flg =0;
             }
     });

       if(flg ==1) // if all field have been filled
      {
           //noExp
           var r = convertToJOSN();
           if(r == 0)
           {
            alert("please fill in all fields")
           }
           else {

               if(blockingVar.length != 0 ) {
                   var tempObj = {}
                   for(var i =0; i< blockingVar.length; i++){
                    var num = i+1
                     if(blockingVar[i] != "None")
                     tempObj[num.toString()] = blockingVar[i]
                     }

                    studyParameters["blockingVar"] = tempObj;


                   primaryNextButton = this;

                   if(animating) return false;
                    animating = true;

                    current_fs = $(this).parent();
                    next_fs = $(this).parent().next();

                    //activate next step on progressbar using the index of next_fs
                    $("#progressbar1 li").eq($("fieldset").index(next_fs)).addClass("active");

                    //show the next fieldset
                    next_fs.show();
                    //hide the current fieldset with style
                    current_fs.animate({opacity: 0}, {
                        step: function(now, mx) {
                            //as the opacity of current_fs reduces to 0 - stored in "now"
                            //1. scale current_fs down to 80%
                            scale = 1 - (1 - now) * 0.2;
                            //2. bring next_fs from the right(50%)
                            left = (now * 50)+"%";
                            //3. increase opacity of next_fs to 1 as it moves in
                            opacity = 1 - now;
                            current_fs.css({'transform': 'scale('+scale+')'});
                            next_fs.css({'left': left, 'opacity': opacity});
                        },
                        duration: 800,
                        complete: function(){
                            current_fs.hide();
                            animating = false;
                        },
                        //this comes from the custom easing plugin
                        easing: 'easeInOutBack'
                    });
                  }
                  else {
                    alert("Select a blocking variable or None")
                  }
           }
       }
     } // else
});



$(".next1").click(function(){




   /*$("#progressbar").show()
   for(var i=0; i<=100; i= i+1){
        var prog =  i + "%"
        $("#progressFileCreating").width(prog)
        //alert(prog);
   }*/

   if(document.getElementById("study_name").value == "")
             alert("Please enter the name of study")

   else {

     studyParameters["studyName"] = document.getElementById("study_name").value
     studyParameters["subjectNo"] = document.getElementById("numSubj").value
     studyParameters["sessionNo"] = 0
     studyParameters["userName"] = userName


   //   $('#sb').trigger('click');


     primaryNextButton = this;

    forAllNext (primaryNextButton)
   }
});



$(".next2").click(function(){
     //alert($( "#sourcetype" ).val());


   studyParameters["primaryExp"] = $("#explanatoryPrim" ).val()
   //doSend($("#explanatoryPrim" ).val())

    if(explOptions.length !=0) {

    var tempObj = {}
        for(var i =0; i< explOptions.length; i++){
           var num = i+1
           if(explOptions[i] != "None")
            tempObj[num.toString()] = explOptions[i]
          }

        studyParameters["secondadryExp"] = tempObj;

        studyParameters["StaticBefore"] = $( "#staticB" ).val();
        studyParameters["StaticAfter"] = $( "#staticA" ).val();




           primaryNextButton = this;

           forAllNext (primaryNextButton)
    }
    else{
        alert("Please select a secondary variable or None")
    }


});


$(".next3").click(function(){
     studyParameters["primaryRes"] = $( "#responsePrim" ).val()
     //doSend($("#responsePrim" ).val())

    if(respOptions.length != 0 ) {
    var tempObj = {}
    for(var i =0; i< respOptions.length; i++){
     var num = i+1
      if(respOptions[i] != "None")
       tempObj[num.toString()] = respOptions[i]
      }

    studyParameters["secondadryRes"] = tempObj;


       primaryNextButton = this;

      forAllNext (primaryNextButton)
      }
     else
     {
       alert("Please select a secondary variable or None")
     }
});



$(".next4").click(function(){
     studyParameters["primaryRes"] = $( "#responsePrim" ).val()
     //doSend($("#responsePrim" ).val())
    var tempObj = {}

    if(subjectLevelPy.length == 0 || sessionLevelPy.length ==0 || subjectLevelPostPy.length == 0){
       alert("Please select a questionnaire or select None")
    }
    else {

    for(var i =0; i< subjectLevelPy.length; i++){
     var num = i+1
     if(subjectLevelPy[i] != "None") {
      tempObj[num.toString()] = subjectLevelPy[i]
      }
      }


    studyParameters["subjectLevelPy"] = tempObj;


    tempObj = {}
     for(var i =0; i< sessionLevelPy.length; i++){
         var num = i+1
          if(sessionLevelPy[i] != "None") {
          tempObj[num.toString()] = sessionLevelPy[i]
          }
          }

        studyParameters["sessionLevelPy"] = tempObj;


     tempObj = {}
     for(var i =0; i< subjectLevelPostPy.length; i++){
         var num = i+1
         if(subjectLevelPostPy[i] != "None") {
          tempObj[num.toString()] = subjectLevelPostPy[i]
          }
          }
        studyParameters["sessionLevelPostPy"] = tempObj;

      //primaryNextButton = this;
      //forAllNext (primaryNextButton)

      if (dataset.length == 0)
            studyParameters["descriptor"] = null;
         else
             studyParameters["descriptor"] = dataset
         //doSend($("#cov" ).val());
         doSend(JSON.stringify(studyParameters));

         $("#progressbar").show()
         }
});


/*$(".next5").click(function(){

  studyParameters["covarite"] = $("#cov" ).val()

   if (dataset.length == 0)
      studyParameters["descriptor"] = null;
   else
       studyParameters["descriptor"] = dataset
   //doSend($("#cov" ).val());
   doSend(JSON.stringify(studyParameters));

   $("#progressbar").show()


});*/

$(".next").click(function(){
     //alert($( "#sourcetype" ).val());
      forAllNext(this);
});


$(".previous0").click(function(){

    //alert(explOptions);
	if(animating) return false;
	animating = true;

	current_fs = $(this).parent();
	previous_fs = $(this).parent().prev();

	//de-activate current step on progressbar
	$("#progressbar1 li").eq($("fieldset").index(current_fs)).removeClass("active");

	//show the previous fieldset
	previous_fs.show();
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		},
		duration: 800,
		complete: function(){
			current_fs.hide();
			animating = false;
		},
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});





 function forAllNext (obj){

 	if(animating) return false;
 	animating = true;

 	current_fs = $(obj).parent();
 	next_fs = $(obj).parent().next();

 	//activate next step on progressbar using the index of next_fs
 	$("#progressbar1 li").eq($("fieldset").index(next_fs)).addClass("active");

 	//show the next fieldset
 	next_fs.show();
 	//hide the current fieldset with style
 	current_fs.animate({opacity: 0}, {
 		step: function(now, mx) {
 			//as the opacity of current_fs reduces to 0 - stored in "now"
 			//1. scale current_fs down to 80%
 			scale = 1 - (1 - now) * 0.2;
 			//2. bring next_fs from the right(50%)
 			left = (now * 50)+"%";
 			//3. increase opacity of next_fs to 1 as it moves in
 			opacity = 1 - now;
 			current_fs.css({'transform': 'scale('+scale+')'});
 			next_fs.css({'left': left, 'opacity': opacity});
 		},
 		duration: 800,
 		complete: function(){
 			current_fs.hide();
 			animating = false;
 		},
 		//this comes from the custom easing plugin
 		easing: 'easeInOutBack'
 	});

 }

$(".previous").click(function(){

    //alert(explOptions);
	if(animating) return false;
	animating = true;

	current_fs = $(this).parent();
	previous_fs = $(this).parent().prev();

	//de-activate current step on progressbar
	$("#progressbar1 li").eq($("fieldset").index(current_fs)).removeClass("active");

	//show the previous fieldset
	previous_fs.show();
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		},
		duration: 800,
		complete: function(){
			current_fs.hide();
			animating = false;
		},
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

$("#createStudy").click(function(){

  // $("#loading").show()
   });




});
