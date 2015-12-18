(function() {
	var $package = oneweb.owjsPackage('fileupload');
	
	$package.FilesSizeValidatorConstructor = function() {};
	
	$package.FilesSizeValidatorConstructor.owextend($package.FilesValidatorConstructor);
	
	$package.FilesSizeValidatorConstructor.prototype.internalValidate = function(formName, fieldName, files) {
		var config = $package.FileSender.getState(formName, fieldName).config;
		
		if (config.maxSize == null) {
			return true;
		}
		
		var invalidFileNames = [];
		
		for(var i = 0; i < files.length; i++) {
			var file = files[i];
			if (file.fileSize > config.maxSize) {
				invalidFileNames.push(file.fileName);
			}
		}
		
		if (invalidFileNames.length == 0) {
			return null;
		}
		invalidFileNames.sort();
		
		return epoint.ow.Utils.replaceI18NParameters(
				config.maxSizeMsg,
				{max_size : epoint.ow.Utils.formatFileSize(config.maxSize), invalid_file_names : invalidFileNames.join(', ')});
	};
})();