(function() {
	var $package = oneweb.owjsPackage('fileupload');
	
	$package.FilesDimensionValidatorConstructor = function() {};
	
	$package.FilesDimensionValidatorConstructor.owextend($package.FilesValidatorConstructor);
	
	$package.FilesDimensionValidatorConstructor.prototype.internalValidate = function(formName, fieldName, files) {
		var config = $package.FileSender.getState(formName, fieldName).config;
		
		var invalidFileNames = [];
		
		for(var i = 0; i < files.length; i++) {
			var file = files[i];
			if (file.width != null) {
				if (config.minWidth != null && file.width < config.minWidth) {
					invalidFileNames.push(file.fileName);
					continue;
				}
				if (config.maxWidth != null && file.width > config.maxWidth) {
					invalidFileNames.push(file.fileName);
					continue;
				}	
			}
			if (file.height != null) {
				if (config.minHeight != null && file.height < config.minHeight) {
					invalidFileNames.push(file.fileName);
					continue;
				}
				if (config.maxHeight != null && file.height > config.maxHeight) {
					invalidFileNames.push(file.fileName);
					continue;
				}	
			}
		}
		
		if (invalidFileNames.length == 0) {
			return null;
		}

		invalidFileNames.sort();
		return epoint.ow.Utils.replaceI18NParameters(
				config.invalidDimensionMsg,
				{invalid_file_names : invalidFileNames.join(', ')});
	};
})();