(function() {
	var $package = oneweb.owjsPackage('fileupload');

	$package.File = function(formName, fieldName) {
		this.formName = formName;
		this.fieldName = fieldName;
		
		this.status = null;
		this.errorData = null;
		this.originalFile = null;
		this.fileName = null;
		this.fileSize = null;
		
		this.imageDataUrl = null;
		this.width = null;
		this.height = null;
	
		this.id = null;
		this.xhr = null;
		this.sentBytes = 0;
		
		this.isImage = false;
		this.isFlash = false;
	};
	
	$package.File.prototype.BEFORE_SENDING = "before_sending";
	$package.File.prototype.SENDING = "sending";
	$package.File.prototype.SENT = "sent";
	$package.File.prototype.ERROR = "error";
	$package.File.prototype.ABORTED = "aborted";
	$package.File.prototype.DELETED = "deleted";
	$package.File.prototype.STATUS_NAMES = [$package.File.prototype.BEFORE_SENDING,
	                                        $package.File.prototype.SENDING,
	                                        $package.File.prototype.SENT,
	                                        $package.File.prototype.ERROR,
	                                        $package.File.prototype.ABORTED,
	                                        $package.File.prototype.DELETED];
	
	$package.File.prototype.initNextTemporaryId = function() {
		var i = 1;
		
		while(true) {
			this.id = "tmp_" + i;
			if (this.getFileDiv().size() == 0) {
				break;
			}
			i++;
		}
	};
	
	$package.File.prototype.createHtmlElements = function() {
		var fileDiv = $package.Utils.getTemplateDiv(this.formName, this.fieldName).clone(true);
		fileDiv.hide();
		fileDiv.attr('id', this.getFileDivId());
		this.getFileContainerDiv().append(fileDiv);
	};
	
	$package.File.prototype.preloadNewFile = function(originalFileData, loadendHandlerFn) {
		if (originalFileData == null) {
			throw "originalFileData can not be null";
		}
		this.originalFile = originalFileData;
		this.fileName = originalFileData.name;
		this.fileSize = originalFileData.size;
		
		var that = this;
		
		// preload image
		var reader = new FileReader();
		reader.onload = function(e) {
			var url = e.target.result;
			
			var img = new Image();
			img.onload = function(e) {
				that.imageDataUrl = url;
				that.width = img.width;
				that.height = img.height;
				that.isImage = true;
				
				loadendHandlerFn(e, that);
			};
			img.onerror = function(e) {
				loadendHandlerFn(e, that);
			};
			img.src = url;
		};
		reader.readAsDataURL(originalFileData);
	};
	
	$package.File.prototype.initNewFile = function() {
		this.initNextTemporaryId();
		this.createHtmlElements();
		this.changeStatus(this.BEFORE_SENDING);
	};
	
	$package.File.prototype.initExistingFile = function(existingFileData) {
		if (existingFileData == null) {
			throw "existingFileData can not be null";
		}
		if (existingFileData.id == null) {
			throw "id can not be null";
		}
		if (existingFileData.fileName == null) {
			throw "fileName can not be null";
		}
		if (existingFileData.fileSize == null) {
			throw "fileSize can not be null";
		}
		
		this.id = existingFileData.id;
		this.fileSize = existingFileData.fileSize;
		this.fileName = existingFileData.fileName;
		this.isImage = existingFileData.isImage;
		this.isFlash = existingFileData.isFlash;
		this.width = existingFileData.width;
		this.height = existingFileData.height;
		
		if (this.getFileDiv().size() == 0) {
			this.createHtmlElements();
		}
		
		this.changeStatus(this.SENT);
	};
	
	$package.File.prototype.changeStatus = function(newStatus) {
		if (	newStatus != this.BEFORE_SENDING &&
				newStatus != this.SENDING &&
				newStatus != this.SENT &&
				newStatus != this.ERROR &&
				newStatus != this.ABORTED &&
				newStatus != this.DELETED) {
			throw "illegal file status:" + newStatus;
		}
		this.status = newStatus;
		this.getFileDiv().trigger($package.Utils.FILE_STATUS_CHANGED_EVENT_NAME, [this]);
		
		epoint.ow.forms.ErrorManager.clearFieldErrors(this.formName, this.fieldName);
		epoint.ow.forms.ErrorDisplayManager.showErrors(this.formName);

		if (newStatus === this.DELETED) {
			var div = this.getFileDiv();
			setTimeout(function() {div.remove();}, 3000);
		}
	};
	
	$package.File.prototype.getFileDivId = function() {
		return $package.Utils.getFileDivId(this.formName, this.fieldName, this.id);
	};
	
	$package.File.prototype.getFileDiv = function() {
		return $('#' + this.getFileDivId());
	};
	
	$package.File.prototype.getFileContainerDivId = function() {
		return $package.Utils.getFileContainerDivId(this.formName, this.fieldName);
	};
	
	$package.File.prototype.getFileContainerDiv = function() {
		return $('#' + this.getFileContainerDivId());
	};
	
	$package.File.prototype.abortUpload = function() {
		if (this.xhr == null) {
			throw "xhr can not be null";
		}
		
		this.changeStatus(this.ABORTED);
		this.xhr.abort();
	};

})();