(function() {
	var $package = jsPackage("epoint.ow.forms");
	
	var Utils = $package.Utils = {

		/**
		 * Zwraca błąd walidacji walidatora JS.
		 * 
		 * @param errorCode kod błędu
		 * @param errorParameters mapa parametrów z walidatora, postaci: {klucz : wartość}
		 * @param mainFieldName nazwa głównego pola, którego błąd dotyczy lub <code>null</code>
		 * @param notValidFieldNames lista nazw pól, których ten błąd dotyczy - opcjonalna
		 * @return błąd walidacji walidatora JS.
		 */
		reportError : function(/*String*/ errorCode, /*key-value map*/ errorParameters, /*String*/ mainFieldName, /*array of String*/ notValidFieldNames) {
			return {
				errorCode : errorCode,
				errorParameters : errorParameters,
				mainFieldName : mainFieldName,
				notValidFieldNames : notValidFieldNames
			};
		},
	
		/**
		 * Zwraca domyślny błąd walidacji walidatora JS.
		 * 
		 * @param errorParameters mapa parametrów z walidatora, postaci: {klucz : wartość}.
		 * @param mainFieldName nazwa głównego pola, którego błąd dotyczy lub <code>null</code>
		 * @param notValidFieldNames lista nazw pól, których ten błąd dotyczy - opcjonalna
		 * @return błąd walidacji walidatora JS.
		 */
		reportDefaultError : function(/*key-value map*/ errorParameters, /*String*/ mainFieldName, /*array of String*/ notValidFieldNames) {
			return Utils.reportError('default', errorParameters, mainFieldName, notValidFieldNames);
		},
		
		/**
		 * Zwraca identyfikator pola o podanej nazwie.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @return identyfikator pola formularza
		 */
		getFieldId : function (formName, fieldName) {
			return formName + "_" + fieldName;
		},

		/**
		 * Zwraca identyfikator kontenera błędów pola o podanej nazwie.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @return identyfikator kontenera błędów pola formularza
		 */
		getFieldErrorContainerId : function (formName, fieldName) {
			return Utils.getFieldId(formName, fieldName) + "_error_container";
		},
		
		/**
		 * Zwraca identyfikator kontenera błędów formularza o podanej nazwie.
		 * 
		 * @param formName nazwa formularza
		 * @return identyfikator kontenera błędów formularza
		 */
		getFormErrorContainerId : function (formName) {
			return formName + "_errors";
		},
		
		/**
		 * Wyzwala zdarzenie <code>beforeFormSubmit</code>.
		 * 
		 * @returns <code>false</code> jeżeli submit formularza powinien zostać przerwany
		 */
		triggerBeforeFormSubmitEvent : function (formName, parametersArray) {
			var form = epoint.ow.Utils.getForm(formName);
			
			if (parametersArray == null) {
				parametersArray = [];
			}
			
			var event = epoint.ow.Utils.createEvent("beforeFormSubmit");
			$(form).trigger(event, [formName].concat(parametersArray));
			return !event.isNextActionPrevented();
		},
		
		/**
		 * Refejstruje domyślny handler zdarzenia <code>submit</code>.
		 * 
		 * @param formName nazwa formularza
		 */
		registerDefaultSubmitEventHandler : function(formName) {
			var form = epoint.ow.Utils.getForm(formName);
			
			$(form).submit(function(e, enableValidation, action) {
				e.preventDefault();
				if (action == null) {
					action = form.action;
				}
				if (enableValidation == null) {
					enableValidation = ConfigManager.isJsValidationEnabled(formName);
				}

				if (epoint.ow.flow.Utils.isAjaxUrl(action)) {
					epoint.ow.flow.Utils.submitForm(formName, enableValidation, action);
				} else {
					var deferred = jQuery.Deferred();
					
					deferred.done(function () {
						Utils.runFormValidationSuccessActions(formName);
						
						if (!Utils.triggerBeforeFormSubmitEvent(formName, [action])) {
							return;
						}
						
						form.action = action;
						oneweb.CsrfTokenManager.submitForm(form.getAttribute('name'));
					});
					
					deferred.fail(function () {
						Utils.runFormValidationFailedActions(formName);
					});
					
					if (enableValidation) {
						Utils.validateForm(formName, deferred);
					} else {
						deferred.resolve();
					}
				}
				
			});
		},
		
		/**
		 * Uruchamia akcje związane z negatywną walidacją formularza.
		 * 
		 * @param formName nazwa formularza
		 */
		runFormValidationFailedActions : function(formName) {
			var form = epoint.ow.Utils.getForm(formName);
			
			ErrorDisplayManager.showErrors(formName);
			ErrorDisplayManager.scrollToErrors(formName);
			ErrorManager.triggerErrorRelatedEvents(formName);
		},
		
		/**
		 * Uruchamia akcje związane z pozytywną walidacją formularza.
		 * 
		 * @param formName nazwa formularza
		 */
		runFormValidationSuccessActions : function(formName) {
			var form = epoint.ow.Utils.getForm(formName);
			
			ErrorDisplayManager.showErrors(formName);
			ErrorManager.triggerErrorRelatedEvents(formName);
		},
		
		/**
		 * Uruchamia akcje związane z negatywną walidacją grupy powiązanych pól formularza.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		runRelatedFieldsValidationFailedActions : function(formName, fieldName) {
			var field = epoint.ow.Utils.getField(formName, fieldName);
			
			ErrorDisplayManager.showErrors(formName);
			ErrorDisplayManager.scrollToErrors(formName);
			ErrorManager.triggerErrorRelatedEvents(formName);
		},
		
		/**
		 * Uruchamia akcje związane z pozytywną walidacją grupy powiązanych pól formularza.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		runRelatedFieldsValidationSuccessActions : function(formName, fieldName) {
			var field = epoint.ow.Utils.getField(formName, fieldName);
			
			ErrorDisplayManager.showErrors(formName);
			ErrorDisplayManager.scrollToErrors(formName);
			ErrorManager.triggerErrorRelatedEvents(formName);
		},
		
		/**
		 * Uruchamia akcje związane z negatywną walidacją pola formularza.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		runFieldValidationFailedActions : function(formName, fieldName) {
			var field = epoint.ow.Utils.getField(formName, fieldName);
			
			ErrorDisplayManager.showErrors(formName);
			ErrorManager.triggerErrorRelatedEvents(formName);
		},
		
		/**
		 * Uruchamia akcje związane z pozytywną walidacją pola formularza.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		runFieldValidationSuccessActions : function(formName, fieldName) {
			var field = epoint.ow.Utils.getField(formName, fieldName);
			
			ErrorDisplayManager.showErrors(formName);
			ErrorManager.triggerErrorRelatedEvents(formName);
		},
		
		
		/**
		 * Zwraca domyślny obiekt <code>deferred</code> zawierający akcje uruchamiane po walidacji formularza.
		 * 
		 * @param formName nazwa formularza
		 */
		getDefaultFormValidationDeferredObject : function(formName) {
			var deferred = jQuery.Deferred();
			deferred.done(function() { Utils.runFormValidationSuccessActions(formName);});
			deferred.fail(function() { Utils.runFormValidationFailedActions(formName);});
			return deferred;
		},
		
		/**
		 * Zwraca domyślny obiekt <code>deferred</code> zawierający akcje uruchamiane po walidacji grupy powiązanych pól formularza.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		getDefaultRelatedFieldsValidationDeferredObject : function(formName, fieldName) {
			var deferred = jQuery.Deferred();
			deferred.done(function() { Utils.runRelatedFieldsValidationSuccessActions(formName, fieldName);});
			deferred.fail(function() { Utils.runRelatedFieldsValidationFailedActions(formName, fieldName);});
			return deferred;
		},
		
		/**
		 * Zwraca domyślny obiekt <code>deferred</code> zawierający akcje uruchamiane po walidacji pola formularza.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		getDefaultFieldValidationDeferredObject : function(formName, fieldName) {
			var deferred = jQuery.Deferred();
			deferred.done(function() { Utils.runFieldValidationSuccessActions(formName, fieldName);});
			deferred.fail(function() { Utils.runFieldValidationFailedActions(formName, fieldName);});
			return deferred;
		},
		
		/**
		 * Waliduje formularz o podanej nazwie.
		 * 
		 * @param formName nazwa formularza
		 * @param deferred akcje wykonane w przypadku poprawnej/błędnej walidacji - opcjonalne
		 */
		validateForm : function(formName, deferred) {
			this.commonValidate(formName, null, deferred);
		},
		
		/**
		 * Waliduje grupę pól formularza powiązaną z danym polem.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola formularza
		 * @param deferred akcje wykonane w przypadku poprawnej/błędnej walidacji - opcjonalne
		 */
		validateRelatedFields : function(formName, fieldName, deferred) {
			this.commonValidate(formName, fieldName, deferred);
		},
		
		/**
		 * Wykonuje walidację pola na zdarzeniu <code>change</code>.
		 * 
		 * @param field nazwa pola formularza
		 */
		fieldValueChanged : function(field) {
			var formName = field.form.getAttribute('name');
			if (!ConfigManager.isOnChangeValidationEnabled(formName)) {
				return;
			}
			this.validateFieldOnChange(field);
		},
		
		commonValidate : function(formName, fieldName, deferred) {
			// clear errors
			if (fieldName != null) {
				ErrorManager.clearFieldErrors(formName, fieldName);
				if (deferred == null) {
					deferred = Utils.getDefaultRelatedFieldsValidationDeferredObject(formName, fieldName);
				}
			} else {
				ErrorManager.clearFormErrors(formName);
				if (deferred == null) {
					deferred = Utils.getDefaultFormValidationDeferredObject(formName);
				}
			}

			// select fields
			var fieldNames;
			if (fieldName != null)  {
				fieldNames = ConfigManager.getFieldRelatedFieldNames(formName, fieldName);
			} else {
				fieldNames = ConfigManager.getAllFormFieldNames(formName);
			}
			
			// run field validators
			for (var i = 0; i < fieldNames.length; i++) {
				var name = fieldNames[i];
				
				var field = epoint.ow.Utils.getField(formName, name);
				if (field.disabled) {
					continue;
				}
				
				var configs = ConfigManager.getFieldValidators(formName, name);
				for(var j = 0; j < configs.length; j++) {
					ValidatorFunctionManager.runConfigValidator(configs[j], formName, name);
				}
			}
			
			// run multifield validators
			var configs = ConfigManager.getMultiFieldValidators(formName);
			for(var j = 0; j < configs.length; j++) {
				var config = configs[j];
				if (fieldName != null && epoint.ow.Utils.arraySearch(config.allFieldNames, fieldName) == -1) {
					continue;
				}
				ValidatorFunctionManager.runConfigValidator(configs[j], formName, null);
			}

			// select this validation errors
			var errors = ErrorManager.getAllErrors(formName);
			if (fieldName != null) {
				var newErrors = [];
				for (var i = 0; i < errors.length; i++) {
					if (ErrorManager.isFieldRelatedError(formName, fieldName, errors[i])) {
						newErrors.push(errors[i]);
					}
				}
				errors = newErrors;
			}
			
			if (ConfigManager.isAjaxValidationEnabled(formName)) {
				// select errors from JavaScript/Java only validators (with no AJAX validator)
				errors = ErrorManager.filterJavaScriptNotAjaxErrors(formName, errors);

				// prepare new deferred object to preserve JavaScript errors
				var newDeferred = jQuery.Deferred();
				newDeferred.done(function () {
					if (errors.length > 0) {
						ErrorManager.addErrors(formName, errors);
						deferred.reject();
					} else {
						deferred.resolve();
					}
				});
				newDeferred.fail(function () {
					ErrorManager.addErrors(formName, errors);
					deferred.reject();
				});
				
				if (fieldName != null) {
					AjaxManager.validateRelatedFields(formName, fieldName, newDeferred);
				} else {
					AjaxManager.validateForm(formName, newDeferred);
				}
			} else {
				if (errors.length > 0) {
					deferred.reject();
				} else {
					deferred.resolve();
				}
			}
		},
		
		/**
		 * Wykonuje walidację pola.
		 * 
		 * @param fieldName nazwa pola formularza
		 * @param deferred akcje wykonane w przypadku poprawnej/błędnej walidacji - opcjonalne
		 */
		validateFieldOnChange : function(field, deferred) {
			var formName = field.form.getAttribute('name');
			var fieldName = field.getAttribute('name');
			
			ErrorManager.clearFieldErrors(formName, fieldName);
			
			if (deferred == null) {
				deferred = Utils.getDefaultFieldValidationDeferredObject(formName, fieldName);
			}
			
			var configs = ConfigManager.getFieldValidators(formName, fieldName);
			
			var valid = true
			for(var i = 0; i < configs.length; i++) {
				var config = configs[i];
				if (config.isRunOnChangeEvent) {
					valid = ValidatorFunctionManager.runConfigValidator(configs[i], formName, fieldName) && valid;
				}
			}

			if (!valid) {
				deferred.reject();
				return;
			}
			
			if (ConfigManager.isAjaxValidationEnabled(formName)) {
				AjaxManager.validateField(formName, fieldName, deferred);
			} else {
				deferred.resolve();
			}
		},
		
		/**
		 * Wyzwala zdarzenie form.submit.
		 * 
		 * @param formName nazwa formularza
		 * @param enableValidation - opcjonalnie
		 * @param action - opcjonalnie
		 */
		submitForm : function (formName, enableValidation, action) {
			$(epoint.ow.Utils.getForm(formName)).trigger('submit', [enableValidation, action]);
		},
		
		/**
		 * Sprawdza, czy formularz o podanej nazwie zawiera niepuste pola typu file.
		 * 
		 * @param formName nazwa formularza
		 * @return <code>true</code> jeżeli formularz zawiera niepuste pola typu file.
		 */
		containsNotEmptyFileFields : function(formName) {
			var $files = $(epoint.ow.Utils.getForm(formName)).find(":file");
			
			for(var i = 0; i < $files.size(); i++) {
				var file = $files.get(i);
				if (file.value != null && file.value.length > 0) {
					return true;
				}
			}
			return false;
		},
		
		submitFormButtonHandler : function(e, formName, enableValidation, action) {
			if (action == null) {
				var href = $(this).attr('href');
				if (href != null && href != '') {
					action = href;
				}
			}
			
			e.preventDefault();
			epoint.ow.forms.Utils.submitForm(formName, enableValidation, action);
		},

		initFieldAutocomplete : function(fieldId, autocompleteData) {
			var field = $('#' + fieldId);
			
			if (autocompleteData.messages == null) {
				autocompleteData.messages = {
						noResults : "",
						results : function() {}
				};
			}
			
			field.autocomplete(autocompleteData);
			var fieldChangeHandler = field.get(0).onchange;
			field.get(0).onchange = null;
			field.bind('autocompletechange', function(e) {fieldChangeHandler.call(this, e);});
		},

		initFieldDatepicker : function(formName, fieldName, datepickerOptions) {
			var field = $(epoint.ow.Utils.getField(formName, fieldName));
			field.datepicker(datepickerOptions);
			var enabled = !field.attr('disabled');
			epoint.ow.Utils.enableDisableField(formName, fieldName, enabled);
		}
	};
	
	var ErrorManager = $package.ErrorManager = {
		errorsMap : {},
		
		/**
		 * Zwraca wszystkie błędy walidacji formularza.
		 * 
		 * @param formName nazwa formularza
		 * @return tablica błędów
		 */
		getAllErrors : function(formName) {
			if (this.errorsMap[formName] == null) {
				this.errorsMap[formName] = new Array();
			}
			return this.errorsMap[formName];
		},
		
		/** 
		 * Dodaje błąd walidacji formularza.
		 * 
		 * @param formName nazwa formularza.
		 * @param fieldName nazwa pola lub <code>null</code> jeżeli jest to błąd walidatora MultiFieldValidator.
		 * @param validatorName unikalna nazwa walidatora.
		 * @param errorCode kod błędu walidacji.
		 * @param errorParameters mapa parametrów błędu walidacji.
		 * @param mainFieldName nazwa głównego pola, którego błąd dotyczy lub <code>null</code>
		 * @param notValidFieldNames lista nazw pól, których ten błąd dotyczy - opcjonalna
		 */
		addError : function (
				/*String*/ formName,
				/*String*/ fieldName,
				/*String*/ validatorName,
				/*String*/ errorCode,
				/*key-value map*/ errorParameters,
				/*String*/ mainFieldName,
				/*Array of String*/ notValidFieldNames) {
				
			var formErrors = this.getAllErrors(formName);
			var notValidFields = notValidFieldNames == null || !(notValidFieldNames instanceof Array) ? [] : notValidFieldNames;
			
			formErrors.push({
					fieldName : fieldName,
					validatorName : validatorName,
					errorCode : errorCode,
					errorParameters : errorParameters,
					mainFieldName : mainFieldName,
					notValidFieldNames : notValidFields
			});
		},
		
		/** 
		 * Dodaje błędy walidacji formularza.
		 * 
		 * @param formName nazwa formularza.
		 * @param errors tablica błędów walidacji formularza
		 */
		addErrors : function(/*String*/ formName, /*array of error*/ errors) {
			for(var i = 0; i < errors.length; i++) {
				var error = errors[i];
				this.addError(formName, error.fieldName, error.validatorName, error.errorCode, error.errorParameters,
						error.mainFieldName, error.notValidFieldNames);
			}
		},
		
		/** 
		 * Ustawia błędy walidacji formularza, kasuje poprzednie błędy.
		 * 
		 * @param formName nazwa formularza.
		 * @param errors tablica błędów walidacji formularza
		 */
		setErrors : function(/*String*/ formName, /*array of error*/ errors) {
			this.clearFormErrors(formName);
			this.addErrors(formName, errors);
		},
		
		/**
		 * Usuwa błędy walidacji danego pola.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		clearFieldErrors : function(formName, fieldName) {
			var allErrors = this.getAllErrors(formName);
			var newErrors = new Array();
			for (var i = 0; i < allErrors.length; i++) {
				var error = allErrors[i];
				
				if (!this.isFieldRelatedError(formName, fieldName, error)) {
					newErrors.push(error);
				}
			}
			this.errorsMap[formName] = newErrors;
		},
		
		/**
		 * Sprawdza, czy błąd jest pochodzi z walidatora związanego z podanym polem.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @return <code>true</code> jeżeli błąd jest związany z podanym polem
		 */
		isFieldRelatedError : function(formName, fieldName, error) {
			var config = ConfigManager.getConfig(formName, error.fieldName, error.validatorName);
			if (epoint.ow.Utils.arraySearch(config.allFieldNames, fieldName) != -1) {
				return true;
			}
			return false;
		},
		
		/**
		 * Filtruje listę błędów <code>errors</code> i zwraca błędy tylko tych
		 * walidatorów, które mają walidator JavaScript, ale nie mają walidatora
		 * AJAX.
		 * 
		 * @param formName nazwa formularza
		 * @param errors lista błędów do przefiltrowania
		 * @return przefiltrowane błędy
		 */
		filterJavaScriptNotAjaxErrors : function(formName, errors) {
			var newErrors = [];
			for (var i = 0; i < errors.length; i++) {
				var error = errors[i];
				var config = ConfigManager.getConfig(formName, error.fieldName, error.validatorName);
				
				if (config.hasJSValidator && !config.isAjaxValidator) {
					newErrors.push(error);
				}
			}
			return newErrors;
		},

		/**
		 * Usuwa błędy walidacji danego formularza.
		 * 
		 * @param formName nazwa formularza
		 */
		clearFormErrors : function(formName) {
			if (this.errorsMap[formName] != null) {
				this.errorsMap[formName] = new Array();
			}
		},
			
		/**
		 * Zwraca tablicę komunikatów błędów walidacji przypisanych do danego pola.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		getFieldErrorMessages : function(formName, fieldName) {
			var allErrors = this.getAllErrors(formName);
			var fieldErrors = new Array();
			for (var i = 0; i < allErrors.length; i++) {
				var error = allErrors[i];
				var config = ConfigManager.getConfig(formName, error.fieldName, error.validatorName);
				var mainFieldName = ErrorManager.getMainFieldName(formName, error);
				if (mainFieldName == fieldName) {
					var msg = ValidatorFunctionManager.formatMessage(config, formName, fieldName, error.errorCode, error.errorParameters);
					fieldErrors.push(msg);
				}
			}
			return fieldErrors;
		},

		/**
		 * Zwraca tablicę komunikatów błędów walidacji nie przypisanych do żadnego pola.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		getFormErrorMessages : function(formName) {
			var allErrors = this.getAllErrors(formName);
			var formErrors = new Array();
			for (var i = 0; i < allErrors.length; i++) {
				var error = allErrors[i];
				var config = ConfigManager.getConfig(formName, error.fieldName, error.validatorName);
				var mainFieldName = ErrorManager.getMainFieldName(formName, error);
				if (mainFieldName == null) {
					var msg = ValidatorFunctionManager.formatMessage(config, formName, null, error.errorCode, error.errorParameters);
					formErrors.push(msg);
				}
			}
			return formErrors;
		},
			
		/**
		 * Sprawdza, czy pole zawiera błędy walidacji.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @return <code>true</code> jeżeli pole nie zawiera błędów walidacji
		 */
		isFieldValid : function(formName, fieldName) {
			var allErrors = this.getAllErrors(formName);

			for (var i = 0; i < allErrors.length; i++) {
				var error = allErrors[i];
				var notValidFields = this.getNotValidFieldNames(formName, error);

				if (epoint.ow.Utils.arraySearch(notValidFields, fieldName) != -1) {
					return false;
				}
			}
			return true;
		},
		
		/**
		 * Zwraca nazwę głównego pola, którego dotyczy błąd.
		 * 
		 * @param formName nazwa formularza
		 * @param error błąd
		 */
		getMainFieldName : function(formName, error) {
			var config = ConfigManager.getConfig(formName, error.fieldName, error.validatorName);
			
			if (error.mainFieldName != null) {
				return error.mainFieldName;
			} else {
				return config.mainFieldName;
			}
		},
		
		/**
		 * Zwraca listę pól zawierających błąd dla podanego błędu walidacji.
		 * 
		 * @param formName nazwa formularza
		 * @param error błąd walidaji
		 * @return lista pól formularza, których dotyczy dany błąd
		 */
		getNotValidFieldNames : function(formName, error) {
			var config = ConfigManager.getConfig(formName, error.fieldName, error.validatorName);
			
			var notValidFields = config.notValidFieldNames;
			if ((error.notValidFieldNames != null) &&
					(error.notValidFieldNames instanceof Array)
					&& (error.notValidFieldNames.length > 0)) {
				notValidFields = error.notValidFieldNames;
			}
			
			return notValidFields;
		},
		
		/**
		 * Sprawdza, czy formularz zawiera błędy walidacji.
		 * 
		 * @param formName nazwa formularza
		 * @return <code>true</code> jeżeli formularz nie zawiera błędów walidacji
		 */
		isFormValid : function(formName) {
			return this.getAllErrors(formName).length == 0;
		},
		
		/**
		 * Wyzwala zdarzenia informujące o istnieniu lub braku błedów formularza i pól.
		 * 
		 * @param formName nazwa formularza
		 */
		triggerErrorRelatedEvents : function(formName) {
			var fieldNames = ConfigManager.getAllFormFieldNames(formName);
			for(var i = 0; i < fieldNames.length; i++) {
				var fieldName = fieldNames[i];
				var field = epoint.ow.Utils.getField(formName, fieldName);
				if (ErrorManager.isFieldValid(formName, fieldName)) {
					$(field).trigger('fieldIsValid');
				} else {
					$(field).trigger('fieldIsNotValid');
				}
			}
			
			var form = epoint.ow.Utils.getForm(formName);
			if (ErrorManager.isFormValid(formName)) {
				$(form).trigger('formIsValid');
			} else {
				$(form).trigger('formIsNotValid');
			}
		}
	};
	
	var ErrorDisplayManager = $package.ErrorDisplayManager = {
		displayHandlers : {},
		
		/**
		 * Ustawia funkcję wyświetlającą błędy walidacji formularza. Jeżeli <code>formName != null</code>,
		 * ustawia funkcję tylko dla tego formularza, w przeciwnym przypadku - ustawia funkcję globalną.
		 * 
		 * @param handlerFn funkcja
		 * @param formName nazwa formularza - opcjonaly
		 */
		setFormHandler : function(handlerFn, formName) {
			if (formName != null) {
				if (this.displayHandlers[formName] == null) {
					this.displayHandlers[formName] = {};
				}
				this.displayHandlers[formName].form = handlerFn;
			} else {
				this.displayHandlers.defaultForm = handlerFn;
			}
		},

		/**
		 * Ustawia funkcję wyświetlającą błędy walidacji pola. Jeżeli <code>formName != null</code> i <code>fieldName != null</code>,
		 * ustawia funkcję tylko dla tego pola. Jeżeli tylko <code>formName != null</code>, ustawia funkcję dla wszystkich pól
		 * tego formularza. Jeżeli <code>formName == null</code> i <code>fieldName == null</code> ustawia funkcję globalną.
		 * 
		 * @param handlerFn funkcja
		 * @param formName nazwa formularza - opcjonaly
		 * @param fieldName nazwa pola - opcjonaly
		 */
		setFieldHandler : function(handlerFn, formName, fieldName) {
			if (formName != null) {
				if (this.displayHandlers[formName] == null) {
					this.displayHandlers[formName] = {};
				}
				
				if (fieldName != null) {
					this.displayHandlers[formName][fieldName] = handlerFn;
				} else {
					this.displayHandlers[formName][''] = handlerFn;
				}
			} else {
				this.displayHandlers.defaultField = handlerFn;
			}
		},
		
		/**
		 * Zwraca funkcję wyświetlającą błędy walidacji formularza.
		 * 
		 * @param formName nazwa formularza
		 * @return funkcja wyświetlająca błędy walidacji formularza
		 */
		getFormHandler : function(formName) {
			if (this.displayHandlers[formName] && this.displayHandlers[formName].form) {
				return this.displayHandlers[formName].form;
			} else if (this.displayHandlers.defaultForm) {
				return this.displayHandlers.defaultForm;
			} else {
				return this.defaultFormHandler;
			}
		},

		/**
		 * Zwraca funkcję wyświetlającą błędy walidacji pola.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @return funkcja wyświetlająca błędy walidacji pola
		 */
		getFieldHandler : function(formName, fieldName) {
			if ( this.displayHandlers[formName] && this.displayHandlers[formName][fieldName]) {
				return this.displayHandlers[formName][fieldName];
			} else if (this.displayHandlers.defaultField) {
				return this.displayHandlers.defaultField;
			} else {
				return this.defaultFieldHandler;
			}
		},
		
		/**
		 * Domyślna funkcja wyświetlającą błędy walidacji formularza.
		 * 
		 * @param formName nazwa formularza
		 * @param messageArray komunikaty błędów walidacji
		 */
		defaultFormHandler : function(formName, messageArray) {
			var $c = $("#" + Utils.getFormErrorContainerId(formName));
			if (messageArray && messageArray.length > 0) {
				
				var value = "";
				for ( var i = 0; i < messageArray.length; i++) {
					var msg = oneweb.HtmlEscapingComponent
							.escapeHtmlBody(messageArray[i]);
					value += "<li>" + msg + "</li>";
				}
				
				$c.show('slow');
				$c.find(".form_errors_list").html("<ul>" + value + "</ul>").end();
			} else {
				$c.hide('slow');
				$c.find(".form_errors_list").empty().end();
			}
		},

		/**
		 * Domyślna funkcja wyświetlającą błędy walidacji pola.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @param messageArray komunikaty błędów walidacji
		 */
		defaultFieldHandler : function(formName, fieldName, messageArray) {
			var form = epoint.ow.Utils.getForm(formName);
			var fieldContainerId = Utils.getFieldErrorContainerId(formName, fieldName);
			var $c = $('#' + fieldContainerId ).first();
			if($c.size() == 0 && messageArray.length > 0) {
				$c = $(form).find("[name=" + fieldName + "]").after("<span id='" + fieldContainerId  + "' class='field_error_container'></span>").nextAll(".field_error_container");
			}
			if ($c.size() > 0 && messageArray.length == 0) {
				$c.hide('slow');
				return;
			}
			var $innerC = $c.find(".field_error_inner");
			if ($innerC.size() == 0) {
				$innerC = $c.append("<span class='field_error_inner'></span>").find(".field_error_inner");
			}

			var value = "";
			for ( var i = 0; i < messageArray.length; i++) {
				var msg = oneweb.HtmlEscapingComponent
						.escapeHtmlBody(messageArray[i]);
				value += "<span>" + msg + "</span>";
			}

			$innerC.html(value);
			$c.show('slow');
		},

		/**
		 * Wyświetla aktualne błędy walidacji formularza.
		 * 
		 * @param formName nazwa formularza
		 */
		showErrors : function(formName) {
			var form = epoint.ow.Utils.getForm(formName);
			this.getFormHandler(formName)(formName, ErrorManager.getFormErrorMessages(formName));
		    
            var fieldNames = ConfigManager.getAllFormFieldNames(formName);
			for (var i = 0; i < fieldNames.length; i++) {
				var fieldName = fieldNames[i];
				this.getFieldHandler(formName, fieldName)(formName, fieldName, ErrorManager.getFieldErrorMessages(formName, fieldName));
				
				if (ErrorManager.isFieldValid(formName, fieldName)) {
					$(form).find("[name=" + fieldName + "]").removeClass("field_error");
					$("#" + formName + "_" + fieldName + "_label").removeClass("field_label_error");
				} else {
					$(form).find("[name=" + fieldName + "]").addClass("field_error");
					$("#" + formName + "_" + fieldName + "_label").addClass("field_label_error");
				}
			}
			
			
		},
		
		/**
		 * Przewija ekran do pierwszego błędu walidacji formularza.
		 * 
		 * @param formName nazwa formularza
		 */
		scrollToErrors : function(formName) {
			var scrollPosition = null;
			
			var fieldNames = ConfigManager.getAllFormFieldNames(formName);
			for (var i = 0; i < fieldNames.length; i++) {
				var fieldName = fieldNames[i];
				if (ErrorManager.getFieldErrorMessages(formName, fieldName).length > 0) {
					var field = epoint.ow.Utils.getField(formName, fieldName);
					var cont = $('#' + Utils.getFieldErrorContainerId(formName, fieldName)).get(0);
					var position = null;
					if (cont == null) {
						position = $(field).offset().top;
					} else {
						position = $(cont).offset().top;
					}
					
					if (scrollPosition == null || position < scrollPosition) {
						scrollPosition = position;
					}
				}
			}

			if (scrollPosition == null && ErrorManager.getFormErrorMessages(formName).length > 0) {
				var container = $('#' + Utils.getFormErrorContainerId(formName)).get(0);
				if (container != null) {
					scrollPosition = $(container).offset().top;
				}
			}
			if (scrollPosition != null) {
				$(document).scrollTop(Math.max(0, scrollPosition - document.documentElement.clientHeight/2));
			}
		}
	};
	
	var ConfigManager = $package.ConfigManager = {
		
		/**
		 * Zarejestrowane konfiguracje walidatorów.
		 */
		validatorConfigs : {},
		
		jsValidationEnabled : {},
		
		ajaxValidationEnabled : {},
		
		onChangeValidationEnabled : {},
		
		/**
		 * Sprawdza, czy walidacja JavaScript jest włączona.
		 * 
		 * @param formName nazwa formularza
		 * @return <code>true</code> jeżeli walidacja JavaScript jest włączona 
		 */
		isJsValidationEnabled : function(formName) {
			return this.jsValidationEnabled[formName] == true;
		},
		
		/**
		 * Sprawdza, czy walidacja AJAX jest włączona.
		 * 
		 * @param formName nazwa formularza
		 * @return <code>true</code> jeżeli walidacja AJAX jest włączona 
		 */
		isAjaxValidationEnabled : function(formName) {
			return this.ajaxValidationEnabled[formName] == true;
		},

		/**
		 * Sprawdza, czy walidacja na zmianie wartości pola jest włączona.
		 * 
		 * @param formName nazwa formularza
		 * @return <code>true</code> jeżeli walidacja na zmianie wartości pola jest włączona 
		 */
		isOnChangeValidationEnabled : function(formName) {
			return this.onChangeValidationEnabled[formName] == true;
		},
		
		/**
		 * Włącza/wyłącza walidację JavaScript.
		 * 
		 * @param formName nazwa formularza
		 * @param isEnabled <code>true</code> jeżeli walidacja JavaScript ma być włączona
		 */
		setJsValidationEnabled : function(formName, isEnabled) {
			this.jsValidationEnabled[formName] = isEnabled == true;
		},
		
		/**
		 * Włącza/wyłącza walidację AJAX.
		 * 
		 * @param formName nazwa formularza
		 * @param isEnabled <code>true</code> jeżeli walidacja AJAX ma być włączona
		 */
		setAjaxValidationEnabled : function(formName, isEnabled) {
			this.ajaxValidationEnabled[formName] = isEnabled == true;
		},

		/**
		 * Włącza/wyłącza walidację na zmianie wartości pola.
		 * 
		 * @param formName nazwa formularza
		 * @param isEnabled <code>true</code> jeżeli walidacja na zmianie wartości pola ma być włączona
		 */
		setOnChangeValidationEnabled : function(formName, isEnabled) {
			this.onChangeValidationEnabled[formName] = isEnabled == true;
		},
		
		/**
		 * Ustawia konfigurację walidatorów dla danego formularza.
		 * 
		 * @param formName nazwa formularza
		 * @param config konfiguracja walidatorów
		 */
		setFormConfig : function(formName, config) {
			this.validatorConfigs[formName] = config;
		},
		
		/**
		 * Zwraca tablicę konfiguracji walidatorów wielopolowych danego formularza.
		 * 
		 * @param formName nazwa formularza
		 * @return tablica konfiguracji walidatorów wielopolowych
		 */
		getMultiFieldValidators : function(formName) {
			var formConfig = this.validatorConfigs[formName];
			if (formConfig == null) {
				return new Array();
			}
			
			return formConfig['multiFieldValidatorConfigs'];
		},

		/**
		 * Zwraca tablicę konfiguracji walidatorów jednopolowych danego pola.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @return tablica konfiguracji walidatorów jednopolowych
		 */
		getFieldValidators : function(formName, fieldName) {
			var formConfig = this.validatorConfigs[formName];
			if (formConfig == null) {
				return [];
			}
			var fieldConfig = formConfig['fieldValidatorConfigs'][fieldName];
			if (fieldConfig == null) {
				return [];
			}
			return fieldConfig;
		},
		
		/**
		 * Zwraca nazwy wszystkich pól formularza.
		 * 
		 * @return nazwy wszystkich pól formularza
		 */
	    getAllFormFieldNames : function(formName) {
			var fieldNames = this.validatorConfigs[formName]['fieldNames'];
			if (fieldNames == null) {
				fieldNames = [];
			}

            return fieldNames;
        },
        
        /**
         * Zwraca konfigurację walidatora dla danego pola.
         * 
         * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @param nazwa walidatora
		 * @return konfiguracja walidatora lub <code>null</code>
         */
        getConfig : function(formName, fieldName, validatorName) {
        	var configs = null;
        	if (fieldName != null) {
        		configs = this.getFieldValidators(formName, fieldName);
        	} else {
        		configs = this.getMultiFieldValidators(formName);
        	}
        	
        	for(var i = 0; i < configs.length; i++) {
        		if (validatorName == configs[i].validatorName) {
        			return configs[i];
        		}
        	}
        	
        	return null;
        },
        
        /**
		 * Zwraca listę nazw pól powiązanych z danym polem.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		getFieldRelatedFieldNames : function(formName, fieldName) {
			var allNames = new Array();

			// search for multifield validator
			configs = ConfigManager.getMultiFieldValidators(formName);
			for(var i = 0; i < configs.length; i++) {
				var config = configs[i];
				
				if (epoint.ow.Utils.arraySearch(config.allFieldNames, fieldName) != -1) {
					allNames = allNames.concat(config.allFieldNames);
				}
			}
			
			// append given field name
			allNames.push(fieldName);

			// remove duplicate names
			return epoint.ow.Utils.removeDuplicate(allNames);
		},
        
        /**
		 * Zwraca listę nazw pól powiązanych z danym polem walidatorami AJAX.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 */
		getFieldRelatedFieldNamesForAjaxValidation : function(formName, fieldName) {
			var allNames = new Array();

			// search for multifield validator
			configs = ConfigManager.getMultiFieldValidators(formName);
			for(var i = 0; i < configs.length; i++) {
				var config = configs[i];
				
				if (epoint.ow.Utils.arraySearch(config.allFieldNames, fieldName) != -1 &&
						config.isAjaxValidator) {
					allNames = allNames.concat(config.allFieldNames);
				}
			}
			
			// search for field validator
			configs = ConfigManager.getFieldValidators(formName, fieldName);
			for(var i = 0; i < configs.length; i++) {
				var config = configs[i];
				if (config.isAjaxValidator) {
					allNames.push(fieldName);
					break;
				}
			}
			
			// remove duplicate names
			return epoint.ow.Utils.removeDuplicate(allNames);
		}
	};
	
	var ValidatorFunctionManager = $package.ValidatorFunctionManager = {
		
		/**
		 * Zarejestrowane funkcje walidatorów JS.
		 */
		validatorFunctions : {},
		
		/**
		 * Ustawia walidator JS o podanej nazwie.
		 * 
		 * @param validatorId pełna nazwa walidatora
		 * @param validatorFn funkcja walidatora JS
		 */
		setValidator : function(validatorId, validatorFn) {
			this.validatorFunctions[validatorId] = validatorFn;
		},
		
		/**
		 * Zwraca komunikat błędu walidacji.
		 * 
		 * @param config konfiguracja walidatora
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @param errorCode kod błędu walidacji
		 * @param errorParameters parametry błędu walidacji
		 * @return komunikat błędu walidacji
		 */
		formatMessage : function (config, formName, fieldName, errorCode, errorParameters) {
			var msg = config.errorMessagesMap[errorCode];
			if (msg == null) {
				throw "add current error code:" + errorCode + " to the list of codes supported by this validator.";
			}
			if (fieldName != null) {
				var values = epoint.ow.Utils.getFieldValues(formName, fieldName);
				if (values.length > 1) {
					errorParameters['field_value'] = values;
				} else if (values.length == 1) {
					errorParameters['field_value'] = values[0];
				} else {
					errorParameters['field_value'] = "";
				}
			}

			return epoint.ow.Utils.replaceI18NParameters(msg, errorParameters);
		},
		
		/**
		 * Uruchamia podany walidator jednopolowy (jeżeli <code>fieldName != null</code>) lub wielopolowy.
		 * 
		 * @param config konfiguracja walidatora
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola - <code>!= null</code> jeżeli walidator <code>config</code> jest walidatorem jednopolowym
		 * @return <code>false</code> jeżeli wystąpiły błędy walidacji
		 */
		runConfigValidator : function (config, formName, fieldName) {
			// nie waliduj, jeżeli nie ma walidatora JS
			if (!config.hasJSValidator) {
				return true;
			}

			// nie waliduj, jeżeli nie wszystkie pola wypełnione i "runIfNotAllFieldsFilled == false" 
			if ((config.runIfNotAllFieldsFilled == false) && (!epoint.ow.Utils.isAllFieldsFilled(formName, config.allFieldNames))) {
				return true;
			}
			
			// nie waliduj, jeżeli powiązane z walidatorem pola zawierają błędy
			for(var name in config.allFieldNames) {
				if (!ErrorManager.isFieldValid(formName, name)) {
					return true;
				}
			}

			var validationResult = null;
			if (fieldName != null) {
				validationResult = this.runFieldValidator(config.JSFunctionName, formName, fieldName, config.validatorParameters);
			} else {
				validationResult = this.runMultiFieldValidator(config.JSFunctionName, formName, config.validatorParameters);
			}
			
			if (validationResult == null) {
				return true;
			}
			
			ErrorManager.addError(formName, fieldName, config.validatorName, validationResult.errorCode,
					validationResult.errorParameters, validationResult.mainFieldName, validationResult.notValidFieldNames);
			return false;
		},
		
		/**
		 * Uruchamia podany walidator jednopolowy.
		 * 
		 * @param validatorId nazwa funkcji walidatora JS
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @param parameters parametry walidatora
		 * @return błąd walidacji formularza lub <code>null</code>
		 */
		runFieldValidator : function(validatorId, formName, fieldName, parameters) {
			if (this.validatorFunctions[validatorId]) {
				return this.validatorFunctions[validatorId].apply(this, [formName, fieldName].concat(parameters));
			} else {
				return null;
			}
		},

		/**
		 * Uruchamia podany walidator wielopolowy.
		 * 
		 * @param validatorId nazwa funkcji walidatora JS
		 * @param formName nazwa formularza
		 * @param parameters parametry walidatora
		 * @return błąd walidacji formularza lub <code>null</code>
		 */
		runMultiFieldValidator : function(validatorId, formName, parameters) {
			if (this.validatorFunctions[validatorId]) {
				return this.validatorFunctions[validatorId].apply(this, [formName].concat(parameters));
			} else {
				return null;
			}
		}
	};
	
	var AjaxManager = $package.AjaxManager = {
		
		/**
		 * URL akcji walidacji formularza.
		 */
		VALIDATE_FORM_URL : "?action=forms.validateForm",
		
		VALIDATE_FIELD_RELATED_FIELDS_URL : "?action=forms.validateFieldRelatedFields",
		
		VALIDATE_FIELD_ON_CHANGE_URL : "?action=forms.validateFieldOnChange",
		
		/**
		 * Waliduje podane pole i pola powiązane, wykonuje akcje z obiektu <code>deferred</code>.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @param isOnChangeValidation czy jest to walidacja na zdarzeniu <code>change</code>
		 * @param deferred akcje wykonywane po zakończeniu walidacji - opcjonaly
		 */
		validateField : function(formName, fieldName, deferred) {
			if (deferred == null) {
				deferred = Utils.getDefaultFieldValidationDeferredObject(formName, fieldName);
			}
			
			var hasFieldValidatorsToRun = false;
			{
				configs = ConfigManager.getFieldValidators(formName, fieldName);
				for(var i = 0; i < configs.length; i++) {
					var config = configs[i];
					if (config.isAjaxValidator &&
							config.isRunOnChangeEvent &&
							(config.runIfNotAllFieldsFilled || epoint.ow.Utils.getFieldValue(formName, fieldName) != null)) {
						hasFieldValidatorsToRun = true;
						break;
					}
				}
			}
			
			if (hasFieldValidatorsToRun) {
				this.submitForm(formName, 
								function(errorsArray) {
									if (errorsArray.length > 0) {
										ErrorManager.clearFieldErrors(formName, fieldName);
										ErrorManager.addErrors(formName, errorsArray);
		
						                deferred.reject();
									} else {
										ErrorManager.clearFieldErrors(formName, fieldName);
										deferred.resolve();
									}
								},
								["form", formName, "field", fieldName],
								this.VALIDATE_FIELD_ON_CHANGE_URL,
								null,
								[fieldName]);
			} else {
				deferred.resolve();
			}
		},
		
		/**
		 * Waliduje podane pole i pola powiązane, wykonuje akcje z obiektu <code>deferred</code>.
		 * 
		 * @param formName nazwa formularza
		 * @param fieldName nazwa pola
		 * @param isOnChangeValidation czy jest to walidacja na zdarzeniu <code>change</code>
		 * @param deferred akcje wykonywane po zakończeniu walidacji - opcjonaly
		 */
		validateRelatedFields : function(formName, fieldName, deferred) {
			if (deferred == null) {
				deferred = Utils.getDefaultRelatedFieldsValidationDeferredObject(formName, fieldName);
			}
			
			var fieldNames = ConfigManager.getFieldRelatedFieldNamesForAjaxValidation(formName, fieldName);

			if (fieldNames.length > 0) {
				this.submitForm(
						formName, 
						function(errorsArray) {
							if (errorsArray.length > 0) {
								ErrorManager.clearFieldErrors(formName, fieldName);
								ErrorManager.addErrors(formName, errorsArray);
								
								deferred.reject();
							} else {
								ErrorManager.clearFieldErrors(formName, fieldName);
								deferred.resolve();
							}
						},
						["form", formName, "field", fieldName],
						this.VALIDATE_FIELD_RELATED_FIELDS_URL,
						null,
						fieldNames);
			} else {
				ErrorManager.clearFieldErrors(formName, fieldName);				
				deferred.resolve();
			}
		},
		
		/**
		 * Waliduje formularz i wykonuje akcje z obiektu <code>deferred</code>.
		 * 
		 * @param formName nazwa formularza
		 * @param deferred akcje wykonywane po zakończeniu walidacji - opcjonaly
		 */
		validateForm : function(formName, deferred) {
			if (deferred == null) {
				deferred = Utils.getDefaultFormValidationDeferredObject(formName);
			}
			
			this.submitForm(
					formName,
					function(errorsArray) {
						if (errorsArray.length > 0) {
							ErrorManager.clearFormErrors(formName);
							ErrorManager.addErrors(formName, errorsArray);

							deferred.reject();
						} else {
							ErrorManager.clearFormErrors(formName);
							deferred.resolve();
						}
					},

					["form", formName],
					this.VALIDATE_FORM_URL);
		},
		
		/**
		 * Submituje formularz.
		 * 
		 * @param formName nazwa formularza
		 * @param callbackFn funkcja callback
		 * @param parametersArray tablica dodatkowych parametrów - opcjonaly
		 * @param submitUrl URL, na który wysłany zostanie request - opcjonaly
		 * @param dataType typ danych - opcjonaly
		 * @param submitOnlyFieldNamesArray tablica nazw pól do submitowania - opcjonaly
		 */
		submitForm : function(formName, callbackFn, parametersArray, submitUrl, dataType, submitOnlyFieldNamesArray) {
			var form = epoint.ow.Utils.getForm(formName);
			
			var url = submitUrl;
			if (url == null) {
				url = form.getAttribute('action');
			}
			
			var params;
			if (submitOnlyFieldNamesArray != null) {
				params = $(form).find("[name=" + submitOnlyFieldNamesArray.join("],[name=") + "]").serializeArray();
			} else {
				params = $(form).serializeArray();
			}
			
			if (parametersArray != null) {
				for(var i = 0; i < parametersArray.length; i+=2) {
					params.push({"name" : parametersArray[i], "value" : parametersArray[i+1]});	
				}
			}
			
			if (dataType == null) {
				dataType = "json";
			}

			oneweb.CsrfTokenManager.postAjax(url, params, callbackFn, dataType);
		},
		
		/**
		 * Dodaje dynamiczne pola typu <code>hidden</code> przenoszące wartości parametrów.
		 * 
		 * @param formName nazwa formularza
		 * @param parametersArray tablica parametrów
		 */
		addParametersAsFormFields : function(formName, parametersArray) {
			var form = epoint.ow.Utils.getForm(formName);
			
			if (form != null && parametersArray != null && parametersArray.length > 0) {
				for(var i = 0; i < parametersArray.length; i+=2) {
					var fieldId = parametersArray[i] + "_parameter_value_carrier";
					var p = $("#" + fieldId).remove();
					var p = document.createElement("input");
					p.setAttribute("type", "hidden");
					p.setAttribute("name", parametersArray[i]);
					p.setAttribute("id", parametersArray[i] + "_parameter_value_carrier");
					p.setAttribute("value", parametersArray[i+1]);
					$(form).append(p);
				}
			}
		},
		
		submitFormWithIFrame : function(formName, callbackFn, parametersArray, submitUrl, dataType) {
			var form = epoint.ow.Utils.getForm(formName);
			var IFRAME_ID = form.getAttribute('id') + "_iframe";
			var IFRAME_NAME = form.getAttribute('name') + "_iframe";
			
			if (submitUrl == null) {
				submitUrl = form.getAttribute('action');
			}
			
			if (document.getElementById(IFRAME_ID) != null) {
				throw "submitFormWithIFrame: other submit in progress, can not submit form '" + formName + "' to URL:" + submitUrl;
			}
			
			var iframe = document.createElement('iframe');
			iframe.setAttribute('id', IFRAME_ID);
			iframe.setAttribute('name', IFRAME_NAME);
			iframe.setAttribute('src', "about:blank");
			
			$(iframe).load(function() {
				iframe.contentWindow.name = iframe.name; 
				$(iframe).unbind('load');
				$(iframe).load(function() {
					var field = null;
					var errorResponseText = null;

					try {
						field = iframe.contentWindow.document.getElementById('carrierField');

						if (field != null) {
							var value = field.value;
							if (dataType == "json") {
								value = jQuery.parseJSON(value);
							}
							document.body.removeChild(document.getElementById(IFRAME_ID));
							callbackFn.apply(document, [value]);
						} else {
							var isErrorFromOW = $(iframe.contentWindow.document.getElementsByTagName('head')[0])
									.find('meta[http-equiv=OneWeb-error-handler-response]').attr('content') == "true";
							if (isErrorFromOW) {
								errorResponseText = $(iframe.contentWindow.document.body).html();
							} else {
								errorResponseText = "Unknown error"; 
							}
						}
					} catch (e) {
						errorResponseText = "Unknown exception";
					}

					if (errorResponseText != null) {
						epoint.ow.JSErrorManager.handleAjaxError({}, {
							responseText : errorResponseText,
							getResponseHeader : function(x){return x == "OneWeb-error-handler-response" ? "true" : ""}
						},
						{
							url : epoint.ow.Utils.getForm(formName).getAttribute('action'),
							dataType : dataType
						},
						errorResponseText);
					}
				});

				AjaxManager.addParametersAsFormFields(formName, parametersArray);
				form.setAttribute('action', submitUrl);
				form.setAttribute('target', IFRAME_NAME);

				oneweb.CsrfTokenManager.submitForm(form.getAttribute('name'));
			});
			
			$(document.body).append(iframe);
			$(iframe).hide();
		}
	}
	
	// register Validator JS functions
	ValidatorFunctionManager.setValidator(
		"pl.epoint.ow.forms.validators.ConstantValueValidator",
		function (formName, fieldName, constantValueArray) {
			var fieldValues = epoint.ow.Utils.getFieldValues(formName, fieldName);
		
			if (epoint.ow.Utils.arrayEquals(fieldValues, constantValueArray)) {
				return null;
			}
			
			return Utils.reportDefaultError({"default_value" : constantValueArray[0], "default_values" : constantValueArray});
		});
	
	ValidatorFunctionManager.setValidator(
		"pl.epoint.ow.forms.validators.ValueRequiredValidator",
		function (formName, fieldName) {
			var values = epoint.ow.Utils.getFieldValues(formName, fieldName);
			if (values.length == 0) {
				return Utils.reportDefaultError({});
			}
		
			return null;
	});
	
	ValidatorFunctionManager.setValidator(
		"pl.epoint.ow.forms.validators.TwoFieldIdentityValidator",
		function (formName, field1Name, field2Name) {
			var field1Value = epoint.ow.Utils.getFieldValues(formName, field1Name);
			var field2Value = epoint.ow.Utils.getFieldValues(formName, field2Name);
			
			if(!epoint.ow.Utils.arrayEquals(field1Value, field2Value)) {
				return Utils.reportDefaultError({});
			}
			
			return null;
	});
	
	ValidatorFunctionManager.setValidator(
			"pl.epoint.ow.forms.validators.TwoFieldDifferentValidator",
			function (formName, field1Name, field2Name) {
				var result = ValidatorFunctionManager.runMultiFieldValidator(
						"pl.epoint.ow.forms.validators.TwoFieldIdentityValidator",
						formName,
						[field1Name, field2Name]);
				
				if (result == null) {
					return Utils.reportDefaultError({});
				}
				
				return null;
		});
	
	ValidatorFunctionManager.setValidator(
			"pl.epoint.ow.forms.validators.FileRequiredValidator",
			function (formName, fieldName, isTemporaryStorageMode, storageFilesSize) {
				if (isTemporaryStorageMode) {
					if (storageFilesSize == 0) {
						return Utils.reportDefaultError({});
					} 
				} else {
					var fieldValue = epoint.ow.Utils.getFieldValues(formName, fieldName);
					
					if (fieldValue == null) {
						return Utils.reportDefaultError({});
					}
				}
				
				return null;
		});
	
	ValidatorFunctionManager.setValidator(
			"pl.epoint.ow.forms.validators.IntegerValidator",
			function (formName, fieldName, minValue, maxValue) {
				var value = epoint.ow.Utils.getFieldValue(formName, fieldName);
				
				if (isNaN(value) || value % 1 != 0) {
					return Utils.reportDefaultError({});
				}
				
				var number = parseInt(value);
				if (minValue != null && number < minValue) {
					return Utils.reportError('MIN', {'min_value': minValue});
				}
				if (maxValue != null && number > maxValue) {
					return Utils.reportError('MAX', {'max_value': maxValue});
				}
				
				return null;

		});
})();
