var userID = 1;
var projectID = 1;






$(document).ready(function() {
    
	
	$('#CreateNewFileBtn').click(function() {
    	//createNewFileRequest.setResponseHandler(function() {alert("ok!");});
		Protocol.createNewFile.request.send("project/sample/start.java", "ok!");

    });
    
    $('#SaveFileBtn').click(function() {
    	
    	var testFileContent = {
    	    type: 'file',
    	    filePath: 'project/sample.start.java',
    	    text: 'some text ya!',
    	    fileType: 'what is filetype?',
    	    revisionID: 'revision ID',
    	    creationDate: (new Date()).getTime(),
            modificationDate: (new Date()).getTime(),
            size: '1000'
    	};
		
    	    	
    	Protocol.uploadFile.request.send(testFileContent);
    	
    });
    
    
    
    
    
    
    
});