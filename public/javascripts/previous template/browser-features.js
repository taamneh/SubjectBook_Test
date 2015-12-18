
BrowserFeatures = {

    isChrome: function() {
        if(window.chrome) {
            return true;
        };
        return false;
    },

    isIE: function() {
        return navigator.userAgent.toLowerCase().indexOf('msie') > -1;
    },

    isIE11: function() {
        return !!(navigator.userAgent.match(/Trident/) && !navigator.userAgent.match(/MSIE/));
    },

    isFirefox: function() {
        return navigator.userAgent.toLowerCase().indexOf('firefox') > -1;
    },

    
    isSafari: function() {
        return navigator.userAgent.indexOf("Safari") > -1;
    },  

    isEditPossible: function() {
        return  this.isChrome() 
    },

    isPreviewPossible: function() {
        return  this.isChrome() || this.isFirefox() || this.isSafari() || this.isIE11();
    },

    info: function() {
        console.log("Chrome:", this.isChrome());
        console.log("Firefox:", this.isFirefox());
        console.log("Safari:", this.isSafari());
        console.log("IE11:", this.isIE11());
        console.log("Edit:", this.isEditPossible());
        console.log("Preview:", this.isPreviewPossible());
        console.log("UserAgent: ", navigator.userAgent);
    }
};

oneweb.DocumentReadyManager.addApplicationAction(function() {
    if($('#diagram').length == 0) {
        if($('#not-supported-browser-info-header')[0]) {
            if (BrowserFeatures.isEditPossible() == false) {
                $('#not-supported-browser-info-header').show();
            } else {
                $('#not-supported-browser-info-header').remove();
            }
        }
    }
}, 99);
