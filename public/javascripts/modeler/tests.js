

var Tests = {};

Tests.addButton = function(label, fn) {

	var container = $('#debug-console-buttons');

	var button = $('<button></button>');

	button.text(label);
	container.append(button);

	button.click(function() {
		fn();
	});
};


Tests.beforeTestSuite = function() {

	//$('#debug-console-buttons').children().remove();
	$('#qunit-container').show();
};


Tests.allTests  = [];

Tests.runAllTests = function() {

	for(var i = 0; i < Tests.allTests.length; i ++) {
		Tests.allTests[i]();
	}
};


$(document).ready(function() {


	$('#debug-console .header h1').text("Self Tests");
	$('#debug-console-content').hide();
	$('#debug-console-buttons').children().remove();
	$('#qunit-container').show();

	Tests.addButton("Reload test suite", app.loadTests.bind(app));
	Tests.addButton("Run all tests", Tests.runAllTests);
	$('#debug-console-buttons').append($('<br>'));


	app.loadTestsFromUrl("/assets/js/modeler/tests/skeleton.js");
	app.loadTestsFromUrl("/assets/js/modeler/tests/table_basic.js");	
	app.loadTestsFromUrl("/assets/js/modeler/tests/table_columns.js");
	app.loadTestsFromUrl("/assets/js/modeler/tests/table_alternate_keys.js");
	app.loadTestsFromUrl("/assets/js/modeler/tests/table_indexes.js");
	app.loadTestsFromUrl("/assets/js/modeler/tests/align.js");
	app.loadTestsFromUrl("/assets/js/modeler/tests/reference.js");
	app.loadTestsFromUrl("/assets/js/modeler/tests/note.js");
	app.loadTestsFromUrl("/assets/js/modeler/tests/sequence.js");
});
