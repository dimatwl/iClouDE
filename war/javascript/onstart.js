var userID = "yarik";
var projectID;

var currentFileID;

//var editor = CodeMirror.fromTextArea(document.getElementById("#codeArea"));



$(document).ready(function() {
    
	var editor = CodeMirror.fromTextArea(document.getElementById("codeArea"), {
		  mode: "text/x-java",
		  lineNumbers: true,
		  lineWrapping: true,
		  onCursorActivity: function() {
		    editor.setLineClass(hlLine, null, null);
		    hlLine = editor.setLineClass(editor.getCursor().line, null, "activeline");
		  }
    });
    
	var hlLine = editor.setLineClass(0, "activeline");
	
	
	$('#CreateNewFileBtn').click(function() {
		var fileName = prompt('Enter name: ', '');
		Protocol.createNewFile.request.send(fileName);
    });
    
    $('#SaveFileBtn').click(function() {
    	
    	var testFileContent = {
    	    type: 'file',
    	    fileID: currentFileID,
    	    text: editor.getValue(),
    	    fileType: 'what is filetype?',
    	    revisionID: 'revision ID',
    	    creationDate: (new Date()).getTime(),
            modificationDate: (new Date()).getTime(),
            size: '1000'
    	};
		
    	Protocol.uploadFile.request.send(testFileContent);
    	
    });
    
    
    $('#CreateNewProjectBtn').click(function() {
    	var projectName = prompt('Enter project name', '');
    	Protocol.createNewProject.request.send(projectName, "web-appl");    	
    });
    
    $('#DownloadProjectStructureBtn').click(function() {
    	var projectID = prompt('Enter project ID: ');
    	Protocol.downloadProjectStructure.request.send(projectID);    	
    });
    
    $('#DownloadFileBtn').click(function() {
    	//var filePath = prompt ('Enter file path to download');
    	var fileID = currentFileID;
    	Protocol.downloadFile.request.send(fileID);
    });
    
    
    
    
    
    
    
    
    
    
    
    
});