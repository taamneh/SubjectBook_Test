
var jsPackage =
/*Object*/ function (/*String*/ qualifiedPackageName) {
	CLASSES_ROOT = window;
	if (qualifiedPackageName == "") {
		return CLASSES_ROOT;
	}
	var names = qualifiedPackageName.split(".");
	var owner = CLASSES_ROOT;
	for (var i=0; i < names.length; i++) {
		var pName = names[i];
		if (!(pName in owner)) {
			owner[pName] = new Object();
		}
		owner = owner[pName];
	}
	return owner;
};

var $package = jsPackage("epoint.ow");

(function () {
	var JSErrorManager = $package.JSErrorManager = {
			registerSessionExpiredHandler : function() {
				$.ajaxPrefilter(function(opts) {
					var _success = opts.success;
					opts.success = function(data, status, jqXHR) {
						if (JSErrorManager.isSessionExpired({jqXHR : jqXHR, data : data, status : status})) {
							JSErrorManager.handleSessionExpired({jqXHR : jqXHR, data : data, status : status});
						} else {
							if (_success != null) {
								_success.apply(this, [ data, status, jqXHR ]);
							}
						}
					};
				});
			},
			
			registerDefaultAjaxSetup : function() {
				$.ajaxSetup({headers : {"Ow-Ajax": "true"}});
			},
			
			init : function() {
				JSErrorManager.registerDefaultAjaxSetup();
				JSErrorManager.registerSessionExpiredHandler();
				
				$(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
					JSErrorManager.handleAjaxError(event, jqXHR, ajaxSettings, thrownError);
				});
			},
			
			getErrorDiv : function() {
				var DIV_ID = "ajax_error_container";

				if($("#" + DIV_ID).size() == 0) {
					$("<div>")
						.attr("id", DIV_ID)
						.css({display: "none"})
						.addClass("popup")
						.addClass("ajax_error_container")
						.appendTo("body");
				}

				return $("#" + DIV_ID).get(0);
			},
			
			showError : function(errorContent) {
				JSErrorManager.getErrorDiv().innerHTML = errorContent;
				epoint.ow.popup.PopupManager.openPopupCover();
				epoint.ow.popup.PopupManager.openPopup(JSErrorManager.getErrorDiv().id);
			},
			
			isSessionExpired : function(params) {
				if (params == null || params.jqXHR == null) {
					throw "jqXHR parameter must be valid XMLHttpRequest object";
				}
				return params.jqXHR.getResponseHeader('Ow-Session-Expired') == "true";
			},
			
			handleSessionExpired : function(params) {
				if (params != null
						&& params.jqXHR != null
						&& params.jqXHR.responseText != null) {
					JSErrorManager.showError(params.jqXHR.responseText);
				} else {
					JSErrorManager.handleDefaultError();
				}
			},
			
			handleOneWebError : function(params) {
				if (params != null
						&& params.jqXHR != null
						&& params.jqXHR.responseText != null) {
					JSErrorManager.showError(params.jqXHR.responseText);
				} else {
					JSErrorManager.handleDefaultError();
				}
				
				if (console != null && console.log != null) {
					console.log(JSErrorManager.getErrorString(params));
				}
			},
			
			getErrorString : function(params) {
				var event = params.event || {};
				var jqXHR = params.jqXHR || {};
				var ajaxSettings = params.ajaxSettings || {};
				var thrownError = params.thrownError;
				
				var errorString = "event.type : " + event.type + "\n\n" +
				"jqXHR.status : " + jqXHR.status + "\n\n" +
				"jqXHR.statusText : " + jqXHR.statusText + "\n\n" +
				"jqXHR.readyState : " + jqXHR.readyState + "\n\n" +
				"ajaxSettings.url : " + ajaxSettings.url + "\n\n" +
				"ajaxSettings.type : " + ajaxSettings.type + "\n\n" +
				"ajaxSettings.contentType : " + ajaxSettings.contentType + "\n\n" +
				"ajaxSettings.dataType (expected): " + ajaxSettings.dataType;
				if (thrownError != null) {
					errorString = errorString + "\n\nthrownError : " + thrownError;
				}
				return errorString;
			},
			
			handleDefaultError : function() {
				JSErrorManager.showError("<span>Ajax error occur. Please report this to the support team.</span>");
			},
			
			handleTimeoutError : function(params) {
				JSErrorManager.handleDefaultError();
			},
			
			handleParseError : function(params) {
				throw JSErrorManager.getErrorString(params);
			},
		
			handleAjaxError : function(event, jqXHR, ajaxSettings, thrownError) {
				if (JSErrorManager.isSessionExpired({event : event, jqXHR : jqXHR, ajaxSettings : ajaxSettings, thrownError : thrownError})) {
					JSErrorManager.handleSessionExpired({event : event, jqXHR : jqXHR, ajaxSettings : ajaxSettings, thrownError : thrownError});
					return;
				}
				if (jqXHR.statusText == "timeout") {
					JSErrorManager.handleTimeoutError({event : event, jqXHR : jqXHR, ajaxSettings : ajaxSettings, thrownError : thrownError});
					return;
				}
				
				if ((jqXHR.statusText == "parsererror")) {
					JSErrorManager.handleParseError({event : event, jqXHR : jqXHR, ajaxSettings : ajaxSettings, thrownError : thrownError});
					return;
				}
				
				if (jqXHR.getResponseHeader('OneWeb-error-handler-response') == "true") {
					JSErrorManager.handleOneWebError({event : event, jqXHR : jqXHR, ajaxSettings : ajaxSettings, thrownError : thrownError});
					return;
				}
				
				if (jqXHR.readyState == 0 && jqXHR.status == 0) {
					// abort request, do nothing
					return;
				}
				
				if (jqXHR.status != 0 && jqXHR.status != 200) {
					JSErrorManager.handleDefaultError();
				}
				throw JSErrorManager.getErrorString({event : event, jqXHR : jqXHR, ajaxSettings : ajaxSettings, thrownError : thrownError});
			}
	};
	
	var Utils = $package.Utils = {
		createEvent : function(/*String*/ eventName) {
			var e = new jQuery.Event(eventName);
			e.preventNextAction = function() {
				e.nextActionPrevented = true; 
			};
			e.isNextActionPrevented = function() {
				return e.nextActionPrevented == true; 
			};
			
			return e;
		},
			
		enableDisableField : function(/*String*/ formName, /*String*/ fieldName, /*boolean*/ enable) {
			var fieldArray = this.getFields(formName, fieldName);
			if (fieldArray.length > 0) {
				if (!enable) {
					epoint.ow.forms.ErrorManager.clearFieldErrors(formName, fieldName);
					epoint.ow.forms.ErrorDisplayManager.showErrors(formName);
				}
			}
			
			for( var i = 0; i < fieldArray.length; i++) {
				var $field = $(fieldArray[i]);
				
				if ($field.hasClass("hasDatepicker")) {
					$field.datepicker(enable ? 'enable' : 'disable');
				} else {
					$field.attr("disabled", !enable);
					if(enable) { 
						$field.removeClass("disabled_field");
					} else {
						$field.addClass("disabled_field");
					}
					
				}
			}
		},
		
		refreshTextPrompts : function() {
			$("[prompttext]").each(function(){
				var fieldId = this.getAttribute('id');
				var $field = $(this);
				
				var promptText = $.trim($field.attr("prompttext"));
				
				if (fieldId == null || promptText.length == 0) {
					return;
				}
				
				var promptSpan = $("#" + fieldId + "_prompt_cover").get(0);
				if (promptSpan == null) {
					promptSpan = $("<span>").addClass("input_prompt_text").attr("id", fieldId + "_prompt_cover");
				}
				var $promptSpan = $(promptSpan);
				
				var fieldZIndex = $field.css('zIndex') == "auto" ? 0 : $field.css('zIndex');
				$field.css('z-index', fieldZIndex);
				$field.before(promptSpan);

				var promptSpanPaddingLeft = $promptSpan.css('paddingLeft') == null ? 0 : parseInt($promptSpan.css('paddingLeft'));
				var promptSpanPaddingRight = $promptSpan.css('paddingRight') == null ? 0 : parseInt($promptSpan.css('paddingRight'));
				$promptSpan.css({
					'z-index': fieldZIndex + 1,
					'width': $field.width() - promptSpanPaddingLeft - promptSpanPaddingRight
				});
				
				$promptSpan.text(promptText);
				
				$promptSpan.click(function() {
					$field.focus();
				});
				
				if($field.val() != '' ||
						$field.filter(':hidden').size() > 0 ||
						this == Utils.getElementWithFocus()){
					$promptSpan.hide();
				} else {
					$promptSpan.show();
				}
				
				
				$field.focus(function(){
					$promptSpan.hide();
				});
				$field.blur(function(){
					if($field.val() == ''){
						$promptSpan.show();
					}
				});
				
			});
		},
			
		replaceString :
			/*String*/ function (/*String*/ text, /*String*/ searchString, /*String*/ replacement) {
			var index = text.indexOf(searchString);
			if (index != -1) {
				text = text.substring(0, index) + replacement + text.substring(index + searchString.length, text.length);
			}
			
			return text;
		},
		
		replaceAllString :
			/*String*/ function (/*String*/ text, /*String*/ searchString, /*String*/ replacement) {
			var index = text.indexOf(searchString);

			while(index != -1) {
				text = text.substring(0, index) + replacement + text.substring(index + searchString.length, text.length);
				index = text.indexOf(searchString, index + replacement.length);
			}
			
			return text;
		},
		
		replaceI18NParameters :
			/*String*/ function (/*String*/ text, /*Object map: String->String*/ i18NParametersMap) {
			if (text == null) {
				throw "text can not be null";
			}
			
			for (var parameterName in i18NParametersMap) {
				if (i18NParametersMap.hasOwnProperty(parameterName)) {
					text = this.replaceString(text, '{' + parameterName + '}', i18NParametersMap[parameterName]);
				}
			}
			
			return text;
		},
		
		trimZeros : 
			/*String*/ function (/*String*/ number) {
			number = this.trim(number + "");
			
			var sign = false;
			if (number.charAt(0) == '-') {
				sign = true;
				number = number.substring(1, number.length);
			}
			
			var zerosNumber = 0;
	
			for (i = 0; i < number.length; i++) {
				if (number.charAt(i) == '0') {
					zerosNumber++;
				} else {
					break;
				}
			}
			if (number.length == zerosNumber || number.charAt(zerosNumber) == ".") {
				zerosNumber = Math.max(0, zerosNumber - 1);
			}			
			number = number.substring(zerosNumber, number.length);
	
			zerosNumber = 0;
			if (number.indexOf('.') != -1) {
				for (i = number.length - 1; i >= 0; i--) {
					if (number.charAt(i) == '0') {
						zerosNumber++;
					} else {
						break;
					}
				}
			}
			
			
			number = number.substring(0, number.length - zerosNumber);
			if (number.charAt(number.length - 1) == ".") {
				number = number.substring(0, number.length - 1);
			}
				
			if (sign == true) {
				number = '-' + number;
			}
			
			return number;
		},
		
		getFieldValues :
			/*Array of not empty String*/ function (/*String*/ formName, /*String*/ fieldName) {
			
			var vals;
			
			var fields = $(this.getForm(formName)).find("[name='" + fieldName + "']");
			if (fields.is('input:file')) {
				vals = fields.filter("input:file").filter("input:enabled").map(function (index) { return this.value; });
			} else if (fields.is("input:checkbox")){
				vals = fields
						.filter(":enabled")
						.filter(":checked")
						.map(function (index) { return $(this).val(); });
			} else if (fields.is("input:radio")){
				vals = fields
						.filter(":enabled")
						.filter(":checked")
						.map(function (index) { return $(this).val(); });
			} else {
				vals = fields.filter(":enabled").map(function(index) {return $(this).val();});
			}
			
			var notEmptyVals = [];
			for(var i = 0; i < vals.length; i++) {
				var v = vals[i];
				if (v.length > 0) {
					notEmptyVals[notEmptyVals.length] = v;
				}
			}
			
			if (fields.is('textarea')) {
				notEmptyVals = $.map(notEmptyVals, function(item, _) { return epoint.ow.Utils.replaceNewLineCharacters(item); });
			}
			
			return notEmptyVals;
		},
		
		getFieldValue :
			/*String or null*/ function (/*String*/ formName, /*String*/ fieldName) {
	
			var vals = this.getFieldValues(formName, fieldName);
			if (vals.length > 0) {
				return vals[0];
			} else {
				return null;
			}
		},
		
		arrayEquals :
		/*boolean*/ function (/*array*/ array1, /*array*/ array2) {
			if (array1.length != array2.length) {
				return false;
			} else {
				for(var i = 0; i < array1.length; i++) {
					if (array1[i] != array2[i]) {
						return false;
					}
				}
			}
			return true;
		},
		
		trim :
		/*String*/ function (/*String*/ str) {
			if (str == null) {
				return null;
			}
			str = str + "";
			if (str.trim) {
				return str.trim();
			} else {
				//find and remove spaces from left and right hand side of string
				return str.replace(/(^\s*)|(\s*$)/g, "");
			}
		},
		
		compare :
			/*-1, 0, 1, null in case of error*/ function (/*String*/ value1, /*String*/ value2) {
				value1 = this.trimZeros(value1);
				value2 = this.trimZeros(value2);
				
				var re = new RegExp('^(-?)(\\d+)(\\.?)(\\d*)$');
				if (!re.test(value1) || !re.test(value2)) {
					return null;
				}
			
				if (value1.charAt(0) == '-') {
					if (value2.charAt(0) == '-') {
						return this.compare(value2.substring(1, value2.length), value1.substring(1, value1.length));
					} else {
						return -1;
					}
				} else if (value2.charAt(0) == '-') {
					return 1;
				}
	
				value1.match(re);
				var intPart1 = RegExp.$2;
				var fraq1 = RegExp.$4;
				value2.match(re);
				var intPart2 = RegExp.$2;
				var fraq2 = RegExp.$4;
				
				if (intPart1.length > intPart2.length) {
					return 1;
				} else if (intPart1.length < intPart2.length) {
					return -1;
				} else {
					for (var i = 0; i < intPart1.length; i++) {
						if (intPart1.charAt(i) > intPart2.charAt(i)) {
							return 1;
						}
						if (intPart1.charAt(i) < intPart2.charAt(i)) {
							return -1;
						}
					}
				}
				
				var len1 = fraq1.length;
				var len2 = fraq2.length;
				var maxLen = Math.max(len1, len2);
				for (var i = 0; i < maxLen; i++) {
					if (i < len1 && i < len2) {
						if (fraq1.charAt(i) > fraq2.charAt(i)) {
							return 1;
						}
						if (fraq1.charAt(i) < fraq2.charAt(i)) {
							return -1;
						}
					} else if (i >= len1 && i < len2) {
						return -1;
					} else if (i < len1 && i >= len2) {
						return 1;
					}
				}
				
				return 0;
		},
		
		arraySearch :
			/* first index in which targetElment (value) is found within an array, or -1 if nothing is found.*/ function (/*array*/ array, targetObject) {
			if (array == null || targetObject == null) {
				return -1;
			}
			for (var i = 0; i < array.length; i++) {
				if (array[i] == targetObject) {
					return i;
				}
			}
			
			return -1;
		},
		
		arraySearchIgnoreCase :
			/* first index in which targetElment (value) is found within an array, or -1 if nothing is found.*/ function (/*array*/ array, targetObject) {
			if (array == null || targetObject == null) {
				return -1;
			}
			for (var i = 0; i < array.length; i++) {
				var item = array[i];
				if (item != null && item.toLowerCase() == targetObject.toLowerCase()) {
					return i;
				}
			}
			
			return -1;
		},
		
		removeDuplicate :
			/*new array*/ function (/*array*/ array) {
			
			var newArray = [];
			for(var i = 0; i < array.length; i++) {
				if (this.arraySearch(newArray, array[i]) == -1) {
					newArray.push(array[i]);
				}
			}
			return newArray;
		},
		
		parseDate :
			/*Date object or null if error occours*/ function (/*String*/ dateString, /*String*/ dateFormat) {
			var PATTERNS = ["yyyy", "MM", "dd", "HH", "mm", "ss", "SSS"];
			var values = [];
			
			var formatPattern = dateFormat;
			for(var i = 0; i < PATTERNS.length; i++) {
				var replacement = "";
				for(var c = PATTERNS[i].length; c > 0; c--) {
					replacement = replacement + "\\d";
				}
				formatPattern = this.replaceString(formatPattern, PATTERNS[i], replacement);
			}
			formatPattern = "^" + formatPattern + "$";
			var re = new RegExp(formatPattern);
			if (!re.test(dateString)) {
				return null;
			}
			
			for(var i = 0; i < PATTERNS.length; i++) {
				var pattern = PATTERNS[i];
				var idx = dateFormat.indexOf(pattern);
				if (idx != -1) {
					var value = parseInt(dateString.substring(idx, idx + pattern.length), 10);
					if (isNaN(value)) {
						return null;
					}
					values[i] = value;
				} else {
					values[i] = 0;
				}
			}
			// month range is 0-11
			values[1] = values[1] == 0 ? values[1] : values[1] - 1;
			
			var date = new Date();
			date.setFullYear(values[0], values[1], values[2]);
			date.setHours(values[3], values[4], values[5], values[6]);
			return date;
		},
		
		getSingleValueMultifieldValue : 
			/*String*/ function (/*String*/ formName, /*Array*/ fieldNames, /*String*/ fieldValueSeparator) {
			
			var multiValue = "";
			for (var i = 0; i < fieldNames.length; i++) {
				if ((multiValue.length > 0) && (fieldValueSeparator != null)) {
					multiValue = multiValue + fieldValueSeparator;
				}
				
				var fieldValue = this.getFieldValue(formName, fieldNames[i]);
				if (fieldValue != null) {
					multiValue = multiValue + fieldValue;
				}
			}
			
			return multiValue;
		},
		
		isAllFieldsFilled : function(/*String*/ formName, /*Array of String*/ fieldsNamesArray) {
			for(var i = 0; i < fieldsNamesArray.length; i++) {
				if (this.getFieldValues(formName, fieldsNamesArray[i]).length == 0) {
					return false;
				}
			}
			return true;
		},
		
		/*Array of Field*/ getFields : function(/*String*/ formName, /*String*/ fieldName) {
			var fields = $(this.getForm(formName)).find("[name=" + fieldName + "]");
			
			var result = [];
			for(var i = 0; i < fields.length; i++) {
				result[result.length] = fields[i];
			}
	
			return result;
		},
		
		/*Field or null*/ getField : function(/*String*/ formName, /*String*/ fieldName) {
			var fields = this.getFields(formName, fieldName);
			if (fields.length > 0) {
				return fields[0];
			} else {
				return null;
			}
		},
		
		/*Form or null*/ getForm : function(/*String*/ formName) {
			return document.forms[formName];
		},
		
		/*String or null*/ replaceNewLineCharacters : function(/*String*/ value) {
			if (value == null) {
				return null;
			}
			return value.replace(/\r\n/g, "\n").replace(/\r/g, "\n");
		},
		
		getUrlParameters : function(url) {
			var map = {};
			
			if (url == null || url.length == 0) {
				return map;
			}

			var array = url.substring(url.indexOf("?")+1).split("&");

			for(var i = 0; i < array.length; i++) {
			    var pair = array[i];
			    var index = pair.indexOf("=");
			    var name = pair.substring(0, index);
			    var value = pair.substring(index+1);

			    if (!map[name]) {
			        map[name] = [];
			    }

			    map[name].push(value);
			}

			return map;
		},
		
		formatFileSize : function(size) {
	        var level = 0;
	        while (size >= 1024 && level < 4) {
	            level++;
	            size /= 1024;
	        }
	        
	        if (level > 0) {
	            size = size.toFixed(1);
	        } else {
	            size = "" + Math.round(size);
	        }
	        
			var unit = "";
			if (level == 0) {
				unit = "B";
			} else if (level == 1) {
				unit = "KB";
			} else if (level == 2) {
				unit = "MB";
			} else if (level == 3) {
				unit = "GB";
			} else {
				unit = "TB";
			}

			return size + " " + unit;
		},
	    
	    getFileExtension : function(name) {
	    	if (name == null) {
	    		throw "name can not be null";
	    	}
	    	
	    	var index = name.lastIndexOf(".");
			if (index != -1) {
				return name.substr(index + 1); 
			} else {
				return "";
			}
	    },
		
		lockDiv : function(divId) {
			$("#" + divId).each(function(i, element) {
				var $element = $(element);
				$("<div>")
					.addClass('box_cover')
					.attr('id', Utils.getLockDivId(divId))
					.css({
						left: $element.position().left,
						top: $element.position().top,
						width: $element.width(),
						height: $element.height()})
					.insertBefore($element);
				});
		},
		
		unlockDiv : function(divId) {
			$("#" + Utils.getLockDivId(divId)).remove();
		},
		
		getLockDivId : function(divId) {
			return divId + "_cover";
		},

		getJsObjectByName : function(name) {
			if (name == null) {
				throw "Name can not be null";
			}

			if ((typeof name) !== (typeof "")) {
				throw "Name must be a string";
			}

			var context = window;
			var index = -1;
			var part;
			while((index = name.indexOf('.')) != -1) {
				part = name.substring(0, index);
				name = name.substring(index + 1);

				if (context[part] == null) {
					return null;
				}

				context = context[part];
			}

			return context[name];
		},

		getJsObjectPackageByName : function(name) {
			if (name == null) {
				throw "Name can not be null";
			}

			if ((typeof name) !== (typeof "")) {
				throw "Name must be a string";
			}

			if (name.indexOf('.') == -1) {
				return window;
			} else {
				name = name.substring(0, name.lastIndexOf('.'))
			}

			return Utils.getJsObjectByName(name);
		},

		bindEvent : function(eventName, targetId, handlerFnName, handlerFnArgs) {
			var fn = Utils.getJsObjectByName(handlerFnName);
			if (fn == null) {
				throw "Function with name '" + handlerFnName + "' not found";
			}
			
			var ar = Array.prototype.slice.call(arguments, 3);
			$('#' + targetId).bind(eventName, function(e) {
				fn.apply(Utils.getJsObjectPackageByName(handlerFnName),
						[e].concat(ar));});
		},
		
		buttonDefaultsHandler : function(e, confirmMessage) {
			var preventAllFn = function(e) {
				e.stopImmediatePropagation();
				e.preventDefault();
				if (e.preventNextAction) {
					e.preventNextAction();
				}
			};
			
			if ($(e.currentTarget).hasClass('disabled')) {
				preventAllFn(e);
				return;
			}
			if (confirmMessage != null && !confirm(confirmMessage)) {
				preventAllFn(e);
				return;
			}
		},

		getElementWithFocus : function() {
			if (document.activeElement == null) {
				return $(':focus').get(0);
			} else if (document.activeElement == document.body) {
				return null;
			} else {
				return document.activeElement;
			}
		}
	};

})();



// RUN OneWeb onReady actions
(function() {
	oneweb.DocumentReadyManager.addOnewebAction(epoint.ow.JSErrorManager.init, 999, true);
	
	oneweb.DocumentReadyManager.addOnewebAction(function() {epoint.ow.Utils.refreshTextPrompts();}, 999, false);
	oneweb.DocumentReadyManager.addOnewebAction(function() { $('.initialHide').hide().removeClass('initialHide');}, 1, false);

	oneweb.DocumentReadyManager.addOnewebAction(function() {
		$.ui.autocomplete.prototype._renderItem = function(ul, item) {
			var fieldValue = this.element.val().toLowerCase();
			var label = item.label;
			if (label == null) {
				label = item.value;
			}

			var from = label.toLowerCase().indexOf(fieldValue);
			if (from != -1) {
				var to = from + fieldValue.length;
				var foundWord = label.substring(from, to);
				label = label.replace(foundWord, "<strong>" + foundWord + "</strong>");
			}
			
			return $( "<li></li>" )
						.data( "item.autocomplete", item )
						.append( $( "<a></a>" ).html(label) )
						.appendTo( ul );
			}
		}, 1000, false);
})();

