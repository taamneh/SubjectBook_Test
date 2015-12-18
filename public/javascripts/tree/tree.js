(function($) {
	 var $package = jsPackage("epoint.ow.tree");

     /****************************************************************************************************
      *
      * Standard (ul/li) tree
      *
      ****************************************************************************************************/

     var Tree = {
         registerHandlers: function(tree) {
             var ctx = null;
             if (typeof tree === "string") {
                 ctx = $("#" + tree);
             } else {
                 ctx = $(tree);
             }
             if (ctx.hasClass("tree")) {
                 $("span.control", ctx).closest("a")
                     .click(Tree.expandHandler)
                     .each(function (idx, anchor) { $(this).attr("href", $(this).attr("href").replace("action=tree.", "action=tree_ajax.")); });
                 $("span.label", ctx).closest("a")
                     .click(Tree.selectHandler)
                     .each(function (idx, anchor) { $(this).attr("href", $(this).attr("href").replace("action=tree.", "action=tree_ajax.")); });
             }
             return this;
         },
         getNodeId : function(anchor) {
             var nodeId = null;
             var match = /node=([0123456789]+)/.exec(anchor.attr("href"));
             if (match && match[1]) {
                 nodeId = parseInt(match[1], 10);
             }
             return nodeId;
         },
         expandHandler : function(evt) {
        	 evt.stopImmediatePropagation();
             evt.preventDefault();
             var anchor = $(evt.currentTarget);
             /* Trigger AJAX action */
             oneweb.CsrfTokenManager.postAjax(anchor.attr("href"));
             /* Change expanded/collapsed state */
             var itemDIV = anchor.closest("div.item");
             var childrenDIV = itemDIV.next("div.children");
             var childrenUL = $("> ul.children", childrenDIV);
             if (itemDIV.hasClass("item_expanded")) {
                 itemDIV.removeClass("item_expanded");
                 childrenDIV.removeClass("children_expanded");
                 childrenUL.removeClass("children_expanded");
                 anchor.attr("href", anchor.attr("href").replace("action=tree_ajax.collapse", "action=tree_ajax.expand"));
             } else {
                 itemDIV.addClass("item_expanded");
                 childrenDIV.addClass("children_expanded");
                 childrenUL.addClass("children_expanded");
                 anchor.attr("href", anchor.attr("href").replace("action=tree_ajax.expand", "action=tree_ajax.collapse"));
             }
             return this;
         },
         selectHandler : function(evt) {
        	 evt.stopImmediatePropagation();
             evt.preventDefault();
             var anchor = $(evt.currentTarget);
             var itemDIV = anchor.closest("div.item");
             /* Invoke only if current node is not selected */
             if (!itemDIV.hasClass("item_selected")) {
                 /* Trigger AJAX action */
            	 oneweb.CsrfTokenManager.postAjax(anchor.attr("href"));
                 /* Clear previous selection. */
                 var tree = anchor.closest("div.tree");
                 var childrenDIV = itemDIV.next("div.children");
                 var childrenUL = $("> ul.children", childrenDIV);
                 $("div.item_selected", tree).removeClass("item_selected");
                 $("div.children_selected", tree).removeClass("children_selected");
                 $("ul.children_selected", tree).removeClass("children_selected");
                 /* Select new node */
                 itemDIV.addClass("item_selected");
                 childrenDIV.addClass("children_selected");
                 childrenUL.addClass("children_selected");
                 /* Trigger component specific event */
                 var nodeId = Tree.getNodeId(anchor);
                 if (nodeId !== null) {
                     tree.trigger('owtree_selected', [nodeId, anchor]);
                 }
             }
             return this;
         }
     };

     $package.Tree = Tree;

     var owtree_methods = {
         registerHandlers : function(tree) {
             Tree.registerHandlers(tree);
         }
     };

     /*
      * jQuery plugin registration
      */
     $.fn.owtree = function(method) {
         if (method === undefined) {
             return Tree.registerHandlers(this);
         } else if (owtree_methods[method]) {
             return owtree_methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
         } else if (typeof method === 'object' || ! method) {
             return owtree_methods.init.apply(this, arguments);
         } else {
             $.error('Method ' +  method + ' does not exist on jQuery.owtree');
         }
         return this;
     };

     /****************************************************************************************************
      *
      * Table tree
      *
      ****************************************************************************************************/

     var TabTree = {
         registerHandlers: function(tree) {
             var ctx = null;
             if (typeof tree === "string") {
                 ctx = $("#" + tree);
             } else {
                 ctx = $(tree);
             }
             if (ctx.hasClass("tab_tree")) {
                 ctx.data("expanded", TabTree.collectExpandedIds(ctx));
                 $("span.control", ctx).closest("a")
                     .click(TabTree.expandHandler)
                     .each(function (idx, anchor) { $(this).attr("href", $(this).attr("href").replace("action=tree.", "action=tree_ajax.")); });
                 $("span.label", ctx).closest("a")
                     .click(TabTree.selectHandler)
                     .each(function (idx, anchor) { $(this).attr("href", $(this).attr("href").replace("action=tree.", "action=tree_ajax.")); });
             }
             return this;
         },
         getNodeId : Tree.getNodeId,
         getLevel : function (div) {
             var lvl = null;
             var match = /item_lvl_([0123456789]+)/.exec(div.attr("class"));
             if (match && match[1]) {
                 lvl = parseInt(match[1], 10);
             }
             return lvl;
         },
         collectExpandedIds : function(tree) {
             var expanded = [];
             $("div.item_expanded a[href*='setSelected']", tree)
                 .each(function(idx, anchor) { expanded.push(TabTree.getNodeId($(anchor))); });
             expanded.sort();
             return expanded;
         },
         isExpanded : function(tree, nodeId) {
             return $.inArray(nodeId, tree.data("expanded")) !== -1;
         },
         expandNode : function(tree, nodeId) {
             var expanded = tree.data("expanded");
             var idx = $.inArray(nodeId, expanded);
             if (idx == -1) {
                 expanded.push(nodeId);
             }
             tree.data("expanded", expanded);
             return this;
         },
         collapseNode : function(tree, nodeId) {
             var expanded = tree.data("expanded");
             var idx = $.inArray(nodeId, expanded);
             if (idx != -1) {
                 Array.prototype.splice.call(expanded, idx, 1);
             }
             tree.data("expanded", expanded);
             return this;
         },
         expandHandler : function(evt) {
        	 evt.stopImmediatePropagation();
             evt.preventDefault();
             var anchor = $(evt.currentTarget);
             /* Trigger AJAX action */
             oneweb.CsrfTokenManager.postAjax(anchor.attr("href"));
             /* Change expanded/collapsed state */
             var tree = anchor.closest("table.tab_tree");
             var nodeId = TabTree.getNodeId(anchor);
             var itemTR = anchor.closest("tr");
             var itemDIV = $("div.item", itemTR);
             var lvl = TabTree.getLevel(itemDIV);
             var nextSiblings = itemTR.nextAll();
             if (itemDIV.hasClass("item_expanded")) {
                 itemDIV.removeClass("item_expanded");
                 /*
                  * WARNING: Complicated code.
                  *
                  * This is recursive algorithm unrolled into loop
                  * with manual stack management.
                  *
                  * Unrolled loop iterates over and hides all nodes
                  * (TR elements) in DFS order until next node (TR)
                  * is found with the same or lower level than
                  * starting node.
                  *
                  * DFS over all elements with higher level than
                  * starting node defines subtree rooted at starting
                  * node - above procedure hides all TRs that belong
                  * to collapsed subtree.
                  *
                  * Stack information:
                  * - lvl - starting (collapsed) node level
                  */
                 nextSiblings.each(function (idx, tr) {
                                       var siblingItemDIV = $("div.item", this);
                                       var siblingLvl = TabTree.getLevel(siblingItemDIV);
                                       if (siblingLvl <= lvl) {
                                           // stop iteration:
                                           // - sibling has same level means we found TR for tree node SIBLING
                                           // - sibling has lower level means we found TR for tree node ANCESTOR
                                           // using 'return false' simulates 'break' for .each() loop
                                           return false;
                                       }
                                       // all other nodes (with lvl > siblingLvl) represent
                                       // descendants of node, so they should be hidden
                                       $(tr).addClass("item_hidden");
                                       return this;
                                   });
                 // update expanded nodes information
                 TabTree.collapseNode(tree, nodeId);
                 // change AJAX action for node
                 anchor.attr("href", anchor.attr("href").replace("action=tree_ajax.collapse", "action=tree_ajax.expand"));
             } else {
                 itemDIV.addClass("item_expanded");
                 /*
                  * WARNING: Complicated code.
                  *
                  * This is recursive algorithm unrolled into loop
                  * with manual stack management.
                  *
                  * Unrolled loop iterates over all nodes (TR
                  * elements) in DFS order. Iteration stops on first
                  * node (TR) with the same or lower level than
                  * starting node.
                  *
                  * DFS over all elements with higher level than
                  * starting node defines subtree rooted at starting
                  * node.
                  *
                  * Each iteration explicitly manages information
                  * whether for current node (TR element) its' all
                  * ancestors are expanded. If all ancestors of a node
                  * are expanded than node is visible.
                  *
                  * Stack information:
                  * - allAncestorsExpanded - stack of booleans. Each
                  *   index holds information whether all ancestors
                  *   of currently processed node were expanded.
                  */
                 var allAncestorsExpanded = [true];
                 nextSiblings.each(function (idx, tr) {
                                       var siblingItemDIV = $("div.item", this);
                                       var siblingLvl = TabTree.getLevel(siblingItemDIV);

                                       if (siblingLvl <= lvl) {
                                           // stop iteration:
                                           // - sibling has same level means we found TR for tree node SIBLING
                                           // - sibling has lower level means we found TR for tree node ANCESTOR
                                           // using 'return false' simulates 'break' for .each() loop
                                           return false;
                                       }

                                       var siblingId = TabTree.getNodeId($("a", siblingItemDIV));
                                       var isSiblingExpanded = TabTree.isExpanded(tree, siblingId);

                                       /*
                                        * Update all ancestors expanded information. DFS order guarantees that
                                        * all elements in allAncestorsExpanded are defined.
                                        */
                                       var siblingIdx = siblingLvl - lvl;
                                       var parentIdx  = siblingIdx - 1;
                                       allAncestorsExpanded[siblingIdx] = isSiblingExpanded && allAncestorsExpanded[parentIdx];

                                       /*
                                        * Update node visibility accordingly
                                        */
                                       if (allAncestorsExpanded[parentIdx]) {
                                           $(tr).removeClass("item_hidden");
                                       } else {
                                           $(tr).addClass("item_hidden");
                                       }

                                       return this;
                                   });
                 // update expanded nodes information
                 TabTree.expandNode(tree, nodeId);
                 // change AJAX action for node
                 anchor.attr("href", anchor.attr("href").replace("action=tree_ajax.expand", "action=tree_ajax.collapse"));
             }
             return this;
         },
         selectHandler : function(evt) {
        	 evt.stopImmediatePropagation();
             evt.preventDefault();
             // alert("select: " + evt.currentTarget);
             var anchor = $(evt.currentTarget);
             var itemDIV = anchor.closest("div.item");
             /* Invoke only if current node is not selected */
             if (!itemDIV.hasClass("item_selected")) {
                 /* Trigger AJAX action */
            	 oneweb.CsrfTokenManager.postAjax(anchor.attr("href"));
                 var tree = anchor.closest("table.tab_tree");
                 /* Clear previous selection. */
                 $("div.item_selected", tree).removeClass("item_selected");
                 /* Select new node */
                 itemDIV.addClass("item_selected");
                 /* Trigger component specific event */
                 var nodeId = TabTree.getNodeId(anchor);
                 if (nodeId !== null) {
                     tree.trigger('owtree_selected', [nodeId, anchor]);
                 }
             }
             return this;
         }
     };

     $package.TabTree = TabTree;

     var owtabtree_methods = {
         registerHandlers : function(tree) {
             TabTree.registerHandlers(tree);
         }
     };

     /*
      * jQuery plugin registration
      */
     $.fn.owtabtree = function(method) {
         if (method === undefined) {
             return TabTree.registerHandlers(this);
         } else if (owtabtree_methods[method]) {
             return owtabtree_methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
         } else if (typeof method === 'object' || ! method) {
             return owtabtree_methods.init.apply(this, arguments);
         } else {
             $.error('Method ' +  method + ' does not exist on jQuery.owtabtree');
         }
         return this;
     };

 })(jQuery);
