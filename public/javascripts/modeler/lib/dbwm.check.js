var dbwm = {};

dbwm.Validation = function(name,value) {
    
    this.value = value;

    if(name == undefined) {
        this.name = "Value";
    } else {
        this.name  = name;        
    }
    
    this.valueNull = true;
    
    
    if(value === undefined) {
        this.raise("is not defined");   
    }

    if(value != null) {
        this.valueNull = false;
    } else {
        this.valueNull = true;
    }
    
    return this;
};

dbwm.Validation.prototype = {

    raise: function(message) {
        throw new Error("" + this.name + " " + message);
    },
    
    notNull: function() {
            
        if(this.valueNull == true) {
            this.raise("is null.");
        }

        return this;
    },

    isFunction: function() {
        this.notNull();
        
        if(typeof(this.value) != "function") {
            this.raise("is not a function");
        }
                
        return this;
    }
};

dbwm.check = function (value,name) {
    return new dbwm.Validation(name,value);
};
