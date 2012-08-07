var makeRow = function(name, request, response) {
    return '<tr><td>' + name + '</td><td>'+ request + '</td><td>' + response + '</td></tr>';
}
    
var userID = "test";
var projectID;
var currentFileID;

var testAndAdd = function(namespace, name, handler) {
	var request = "NOT CHECKED";
	var response = "NOT CHECKED";
	
    namespace.request.setResponseHandler(function(data) {
        if (data.result) {
        	request = "OK";
        	var res = Protocol.checkResponse.call(namespace, data);
        	if (res.correct) {
        		response = "OK";
        	    handler(data);
        	}
        	else {
        	    response = data.description;
        	}
        }
        else {
            response = data.description;
		   	if (!response)
		   		response = "We got strange packet!";
        }	
		$('#testTable tr:last').after(makeRow(name, request, response));
    });
    
    namespace.request.setErrorHandler(function() {
        response = "REQUEST FUCKED UP";
        
        $('#testTable tr:last').after(makeRow(name, request, response));
    });    
}


$(document).ready(function() {
    
    
	
	
	var projectName = 'AAAAAA';
    var projectType = 'BBBBBBBB';
        
    
    var createNewProjectHandler = function(data) {
        projectID = data.id;
        
        var fileName = 'QUCUCU';
        testAndAdd(Protocol.createNewFile, 'Create New File', createNewFileHandler);
        Protocol.createNewFile.request.send(fileName, projectID, projectID);
    };
    
    
    var createNewFileHandler = function(data) {
        currentFileID = data.id;
        testAndAdd(Protocol.uploadFile, 'Upload File', uploadFileHandler);
        Protocol.uploadFile.request.send({
    	    type: 'file',
    	    fileID: currentFileID,
    	    text: "I am a helicopter",
    	    fileType: 'what is filetype?',
    	    revisionID: 'revision ID',
    	    creationDate: (new Date()).getTime(),
            modificationDate: (new Date()).getTime(),
            size: '1000'
    	});
    };
    
    
    var uploadFileHandler = function(data) {
    	testAndAdd(Protocol.downloadFile, 'Download File', downloadFileHandler);
        Protocol.downloadFile.request.send(currentFileID);    	
    }    
    
    var downloadFileHandler = function(data) {
    	alert('Download OK!');
    }
    
    
    
    testAndAdd(Protocol.createNewProject, 'Create New Project', createNewProjectHandler);
    Protocol.createNewProject.request.send(projectName, projectType);
    
    
    
    
    
    
    
    
    
    
    
    
});
