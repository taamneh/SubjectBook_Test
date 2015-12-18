(function() {
	var $package = jsPackage("epoint.ow.htmleditor");

	var HtmlEditorManager = $package.HtmlEditorManager = {

			replaceWithCkEditor : function(/* object */ config) {
				if (config == null || config.fieldId == null) {
					throw "config fieldId can not be null";
				}

				var editor = CKEDITOR.instances[config.fieldId];
				if (editor) {
					try {
						editor.destroy(true);
						editor = null;
					} catch (err) {
						// do nothing
					}
				}
				
				if (editor) {
					delete CKEDITOR.instances[config.fieldId];
				}

				editor = CKEDITOR.replace(config.fieldId, config);

				// epoint special css field handling
				if (config.css != null) {
					editor.addCss(config.css);
				}

				editor.on('instanceReady', function(e) {
					$( editor.window.$.frameElement )
						.contents()
						.find("body")
						.blur(function() {
							editor.fire('blur');});
				});

				editor.on('blur', function(e) {
					editor.updateElement();
					$('#' + config.fieldId).change();
				}, null, null, 1);
			},

			removeCkEditor : function(/*String*/ fieldId) {
				for (var editorName in CKEDITOR.instances) {
					if (!CKEDITOR.instances.hasOwnProperty(editorName)) {
						continue;
					}
					var editor = CKEDITOR.instances[editorName];
					if (editor && editor.element.getId() == fieldId) {
						editor.updateElement();
						editor.destroy();
					}
				}
			},
			
			replaceWithCodeMirrorEditor : function(/*object*/ config) {
				if (config == null || config.fieldId == null) {
					throw "config fieldId can not be null";
				}
				
				var field = $('#' + config.fieldId).get(0);
				if (!field.editor) {
					if (config.onBlur == null) {
						config.onBlur = function() {
							editor.save();
							$(field).change();
						};
					}
					if (config.mode == null) {
						config.mode = "htmlmixed";
					}
					
					var editor = CodeMirror.fromTextArea(field, config);
					field.editor = editor;
				}
			},

			removeCodeMirrorEditor : function(/*String*/ fieldId) {
				var field = $('#' + fieldId).get(0);
				if (field.editor) {
					field.editor.toTextArea();
					field.editor = null;
				}
			},
			
			initEditorField : function(/*String*/ fieldId, /*String*/ radioFieldName, /*object*/ ckEditorConfig, /*object*/ codeMirrorConfig) {
				var $field = $('#' + fieldId);
				var form = $field.get(0).form;

				$(form).find("[name='" + radioFieldName + "']")
					.each(function() {
						$(this).click(function(e) {
							if (this.value == 'true') {
								HtmlEditorManager.removeCkEditor(fieldId);
								HtmlEditorManager.replaceWithCodeMirrorEditor(codeMirrorConfig);
							} else {
								HtmlEditorManager.removeCodeMirrorEditor(fieldId);
								HtmlEditorManager.replaceWithCkEditor(ckEditorConfig);
							}
						});
					});

				$(form)
					.find("[name='" + radioFieldName + "']")
					.filter(':checked')
					.click();

			}
	}
})();
