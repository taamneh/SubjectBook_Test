var oneweb = {};

oneweb.owjsPackage = /* Object */function(/* String */qualifiedPackageName) {
	var owner = oneweb;
	if (qualifiedPackageName == null || qualifiedPackageName == "") {
		return owner;
	}
	var packageNames = qualifiedPackageName.split(".");
	for ( var i = 0; i < packageNames.length; i++) {
		var packageName = packageNames[i];
		if (packageName.length == 0) {
			continue;
		}
		if (!(packageName in owner)) {
			owner[packageName] = new Object();
		}
		owner = owner[packageName];
	}
	return owner;
};

Function.prototype.owextend = function(parentFunction) {
	if (parentFunction == null) {
		throw "can not extend null function:" + this;
	}
	if (!(parentFunction instanceof Function)) {
		throw "only functions can be extended";
	}
	var f = function() {};
	f.prototype = parentFunction.prototype;
	
	this.prototype = new f();
	this.prototype.constructor = this;
	return this;
};