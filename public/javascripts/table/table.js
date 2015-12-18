(function($) {
	 var $package = jsPackage("epoint.ow.table");

     var Table = {
         registerHandlers: function(table) {
             var ctx = null;
             if (typeof table === "string") {
                 ctx = $("#" + table);
             } else {
                 ctx = $(table);
             }
             if (ctx.hasClass("box_table")) {
                 $('a[href*="action=table."]', ctx)
                 	 .unbind('click')
                     .click(Table.stateChangeLinkHandler)
                     .each(function (idx, anchor) { $(this).attr("href", $(this).attr("href").replace("action=table.", "action=table_ajax.")); });
                 $('select[onchange*="action=table."]', ctx)
                     .removeAttr("onchange")
                     .change(Table.setTablePageSizeHandler);
             }
             return this;
         },
         stateChangeLinkHandler: function(evt) {
             evt.preventDefault();
             var anchor = $(evt.currentTarget);
             var tableId = $(anchor).closest("div.box_table").attr('id');
             epoint.ow.Utils.lockDiv(tableId);
             
             oneweb.CsrfTokenManager.postAjax(
            		 anchor.attr("href"),
            		 null,
            		 function (data) {Table.ajaxSuccessHandler(tableId, data);},
            		 "html");
             return this;
         },
         setTablePageSizeHandler: function(evt) {
             evt.preventDefault();
             var select = $(evt.currentTarget);
             var size = select.val();
             var tableId = $(select).closest("div.box_table").attr('id');
             epoint.ow.Utils.lockDiv(tableId);
             // drop "table_" prefix
             var owTableId = tableId.substring(6);
             oneweb.CsrfTokenManager.postAjax(
            		 '?action=table_ajax.setPageSize&table='+owTableId+'&size='+size,
            		 null,
            		 function (data) {Table.ajaxSuccessHandler(tableId, data);},
            		 "html");
             return this;
         },
         ajaxSuccessHandler: function(tableId, data, status, xhr) {
             Table.replaceTable(tableId, data);
             epoint.ow.Utils.unlockDiv(tableId);
             oneweb.DocumentReadyManager.runAllActions();
             Table.registerHandlers(tableId);
         },
         replaceTable: function(tableId, content) {
             $('#'+tableId).replaceWith(content);
         }
     };

     $package.Table = Table;

     var owtable_methods = {
         registerHandlers : function(table) {
        	 Table.registerHandlers(table);
         }
     };

     /*
      * jQuery plugin registration
      */
     $.fn.owtable = function(method) {
    	 var that = this;
    	 oneweb.DocumentReadyManager.addOnewebAction(function() {
	         if (method === undefined) {
	        	 Table.registerHandlers(that);
	        	 return that;
	         } else if (owtable_methods[method]) {
	             return owtable_methods[method].apply(that, Array.prototype.slice.call(arguments, 1));
	         } else if (typeof method === 'object' || ! method) {
	             return owtable_methods.init.apply(that, arguments);
	         } else {
	             $.error('Method ' +  method + ' does not exist on jQuery.owtable');
	         }
    	 });
         return that;
     };

 })(jQuery);
