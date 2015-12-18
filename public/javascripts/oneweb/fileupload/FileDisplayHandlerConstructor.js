(function() {
	var $package = oneweb.owjsPackage('fileupload');
	
	$package.FileDisplayHandlerConstructor = function() {};
	
	$package.FileDisplayHandlerConstructor.prototype.updateFileInfoArea =  function(file) {
		var div = file.getFileDiv();
		$.each(file.STATUS_NAMES, function() {
			div.removeClass("status_" + this);
		});
		div.addClass("status_" + file.status);
	};
	
	$package.FileDisplayHandlerConstructor.prototype.updateFileName = function(file) {
		var config = $package.FileSender.getState(file.formName, file.fieldName).config;
		
		var nameDiv = file.getFileDiv().find('.file-name');
		var fileName = file.fileName;
		var status = file.status;
		
		if (status == file.BEFORE_SENDING) {
			nameDiv.empty().append(fileName);
		} else if (status == file.SENDING) {
			var bold = $('<b>').append(fileName);
			nameDiv.empty().append(bold);
		} else if (status == file.SENT) {
			var preventAllFn = function(e) {
				e.stopImmediatePropagation();
				e.preventDefault();
				if (e.preventNextAction) {
					e.preventNextAction();
				}
			};

			var clickHandlerFn = function(e) {
				if ($(this).hasClass('disabled')) {
					preventAllFn(e);
					return;
				}
			};
			
			var anhor = $("<a>")
				.attr('href', config.temporaryStoragePrefix + file.id + "," + file.fileName)
				.attr('class', 'fileuploadTemporaryStorageLink')
				.click(clickHandlerFn)
				.append(fileName);
			nameDiv.empty().append(anhor);
		} else if (status == file.ERROR || status == file.ABORTED) {
			var strike = $('<strike>').append(fileName);
			nameDiv.empty().append(strike);
		}
	};
	
	$package.FileDisplayHandlerConstructor.prototype.updateFileSize = function(file) {
		var size = epoint.ow.Utils.formatFileSize(file.fileSize);

		file.getFileDiv().find('.file-size')
			.empty()
			.append(size);
	};
	
	$package.FileDisplayHandlerConstructor.prototype.updateFileProgress = function(file) {
		var status = file.status;
		
		var config = $package.FileSender.getState(file.formName, file.fieldName).config;

		var progressDiv = file.getFileDiv().find('.file-progress');

		if (status == file.BEFORE_SENDING) {
			progressDiv.find(".progress-inner").width("0%")
			progressDiv.find(".progress-text, .text").text("0%");
			progressDiv.show('slow');
		} else if (status == file.SENDING) {
			var progress = Math.floor(100*file.sentBytes/file.fileSize);
			progressDiv.find(".progress-inner").width(progress+"%")
			progressDiv.find(".progress-text, .text").text(progress + "%");
			progressDiv.show('slow');
		} else if (status == file.SENT) {
			progressDiv.hide('slow');
		} else if (status == file.ERROR) {
			var code = "";
			var description = "";
			if (file.errorData != null) {
				if (file.errorData.status != null) {
					code = file.errorData.status;
				}
				if (file.errorData.statusText != null) {
					description = file.errorData.statusText;
				}
			}
			var msg = epoint.ow.Utils.replaceI18NParameters(config.errorMsg, {error_code:code, error_description:description});
			progressDiv.empty().append($('<span>').append(msg).attr('style', 'color:red')).show('slow');
		} else if (status == file.ABORTED) {
			var abortInfo = $('<span>').append(config.abortedInfo).attr('style', 'color:grey');
			progressDiv.empty().append(abortInfo).show('slow');
		}
	};
	
	$package.FileDisplayHandlerConstructor.prototype.updateRemoveButton = function(file) {
		var status = file.status;
		var config = $package.FileSender.getState(file.formName, file.fieldName).config;
		
		var label;
		if (status == file.BEFORE_SENDING) {
			label = config.abortUploadButton;
		} else if (status == file.SENDING) {
			label = config.abortUploadButton;
		} else if (status == file.SENT || status == file.DELETED) {
			label = config.removeFileButton;
		} else {
			label = $('<img>').attr('src', config.imgPrefix + "remove.gif");
		}
		
		var btn = $('<a>')
			.html(label)
			.addClass('button_remove')
			.attr("href", "#")
			.click($package.FileSender.getRemoveButtonHandlerFn(file));
		
		var buttonDiv = file.getFileDiv().find('.file-button');
		buttonDiv.empty().append(btn);
	};
	
	$package.FileDisplayHandlerConstructor.prototype.updateTotalSize = function(formName, fieldName) {
		var progress = $package.FileSender.getTotalProgress(formName, fieldName);
		$('#' + $package.Utils.getTotalSizeDivId(formName, fieldName))
			.empty()
			.append(epoint.ow.Utils.formatFileSize(progress.progress) + "/" + epoint.ow.Utils.formatFileSize(progress.total))
			.show('slow');
	};
	
	$package.FileDisplayHandlerConstructor.prototype.updateTotalProgress = function(formName, fieldName) {
		var progress = $package.FileSender.getTotalProgress(formName, fieldName);

		var percent = progress.total;
		if (progress.total > 0) {
			percent = Math.floor(100*progress.progress/progress.total);
		}
		
		var progressDiv = $('#' + $package.Utils.getTotalProgressDivId(formName, fieldName));
		
		progressDiv.find(".progress-inner").width(percent+"%")
		progressDiv.find(".progress-text, .text").text(percent + "%");
		progressDiv.show('slow');
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initDraggableHandler = function(formName, fieldName) {
		var field = epoint.ow.Utils.getField(formName, fieldName);
		
		var draggable = $('#' + field.id + "_draggable");
		
		draggable
			.removeClass('initialHide')
			.show('slow');
		
		var area = draggable.closest('.box_advanced_upload_field');
		
		var wrapper = $("<div class='drop_mask_wrapper'></div>");
		var element = $("<div class='drop_mask'></div>");

		wrapper.append(element);
		area.prepend(wrapper);
		
		var updateSizeFn = function() {
			element.width(area.width());
			element.height(area.height());
		};

		var interval = null;
		area
			.bind('dragenter', function(e) {
				updateSizeFn();
				interval = setInterval(updateSizeFn, 100);
				wrapper.show();
			});
	
		element
			.bind('dragleave', function(e) {
				wrapper.hide();
			})
			.bind('dragover', function(e) {
				e.preventDefault();
			})
			.bind('drop', function(e) {
				e.preventDefault();
				clearInterval(interval);
				wrapper.hide();
				$package.FileHandler.handleFileList(formName, fieldName, e.originalEvent.dataTransfer.files);
			});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initChangeEventHandler = function(formName, fieldName) {
		var field = epoint.ow.Utils.getField(formName, fieldName);
		
		$(field).change(function(e) {
			$package.FileHandler.handleFileList(formName, fieldName, this.files);
			var c = $(this).clone(true);
			$(c).val("");
			$(this).after(c);
			$(this).remove();
		});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initAbortAllButton = function(formName, fieldName) {
		var abortAllButton = $('#' + $package.Utils.getAbortAllButtonId(formName, fieldName));
		abortAllButton
			.unbind('click')
			.click(function(e) {
				$package.FileSender.abortAllUpload(formName, fieldName);});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initMultiUploadInProgressEventHandler = function(formName, fieldName) {
		
		var globalErrorDiv = $('#' + $package.Utils.getGlobalErrorDivId(formName, fieldName));
		var filesSentInfoDiv = $('#' + $package.Utils.getFilesSentInfoDivId(formName, fieldName));
		var multiUploadDiv = $('#' + $package.Utils.getMultiUploadDivId(formName, fieldName));
		var multiUploadInProgressDiv = $('#' + $package.Utils.getMultiUploadInProgressDivId(formName, fieldName));
		
		multiUploadDiv
			.bind($package.Utils.MULTI_UPLOAD_IN_PROGRESS_EVENT_NAME, function(e) {
				$('.fileuploadTemporaryStorageLink').addClass('disabled');
				globalErrorDiv.hide('slow');
				multiUploadDiv.show('slow');
				multiUploadInProgressDiv.show('slow');
				filesSentInfoDiv.hide('slow');});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initSingleUploadInProgressEventHandler = function(formName, fieldName) {
		
		var multiUploadDiv = $('#' + $package.Utils.getMultiUploadDivId(formName, fieldName));
		var filesSentInfoDiv = $('#' + $package.Utils.getFilesSentInfoDivId(formName, fieldName));
		
		multiUploadDiv
			.bind($package.Utils.SINGLE_UPLOAD_IN_PROGRESS_EVENT_NAME, function(e) {
				$('.fileuploadTemporaryStorageLink').addClass('disabled');
				multiUploadDiv.hide('slow');
				filesSentInfoDiv.hide('slow');});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initEndSingleUploadEventHandler = function(formName, fieldName) {
		var multiUploadDiv = $('#' + $package.Utils.getMultiUploadDivId(formName, fieldName));

		multiUploadDiv
			.bind($package.Utils.END_SINGLE_UPLOAD_EVENT_NAME, function(e) {
				$('.fileuploadTemporaryStorageLink').removeClass('disabled');});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initAbortAllUploadEventHandler = function(formName, fieldName) {
		var multiUploadDiv = $('#' + $package.Utils.getMultiUploadDivId(formName, fieldName));

		multiUploadDiv
			.bind($package.Utils.ABORT_ALL_UPLOAD_EVENT_NAME, function(e) {
				multiUploadDiv.hide('slow');});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initEndMultiUploadEventHandler = function(formName, fieldName) {
		
		var multiUploadDiv = $('#' + $package.Utils.getMultiUploadDivId(formName, fieldName));
		var multiUploadInProgressDiv = $('#' + $package.Utils.getMultiUploadInProgressDivId(formName, fieldName));
		var filesSentInfoDiv = $('#' + $package.Utils.getFilesSentInfoDivId(formName, fieldName));
		
		var state = $package.FileSender.getState(formName, fieldName);
		multiUploadDiv
			.bind($package.Utils.END_MULTI_UPLOAD_EVENT_NAME, function(e, uploadedFiles) {
				$('.fileuploadTemporaryStorageLink').removeClass('disabled');
				multiUploadDiv.show('slow');
				multiUploadInProgressDiv.hide('slow');
				filesSentInfoDiv
					.html(epoint.ow.Utils.replaceI18NParameters(state.config.filesSentMsg, {"sent_files" : uploadedFiles.length}))
					.append($('<a>')
								.html($('<img>').attr('src', state.config.imgPrefix + "remove.gif"))
								.click(function() {multiUploadDiv.hide('slow');}))
					.show('slow');});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initShowGlobalErrorEventHandler = function(formName, fieldName) {
		var globalErrorDiv = $('#' + $package.Utils.getGlobalErrorDivId(formName, fieldName));
		
		globalErrorDiv
			.bind($package.Utils.SHOW_GLOBAL_ERROR_EVENT_NAME, function(e, msg) {
				globalErrorDiv
					.html(msg)
					.show('slow');
				setTimeout(function() {globalErrorDiv.hide('slow');}, 3000);});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initFileStatusChangedEventHandler = function(formName, fieldName) {
		var that = this;
		$package.Utils.getTemplateDiv(formName, fieldName)
			.bind($package.Utils.FILE_STATUS_CHANGED_EVENT_NAME, function(e, file) {
				if (file.status == file.DELETED) {
					file.getFileDiv().hide('slow');
				} else {
					that.updateFileInfoArea(file);
					
					that.updateFileName(file);
					that.updateFileProgress(file);
					
					that.updateFileSize(file);
					that.updateRemoveButton(file);
					
					file.getFileDiv().show('slow');
				}
				
				that.updateTotalProgress(file.formName, file.fieldName);
				that.updateTotalSize(file.formName, file.fieldName);
			});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initFileProgressChangedEventHandler = function(formName, fieldName) {
		var that = this;
		$package.Utils.getTemplateDiv(formName, fieldName)
			.bind($package.Utils.FILE_PROGRESS_CHANGED_EVENT_NAME, function(e, file) {
				that.updateFileProgress(file);
				that.updateTotalSize(file.formName, file.fieldName);
				that.updateTotalProgress(file.formName, file.fieldName);});
	};
	
	$package.FileDisplayHandlerConstructor.prototype.initAllHandlers = function(formName, fieldName) {
		this.initAbortAllButton(formName, fieldName);
		this.initChangeEventHandler(formName, fieldName);
		this.initDraggableHandler(formName, fieldName);
		
		this.initMultiUploadInProgressEventHandler(formName, fieldName);
		this.initSingleUploadInProgressEventHandler(formName, fieldName);
		this.initEndSingleUploadEventHandler(formName, fieldName);
		this.initEndMultiUploadEventHandler(formName, fieldName);
		this.initAbortAllUploadEventHandler(formName, fieldName);

		this.initShowGlobalErrorEventHandler(formName, fieldName);
		
		this.initFileStatusChangedEventHandler(formName, fieldName);
		this.initFileProgressChangedEventHandler(formName, fieldName);
	};
	
	$package.FileDisplayHandler = new $package.FileDisplayHandlerConstructor();
})();
