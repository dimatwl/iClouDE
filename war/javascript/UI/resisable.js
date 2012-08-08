$(document).ready(sizeContent);
$(window).resize(sizeContent);

function sizeContent() {
    var newHeight = $("html").height() - $("#header").height() - $("#footer").height() + "px";
	$("#content").css("height", newHeight);
	$("#sidebar").css("height", newHeight);
	var newHeight = $("#content").height() - $("#statusArea").height() + "px";
	$("#textArea").css("height", newHeight);
	$("#textArea").resizable( "option", "maxHeight", $("#content").height() -50);
}

$(function() {
	$( "#textArea" ).resizable({
		handles: "s",
		minHeight: 150,
		alsoResizeReverse: "#statusArea",
		stop: function(h, ui) {
			$("#textArea").css("width", "100%");
			$("#statusArea").css("width", "100%");
		}
	});
	$( "#sidebar" ).resizable({
		handles: "e",
		maxWidth: 300,
		alsoResizeReverse: "#content"
	});
});