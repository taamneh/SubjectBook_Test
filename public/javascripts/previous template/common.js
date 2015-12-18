
//Skrypty JS wspólne dla całej aplikacji

(function() {
	var $package = jsPackage("pl.epoint.dbwm.war_user.model_share")

	function split(val) {
		return val.split(/,\s*/);
	}

	$package.registerShareModelAutocompleteHandler = function(formName, fieldName) {
		oneweb.DocumentReadyManager.addApplicationAction(function() {
			$(epoint.ow.Utils.getField(formName, fieldName))
			.autocomplete({
				select: function(e, ui) {
					var newValue = ui.item.value;

					var field = $(epoint.ow.Utils.getField(formName, fieldName));
					if (field != null) {
						var emails = split(field.val());
						emails.pop();
						emails.push(newValue);
						emails.push("");
						
						field.val(emails.join(", "));
					}
					
					return false;
				}
			});
		});
	};

	$package.registerFocusEmailFieldAfterOpen = function(popupId, formName, fieldName) {
		var popup = $('#' + popupId);

		var obj = $('#' + formName + "_" + fieldName);
		if (obj.length > 0) {
			obj.focus();
		}
		
		popup.on("afterOpenPopup", function() {
			$('#' + formName + "_" + fieldName).focus();
		});
		
		popup.on("afterAjaxFlowContentReplace", function() {
			var obj = $('#' + formName + "_" + fieldName);
			if (obj.length > 0) {
				obj.focus();
			}
		});
	}

})();

function initTimeZoneField(fieldName, formName) {
    oneweb.DocumentReadyManager.addApplicationAction(function() {
        var fieldId = '#' + formName + '_' + fieldName;
        $(fieldId).val(jstz.determine().name());
    });
}