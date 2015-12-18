(function() {
	var $package = oneweb.owjsPackage('fileupload');
	
	$package.FilesCountValidatorConstructor = function() {};
	
	$package.FilesCountValidatorConstructor.owextend($package.FilesValidatorConstructor);
	
	$package.FilesCountValidatorConstructor.prototype.internalValidate = function(formName, fieldName, files) {
		var state = $package.FileSender.getState(formName, fieldName);
		var config = state.config;
		
		if (config.maxFileCount == null) {
			return null;
		}

		var filesCount = state.sessionFiles
			.filter(function(f) {return f.status == f.BEFORE_SENDING || f.status == f.SENDING;}).length;
		filesCount += state.uploadedFiles.length;
		filesCount += files.length;

		if (filesCount <= config.maxFileCount) {
			return null;
		}
		return epoint.ow.Utils.replaceI18NParameters(
				config.maxFileCountMsg,
				{file_count : filesCount, max_count : config.maxFileCount});
	};
})();