(function() {
	var $package = oneweb.owjsPackage('fileupload');
	
	$package.FileHandlerConstructor = function() {};
	
	$package.FileHandlerConstructor.prototype.getValidators = function() {
		return [
		                  new $package.FilesCountValidatorConstructor(),
		                  new $package.FilesNotEmptyValidatorConstructor(),
		                  new $package.FilesSizeValidatorConstructor(),
		                  new $package.FilesExtensionValidatorConstructor(),
		                  new $package.FilesDimensionValidatorConstructor()
		                  ];
	};
	
	$package.FileHandlerConstructor.prototype.validateFiles = function(formName, fieldName, files) {
		var validators = this.getValidators();
		
		for(var i = 0; i < validators.length; i++) {
			var validator = validators[i];
			
			if (!validator.validate(formName, fieldName, files)) {
				return false;
			}
		}
		
		return true;
	};
	
	$package.FileHandlerConstructor.prototype.handleFileList = function(formName, fieldName, fileList) {
		var filesCount = fileList.length;
		var filesToLoad = [];
		
		var that = this;
		
		var loadendHandlerFn = function(e, file) {
			filesToLoad.push(file);
			if (filesToLoad.length < filesCount) {
				return;
			}
			
			if (!that.validateFiles(formName, fieldName, filesToLoad)) {
				return;
			}
			
			for(var i = 0; i < filesToLoad.length; i++) {
				var file = filesToLoad[i];
				file.initNewFile();
				$package.FileSender.addFile(file);
			}
			
			$package.FileSender.startSending(formName, fieldName);
		}
		
		for(var i = 0; i < fileList.length; i++) {
			var file = new $package.File(formName, fieldName);
			file.preloadNewFile(fileList[i], loadendHandlerFn);
		}
	};

	$package.FileHandlerConstructor.prototype.initFileHandler = function(formName, fieldName, submitAjaxAction, config, renderTimeMillis) {
		var xhr = null;
		try {
			xhr = new XMLHttpRequest();
		} catch(e) {
			// do nothing
		}
		if (xhr != null && xhr.upload != null) {
			$package.Utils.getTemplateDiv(formName, fieldName)
				.trigger($package.Utils.INIT_ADVANCED_FILE_HANDLERS_EVENT_NAME, [formName, fieldName]);
			$package.FileSender.initFilesOnReload(formName, fieldName, config, renderTimeMillis);
		} else {
			var field = epoint.ow.Utils.getField(formName, fieldName);
			$(field).change(function(e) {
				epoint.ow.forms.Utils.submitForm(formName, false, submitAjaxAction);
			});
		}
	};

	$package.FileHandler = new $package.FileHandlerConstructor();
	
	oneweb.DocumentReadyManager.addOnewebAction(function() {
		$(document).find('.box_advanced_image_field')
			.unbind($package.Utils.INIT_ADVANCED_FILE_HANDLERS_EVENT_NAME)
			.bind(
					$package.Utils.INIT_ADVANCED_FILE_HANDLERS_EVENT_NAME,
					function(e, formName, fieldName) {$package.ImageDisplayHandler.initAllHandlers(formName, fieldName);});

		$(document).find('.box_advanced_file_field')
			.unbind($package.Utils.INIT_ADVANCED_FILE_HANDLERS_EVENT_NAME)
			.bind(
					$package.Utils.INIT_ADVANCED_FILE_HANDLERS_EVENT_NAME,
					function(e, formName, fieldName) {$package.FileDisplayHandler.initAllHandlers(formName, fieldName);});
	}, 90, false);
})();