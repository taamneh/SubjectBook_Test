(function() {
	var $package = jsPackage("epoint.ow.popup");
	
	var PopupManager = $package.PopupManager = {
		openPopup : function(popupId, handlerParameters) {
			$popup = $('#' + popupId);
			if (handlerParameters == null) {
				handlerParameters = [];
			}
			
			var event = epoint.ow.Utils.createEvent("beforeOpenPopup");
			$popup.trigger(event, handlerParameters);
			if (event.isNextActionPrevented()) {
				return;
			}
			
			event = epoint.ow.Utils.createEvent("openPopup");
			$popup.trigger(event, handlerParameters);
			if (event.isNextActionPrevented()) {
				return;
			}
			
			event = epoint.ow.Utils.createEvent("afterOpenPopup");
			$popup.trigger(event, handlerParameters);
		},
		
		closePopup : function(popupId, handlerParameters) {
			$popup = $('#' + popupId);
			if (handlerParameters == null) {
				handlerParameters = [];
			}
			
			var event = epoint.ow.Utils.createEvent("beforeClosePopup");
			$popup.trigger(event, handlerParameters);
			if (event.isNextActionPrevented()) {
				return;
			}
			
			event = epoint.ow.Utils.createEvent("closePopup");
			$popup.trigger(event, handlerParameters);
			if (event.isNextActionPrevented()) {
				return;
			}
			
			event = epoint.ow.Utils.createEvent("afterClosePopup");
			$popup.trigger(event, handlerParameters);
		},
		
		openPopupCover : function(handlerParameters) {
			if (handlerParameters == null) {
				handlerParameters = [];
			}
			
			var event = epoint.ow.Utils.createEvent("beforeOpenPopupCover");
			$(document).trigger(event, handlerParameters);
			if (event.isNextActionPrevented()) {
				return;
			}
			
			event = epoint.ow.Utils.createEvent("openPopupCover");
			$(document).trigger(event, handlerParameters);
			if (event.isNextActionPrevented()) {
				return;
			}
			
			event = epoint.ow.Utils.createEvent("afterOpenPopupCover");
			$(document).trigger(event, handlerParameters);
		},
		
		closePopupCover : function(handlerParameters) {
			if (handlerParameters == null) {
				handlerParameters = [];
			}
			
			var event = epoint.ow.Utils.createEvent("beforeClosePopupCover");
			$(document).trigger(event, handlerParameters);
			if (event.isNextActionPrevented()) {
				return;
			}
			
			event = epoint.ow.Utils.createEvent("closePopupCover");
			$(document).trigger(event, handlerParameters);
			if (event.isNextActionPrevented()) {
				return;
			}
			
			event = epoint.ow.Utils.createEvent("afterClosePopupCover");
			$(document).trigger(event, handlerParameters);
		},
		
		openPopupClickHandler : function(e, popupId, handlerParameters) {
			PopupManager.openPopupCover(handlerParameters);
			PopupManager.openPopup(popupId, handlerParameters);
		},

		closePopupClickHandler : function(e, popupId, handlerParameters) {
			PopupManager.closePopup(popupId, handlerParameters);
			PopupManager.closePopupCover(handlerParameters);
		},
		
		openPopupAfterAjaxFlowReloadClickHandler : function(e, popupId, flowId, handlerParameters) {
				$('#' + flowId).one('afterAjaxFlowAction',
						function(e2){PopupManager.openPopup(popupId, handlerParameters);});

				PopupManager.openPopupCover(handlerParameters);
		}
	}
})();

oneweb.DocumentReadyManager.addApplicationAction(function() {
	var MODAL_POPUP_COVER_ID = "defaultModalPopupCover";
	
	$(document)
		.bind("openPopup", function(e) { $(e.target).show();})
		.bind("closePopup", function(e) { $(e.target).hide();})
		.bind("openPopupCover", function(e) { 
			var docB = document.body;
			var docE = document.documentElement;
			var maxDocHeight = Math.max(docB.scrollHeight, docE.scrollHeight, 
										docB.offsetHeight, docE.offsetHeight, 
										docB.clientHeight, docE.clientHeight);
			var dimensions = {
				width: $("html").width(),
				height: maxDocHeight };
			
			if($("#" + MODAL_POPUP_COVER_ID).size() == 0) {
				$("<div>")
					.attr("id", MODAL_POPUP_COVER_ID)
					.css({display: "none"})
					.addClass("popup_cover")
					.prependTo("body");
			}
			
			$("#" + MODAL_POPUP_COVER_ID)
				.css(dimensions)
				.show();
		})
		.bind("closePopupCover", function(e) { $("#" + MODAL_POPUP_COVER_ID).hide();})
		.bind('keydown', function(e) {
			if (e.which != 27) {
				return;
			}
			$('.popup').trigger('closePopup');
			$(document).trigger('closePopupCover');
		});
}, 1000, true);
