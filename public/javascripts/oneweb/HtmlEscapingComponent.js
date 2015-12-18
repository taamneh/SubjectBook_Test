oneweb.owjsPackage().HtmlEscapingComponent = {
	escapeHtmlBody : function(text) {
		if (text == null) {
			throw "text can not be null";
		}
		
		text = epoint.ow.Utils.replaceAllString(text, "&", "&amp;");
		text = epoint.ow.Utils.replaceAllString(text, "<", "&lt;");
		text = epoint.ow.Utils.replaceAllString(text, ">", "&gt;");
		text = epoint.ow.Utils.replaceAllString(text, "\"", "&quot;");
		text = epoint.ow.Utils.replaceAllString(text, "'", "&#x27;");
		text = epoint.ow.Utils.replaceAllString(text, "/", "&#x2F;");
		
        return text;
	},
	
	escapeHtmlAttribute : function(text) {
		if (text == null) {
			throw "text can not be null";
		}
		
		text = epoint.ow.Utils.replaceAllString(text, "\"", "&quot;");
		text = epoint.ow.Utils.replaceAllString(text, "'", "&#x27;");
		
        return text;
	},
	
	escapeJavaScriptString : function(text) {
		if (text == null) {
			throw "text can not be null";
		}
		
		text = epoint.ow.Utils.replaceAllString(text, "\"", "&quot;");
		text = epoint.ow.Utils.replaceAllString(text, "'", "&#x27;");
		text = epoint.ow.Utils.replaceAllString(text, "<", "&lt;");
        text = epoint.ow.Utils.replaceAllString(text, ">", "&gt;");
		
        return text;
	},
	
	escapeUrlParameter : function(text) {
		if (text == null) {
			throw "text can not be null";
		}
		
        text = encodeURIComponent(text);
        text = epoint.ow.Utils.replaceAllString(text, "'", "%27");
        text = epoint.ow.Utils.replaceAllString(text, "(", "%28");
        text = epoint.ow.Utils.replaceAllString(text, ")", "%29");
        return text;
	}
};