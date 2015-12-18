


Tests.table_alternate_keys = function() {


	var rand = function(prefix) {
		return prefix + "" + (new Date().getTime());
	};
 		

	var changeField = function(selector, value) {
		$(selector).focus().val(value).trigger("change");
	};


	var click = function(selector) {
		$(selector).trigger('click'); 
	}


	var undo = function() {
		click('.toolbar-button-undo');
	}

	module( "Table alternate keys" );



	test("Add table alternate key", function() {

		$('#diagram').focus(); 

		var lastTable = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(lastTable);

		var before = lastTable.get('alternateKeys').size();

		$(".table-details-alternate-keys .modeler_add_button").trigger("click");
		 
		equal(lastTable.get('alternateKeys').size(), before + 1, "New alternateKey.");

		undo();

		equal(lastTable.get('alternateKeys').size(), before, "Undo");

	});


	test("Add table alternate key and change it", function() {

		$('#diagram').focus(); 

		var table = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(table);

		$(".table-details-alternate-keys .modeler_add_button").trigger("click");

		var alternateKeys = table.get('alternateKeys');

		var alternateKey  = alternateKeys.at(0);

		var oldName = alternateKey.get('name');

		var newName = rand('ak_');
		changeField('.table-details-alternate-keys input[alternatekeyid=' + alternateKey.get('id') + ']', newName);

		equal(newName, alternateKey.get('name'), "New name");

		undo();

		equal(oldName, alternateKey.get('name'), "Undo");
		 


	});




};


Tests.allTests.push(Tests.table_alternate_keys);
Tests.addButton("Table alternate keys", Tests.table_alternate_keys);

