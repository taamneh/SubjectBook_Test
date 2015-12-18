(function() {
	var $package = oneweb.owjsPackage('fileupload');

	$package.FileSenderConstructor = function(formName, fieldName) {};
	
	$package.FileSenderConstructor.prototype.fileSenderState = {};
	
	$package.FileSenderConstructor.prototype.getState = function(formName, fieldName) {
		var fieldId = epoint.ow.forms.Utils.getFieldId(formName, fieldName);
		var state = this.fileSenderState[fieldId];
		if (state == null) {
			state = {
						sessionFiles : [],
						uploadedFiles : [],
						lastModificationTimeMillis : null
			};
			
			this.fileSenderState[fieldId] = state;
		}
		
		return state;
	};
	
	$package.FileSenderConstructor.prototype.getFileFromArrayById = function(array, fileId) {
		for(var i = 0; i < array.length; i++) {
			if (array[i].id == fileId) {
				return array[i];
			}
		}
		return null;
	};
	
	$package.FileSenderConstructor.prototype.getFileById = function(formName, fieldName, fileId) {
		var state = this.getState(formName, fieldName);
		
		var file = this.getFileFromArrayById(state.uploadedFiles, fileId);
		if (file != null) {
			return file;
		}
		
		return this.getFileFromArrayById(state.sessionFiles, fileId);
	};
	
	
	$package.FileSenderConstructor.prototype.removeFileFromMemory = function(file) {
		var state = this.getState(file.formName, file.fieldName);
		var array = [state.sessionFiles, state.uploadedFiles];
		
		var foundFile = this.getFileFromArrayById(state.uploadedFiles, file.id)
		if (foundFile != null) {
			state.uploadedFiles.splice(state.uploadedFiles.indexOf(foundFile), 1);
		}
		
		file.changeStatus(file.DELETED);
	};
	
	$package.FileSenderConstructor.prototype.abortAllUpload = function(formName, fieldName) {
		var sessionFiles = this.getState(formName, fieldName).sessionFiles;
		
		var filesToRemove = [];
		for(var i = 0; i < sessionFiles.length; i++) {
			var file = sessionFiles[i];
			
			if (file.status == file.BEFORE_SENDING) {
				file.changeStatus(file.ABORTED);
				filesToRemove.push(file);
			}
		}
		for(var i = 0; i < sessionFiles.length; i++) {
			var file = sessionFiles[i];
			
			if (file.status == file.SENDING) {
				file.abortUpload();
				filesToRemove.push(file);
			}
		}
		
		var that = this;
		setTimeout(function() {
			for(var i = 0; i < filesToRemove.length; i++) {
				that.removeFileFromMemory(filesToRemove[i]);
			}
			
			$('#' + $package.Utils.getMultiUploadDivId(formName, fieldName))
				.trigger($package.Utils.ABORT_ALL_UPLOAD_EVENT_NAME);
			
		}, 3000);
	};
	
	$package.FileSenderConstructor.prototype.isRemoveCancelled = function(file) {
		var status = file.status;
		
		var config = this.getState(file.formName, file.fieldName).config;
		
		if (status == file.BEFORE_SENDING ||
				status == file.SENDING) {
			return confirm(config.abortUploadConfirmMsg) == false;
		} else if (status == file.SENT) {
			return confirm(config.removeFileConfirmMsg) == false;
		}
		
		return false;
	};
	
	$package.FileSenderConstructor.prototype.getRemoveButtonHandlerFn = function(file) {
		var that = this;
		return function(e) {
			e.preventDefault();

			if (that.isRemoveCancelled(file)) {
				return;
			}
			
			var status = file.status;
			
			if (status == file.SENDING) {
				file.abortUpload();
				that.removeFileFromMemory(file);
			} else if (status == file.SENT) {
				var params = {
						form : file.formName,
						field : file.fieldName,
						storage : file.id,
						isMultiUpload : false};

				oneweb.CsrfTokenManager.postAjax('?action=forms.removeFileFromTemporaryStorageWithAjax', params, function() {

					that.removeFileFromMemory(file);
				});
			} else {
				that.removeFileFromMemory(file);
			}
		}
	};
	
	$package.FileSenderConstructor.prototype.addFile = function(file) {
		var state = this.getState(file.formName, file.fieldName);
		
		if (file == null) {
			throw "file can not be null";
		}
		
		this.getState(file.formName, file.fieldName).sessionFiles.push(file);
	};
	
	$package.FileSenderConstructor.prototype.getTotalProgress = function(formName, fieldName) {
		var total = 0;
		var progress = 0;
		
		var files = this.getState(formName, fieldName).sessionFiles;
		for(var i = 0; i < files.length; i++) {
			var file = files[i];
			if (file.status != file.BEFORE_SENDING
					&& file.status != file.SENDING
					&& file.status != file.SENT) {
				continue;
			}
			total += file.fileSize;
			
			if (file.status != file.BEFORE_SENDING) {
				progress += file.sentBytes;
			}
		}
		
		return {
			total : total,
			progress : progress
		};
	};
	
	$package.FileSenderConstructor.prototype.isSessionEnded = function(formName, fieldName) {
		var sessionFiles = this.getState(formName, fieldName).sessionFiles;
		return sessionFiles
				.filter(function(file) {
					return (file.status == file.BEFORE_SENDING)
							|| (file.status == file.SENDING);})
				.length == 0;
	};
	
	$package.FileSenderConstructor.prototype.isSendingInProgress = function(formName, fieldName) {
		var sessionFiles = this.getState(formName, fieldName).sessionFiles;
		return sessionFiles
				.filter(function(file) {
					return file.status == file.SENDING;})
				.length > 0;
	};
	
	$package.FileSenderConstructor.prototype.handleSessionEnd = function(formName, fieldName) {
		var sessionFiles = this.getState(formName, fieldName).sessionFiles;
		if (sessionFiles.length > 1) {
			var sentFiles = sessionFiles
				.filter(function(file) {return file.status == file.SENT;});
			$('#' + $package.Utils.getMultiUploadDivId(formName, fieldName))
				.trigger($package.Utils.END_MULTI_UPLOAD_EVENT_NAME, [sentFiles]);
		} else {
			$('#' + $package.Utils.getMultiUploadDivId(formName, fieldName))
				.trigger($package.Utils.END_SINGLE_UPLOAD_EVENT_NAME);
		}
		
		this.getState(formName, fieldName).sessionFiles = [];
		return;
	};
	
	
	$package.FileSenderConstructor.prototype.handleSessionInProgress = function(formName, fieldName) {
		var sessionFiles = this.getState(formName, fieldName).sessionFiles;
		if (sessionFiles.length > 1) {
			$('#' + $package.Utils.getMultiUploadDivId(formName, fieldName))
				.trigger($package.Utils.MULTI_UPLOAD_IN_PROGRESS_EVENT_NAME);
		} else {
			$('#' + $package.Utils.getMultiUploadDivId(formName, fieldName))
				.trigger($package.Utils.SINGLE_UPLOAD_IN_PROGRESS_EVENT_NAME);
		}
		return;
	};
	
	
	$package.FileSenderConstructor.prototype.startSending = function(formName, fieldName) {
		
		if (this.isSessionEnded(formName, fieldName)) {
			this.handleSessionEnd(formName, fieldName);
			return;
		}
		
		this.handleSessionInProgress(formName, fieldName);
		if (this.isSendingInProgress(formName, fieldName)) {
			return;
		}
		
		var file = this.getState(formName, fieldName).sessionFiles
			.filter(function(f) {return f.status == f.BEFORE_SENDING;})[0];
		if (file != null) {
			this.uploadFile(file);
		}
	};
	
	$package.FileSenderConstructor.prototype.synchronizeFiles = function(formName, fieldName, existingFiles, currentTimeMillis) {
		var state = this.getState(formName, fieldName);
		
		if (state.lastModificationTimeMillis == null ||
				currentTimeMillis > state.lastModificationTimeMillis) {
			state.lastModificationTimeMillis = currentTimeMillis;

			// synchronize deleted files
			var filesToRemove = [];
			for(var i = 0; i < state.uploadedFiles.length; i++) {
				var uploadedFile = state.uploadedFiles[i];
				
				if (this.getFileFromArrayById(existingFiles, uploadedFile.id) == null) {
					filesToRemove.push(uploadedFile);
				}
			}
			for(var i = 0; i < filesToRemove.length; i++) {
				this.removeFileFromMemory(filesToRemove[i]);
			}
			
			// synchronize new files
			var newUploadedFiles = [];
			for(var i = 0; i < existingFiles.length; i++) {
				var existingFile = existingFiles[i];
				
				if (this.getFileById(formName, fieldName, existingFile.id) == null) {
					var newFile = new $package.File(formName, fieldName);
					newFile.initExistingFile(existingFile);
					newUploadedFiles.push(newFile);
				} else {
					var uploadedFile = this.getFileById(formName, fieldName, existingFile.id);
					newUploadedFiles.push(uploadedFile);
					if (uploadedFile.getFileDiv().size() == 0) {
						uploadedFile.createHtmlElements();
						uploadedFile.changeStatus(uploadedFile.status);
					}
				}
			}
			state.uploadedFiles = newUploadedFiles;
		}
		
		// init not uploaded files
		for(var i = 0; i < state.sessionFiles.length; i++) {
			var sessionFile = state.sessionFiles[i];

			if (
					(sessionFile.status != sessionFile.DELETED) &&
					(sessionFile.getFileDiv().size() == 0)) {
				sessionFile.initNextTemporaryId();
				sessionFile.createHtmlElements();
				sessionFile.changeStatus(sessionFile.status);
			}
		}
	};
	
	$package.FileSenderConstructor.prototype.initFilesOnReload = function(formName, fieldName, config, renderTimeMillis) {
		var state = this.getState(formName, fieldName);
		if (config == null) {
			throw "config can not be null";
		}
		state.config = config;

		$('#' + $package.Utils.getFileContainerDivId(formName, fieldName)).empty();
		
		var files = config.files != null ? config.files : [];
		config.files = [];
		
		this.synchronizeFiles(formName, fieldName, files, renderTimeMillis);
		if (this.isSessionEnded(formName, fieldName)) {
			this.handleSessionEnd(formName, fieldName);
		} else {
			this.handleSessionInProgress(formName, fieldName);
		}
	};
	
	
	$package.FileSenderConstructor.prototype.uploadFile = function(file) {
		var xhr = new XMLHttpRequest();
		
		if (xhr.upload == null) {
			throw "upload not supported";
		}
		
		file.xhr = xhr;
		file.changeStatus(file.SENDING);
		
		var that = this;
		
		xhr.upload.onprogress = function(e) {
			file.sentBytes = e.position || e.loaded;
			var fileDiv = file.getFileDiv();
			
			if ($('#' + $package.Utils.getFileContainerDivId(file.formName, file.fieldName)).size() == 0) {
				// field not exists
				that.abortAllUpload(file.formName, file.fieldName);
			} else {
				fileDiv.trigger($package.Utils.FILE_PROGRESS_CHANGED_EVENT_NAME, [file]);
			}
		};
		
		xhr.onreadystatechange = function(e) {
			if (this.readyState == xhr.DONE || this.readyState == xhr.UNSENT) {
				if ($('#' + $package.Utils.getFileContainerDivId(file.formName, file.fieldName)).size() == 0) {
					that.abortAllUpload(file.formName, file.fieldName);
					return;
				}
				
				if (xhr.status == 200) {
					var response = JSON.parse(xhr.responseText);
					
					var state = that.getState(file.formName, file.fieldName);
					
					if (response.timeMillis > state.lastModificationTimeMillis) {
						file.getFileDiv().attr('id', $package.Utils.getFileDivId(file.formName, file.fieldName, response.id));
						file.id = response.id;
						
						var fileData = that.getFileFromArrayById(
								response.existingFiles,
								file.id);
						file.isImage = fileData.isImage;
						file.isFlash = fileData.isFlash;
						file.width = fileData.width;
						file.height = fileData.height;
						file.changeStatus(file.SENT);
						
						that.getState(file.formName, file.fieldName).uploadedFiles.push(file);
					} else {
						that.removeFileFromMemory(file);
					}

					that.synchronizeFiles(file.formName, file.fieldName, response.existingFiles, response.timeMillis);
				} else {
					if (file.status == file.SENDING) {
						file.errorData = xhr;
						file.changeStatus(file.ERROR);
					}
				}
		
				that.startSending(file.formName, file.fieldName);
			}
		};
		
		xhr.open('post', "?action=forms.uploadFileToTemporaryStorageWithAjax", true);
		
		var fd = new FormData();
		fd.append(file.fieldName, file.originalFile);
		fd.append('form', file.formName);
		fd.append('field', file.fieldName);
		fd.append(oneweb.CsrfTokenManager.CSRF_TOKEN_NAME, oneweb.CsrfTokenManager.token);
		
		xhr.send(fd);
	};
	
	$package.FileSender = new $package.FileSenderConstructor();
})();