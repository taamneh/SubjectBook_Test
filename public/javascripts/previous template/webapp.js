
oneweb.DocumentReadyManager.addApplicationAction(function() {
	
	$("#header").on("click", ".dropdown", function(e, params) {
		if (params && params.preventClick) {
			return;
		}
		// przełączam kliknięty
		var holder = $(this).closest(".dropdown_holder").toggleClass("dropdown__opened");
		// przełączam inny otwarty
		var $otherHolder = $("#header .dropdown_holder.dropdown__opened").not(holder).removeClass("dropdown__opened");
		// wywołuję klik w innym otwartym jeśli jest A 
		$otherHolder.find("A.dropdown").trigger("click", {preventClick: true});

	});
	
	$(document).click(function(e, params) {
		var $target = $(e.target);
		var $dropDown = ($target.is(".dropdown") && $target) || $target.closest(".dropdown");
		if ($dropDown[0]) {
			return;
		}
		$(".dropdown_holder.dropdown__opened").each(function() {
			var $this = $(this).removeClass("dropdown__opened");
			$this.find("A.dropdown").click();
		})
	});
	
	$(document).on("click", "div.details > summary", function() {
		var $closest = $(this).closest("div.details");
		if (!$closest.attr("open")) {
			$closest.attr("open", "open");
		} else {
			$closest.removeAttr("open");
		}
	});
	
});

oneweb.DocumentReadyManager.addApplicationAction(function() {

	$(".box_inner > .footer > .backbutton A:not(.prepared)").click(function(e) {
		e.preventDefault();
		$(this).closest(".box").find(".box_inner > .header > .backbutton A").click();
	}).each(function() { $(this).addClass("prepared"); } );

	$("#messages-placeholder").toggle(!($("#oneweb-messages-placeholder").is(":empty") && !$("#not-supported-browser-info-header")[0]));

}, 100, false);


function replaceAddonsContent(addons) {
	for(var addonContainerId in addons) {
		if (addons.hasOwnProperty(addonContainerId)) {
			$('#' + addonContainerId).replaceWith(addons[addonContainerId]);
		}
	}
}

$(document).ready(function() {

	$(document).unbind("openPopup").bind("openPopup", function(e) {
		var el = $(e.target);
		el.show();
	});

	$('#trial-expired-banner-button').click(function() {
		document.location.href= "/redirect/change_account_plan";
	});

	function fixHeight() {
		var total = $(window).height();
		var left = total - $("#header_wrapper").outerHeight() - $("#footer_wrapper").outerHeight();
		if (left >= $("#content").outerHeight()) {
			$("#content_wrapper").outerHeight(left);
		} else {
			$("#content_wrapper").outerHeight("");
		}

		var box = $("#content .box");
		var otherH = 0;
		$("#content > *").each(function() {
			if ($.contains(this, box[0])) {
				return;
			}
			otherH += $(this).outerHeight();
		});

		if (box.length == 1 && box.hasClass("box__small") && box.closest(".box_wrapper")[0].className == "box_wrapper") {
			box.closest(".box_pre_wrapper").addClass("v").outerHeight(left - otherH);
		}
	}
	if ($("#footer_wrapper")[0]) {
		$(window).resize(fixHeight);
		$(document).bind("afterAjaxFlowAction", fixHeight);
		fixHeight();
	}



	$('#' +epoint.ow.flow.Utils.getFlowDivId("import_model_from_xml")).bind('afterAjaxFlowAction', function(e,flow,oldState,newState) {
		if(newState  == "imported") {
			Model.reloadToLatestVersion();
		}
	});

	$('#' +epoint.ow.flow.Utils.getFlowDivId("import_ddl")).bind('afterAjaxFlowAction', function(e,flow,oldState,newState) {
		if(newState  == "imported") {
			Model.reloadToLatestVersion();
		}
	});


});

$(document).ready(function() {

	var MenuUtil = {
		collapseHeader: function() {
    		var node = $("#header");
    		node.find(".expanded").removeClass("expanded");
    	},
        resizeHeader: function() {
        	var node = $("#header");
        	var availWidth = node.innerWidth();
        	node.find(".logo, .links, .user_menu").each(function() { 
        		availWidth -= $(this).outerWidth();
        	});
        	var expandables = MenuUtil._sortExpandablesByPriority(node.find(".expandable"));
        	expandables.each(function() {
        		var expandable = $(this);
        		var before = expandable.is(":visible") && expandable.outerWidth() || 0;
        		expandable.addClass("expanded");
        		var after = expandable.outerWidth();
        		//console.log(this.className, "before: " + before, "after: " + after, "need: " + (after-before) ,"avail: " + availWidth, "left after: " + (availWidth - (after - before)))
    			if (availWidth - (after - before) < 0) {
    				expandable.removeClass("expanded");
    				return false;
    			}
    			availWidth -= (after - before);
        	});
        },
    	_sortExpandablesByPriority: function(j) {
        	var prefix = "expand-priority-";
    		return $(j.toArray().sort(function (a, b) {
        		var index1 = a.className.indexOf(prefix);
        		var index2 = b.className.indexOf(prefix);
        		if (index1 == -1 && index2 == -1) {
        			return 0;
        		} else if (index2 == -1) {
        			return -1;
        		} else if (index1 == -1) {
        			return 1;
        		}
        		var priority1 = parseInt(a.className.substring(index1+prefix.length));
        		var priority2 = parseInt(b.className.substring(index2+prefix.length));
        		if (priority1 == priority2) {
        			return 0;
        		} else if (priority1 < priority2) {
        			return -1;
        		}
        		return 1;
        	}));
    	}
	};
	
	var $package = jsPackage("epoint.dbwm.ui");
	$package.MenuUtil = MenuUtil;
	
    if($('#diagram').length == 0) {
    	MenuUtil.resizeHeader();
    }
});

(function() {
	var $package = jsPackage("epoint.ow.flow");
	var Utils = $package.Utils;
	
	Utils.getCallbackFunction = function(reloadFlowNamesArray) {
		return function(response) {

			if (response == null) {
				return;
			}
			if (response.status == "flows") {
				var responseArray = response.data;
				
				
				
				for(var i = 0; i < responseArray.length; i++) {
					var flowResponse = responseArray[i];
					
					var flowName = flowResponse.flowName;
					var newState = flowResponse.currentStateName;
					var oldState = Utils.getCurrentState(flowName);

					Utils.setCurrentState(flowName, newState);
					
					Utils.triggerAjaxFlowEvent(flowName, "beforeAjaxFlowContentReplace", [flowName, oldState, newState]);
					$('#' + Utils.getFlowDivId(flowName)).html(flowResponse.stateContent);
					if (i == 0 && response.addons != null) {
						var addons = response.addons;
						replaceAddonsContent(addons);
					}

					Utils.triggerAjaxFlowEvent(flowName, "afterAjaxFlowContentReplace", [flowName, oldState, newState]);
					oneweb.DocumentReadyManager.runAllActions();
					
					Utils.triggerAjaxFlowEvent(flowName, "ajaxFlowPostState", [flowName, oldState, newState]);
					Utils.triggerAjaxFlowEvent(flowName, "ajaxFlowPreState", [flowName, oldState, newState]);
					
					var flowDivId = Utils.getFlowDivId(flowName);
					epoint.ow.Utils.unlockDiv(flowDivId);
					
					Utils.triggerAjaxFlowEvent(flowName, "afterAjaxFlowAction", [flowName, oldState, newState]);
				}
				
				
			} else if (response.status == "redirect") {
				document.location = response.data;
			} else {
				throw "Unsupported response status:" + response.status; 
			}
		};
	};
})();



(function() {
    var $package = jsPackage("epoint.ow");
    var JSErrorManager = $package.JSErrorManager
    
    // EDWM-971
    JSErrorManager.showError = function(errorContent) {
        // musimy wyciąć samą treść
        var withoutBeginning = errorContent.substring(errorContent.indexOf("<body>") + 6);
        var withoutEnd = withoutBeginning.substring(0, withoutBeginning.indexOf("</body>"));
        
        // nalanie treści z wykonaniem skryptów, a następnie wywołanie akcji OW
        $(JSErrorManager.getErrorDiv()).html(withoutEnd);
        setTimeout(function() {
            oneweb.DocumentReadyManager.runAllActions();
        }, 500);
        
        epoint.ow.popup.PopupManager.openPopupCover();
        epoint.ow.popup.PopupManager.openPopup(JSErrorManager.getErrorDiv().id);
    };
    
    
    // EDWM-655 Obsługa błędu dostępu wielu osób z jednego konta
    JSErrorManager.originalHandleAjaxError = JSErrorManager.handleAjaxError;
    
    JSErrorManager.isSingleAccessViolated = function(params) {
        if (params == null || params.jqXHR == null) {
            throw "jqXHR parameter must be valid XMLHttpRequest object";
        }
        return params.jqXHR.getResponseHeader('Vertabelo-Single-Access-Violated') == "true";
    };
    
    JSErrorManager.handleSingleAccessViolated = function(params) {
        window.location.href = '/session-closed';
    };
    
    JSErrorManager.handleAjaxError = function(event, jqXHR, ajaxSettings, thrownError) {
        
        if(JSErrorManager.isSingleAccessViolated({event : event, jqXHR : jqXHR, ajaxSettings : ajaxSettings, thrownError : thrownError})) {
            JSErrorManager.handleSingleAccessViolated({event : event, jqXHR : jqXHR, ajaxSettings : ajaxSettings, thrownError : thrownError});
            return;
        }
        
        JSErrorManager.originalHandleAjaxError(event, jqXHR, ajaxSettings, thrownError);
    };
})();

    