


Tests.sequences = function() {
	var rand = function(prefix) {
		return prefix + "" + (new Date().getTime());
	};
 		

	var changeField = function(selector, value) {
		$(selector).focus().val(value).trigger("change");
	};
	
	var uncheckField = function(selector, value) {
		$(selector).focus().prop('checked', false).trigger("change");
	};
	
	var checkField = function(selector, value) {
		$(selector).focus().prop('checked', true).trigger("change");
	};


	var click = function(selector) {
		$(selector).trigger('click'); 
	};


	var undo = function() {
		click('.toolbar-button-undo');
	};
	
	var redo = function() {
		click('.toolbar-button-redo');
	};
	
	var createSequence = function() {
		$('#sequence-list-root-node').click();
		$('#sequence-list-root-node .context-menu-trigger').click();
		$('.handy-menu-create-button').click();
	};
	
	module("Sequences");

	test("Create sequence", function() {
		var sequence_num = Model.sequences.size();

		createSequence();
	
		equal(Model.sequences.size(), sequence_num + 1, "There should be new sequence");
		
		undo();
		
		equal(Model.sequences.size(), sequence_num, "New sequence should be removed on undo");
		
		redo();
		
		equal(Model.sequences.size(), sequence_num + 1, "New sequence should be recreated on redo");
	});
	
	test("Change sequence name", function() {
		var sequence_num = Model.sequences.size();
		
		createSequence();
		
		var lastSequence = Model.sequences.at(Model.sequences.size() - 1);
		var oldSequenceName = lastSequence.get('name');
		
		var newSequenceName = rand("Sequence_");
		
		$('#sequence-list [title=' + oldSequenceName + ']').click();
		
		changeField('#sequence-details-common .details [name=name]', newSequenceName);
		equal(lastSequence.get('name'), newSequenceName, "New name");
		
		undo();
		
		equal(lastSequence.get('name'), oldSequenceName, "Undo should revert old sequence name");
		
		redo();	
		
		equal(lastSequence.get('name'), newSequenceName, "Redo should revert new sequence name");
	});
	
	test("Change start with", function() {
		var sequence_num = Model.sequences.size();
		
		createSequence();
		
		var lastSequence = Model.sequences.at(Model.sequences.size() - 1);
		var sequenceName = lastSequence.get('name');
		
		$('#sequence-list [title=' + sequenceName + ']').click();
		
		var oldStartWith = lastSequence.get('startWith');
		
		var newStartWith = 100;
		changeField('#sequence-details-common .details [name=startWith]', newStartWith);
		equal(lastSequence.get('startWith'), newStartWith, "New start with");
		
		undo();
		
		equal(lastSequence.get('startWith'), oldStartWith, "Undo should revert old startWith value");
		
		redo();
		
		equal(lastSequence.get('startWith'), newStartWith, "Redo should revert new startWith value");
	});
	
	test("Change increment by", function() {
		var sequence_num = Model.sequences.size();
		
		createSequence();
		
		var lastSequence = Model.sequences.at(Model.sequences.size() - 1);
		var sequenceName = lastSequence.get('name');
		
		$('#sequence-list [title=' + sequenceName + ']').click();
		
		var oldIncrementBy = lastSequence.get('incrementBy');
		
		var newIncrementBy = 1;
		changeField('#sequence-details-common .details [name=incrementBy]', newIncrementBy);
		equal(lastSequence.get('incrementBy'), newIncrementBy, "New increment by");
		
		undo();
		
		equal(lastSequence.get('incrementBy'), oldIncrementBy, "Undo should revert old incrementBy value");
		
		redo();
		
		equal(lastSequence.get('incrementBy'), newIncrementBy, "Redo should revert new incrementBy value");
	});
	
	test("Change min value", function() {
		var sequence_num = Model.sequences.size();
		
		createSequence();
		
		var lastSequence = Model.sequences.at(Model.sequences.size() - 1);
		var sequenceName = lastSequence.get('name');
		
		$('#sequence-list [title=' + sequenceName + ']').click();
		
		var oldMinValue = lastSequence.get('minValue');
		
		var newMinValue = 1;
		uncheckField('#sequence-details-common .details [name=hasMinValue]');
		changeField('#sequence-details-common .details [name=minValue]', newMinValue);
		equal(lastSequence.get('minValue'), newMinValue, "New min value");
		equal(lastSequence.get('hasMinValue'), true, "New has min value");
		
		undo(); // change min value
		undo(); // change has min value
		
		equal(lastSequence.get('minValue'), oldMinValue, "Undo should revert old min value");
		
		redo(); // change has min value
		redo(); // change min value
		
		equal(lastSequence.get('minValue'), newMinValue, "Redo should revert new min value");
	});
	
	test("Change max value", function() {
		var sequence_num = Model.sequences.size();
		
		createSequence();
		
		var lastSequence = Model.sequences.at(Model.sequences.size() - 1);
		var sequenceName = lastSequence.get('name');
		
		$('#sequence-list [title=' + sequenceName + ']').click();
		
		var oldMaxValue = lastSequence.get('maxValue');
		
		var newMaxValue = 1;
		uncheckField('#sequence-details-common .details [name=hasMaxValue]');
		changeField('#sequence-details-common .details [name=maxValue]', newMaxValue);
		equal(lastSequence.get('maxValue'), newMaxValue, "New max value");
		equal(lastSequence.get('hasMaxValue'), true, "New has max value");
		
		undo(); // change max value
		undo(); // change has max value
		
		equal(lastSequence.get('maxValue'), oldMaxValue, "Undo should revert old max value");
		
		redo(); // change has max value
		redo(); // change max value
		
		equal(lastSequence.get('maxValue'), newMaxValue, "Redo should revert new max value");
	});
	
	test("Change cache", function() {
		var sequence_num = Model.sequences.size();
		
		createSequence();
		
		var lastSequence = Model.sequences.at(Model.sequences.size() - 1);
		var sequenceName = lastSequence.get('name');
		
		$('#sequence-list [title=' + sequenceName + ']').click();
		
		var oldCache = lastSequence.get('cache');
		
		var newCache = 1;
		uncheckField('#sequence-details-common .details [name=hasCache]');
		changeField('#sequence-details-common .details [name=cache]', newCache);
		equal(lastSequence.get('cache'), newCache, "New cache");
		equal(lastSequence.get('hasCache'), true, "New has cache");
		
		undo(); // change cache
		undo(); // change has cache
		
		equal(lastSequence.get('cache'), oldCache, "Undo should revert old cache");
		
		redo(); // change has cache
		redo(); // change cache
		
		equal(lastSequence.get('cache'), newCache, "Redo should revert new cache");
	});
	
	
	test("Change cycle", function() {
		var sequence_num = Model.sequences.size();
		
		createSequence();
		
		var lastSequence = Model.sequences.at(Model.sequences.size() - 1);
		var sequenceName = lastSequence.get('name');
		
		$('#sequence-list [title=' + sequenceName + ']').click();
		
		var oldCycle = lastSequence.get('cycle');
		checkField('#sequence-details-common .details [name=cycle][value=true]');
		equal(lastSequence.get('cycle'), true, "Set cycle");
		
		undo(); // change cache
		
		equal(lastSequence.get('cycle'), oldCycle, "Undo should revert old cycle");
		
		redo(); // change has cache
		
		equal(lastSequence.get('cycle'), true, "Redo should revert new cycle");
	});
};

Tests.allTests.push(Tests.sequences);
Tests.addButton("Sequences", Tests.sequences);




