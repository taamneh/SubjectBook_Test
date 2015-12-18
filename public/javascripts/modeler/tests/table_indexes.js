


Tests.table_indexes = function() {


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

	module( "Table indexes" );



	test("Add table index", function() {

		$('#diagram').focus(); 

		var table = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(table);

		var before = table.get('indexes').size();

		$(".table-details-indexes .modeler_add_button").trigger("click");
		 
		equal(table.get('indexes').size(), before + 1, "New index");

		undo();

		equal(table.get('indexes').size(), before, "Undo");

	});


	test("Add table index and change it", function() {

		$('#diagram').focus(); 

		var table = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(table);

		$(".table-details-indexes .modeler_add_button").trigger("click");

		var indexes = table.get('indexes');

		var index = indexes.at(0);

		var oldName = index.get('name');

		var newName = rand('idx_');

		changeField('.table-details-indexes input[indexid=' + index.get('id') + ']', newName);

		equal(newName, index.get('name'), "New name");

		undo();

		equal(oldName, index.get('name'), "Undo");

	});


};


Tests.allTests.push(Tests.table_indexes);
Tests.addButton("Table indexes", Tests.table_indexes);

