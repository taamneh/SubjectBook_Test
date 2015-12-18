


Tests.notes = function() {
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
	
	module("Notes");

	test("Create note", function() {
		var notes_nr = Model.notes.size();

		$('#diagram').focus(); 

		$('.add_note_button').trigger('click'); 

		app.getDiagram().getBackgroundLayer().fire('mousedown');

		equal(Model.notes.size(), notes_nr + 1, "There should be new note.");

		undo();

		equal(Model.notes.size(), notes_nr, "Undo");
	});
	
	test("Change note text", function() {
		$('#diagram').focus(); 

		$('.add_note_button').trigger('click'); 

		app.getDiagram().getBackgroundLayer().fire('mousedown');

		var lastNote = Model.notes.at(Model.notes.size() - 1);

		var oldText = lastNote.get('content');
		var newText = rand("Text");

		changeField('.details [name=content]', newText);

		equal(lastNote.get('content'), newText, "Note should have content: " + newText);
		
		undo();
		
		equal(lastNote.get('content'), oldText, "Note should have old content: " + oldText);
	});
	
	test("Change note name", function() {
		$('#diagram').focus(); 

		$('.add_note_button').trigger('click'); 

		app.getDiagram().getBackgroundLayer().fire('mousedown');

		var lastNote = Model.notes.at(Model.notes.size() - 1);

		var oldName =  lastNote.get('name');

		var newName = rand("Note_");

		changeField('#note-details-common .details [name=name]', newName);

		equal(lastNote.get('name'), newName, "Note should have name: " + newName);
		
		undo();
		
		equal(lastNote.get('name'), oldName, "Note should have old name: " + oldName);
	});
	
	test("Delete note", function() {
		var notes_nr = Model.notes.size();
		
		$('#diagram').focus(); 

		$('.add_note_button').trigger('click'); 
		
		app.getDiagram().getBackgroundLayer().fire('mousedown');
		
		var lastNote = Model.notes.at(Model.notes.size() - 1);
		
		Display.selection.selectElement(lastNote);
		
		$('.toolbar-button-delete').click();
		
		equal(Model.notes.size(), notes_nr, "Delete");
		
		undo();
		
		equal(Model.notes.size(), notes_nr + 1, "Undo delete");
	});
	
};

Tests.allTests.push(Tests.notes);
Tests.addButton("Notes", Tests.notes);




