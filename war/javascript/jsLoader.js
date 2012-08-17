/**
 * Dynamic javascript files loader
 * @param url what we actually download
 */

function loadJS(url) {
	var s = document.createElement("script");
	s.setAttribute("type", "text/javascript");
	s.setAttribute("src", url);
	var h = document.getElementsByTagName("head")[0];
	h.appendChild(s);
}