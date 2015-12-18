
// Miejsce na nadpisywanie domyślnych konfiguracji bibliotek


// W wersji 1.9 wyleciała właściwość $.browser, której używa jquery.carret.js
// Bieda plugin pobrany z:
// http://pupunzi.open-lab.com/2013/01/16/jquery-1-9-is-out-and-browser-has-been-removed-a-fast-workaround/

jQuery.browser = {};
jQuery.browser.mozilla = /mozilla/.test(navigator.userAgent.toLowerCase()) && !/webkit/.test(navigator.userAgent.toLowerCase());
jQuery.browser.webkit = /webkit/.test(navigator.userAgent.toLowerCase());
jQuery.browser.opera = /opera/.test(navigator.userAgent.toLowerCase());
jQuery.browser.msie = /msie/.test(navigator.userAgent.toLowerCase());


// underscore.js - treści są wpisywane w szablonie w podwójnych wąsach "{{ }}"

_.templateSettings = {
    interpolate : /\{\{(.+?)\}\}/g
};


