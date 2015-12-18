
window = {};
sessionStorage = {

    getItem: function() {
        return null;
    },
    
    setItem: function() {
        return null;
    }
};

importScripts('/assets/js/modeler/worker_deps.js');

var console = {
    log: function() {
        var args = [];
        for(var i = 0; i < arguments.length; i++) {
            args.push(arguments[i]);
        }
        
        var msg = {debug: args};
        self.postMessage(JSON.stringify(msg));
    }
};


// Uproszczona implementacja loggera z pliku logger.js.
var Logger = function() {
      
};
Logger.prototype = {


    flush: function() {
        
    },

    info: function(msg) {
        console.log(msg);        
    },

    error: function(msg) {
        console.log(msg);        
    },
 
};
var logger = new Logger();


self.addEventListener('message', function(e) {
                             
    var command = e.data[0];

    if(command == 'validate') {
        var data = JSON.parse(e.data[1]);
        Model.tables.reset(data.tables);
        Model.tableDisplays.reset(data.tableDisplays);
        Model.references.reset(data.references);
        Model.referenceDisplays.reset(data.referenceDisplays);
        Model.references.triggerTables(); 
        Model.sequences.reset(data.sequences);
        Model.views.reset(data.views);
                          
        Model.validationProperties.set(JSON.parse(e.data[2]));                          
        Model.databaseTypes.reset(JSON.parse(e.data[3]));                          
        Model.supportedObjectTypes.reset(JSON.parse(e.data[4]));                      
        Model.problemReports.reset([]); 
                         
        Model.Validation.Validator.validateModel();                         
                          
        var problems = {problems: Model.problemReports.toJSON()};
        self.postMessage(JSON.stringify(problems));
    } else if(command == "importScript") {
        var name = e.data[1];
        importScripts(name);
    } else {
        console.log("unknown worker command: " + command);
    }

}, false);


