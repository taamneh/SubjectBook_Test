
$(document).ready(function(){


// this is to prevent user from hitting enter
  $(window).keydown(function(event){
    if(event.keyCode == 13) {
      event.preventDefault();
      return false;
    }
  });

// slaha aldeeee
var studyParameters = {}

var explOptions = [];
var respOptions = [];

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
 function onMessage(evt)
{

  // at the begining we recieve two messages one for the number of subjects and the second is the number of session and their names..
 // when we check the subjects number
    if(messageNo ==1) {
       var subjNo = document.getElementById("numSubj").value;
        var r = confirm( evt.data + " Subjects were found Not "+  subjNo + "! Would you like to continue");
        if (r == true) {
           subjectOk = true;
           if($("#quickCreation").is(":checked")){
                            doSend("QUICK")

                            //to submit the form now and regirect to list of studies
                            $('#sb').trigger('click');

                  }

        } else {
           messageNo = messageNo -2;
           doSend("BACK");
           $("#loading").hide()
            //$(".previous").trigger("click");
        }
    }
    // to decide the name of baseline experiment
     if(messageNo ==2 && subjectOk)
      {
          var str = evt.data
          str = str.replace("[", "");
          str = str.replace("]", "");

          var res = str.split(",");
          var text = "Enter the number of the session that you would like to use as a baseline session:\n"

          resBl = res
          $( "#mdl1Sub" ).empty();

          //onbaslinechange();
          for(var i =0; i< res.length; i++)
          {
             var num = i+1;
             text = text + num + ". " +  res[i] + "\n"
             var id = "bl" + num
              $( "#mdl1Sub" ).append( "<input id=\"" + id + "\" type=\"radio\" name=\"baseline\" value=\"female\" checked=\"true\" >" + res[i]  + "<br>" );
              studyParameters["baseLineFolder"] = res[i]
          }



          $( "#mdl1" ).trigger( "click" );

            $('input[type=radio][name=baseline]').change(function() {
                     onbaslinechange();
             });



          forAllNext(firstNextButton)
          $("#loading").hide()
           onbaslinechange();
      }

    // recieve the possible explantory values
      if(messageNo ==3)
      {
          //alert(evt.data);
          // if user enter signal that is not exist
         if(evt.data == "WRONG"){
             //$(".previous").trigger("click");
            $("#loading").hide()
             alert("We could not find that signal in one or more folders! Please select another one")
             messageNo--;

          }
          else {

             var str = evt.data
                    str = str.replace("[", "");
                    str = str.replace("]", "");

                    var res = str.split(",");
                    var text = "Enter the number of the varaible that you would like to use as key explanatory variable:\n"

                      resEx = res
                    $( "#mdl1Expl" ).empty();
                    for(var i =1; i< res.length; i++)
                    {
                       var num = i;
                       text = text + num + ". " +  res[i] + "\n"
                        var id = "expl" + num
                       $( "#mdl1Expl" ).prepend( "<input id=\"" + id + "\" type=\"radio\" name=\"explanatory\" value=\"female\" checked=\"true\">" + res[i]  + "<br>" );
                        studyParameters["explanColNo"] = i
                    }

                    $( "#mdl2" ).trigger( "click" );

                     $('input[type=radio][name=explanatory]').change(function() {
                              onExpChange();
                       });



                    forAllNext(secondNextButton);
                    $("#loading").hide()

          }


      }
       if(messageNo ==4)
        {

             if(evt.data == "WRONG"){
                 $("#loading").hide()
                 alert("We could not find that signal in one or more folders! Please select another one")
                 messageNo--;

            }
            else
            {
               var str = evt.data
                        str = str.replace("[", "");
                        str = str.replace("]", "");

                        var res = str.split(",");
                        var text = "Enter the number of the varaible that you would like to use as key Response variable:\n"

                        resRe = res
                        $( "#mdl1Resp" ).empty();
                        for(var i =1; i< res.length; i++)
                        {
                           var num = i;
                           text = text + num + ". " +  res[i] + "\n"
                              var id = "resp" + num
                            $( "#mdl1Resp" ).prepend( "<input id=\"" + id + "\" type=\"radio\" name=\"Response\" value=\"female\" checked=\"true\">" + res[i]  + "<br>" );
                            studyParameters["respColNo"] = i
                        }

                        $( "#mdl3" ).trigger( "click" );

                         $('input[type=radio][name=Response]').change(function() {
                                       onRespChange();
                             });



                        forAllNext(thirdNextButton);
                        //alert(JSON.stringify(studyParameters))
                         $("#loading").hide()

            }

        }

   messageNo++;
  }
  function onOpen(evt)
  {
        websocket.send(userName);
  }

  function doSend(message)
  {
    websocket.send(message);
  }





    function init()
    {
      // alert('caling init')
       websocket.send(userName);
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



$( '.dropdown-menu .small2' ).on( 'click', function( event ) {

   var $target = $( event.currentTarget ),
       val = $target.attr( 'data-value' ),
       $inp = $target.find( 'input' ),
       idx;

   if ( ( idx = respOptions.indexOf( val ) ) > -1 ) {
      respOptions.splice( idx, 1 );
      setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
   } else {
      respOptions.push( val );
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
       $inp = $target.find( 'input' ),
       idx;

   if ( ( idx = explOptions.indexOf( val ) ) > -1 ) {
      explOptions.splice( idx, 1 );
      setTimeout( function() { $inp.prop( 'checked', false ) }, 0);
   } else {
      explOptions.push( val );
      setTimeout( function() { $inp.prop( 'checked', true ) }, 0);
   }

   $( event.target ).blur();

   console.log( explOptions );
   return false;
});


$("form" ).submit(function( event ) {
   studyParameters["covarite"] = $("#cov" ).val()

   if (dataset.length == 0)
      studyParameters["descriptor"] = null;
   else
       studyParameters["descriptor"] = dataset
   //doSend($("#cov" ).val());
   doSend(JSON.stringify(studyParameters));

   alert( "Your study is being created. Please check back after few minutes" );
  $("#loading").show()
  //event.preventDefault();
    });



$(".next0").click(function(){

   // to build the descriptor

  // alert(dataset.length);
   var r = fun();

   //alert(JSON.stringify(dataset));

   if(r == 0)
   {
    alert("please fill in all fields")
   }
   else {

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


$(".next1").click(function(){
     //alert($( "#sourcetype" ).val());
     if(document.getElementById("study_name").value == "")
        alert("Please enter the name of study")
     else if(document.getElementById("folder_name").value == "")
         {
            alert("Please enter the URL")
         }
     else
     {
     var studyId = document.getElementById("folder_id").value;
     doSend(studyId);

     studyParameters["studyName"] = document.getElementById("study_name").value
     studyParameters["studyLocation"] = document.getElementById("folder_id").value
     studyParameters["sourceType"] = document.getElementById("sourcetype").value

      if($("#publicCh").is(":checked"))
          studyParameters["public"] = 1
       else
        studyParameters["public"] = 0
       if($("#portraitCh").is(":checked"))
          studyParameters["portrait"] =1
       else
        studyParameters["portrait"] =0

       //alert(JSON.stringify(studyParameters))

     $("#loading").show()

     firstNextButton = this;
      //forAllNext(this);

	}
});


$(".next2").click(function(){
     //alert($( "#sourcetype" ).val());


   studyParameters["primaryExp"] = $("#explanatoryPrim" ).val()
   doSend($("#explanatoryPrim" ).val())

    var tempObj = {}
    for(var i =0; i< explOptions.length; i++){
       var num = i+1
      tempObj[num.toString()] = explOptions[i]
      }

    studyParameters["secondadryExp"] = tempObj;

    studyParameters["StaticBefore"] = $( "#staticB" ).val();
    studyParameters["StaticAfter"] = $( "#staticA" ).val();




    //alert(JSON.stringify(studyParameters))

    $("#loading").show()
    secondNextButton = this
    //forAllNext(this);

});


$(".next3").click(function(){
     studyParameters["primaryRes"] = $( "#responsePrim" ).val()
     doSend($("#responsePrim" ).val())
    var tempObj = {}
    for(var i =0; i< respOptions.length; i++){
     var num = i+1
      tempObj[num.toString()] = respOptions[i]
      }

    studyParameters["secondadryRes"] = tempObj;


    $("#loading").show()
    thirdNextButton = this
    //forAllNext(this);

});


$(".next").click(function(){
     //alert($( "#sourcetype" ).val());
      forAllNext(this);


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

// becuse we recieve two message : subjectno and session list
   switch (messageNo){
        case 3:
           messageNo = messageNo -2
           break;
        case 4:
           messageNo--;
           break;
         case 5:
           messageNo--;
           break;
       }
    doSend("BACK");

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
