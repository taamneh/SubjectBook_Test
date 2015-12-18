

var OwOrder = {

		
		
    /**
     * Aktualizuje listę wybranych elementów.
     * 
     * @param id identyfikator kontrolki
     */
    updateSelectedList: function (id) {
		var selid = "order_" + id;
		var select = document.getElementById(selid);
		var listid = "order_selected_list_" + id;		
        var listElement = document.getElementById(listid);

		var list = new Array();
		for (var i = 0; i < select.options.length; i++) {
	    	list[i] = select.options[i].value;
        }
		listElement.value = list.join(',');
    },


		
    /**
     * Zmienia pozycję elementu 
     *
     * @param id identyfikator kontrolki
     * @param func funkcja <code>f(biezaca_pozycja, maksymalna_pozycja) -> nowa_pozycja</code> 
     */
    moveUpDown: function(id, func, isTopwards) {
		var selid = "order_" + id;
		var sel = document.getElementById(selid);
		
		if (isTopwards) {
			start = 0;
			finish = function (i) { return (i < sel.options.length); };
			delta = 1;
		} else {
			start = sel.options.length - 1;
			finish = function (i) { return (i >= 0); };
			delta = -1;
		}
		
		for (i = start; finish(i, sel); i += delta) {
			if (sel.options[i].selected) {
			    var newPos = func(i, sel.options);
			    if (newPos != null) {
		    		newPos = (newPos  + sel.options.length) % sel.options.length;
			    	var a = sel.options[i];
				    var b = sel.options[newPos];
				    // b jest zawsze nie zaznaczony, a jest zawsze zaznaczony
		    		sel.options[i] = new Option(b.text, b.value, false);
				    sel.options[newPos] = new Option(a.text, a.value, true);
				    sel.options[newPos].selected = true;
			    }
			}
		}
		
       	OwOrder.updateSelectedList(id);
    },	
    
    moveTopBottom: function(id, isTopwards) {
		var selid = "order_" + id;
		var sel = document.getElementById(selid);

		var selectedOptions = new Array();
		var j = 0;
		var unselectedOptions = new Array();
		var k = 0;
		for (var i = 0; i < sel.options.length; i ++) {
			if (sel.options[i].selected) {
				selectedOptions[j++] = sel.options[i];
			} else {
				unselectedOptions[k++] = sel.options[i];
			}
		}
		
		if (isTopwards) {
			for (i = 0; i < j; i++) {
				var selectedOption = selectedOptions[i];
				sel.options[i] = new Option(selectedOption.text, selectedOption.value, true);
				sel.options[i].selected = true;
			}
			for (i = 0; i < k; i++) {
				var unselectedOption = unselectedOptions[i];
				sel.options[i + j] = new Option(unselectedOption.text, unselectedOption.value, false);
			}
		} else {
			for (var i = 0; i < k; i++) {
				var unselectedOption = unselectedOptions[i];
				sel.options[i] = new Option(unselectedOption.text, unselectedOption.value, false);
			}
			for (var i = 0; i < j; i++) {
				var selectedOption = selectedOptions[i];
				sel.options[i + k] = new Option(selectedOption.text, selectedOption.value, true);
				sel.options[i + k].selected = true;
			}
			
			
		}
		
		OwOrder.updateSelectedList(id);
	},
    
    /**
     * Przesuwa element w górę.
     * 
     * @param id identyfikator kontrolki
     */
    moveUp: function (id) { 
		OwOrder.moveUpDown(id, OwOrder.findNotSelectedUp, true);
    },
    
    findNotSelectedUp: function (index, options) {
    	for (j = index - 1; j >= 0; j--) {
    		if (!options[j].selected) {
    			return j;
    		}
    	}
    	
    	return null;
    },

    /**
     * Przesuwa element w dół.
     * 
     * @param id identyfikator kontrolki
     */
    moveDown: function (id) { 
		OwOrder.moveUpDown(id, OwOrder.findNotSelectedDown, false);
    },

    findNotSelectedDown: function (index, options) {
    	for (j = index + 1; j < options.length; j++) {
    		if (!options[j].selected) {
    			return j;
    		}
    	}
    	
    	return null;
    },

    /**
     * Przesuwa element na szczyt.
     * 
     * @param id identyfikator kontrolki
     */
    moveTop:  function (id) {
		OwOrder.moveTopBottom(id, true);
    },

    /**
     * Przesuwa element na szczyt.
     * 
     * @param id identyfikator kontrolki
     */
    moveBottom: function (id) {
       	OwOrder.moveTopBottom(id, false);
    },
    
    /**
     * Sortuje alfabetycznie elementy rosnąco lub malejąco
     */
    sort: function(id, asc){
		var selid = "order_" + id; 
		var select = document.getElementById(selid); // select z listą opcji
		var listid = "order_selected_list_" + id; 	
        var listElement = document.getElementById(listid); // pole ukryte z listą identyfikatorów

        
        for (var j = select.options.length - 1; j>0; j--){
        	var maxIdx = j;
        	for(i = 0; i < j; i++){
        		if(asc){
	        		if(select.options[i].text > select.options[maxIdx].text){
	        			maxIdx = i;
	        		}
        		}else{
        			if(select.options[i].text < select.options[maxIdx].text){
	        			maxIdx = i;
	        		}
        		}
        	}
        	var optJ = select.options[j];
        	var optMax = select.options[maxIdx];
        	select.options[j] = new Option(optMax.text, optMax.value, false);
        	select.options[maxIdx] = new Option(optJ.text, optJ.value, false);
        }
        OwOrder.updateSelectedList(id);
    },
    
    sortAZ: function(id){
    	OwOrder.sort(id, true);
    },
    
    sortZA: function(id){
    	OwOrder.sort(id, false);
    }
    
};
