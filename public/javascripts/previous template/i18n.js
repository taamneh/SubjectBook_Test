
// i18n ------------------------------------------------------------------------

function i18n () {}

i18n.$bundles = {};

/* i18nBundle */ i18n.getBundle = function (id) {
	if (typeof i18n.$bundles[id] == "undefined") {
		//Debug.error("i18n # Unable to get bundle: " + id);
		return null;
	}
	return i18n.$bundles[id];
};

i18n.initBundle = function(id, data) {
	i18n.$bundles[id] = new i18nBundle(id, data);
};

// i18nBundle ------------------------------------------------------------------

function i18nBundle (id, data) {
	data = (typeof data == "undefined") ? {} : data;
	this.$id = id;
	this.$strings = eval('('+data+')');
}

/* void */ i18nBundle.prototype.add = function (key, value) {
	this.$strings[key] = value;
};

/* string */ i18nBundle.prototype.get = function (key) {
	if (typeof this.$strings[key] == "undefined") {
		//Debug.warning(this + " # Unable to find string for key: " + key);
		return "NO_BUNDLE_" + key;
	} else
	{
		return this.$strings[key];
	}
};

/* string */ i18nBundle.prototype.toString = function () {
	return "[object i18nBundle]"
	+ "\nid: " + this.$id;
};
