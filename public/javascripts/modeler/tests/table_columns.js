


Tests.table_columns = function() {


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


	var redo = function() {
		click('.toolbar-button-redo');
	}


	module( "Tables Columns" );

	test("Add table column", function() {

		$('#diagram').focus(); 

		var lastTable = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(lastTable);

		var before = lastTable.get('columns').size();


		$(".table-details-columns .modeler_add_button").trigger("click");
		 
		equal(lastTable.get('columns').size(), before + 1, "New column is expected.");

		undo();

		equal(lastTable.get('columns').size(), before, "Undo");

		redo();

		equal(lastTable.get('columns').size(), before + 1, "Redo");

	});

	test("Delete table column", function() {

		$('#diagram').focus(); 

		var table = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(table);

		$(".table-details-columns .modeler_add_button").trigger("click");

		var column = table.get('columns').at(table.get('columns').size() - 1);

		var before = table.get('columns').size();

		$('#table-details-column-' +  column.get('id') + ' .setting_delete_button').click();


		equal(table.get('columns').size(), before - 1, "New column is deleted.");

		undo();
		equal(table.get('columns').size(), before, "Undo");

		redo();
		equal(table.get('columns').size(), before - 1, "Redo");

	});

	test("Add table column and rename it", function() {

		$('#diagram').focus(); 

		var table = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(table);

		var before = table.get('columns').size();

		click(".table-details-columns  .modeler_add_button");
		var after = table.get('columns').size();

		equal(after, before + 1, "New column is expected.");


		var column = table.get('columns').at(0);
		var oldValue = column.get('name');
		var newValue = rand("c_");

		changeField('#table-details-column-' + column.get('id') + " input[name=name]", newValue);

		var column = table.get('columns').at(0);

		equal(column.get('name'), newValue);

		undo();

		var column = table.get('columns').at(0);
		equal(column.get('name'), oldValue, "Undo");

		redo();

		var column = table.get('columns').at(0);
		equal(column.get('name'), newValue, "Redo");



	});

	test("Add column and change type", function() {

		$('#diagram').focus(); 

		var table = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(table);

		var before = table.get('columns').size();

		click(".modeler_add_button");
		var after = table.get('columns').size();

		equal(after, before + 1, "New column is expected.");

		var column = table.get('columns').at(0);
		var oldType = column.get('type');
		var newType = rand("t_");

		changeField('#table-details-column-' + column.get('id') + " input[name=type]", newType);

		var column = table.get('columns').at(0);

		equal(column.get('type'), newType,"New type");

		undo();

		equal(column.get('type'), oldType, "Undo");

		redo();

		equal(column.get('type'), newType, "Redo");


	});


	test("Toggle NULL", function() {

		$('#diagram').focus(); 

		var table = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(table);

		click(".modeler_add_button");


		var column = table.get('columns').at(table.get('columns').size() - 1);

		var before = column.get('nullable');
			
		// first toggle

		$('#table-details-column-' +  column.get('id') + ' input[name=nullable]').click();

		equal(column.get('nullable'), !before, "Toggled nullable");

		undo();
		equal(column.get('nullable'), before, "Undo");

		redo();
		equal(column.get('nullable'), !before, "Redo");


		// toggle again

		$('#table-details-column-' +  column.get('id') + ' input[name=nullable]').click();


		equal(column.get('nullable'), before, "Toggled nullable again");

		undo();
		equal(column.get('nullable'), !before, "Undo");

		redo();
		equal(column.get('nullable'), before, "Redo");


	});


	test("Toggle PK", function() {

		$('#diagram').focus(); 

		var table = Model.tables.at(Model.tables.size() - 1);

		Model.selection.selectElement(table);

		click(".modeler_add_button");


		var column = table.get('columns').at(table.get('columns').size() - 1);

		var before = column.get('pk');
			
		// first toggle

		$('#table-details-column-' +  column.get('id') + ' input[name=pk]').click();

		equal(column.get('pk'), !before, "Toggled pk");

		undo();
		equal(column.get('pk'), before, "Undo");

		redo();
		equal(column.get('pk'), !before, "Redo");


		// toggle again

		$('#table-details-column-' +  column.get('id') + ' input[name=pk]').click();


		equal(column.get('pk'), before, "Toggled pk again");

		undo();
		equal(column.get('pk'), !before, "Undo");

		redo();
		equal(column.get('pk'), before, "Redo");


	});


};


Tests.allTests.push(Tests.table_columns);
Tests.addButton("Table columns", Tests.table_columns);

