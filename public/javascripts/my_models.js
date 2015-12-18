function registerModelVersionActionSelectClickHandler(fieldId, configStr) {
	var field = $('#' + fieldId);
	var config = JSON.parse(configStr);
	
	var handlerFn = function(e) {
		var fieldValue = field.val();
		field.get(0).value = "";		
		
		e.preventDefault();
		
		switch (fieldValue){
		case "0":
			alert("Not implemented yet!");
			break;
		case "1":
			window.open(config["1"]);
			break;
		case "2":
			alert("Not implemented yet!");
			break;
		case "3":
			epoint.ow.flow.Utils.handleUrl(config["3"]);
			break;
		case "4":
			epoint.ow.flow.Utils.handleUrl(config["4"]);
			break;
		case "5":
			epoint.ow.flow.Utils.handleUrl(config["5"]);
			break;
		default:
			console.warn("No such field value: " + fieldValue);
			break;
		}
	};
	
	oneweb.DocumentReadyManager.addApplicationAction(function() {
		field.change(handlerFn);
	});
};

function registerShowAutosavedVersionsClickHandler(fieldId, url) {
	var checkboxField = $("#" + fieldId);

	var handlerFn = function(e) {
		epoint.ow.forms.Utils.submitForm("show_autosaved_versions", false, url);
	};

	oneweb.DocumentReadyManager.addApplicationAction(function() {
		checkboxField.change(handlerFn);
	});
};

function registerOpenModelSharePopup(popupFlowId, formName, fieldName) {
	oneweb.DocumentReadyManager.addApplicationAction(function() {
		epoint.ow.popup.PopupManager.openPopup(popupFlowId);
		var obj = $('#' + formName + "_" + fieldName);
		if (obj.length > 0) {
			obj.focus();
		}
	}, 100, true);
};

$(document).ready(function() {
    $('.account_plan_table_limit_reached').click(function() {
        alert('Database model limit has been reached. Remove unnecessary models or upgrade your subscription plan.');
        return false;
    });

	$('#public-link-url-text-field').focus(function(el) {
		
		$(this).one('mouseup', function () {
            $(this).select();
            return false;
        }).select();

    });
});

