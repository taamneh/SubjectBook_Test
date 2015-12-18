(function() {
	var $package = oneweb.owjsPackage('fileupload');

	$package.Utils = {
			INIT_ADVANCED_FILE_HANDLERS_EVENT_NAME : "INIT_ADVANCED_FILE_HANDLERS",
			FILE_STATUS_CHANGED_EVENT_NAME : "fileStatusChanged",
			FILE_PROGRESS_CHANGED_EVENT_NAME : "fileProgressChanged",
			MULTI_UPLOAD_IN_PROGRESS_EVENT_NAME : "multiUploadInProgress",
			END_SINGLE_UPLOAD_EVENT_NAME : "endSingleUpload",
			END_MULTI_UPLOAD_EVENT_NAME : "endMultiUpload",
			SINGLE_UPLOAD_IN_PROGRESS_EVENT_NAME : "singleUploadInProgress",
			SHOW_GLOBAL_ERROR_EVENT_NAME : "showGlobalError",
			ABORT_ALL_UPLOAD_EVENT_NAME : "abortAllUpload",
				
			getFileContainerDivId : function(formName, fieldName) {
				return epoint.ow.forms.Utils.getFieldId(formName, fieldName) + "_cntDiv";
			},

			getFileDivId : function(formName, fieldName, fileId) {
				return epoint.ow.forms.Utils.getFieldId(formName, fieldName) + "_fileDiv_" + fileId;
			},

			getTemplateDiv : function(formName, fieldName) {
				var templateDiv = $('#' + this.getFileDivId(formName, fieldName, "tmp"));
				if (templateDiv.size() == 0) {
					throw "template DIV not found in form:" + formName + ", field:" + fieldName;
				}
				return templateDiv;
			},

			getMultiUploadDivId : function(formName, fieldName) {
				return epoint.ow.forms.Utils.getFieldId(formName, fieldName) + "_multiUpload";
			},
			
			getMultiUploadInProgressDivId : function(formName, fieldName) {
				return epoint.ow.forms.Utils.getFieldId(formName, fieldName) + "_multiUploadInProgress";
			},
			
			getTotalProgressDivId : function(formName, fieldName) {
				return epoint.ow.forms.Utils.getFieldId(formName, fieldName) + "_totalProgress";
			},
			
			getFilesSentInfoDivId : function(formName, fieldName) {
				return epoint.ow.forms.Utils.getFieldId(formName, fieldName) + "_filesSentInfo";
			},
			
			getTotalSizeDivId : function(formName, fieldName) {
				return epoint.ow.forms.Utils.getFieldId(formName, fieldName) + "_totalSize";
			},

			getGlobalErrorDivId : function(formName, fieldName) {
				return epoint.ow.forms.Utils.getFieldId(formName, fieldName) + "_global_error";
			},

			getAbortAllButtonId : function(formName, fieldName) {
				return epoint.ow.forms.Utils.getFieldId(formName, fieldName) + "_abortAll";
			}
	};
})();