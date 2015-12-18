//
// Testy funkcjonalne operacji wyrównywania obiektów na diagramie
// 

Tests.align = function() {

	var rand = function(prefix) {
		return prefix + "" + (new Date().getTime());
	};

	var click = function(selector) {
		$(selector).trigger('click'); 
	}

	var undo = function() {
		click('.toolbar-button-undo');
	}

	module("Align");

	test("Align horizontal left", function() {
		Commands.createTable(4800, 5000);
		Commands.createTable(5000, 5200);

		var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 2);
		var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
		var t1x = tableDisplay1.get("x");
		var t2x = tableDisplay2.get("x");
		
		Display.selection.selectElement(tableDisplay1);
		Display.selection.selectAndAttachElement(tableDisplay2);
		Commands._redraw();
		Commands._notifySelectionChanged();
		
		click('.toolbar-button-align');
		click('.toolbar-button-align-horizontal-left'); 

		equal(tableDisplay1.get("x"), tableDisplay2.get("x"), "Align left - lewe współrzędne obu tabel takie same");
		equal(tableDisplay1.get("x"), t1x, "Align left - tabelka zaznaczona jako pierwsza pozostaje na swoim miejscu");
		
		undo();
		
		equal(tableDisplay1.get("x"), t1x, "Undo align left t1");
		equal(tableDisplay2.get("x"), t2x, "Undo align left t2");
	});
	
	test("Align horizontal right", function() {
		Commands.createTable(4800, 5000);
		Commands.createTable(5000, 5200);

		var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 2);
		var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
		var t1x = tableDisplay1.get("x");
		var t2x = tableDisplay2.get("x");
		
		Display.selection.selectElement(tableDisplay1);
		Display.selection.selectAndAttachElement(tableDisplay2);
		Commands._redraw();
		Commands._notifySelectionChanged();
		
		click('.toolbar-button-align');
		click('.toolbar-button-align-horizontal-right'); 

		equal(tableDisplay1.get("x") + tableDisplay1.get("width"), tableDisplay2.get("x") + tableDisplay1.get("width"), "Align right - prawe krawędzie obu tabel takie same");
		equal(tableDisplay1.get("x"), t1x, "Align right - tabelka zaznaczona jako pierwsza pozostaje na swoim miejscu");
		
		undo();
		
		equal(tableDisplay1.get("x"), t1x, "Undo align right t1");
		equal(tableDisplay2.get("x"), t2x, "Undo align right t2");
	});
	
	test("Align horizontal center", function() {
		Commands.createTable(4800, 5000);
		Commands.createTable(5000, 5200);

		var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 2);
		var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
		var t1x = tableDisplay1.get("x");
		var t2x = tableDisplay2.get("x");
		
		// ustawiamy różne szerokości, żeby było widać, że operacja centrowania faktycznie działa
		tableDisplay1.set("width", 100);
		tableDisplay2.set("width", 150);
		
		Display.selection.selectElement(tableDisplay1);
		Display.selection.selectAndAttachElement(tableDisplay2);
		Commands._redraw();
		Commands._notifySelectionChanged();
		
		click('.toolbar-button-align');
		click('.toolbar-button-align-horizontal-center'); 

		var t1center = tableDisplay1.computeCenter();
		var t2center = tableDisplay2.computeCenter();
		
		equal(t1center.x, t2center.x, "Align center - środki tabel takie same");
		equal(tableDisplay1.get("x"), t1x, "Align center - tabelka zaznaczona jako pierwsza pozostaje na swoim miejscu");
		
		undo();
		
		equal(tableDisplay1.get("x"), t1x, "Undo align center t1");
		equal(tableDisplay2.get("x"), t2x, "Undo align center t2");
	});
	
	test("Align vertical top", function() {
		Commands.createTable(4800, 5000);
		Commands.createTable(5000, 5200);

		var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 2);
		var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
		var t1y = tableDisplay1.get("y");
		var t2y = tableDisplay2.get("y");
		
		Display.selection.selectElement(tableDisplay1);
		Display.selection.selectAndAttachElement(tableDisplay2);
		Commands._redraw();
		Commands._notifySelectionChanged();
		
		click('.toolbar-button-align');
		click('.toolbar-button-align-vertical-top'); 

		equal(tableDisplay1.get("y"), tableDisplay2.get("y"), "Align top - górne współrzędne obu tabel takie same");
		equal(tableDisplay1.get("y"), t1y, "Align top - tabelka zaznaczona jako pierwsza pozostaje na swoim miejscu");
		
		undo();
		
		equal(tableDisplay1.get("y"), t1y, "Undo align top t1");
		equal(tableDisplay2.get("y"), t2y, "Undo align top t2");
	});
	
	test("Align vertical bottom", function() {
		Commands.createTable(4800, 5000);
		Commands.createTable(5000, 5200);

		var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 2);
		var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
		var t1y = tableDisplay1.get("y");
		var t2y = tableDisplay2.get("y");
		
		Display.selection.selectElement(tableDisplay1);
		Display.selection.selectAndAttachElement(tableDisplay2);
		Commands._redraw();
		Commands._notifySelectionChanged();
		
		click('.toolbar-button-align');
		click('.toolbar-button-align-vertical-bottom'); 

		equal(tableDisplay1.get("y") + tableDisplay1.get("width"), tableDisplay2.get("y") + tableDisplay2.get("width"), "Align bottom - dolne krawędzie obu tabel takie same");
		equal(tableDisplay1.get("y"), t1y, "Align bottom - tabelka zaznaczona jako pierwsza pozostaje na swoim miejscu");
		
		undo();
		
		equal(tableDisplay1.get("y"), t1y, "Undo align bottom t1");
		equal(tableDisplay2.get("y"), t2y, "Undo align bottom t2");
	});
	
	test("Align vertical center", function() {
		Commands.createTable(4800, 5000);
		Commands.createTable(5000, 5200);

		var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 2);
		var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
		var t1y = tableDisplay1.get("y");
		var t2y = tableDisplay2.get("y");
		
		// ustawiamy różne wysokości, żeby było widać, że operacja centrowania faktycznie działa
		tableDisplay1.set("height", 100);
		tableDisplay2.set("height", 150);
		
		Display.selection.selectElement(tableDisplay1);
		Display.selection.selectAndAttachElement(tableDisplay2);
		Commands._redraw();
		Commands._notifySelectionChanged();
		
		click('.toolbar-button-align');
		click('.toolbar-button-align-vertical-center'); 

		var t1center = tableDisplay1.computeCenter();
		var t2center = tableDisplay2.computeCenter();
		
		equal(t1center.y, t2center.y, "Align center - środki obu tabel takie same");
		equal(tableDisplay1.get("y"), t1y, "Align center - tabelka zaznaczona jako pierwsza pozostaje na swoim miejscu");
		
		undo();
		
		equal(tableDisplay1.get("y"), t1y, "Undo align center t1");
		equal(tableDisplay2.get("y"), t2y, "Undo align center t2");
	});
	
	test("Align different objects", function() {
		Commands.createTable(4800, 5000);
		Commands.createView(4900, 5100);
		Commands.createNote(5000, 5200);
		
		var tableDisplay = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
		var viewDisplay = Model.viewDisplays.at(Model.viewDisplays.size() - 1);
		var note = Model.notes.at(Model.notes.size() - 1);
		
		var tableX = tableDisplay.get("x");
		var viewX = viewDisplay.get("x");
		var noteX = note.get("x");
		
		Display.selection.selectElement(tableDisplay);
		Display.selection.selectAndAttachElement(viewDisplay);
		Display.selection.selectAndAttachElement(note);
		
		Commands._redraw();
		Commands._notifySelectionChanged();
		
		click('.toolbar-button-align');
		click('.toolbar-button-align-horizontal-left'); 

		equal(tableDisplay.get("x"), viewDisplay.get("x"), "Align objects - lewe współrzędne obu tabelki i widoku takie same");
		equal(tableDisplay.get("x"), note.get("x"), "Align objects - lewe współrzędne obu tabelki i notatki takie same");
		equal(tableDisplay.get("x"), tableX, "Align objects - tabelka zaznaczona jako pierwsza pozostaje na swoim miejscu");
		
		undo();
		
		equal(tableDisplay.get("x"), tableX, "Undo align objects table");
		equal(viewDisplay.get("x"), viewX, "Undo align objects view");
		equal(note.get("x"), noteX, "Undo align objects note");
	});
};

Tests.allTests.push(Tests.align);
Tests.addButton("Align", Tests.align);
