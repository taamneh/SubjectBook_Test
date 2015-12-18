(function() {
	var $package = oneweb.owjsPackage('fileupload');
	
	$package.FilesExtensionValidatorConstructor = function() {};
	
	$package.FilesExtensionValidatorConstructor.owextend($package.FilesValidatorConstructor);
	
	$package.FilesExtensionValidatorConstructor.prototype.internalValidate = function(formName, fieldName, files) {
		var config = $package.FileSender.getState(formName, fieldName).config;
		
		if (config.allowedExtensions == null || config.allowedExtensions.length == 0) {
			return null;
		}
		
		var invalidFileNames = [];
		
		for(var i = 0; i < files.length; i++) {
			var file = files[i];
			var extension = epoint.ow.Utils.getFileExtension(file.fileName);

			if (epoint.ow.Utils.arraySearchIgnoreCase(config.allowedExtensions, extension) == -1) {
				invalidFileNames.push(file.fileName);
			}
		}
		
		if (invalidFileNames.length == 0) {
			return null;
		}
		
		invalidFileNames.sort();
		return epoint.ow.Utils.replaceI18NParameters(
				config.invalidExtensionMsg,
				{invalid_file_names : invalidFileNames.join(', ')});
	};
})();