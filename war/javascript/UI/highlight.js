/**
 * Syntax highlight library usage
 */

$(document).ready(function() {
	
	/**
	 * Variable corresponding to text area, and text within 
	 */	
	editor = CodeMirror.fromTextArea(document.getElementById("codeArea"), {
		  mode: "text/x-java",
		  lineNumbers: true,
		  lineWrapping: true,
		  onCursorActivity: function() {
		    editor.setLineClass(hlLine, null, null);
		    hlLine = editor.setLineClass(editor.getCursor().line, null, "activeline");
		  }
    });
    
	hlLine = editor.setLineClass(0, "activeline");
});