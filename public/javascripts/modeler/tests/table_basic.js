


Tests.table_basic = function() {


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

	module( "Table Basic" );




	test("Create table", function() {

		var tables_nr = Model.tables.size();

		$('#diagram').focus(); 

		$('.add_table_mode_button').trigger('click'); 

		app.getDiagram().getBackgroundLayer().fire('mousedown');

		equal(Model.tables.size(), tables_nr + 1, "Created new table");

		undo();
		equal(Model.tables.size(), tables_nr, "Undo");


		redo();
		equal(Model.tables.size(), tables_nr + 1, "Redo");
		
	});

	test("Change table name", function() {


		$('#diagram').focus(); 

		$('.add_table_mode_button').trigger('click'); 

		app.getDiagram().getBackgroundLayer().fire('mousedown');

		var lastTable = Model.tables.at(Model.tables.size() - 1);

		var oldName =  lastTable.get('name');

		var newName = rand("Table_");

		changeField('.table-details-basic-name',newName);

		equal(lastTable.get('name'), newName, "Changed name");

		undo();
		equal(lastTable.get('name'), oldName, "After undo");

		redo();
		equal(lastTable.get('name'), newName, "After redo");

	});

	test("Change table description", function() {


		$('#diagram').focus(); 

		$('.add_table_mode_button').trigger('click'); 

		app.getDiagram().getBackgroundLayer().fire('mousedown');

		var lastTable = Model.tables.at(Model.tables.size() - 1);

		var oldValue =  lastTable.get('description');

		var newValue = rand("DESCRIPTION_");

		changeField('.table-details-basic-description', newValue);

		equal(lastTable.get('description'), newValue, "Description change");

		undo();
		equal(lastTable.get('description'), oldValue, "Undo");

		redo();
		equal(lastTable.get('description'), newValue, "Redo");



	});

	test("Delete table", function() {
	

		var tables_nr = Model.tables.size();

		$('#diagram').focus(); 
		$('.add_table_mode_button').trigger('click'); 
		app.getDiagram().getBackgroundLayer().fire('mousedown');

		var lastTable = Model.tables.at(Model.tables.size() - 1);

		equal(Model.tables.size(), tables_nr + 1, "There should be new table.");

		click(".toolbar-button-delete");

		equal(Model.tables.size(), tables_nr, "After delete");

		undo();
		equal(Model.tables.size(), tables_nr + 1, "After undo");

		redo();
		equal(Model.tables.size(), tables_nr, "After redo");

	});
	

};


Tests.allTests.push(Tests.table_basic);
Tests.addButton("Table - Basic", Tests.table_basic);

