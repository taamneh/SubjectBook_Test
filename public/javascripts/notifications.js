//
// Skrypty JS modułu Notifications
//

(function() {
  var $package = jsPackage("pl.epoint.dbwm.war_user.notifications")

  var REFRESH_TIMEOUT_MILLIS = 10000;
  var refreshTimeoutHandler = null;
  
  $package.doRefreshNotificationsLayer = function(refreshUrl) {
      epoint.ow.flow.Utils.handleUrl(refreshUrl);
  }
  
  $package.startNotificationsLayerRefresh = function(refreshUrl) {
      $package.stopNotificationsLayerRefresh();
      refreshTimeoutHandler = setTimeout(function() {
          $package.doRefreshNotificationsLayer(refreshUrl);
      }, REFRESH_TIMEOUT_MILLIS);
  }

  $package.stopNotificationsLayerRefresh = function() {
      if(refreshTimeoutHandler != null) {
          clearTimeout(refreshTimeoutHandler);
          refreshTimeoutHandler = null;
      }
  }

  $package.onLayerShow = function() {
	  $package.stopNotificationsLayerRefresh();

	  var node = $(".notifications_layer");
	  var maxHeight = $(window).height() - node.offset().top -5;
	  if (node.outerHeight() > maxHeight) {
		node.outerHeight(maxHeight);
	  }
  }

  /*
   * Funkcje wspólne dla layera i listy powiadomień
   */
  $package.registerNotificationClickAction = function(elementId, actionUrl) {
      var element = $("#" + elementId);
      
      $(element).click(function(e) {
          e.stopPropagation();
          epoint.ow.flow.Utils.handleUrl(actionUrl);
      });
  }
  
})();
