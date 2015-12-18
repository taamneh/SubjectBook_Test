
(function() {
	var $package = jsPackage("epoint.ow.flow");
	
	var Utils = $package.Utils = {
		currentState : {},
		
		setCurrentState : function(flowName, stateName) {
			Utils.currentState[flowName] = stateName;
		},
		
		getCurrentState : function(flowName) {
			var name = Utils.currentState[flowName];
			if (name) {
				return name;
			} else {
				return null;
			}
		},
		
		getFlowNameFromUrl : function(url) {
			var parameters = epoint.ow.Utils.getUrlParameters(url)['flow.flow'];
			if (parameters == null || parameters.length == 0) {
				return null;
			}
			return parameters[0];
		},
		
		getTransitionNameFromUrl : function(url) {
			var parameters = epoint.ow.Utils.getUrlParameters(url)['flow.transition'];
			if (parameters == null || parameters.length == 0) {
				return null;
			}
			return parameters[0];
		},
		
		getReloadFlowNamesFromUrl : function(url) {
			var parameters = epoint.ow.Utils.getUrlParameters(url)['flow.reloadFlow'];
			if (parameters == null) {
				return [];
			}
			return epoint.ow.Utils.removeDuplicate(parameters);
		},
		
		isAjaxUrl : function(url) {
			return url != null && Utils.getReloadFlowNamesFromUrl(url).length > 0;
		},
		
		triggerAjaxFlowEvent : function(flowName, eventName, paramsArray) {
			if (paramsArray == null) {
				paramsArray = [];
			}
			
			var event = epoint.ow.Utils.createEvent(eventName);
			$("#" + Utils.getFlowDivId(flowName)).trigger(event, paramsArray);
			return !event.isNextActionPrevented();
		},
		
		triggerBeforeAjaxFlowActionEvent : function(url) {
			var triggerEventFlowNamesArray = Utils.getReloadFlowNamesFromUrl(url);
			var flowName = Utils.getFlowNameFromUrl(url);
			
			if (flowName != null
					&& epoint.ow.Utils.arraySearch(triggerEventFlowNamesArray, flowName) == -1) {
				triggerEventFlowNamesArray.push(flowName);
			}
			
			for(var i = 0; i < triggerEventFlowNamesArray.length; i++) {
				var name = triggerEventFlowNamesArray[i];
				if (!Utils.triggerAjaxFlowEvent(name, "beforeAjaxFlowAction", [name, url])) {
					return false;
				};
			}
			
			return true;
		},
		
		handleUrl : function(url) {
			var reloadFlowNamesArray = Utils.getReloadFlowNamesFromUrl(url);
			
			if (url == null || url == "" || reloadFlowNamesArray.length == 0) {
				return;
			}
			if (!Utils.triggerBeforeAjaxFlowActionEvent(url)) {
				return;
			}
			
			var callbackFn = Utils.getCallbackFunction(reloadFlowNamesArray);
			
			Utils.lockFlowDivs(reloadFlowNamesArray);
			oneweb.CsrfTokenManager.postAjax(url, null, callbackFn, "json");
		},
		
		getCallbackFunction : function(reloadFlowNamesArray) {
			return function(response) {
				if (response == null) {
					return;
				}
				if (response.status == "flows") {
					var responseArray = response.data;
					var transitionName = response.transitionName;

					var elementWithFocus = epoint.ow.Utils.getElementWithFocus();

					for ( var i = 0; i < responseArray.length; i++) {
						var flowResponse = responseArray[i];
						
						var flowName = flowResponse.flowName;
						var newState = flowResponse.currentStateName;
						var oldState = Utils.getCurrentState(flowName);

						Utils.setCurrentState(flowName, newState);
						
						Utils.triggerAjaxFlowEvent(flowName, "beforeAjaxFlowContentReplace", [flowName, oldState, newState, transitionName]);
						$('#' + Utils.getFlowDivId(flowName)).html(flowResponse.stateContent);
						Utils.triggerAjaxFlowEvent(flowName, "afterAjaxFlowContentReplace", [flowName, oldState, newState, transitionName]);
						oneweb.DocumentReadyManager.runAllActions();
						
						Utils.triggerAjaxFlowEvent(flowName, "ajaxFlowPostState", [flowName, oldState, newState, transitionName]);
						Utils.triggerAjaxFlowEvent(flowName, "ajaxFlowPreState", [flowName, oldState, newState, transitionName]);
						
						var flowDivId = Utils.getFlowDivId(flowName);
						epoint.ow.Utils.unlockDiv(flowDivId);
						
						Utils.triggerAjaxFlowEvent(flowName, "afterAjaxFlowAction", [flowName, oldState, newState, transitionName]);
					}

					if (elementWithFocus != null && elementWithFocus.id != null) {
						$("#" + elementWithFocus.id).focus();
					}
				} else if (response.status == "redirect") {
					document.location = response.data;
				} else {
					throw "Unsupported response status:" + response.status; 
				}
			};
		},
		
		submitForm : function(formName, enableValidation, action) {
			if (action == null) {
				action = form.action;
			}
			if (enableValidation == null) {
				enableValidation = true;
			}
			
			var form = epoint.ow.Utils.getForm(formName);
			var reloadFlowNamesArray = Utils.getReloadFlowNamesFromUrl(action);
			
			var deferred = jQuery.Deferred();
				
			deferred.done(function () {
				epoint.ow.forms.Utils.runFormValidationSuccessActions(formName);
				
				if (!Utils.triggerBeforeAjaxFlowActionEvent(action) ||
						!epoint.ow.forms.Utils.triggerBeforeFormSubmitEvent(formName, [action])) {
					Utils.unlockFlowDivs(reloadFlowNamesArray);
					return;
				}
				
				var callbackFn = Utils.getCallbackFunction(reloadFlowNamesArray);
				
				if (epoint.ow.forms.Utils.containsNotEmptyFileFields(formName)) {
					epoint.ow.forms.AjaxManager.submitFormWithIFrame(formName, callbackFn, ["iframe_submit", true], action, "json");
				} else {
					epoint.ow.forms.AjaxManager.submitForm(formName, callbackFn, null, action, "json", null);
				}
			});
				
			deferred.fail(function () {
				Utils.unlockFlowDivs(reloadFlowNamesArray);
				epoint.ow.forms.Utils.runFormValidationFailedActions(formName);
			});
			
			Utils.lockFlowDivs(reloadFlowNamesArray);
			if (enableValidation) {
				epoint.ow.forms.Utils.validateForm(formName, deferred);
			} else {
				deferred.resolve();
			}
		},
		
		lockFlowDivs : function(flowNamesArray) {
			for(var i = 0; i < flowNamesArray.length; i++) {
				epoint.ow.Utils.lockDiv(Utils.getFlowDivId(flowNamesArray[i]));
			}
		},
		
		unlockFlowDivs : function(flowNamesArray) {
			for(var i = 0; i < flowNamesArray.length; i++) {
				epoint.ow.Utils.unlockDiv(Utils.getFlowDivId(flowNamesArray[i]));
			}
		},
		
		getFlowDivId : function(flowName) {
			return "flow_" + flowName + "_div"
		},

		ajaxFlowHandler : function(e, actionUrl) {
			if (actionUrl == null) {
				var href = $(e.currentTarget).attr('href');
				if (href != null && href != '') {
					actionUrl = href;
				}
			}
			e.preventDefault(); epoint.ow.flow.Utils.handleUrl(actionUrl);
		}
	};
})();