
Tests.references = function() {
    module("Reference");

    var click = function(selector) {
        $(selector).trigger('click'); 
    }
    
    var undo = function() {
        click('.toolbar-button-undo');
    }
    
	var redo = function() {
		click('.toolbar-button-redo');
	}
	
	var changeField = function(selector, value) {
		$(selector).focus().val(value).trigger("change");
	}
	
	var rand = function(prefix) {
		return prefix + "" + (new Date().getTime());
	};
	
	var uncheckField = function(selector, value) {
		$(selector).focus().prop('checked', false).trigger("change");
	};
	
	var checkField = function(selector, value) {
		$(selector).focus().prop('checked', true).trigger("change");
	};
    
    
    test("Add reference", function() {
        var referencesNumberBefore = Model.references.size();
        var referenceDisplaysNumberBefore = Model.referenceDisplays.size();
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        // weryfikacja - model
        var referencesNumberAfter = Model.references.size();
        var reference = Model.references.at(Model.references.size() - 1);
        equal((referencesNumberAfter - referencesNumberBefore), 1, "Referencja utworzona (model)");
        equal(reference.get('pkTableId'), table1.get('id'), "Tabela PK poprawna (model)")
        equal(reference.get('fkTableId'), table2.get('id'), "Tabela FK poprawna (model)")
        
        // weryfikacja - display
        var referenceDisplaysNumberAfter = Model.referenceDisplays.size();
        var referenceDisplay = Model.referenceDisplays.at(Model.referenceDisplays.size() - 1);
        equal((referenceDisplaysNumberAfter - referenceDisplaysNumberBefore), 1, "Referencja utworzona (display)");
        equal(referenceDisplay.get('modelId'), reference.get('id'), "Poprawny model wizualizacji referencji");
        equal(referenceDisplay.get('pkTableDisplayId'), tableDisplay1.get('id'), "Tabela PK poprawna (display)")
        equal(referenceDisplay.get('fkTableDisplayId'), tableDisplay2.get('id'), "Tabela FK poprawna (display)")
        
        // undo
        undo();
        
        // weryfikacja - undo
        var referencesNumberAfterUndo = Model.references.size();
        var referenceDisplaysNumberAfterUndo = Model.referenceDisplays.size();
        equal(referencesNumberAfterUndo, referencesNumberBefore, "Undo: Referencja usunięta (model)");
        equal(referenceDisplaysNumberAfterUndo, referenceDisplaysNumberBefore, "Undo: Referencja usunięta (display)");
    });
    
    
    test("Add FK table column", function() {
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        var table1Column = Commands.createTableColumn(table1.get('id'));
        Commands.changeTableColumn(table1.get('id'), table1Column.get('id'), 'pk', true);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        // weryfikacja
        equal(table2.get('columns').size(), 1, "Kolumna w tabeli FK została utworzona");

        // undo
        undo();
        
        // weryfikacja - undo
        equal(table2.get('columns').size(), 0, "Undo: Kolumna w tabeli FK została usunięta");
        
    });
    
    test("Add FK table double column", function() {
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        var table1Column = Commands.createTableColumn(table1.get('id'));
        var table1Column2 = Commands.createTableColumn(table1.get('id'));
        Commands.changeTableColumn(table1.get('id'), table1Column.get('id'), 'pk', true);
        Commands.changeTableColumn(table1.get('id'), table1Column2.get('id'), 'pk', true);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        // weryfikacja
        equal(table2.get('columns').size(), 2, "Kolumny w tabeli FK zostały utworzone");

        // undo
        undo();
        
        // weryfikacja - undo
        equal(table2.get('columns').size(), 0, "Undo: Kolumny w tabeli FK zostały usunięte");
        
        redo();
        
        // weryfikacja
        equal(table2.get('columns').size(), 2, "Kolumny w tabeli FK zostały utworzone");
    });
    
    
    test("Change reference name", function() {
        var referencesNumberBefore = Model.references.size();
        var referenceDisplaysNumberBefore = Model.referenceDisplays.size();
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        var reference = Model.references.at(Model.references.size() - 1);
        var oldReferenceName = reference.get('name');
        Display.selection.selectElement(reference);
        
        var newReferenceName = rand("Reference_");
        changeField('#reference-details-name', newReferenceName);
        
        equal(reference.get('name'), newReferenceName, "Reference name was changed");
        
        undo();
        
        equal(reference.get('name'), oldReferenceName, "Undo reverted old reference name");
        
        redo();
        
        equal(reference.get('name'), newReferenceName, "Redo reverted new reference name");
    });
    
    test("Change reference cardinality", function() {
        var referencesNumberBefore = Model.references.size();
        var referenceDisplaysNumberBefore = Model.referenceDisplays.size();
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        var reference = Model.references.at(Model.references.size() - 1);
        var oldCardinality = reference.get('cardinality');
        Display.selection.selectElement(reference);
        
        var newCardinality = "1..1";
        changeField('#reference-details-cardinality-selector', newCardinality);
        
        equal(reference.get('cardinality'), newCardinality, "Reference name was changed");
        
        undo();
        
        equal(reference.get('cardinality'), oldCardinality, "Undo set cardinality");
        
        redo();
        
        equal(reference.get('cardinality'), newCardinality, "Redo set cardinality");
    });
    
    test("Change update constraint action", function() {
    	var referencesNumberBefore = Model.references.size();
        var referenceDisplaysNumberBefore = Model.referenceDisplays.size();
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        var reference = Model.references.at(Model.references.size() - 1);
        var oldAction = reference.get('onUpdateAction');
        Display.selection.selectElement(reference);
        
        var newAction = "Cascade";
        changeField('#reference-details-update-constraint-action', newAction);
        
        equal(reference.get('onUpdateAction'), newAction, "Reference on update was changed");
        
        undo();
        
        equal(reference.get('onUpdateAction'), oldAction, "Undo on update action");
        
        redo();
        
        equal(reference.get('onUpdateAction'), newAction, "Redo on update action");
        
    });
    
    test("Change delete constraint action", function() {
    	var referencesNumberBefore = Model.references.size();
        var referenceDisplaysNumberBefore = Model.referenceDisplays.size();
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        var reference = Model.references.at(Model.references.size() - 1);
        var oldAction = reference.get('onDeleteAction');
        Display.selection.selectElement(reference);
        
        var newAction = "Cascade";
        changeField('#reference-details-delete-constraint-action', newAction);
        
        equal(reference.get('onDeleteAction'), newAction, "Reference on delete was changed");
        
        undo();
        
        equal(reference.get('onDeleteAction'), oldAction, "Undo on delete action");
        
        redo();
        
        equal(reference.get('onDeleteAction'), newAction, "Redo on delete action");
    });
    
    test("Swap reference", function() {
    	var referencesNumberBefore = Model.references.size();
        var referenceDisplaysNumberBefore = Model.referenceDisplays.size();
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        var table1Column = Commands.createTableColumn(table1.get('id'));
        Commands.changeTableColumn(table1.get('id'), table1Column.get('id'), 'pk', true);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        var table2Column = Commands.createTableColumn(table2.get('id'));
        Commands.changeTableColumn(table2.get('id'), table2Column.get('id'), 'pk', true);
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        var reference = Model.references.at(Model.references.size() - 1);
        var referenceDisplay = Model.referenceDisplays.at(Model.referenceDisplays.size() - 1);
        var oldPkTableId = reference.get('pkTableId');
        var oldFkTableId = reference.get('fkTableId');
        var oldPkTableDisplayId = referenceDisplay.get('pkTableDisplayId');
        var oldFkTableDisplayId = referenceDisplay.get('fkTableDisplayId');
        Display.selection.selectElement(reference);
        
        $("#reference-details-swap-button").click();
        
        equal(reference.get('pkTableId'), table2.get('id'), "Reference pk table changed");
        equal(reference.get('fkTableId'), table1.get('id'), "Reference fk table changed");
        equal(referenceDisplay.get('pkTableDisplayId'), tableDisplay2.get('id'), "Reference pk table display changed");
        equal(referenceDisplay.get('fkTableDisplayId'), tableDisplay1.get('id'), "Reference fk table display changed");
        
        undo();
        
        equal(reference.get('pkTableId'), oldPkTableId, "Reference pk table reverted after undo");
        equal(reference.get('fkTableId'), oldFkTableId, "Reference fk table reverted after undo");
        equal(referenceDisplay.get('pkTableDisplayId'), oldPkTableDisplayId, "Reference pk table display reverted after undo");
        equal(referenceDisplay.get('fkTableDisplayId'), oldFkTableDisplayId, "Reference fk table display reverted after undo");
        
        redo();
        
        equal(reference.get('pkTableId'), table2.get('id'), "Reference pk table restored after redo");
        equal(reference.get('fkTableId'), table1.get('id'), "Reference fk table restored after redo");
        equal(referenceDisplay.get('pkTableDisplayId'), tableDisplay2.get('id'), "Reference pk table display restored after redo");
        equal(referenceDisplay.get('fkTableDisplayId'), tableDisplay1.get('id'), "Reference fk table display restored after redo");
    });
    
    test("Uncheck mandatory", function() {
    	var referencesNumberBefore = Model.references.size();
        var referenceDisplaysNumberBefore = Model.referenceDisplays.size();
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        var reference = Model.references.at(Model.references.size() - 1);
        var oldMandatory = reference.get('mandatory');
        Display.selection.selectElement(reference);
        
        uncheckField('#reference-details-mandatory');
        
        equal(reference.get('mandatory'), false, "Reference is not mandatory");
        
        undo();
        
        equal(reference.get('mandatory'), oldMandatory, "Undo restores old mandatory value");
        
        redo();
        
        equal(reference.get('mandatory'), false, "Redo restores new mandatory value");
    });
    
    test("Check mandatory", function() {
    	var referencesNumberBefore = Model.references.size();
        var referenceDisplaysNumberBefore = Model.referenceDisplays.size();
        
        var p1 = {x: 4800, y: 4800};
        var p2 = {x: 5000, y: 5200};
        
        Commands.createTable(p1.x, p1.y);
        var table1 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay1 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createTable(p2.x, p2.y);
        var table2 = Model.tables.at(Model.tables.size() - 1);
        var tableDisplay2 = Model.tableDisplays.at(Model.tableDisplays.size() - 1);
        
        Commands.createReference(tableDisplay1, tableDisplay2, p1, p2);
        
        var reference = Model.references.at(Model.references.size() - 1);
        var oldMandatory = reference.get('mandatory');
        Display.selection.selectElement(reference);
        
        checkField('#reference-details-mandatory');
        
        equal(reference.get('mandatory'), true, "Reference is not mandatory");
        
        undo();
        
        equal(reference.get('mandatory'), oldMandatory, "Undo restores old mandatory value");
        
        redo();
        
        equal(reference.get('mandatory'), true, "Redo restores new mandatory value");
    });
};

Tests.allTests.push(Tests.references);
Tests.addButton("Reference", Tests.references);
