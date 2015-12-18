(function() {
	var CsrfTokenManager = oneweb.owjsPackage().CsrfTokenManager = {
		CSRF_TOKEN_NAME : "csrfToken",
		
		token : null,
		
		setToken : function(token) {
			CsrfTokenManager.token = token;
		},

		addFieldWithToken : function(formName) {
			epoint.ow.forms.AjaxManager.addParametersAsFormFields(formName, [
					CsrfTokenManager.CSRF_TOKEN_NAME, CsrfTokenManager.token ]);
		},
		
		submitForm : function(formName) {
			CsrfTokenManager.addFieldWithToken(formName);
			epoint.ow.Utils.getForm(formName).submit();
		},

		postAjax : function(url, parameters, callbackFn, dataType) {
			var stringParams = "";
			if ((typeof parameters) == "string") {
				stringParams = parameters;
			} else if (parameters != null) {
				stringParams = $.param(parameters);
			}
			var tokenParams = $.param([{name:CsrfTokenManager.CSRF_TOKEN_NAME, value:CsrfTokenManager.token}]);
			if (stringParams.length > 0) {
				stringParams += "&";
			}
			stringParams += tokenParams;
			return $.post(url, stringParams, callbackFn, dataType);
		},

		post : function(url, target) {
			var ID = "form_dynamic_post_id";
			$('#' + ID).remove();
			
			var form = document.createElement('form');
			form.setAttribute('id', ID);
			form.setAttribute('name', "post");
			form.setAttribute('method', 'POST');
			form.setAttribute('enctype', 'application-x-www-form-urlencoded');
			form.setAttribute('accept-charset', 'UTF-8');
			form.setAttribute('action', url);
			if (target != null) {
				form.setAttribute('target', target);	
			}

			document.getElementsByTagName('body')[0].appendChild(form);
			CsrfTokenManager.submitForm(form.getAttribute('name'));
		},
		
		postHandler : function(e, url, target) {
			e.preventDefault();
			e.stopImmediatePropagation();
			
			if (target == null) {
				target = $(e.currentTarget).attr('target');
			}
			
			if (url == null) {
				var href = $(e.currentTarget).attr('href');
				if (href != null && href != '') {
					CsrfTokenManager.post(href, target);
				}
			} else {
				CsrfTokenManager.post(url, target);
			}
		}
	};
})();