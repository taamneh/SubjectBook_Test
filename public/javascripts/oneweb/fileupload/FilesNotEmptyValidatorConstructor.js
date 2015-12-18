(function() {
	var $package = oneweb.owjsPackage('fileupload');
	
	$package.FilesNotEmptyValidatorConstructor = function() {};
	
	$package.FilesNotEmptyValidatorConstructor.owextend($package.FilesValidatorConstructor);
	
	$package.FilesNotEmptyValidatorConstructor.prototype.internalValidate = function(formName, fieldName, files) {
		var config = $package.FileSender.getState(formName, fieldName).config;
		
		var invalidFileNames = [];
		
		for(var i = 0; i < files.length; i++) {
			var file = files[i];
			if (file.fileSize == 0) {
				invalidFileNames.push(file.fileName);
			}
		}
		
		if (invalidFileNames.length == 0) {
			return null;
		}
		invalidFileNames.sort();
		
		return epoint.ow.Utils.replaceI18NParameters(
				config.filesNotEmptyMsg,
				{invalid_file_names : invalidFileNames.join(', ')});
	};
})();