(function() {
	var $package = oneweb.owjsPackage('fileupload');
	
	$package.ImageDisplayHandlerConstructor = function() {};
	$package.ImageDisplayHandlerConstructor.owextend($package.FileDisplayHandlerConstructor);
	
	$package.ImageDisplayHandlerConstructor.prototype.updateFileDimensions = function(image) {
		var div = image.getFileDiv().find('.file-dimension');
		div.empty();
		if (image.width != null && image.height != null) {
			div.html(image.width + "x" + image.height + "px");
		}
	};
	
	$package.ImageDisplayHandlerConstructor.prototype.updateFileImage = function(image) {
		var state = $package.FileSender.getState(image.formName, image.fieldName);
		var maxPreviewWidth = state.config.maxPreviewWidth != null ? state.config.maxPreviewWidth : 200;
		var maxPreviewHeight = state.config.maxPreviewHeight != null ? state.config.maxPreviewHeight : 200;

		var width = maxPreviewWidth;
		var height = maxPreviewHeight;
		var marginTop = 0;

		var imageDiv = image.getFileDiv().find('.file-image');
		imageDiv.height(maxPreviewHeight);
		var status = image.status;

		if (image.width != null && image.height != null) {
			var originalScale = image.height / image.width;
			var maxScale = maxPreviewHeight / maxPreviewWidth;

			if (originalScale > maxScale) {
				height = Math.min(maxPreviewHeight, image.height);
				width = height/image.height * image.width;
			} else {
				width = Math.min(maxPreviewWidth, image.width);
				height = width/image.width * image.height;
			}
			if (height < maxPreviewHeight) {
				marginTop = Math.floor((maxPreviewHeight-height)/2);
			}
		}
		
		if (status == image.BEFORE_SENDING || status == image.SENDING) {
			if (image.imageDataUrl != null) {
				var img = $('<img>')
					.attr('width', width)
					.attr('height', height)
					.css('margin-top', marginTop)
					.attr('src', image.imageDataUrl);
				imageDiv.empty().append(img).show('slow');
			}
		} else if (status == image.SENT) {
			if (image.isImage == true) {
				var img = new Image();
				if (imageDiv.children().size() > 0) {
					img.onload = img.onerror = function(e) {
						imageDiv.empty().append(img).show('slow');
					};
				} else {
					imageDiv.empty().append(img).show('slow');
				}
				
				$(img)
					.attr('width', width)
					.attr('height', height)
					.css('margin-top', marginTop)
					.attr('src', '/temporary_storage/' + image.id);
			} else if (image.isFlash == true) {
				var img = $('<object>')
				.attr('data', '/temporary_storage/' + image.id)
				.attr('width', width)
				.attr('height', height)
				.css('margin-top', marginTop);
				imageDiv.empty().append(img).show('slow');
			} else {
				var img = $('<a>')
				.attr('href', '/temporary_storage/' + image.id)
				.html(image.fileName);
				imageDiv.empty().append(img).show('slow');
			}
		} else if (status == image.ERROR) {
			var strike = $('<strike>').append(image.fileName);
			imageDiv.empty().append(strike);
		}
	};
	
	$package.ImageDisplayHandlerConstructor.prototype.initFileStatusChangedEventHandler = function(formName, fieldName) {
		var that = this;
		$package.Utils.getTemplateDiv(formName, fieldName)
			.bind($package.Utils.FILE_STATUS_CHANGED_EVENT_NAME, function(e, file) {
				if (file.status == file.DELETED) {
					file.getFileDiv().hide('slow');
				} else {
					file.getFileDiv().show('slow');
				}
				
				that.updateFileInfoArea(file);
	
				that.updateFileName(file);
				that.updateFileDimensions(file);
				that.updateFileImage(file);
				that.updateFileProgress(file);
	
				that.updateFileSize(file);
				that.updateRemoveButton(file);
	
				that.updateTotalProgress(file.formName, file.fieldName);
				that.updateTotalSize(file.formName, file.fieldName);});
	};
	
	
	$package.ImageDisplayHandler = new $package.ImageDisplayHandlerConstructor();
})();