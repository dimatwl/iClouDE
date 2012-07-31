var userID = 1;
var projectID = 1;






$(document).ready(function() {
    
	
	$('#checkNewFileBtn').click(function() {
    	//createNewFileRequest.setResponseHandler(function() {alert("ok!");});
		Protocol.createNewFile.request.send("project/sample/start.java", "ok!");

    });
    
    $('#checkUploadFileBtn').click(function() {
    	
    	var testFileContent = {
    	    type: 'file',
    	    filePath: 'project/sample.start.java',
    	    text: 'some text ya!',
    	    fileType: 'what is filetype?',
    	    revisionID: 'revision ID',
    	    creationDate: 'currDate',
            modificationDate: 'nodificationDate',
            size: '1000'
    	};
		
    	    	
    	Protocol.uploadFile.request.send(testFileContent);
    	
    });
    
    
});