(function() {
	var $package = oneweb.owjsPackage('fileupload');
	
	$package.FilesValidatorConstructor = function() {};
	
	$package.FilesValidatorConstructor.prototype.validate = function(formName, fieldName, files) {
		var msg = this.internalValidate(formName, fieldName, files);
		
		if (msg == null) {
			return true;
		}
		
		$('#' + $package.Utils.getGlobalErrorDivId(formName, fieldName))
			.trigger($package.Utils.SHOW_GLOBAL_ERROR_EVENT_NAME, [msg]);
		return false;
	};
})();