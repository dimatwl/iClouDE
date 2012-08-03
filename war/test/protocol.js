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
        	var res = Protocol.checkResponse.call(namespace, data);
        	if (res.correct) {
        		response = "OK";
        	    handler(data);
        	}
        	else
        		response = data.description;
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
        
        $('#testTable tr:last').after(name, makeRow(request, response));
    });    
}


$(document).ready(function() {
    /*
	var request = "NOT CHECKED";
	var response = "NOT CHECKED";
    
	
	
    Protocol.createNewProject.request.setResponseHandler(function(data) {
        var res = Protocol.checkResponse.call(Protocol.createNewProject, data);
		if (res.correct) {
        	response = "OK";
        }
        else
            response = res.description;
		
		$('#testTable tr:last').after(makeRow('Create Project', request, response));
    });
    
    Protocol.createNewProject.request.setErrorHandler(function() {
        response = "REQUEST FUCKED UP";
        
        $('#testTable tr:last').after(makeRow('Create Project', request, response));
    });
            
    */
    
    var projectName = 'AAAAAA';
    var projectType = 'BBBBBBBB';
    
    
    
    var createNewProjectHandler = function(data) {
        window.projectID = data.id;
    };
    
    testAndAdd(Protocol.createNewProject, 'Create New Project', createNewProjectHandler);
    Protocol.createNewProject.request.send(projectName, projectType);
    
    
    var createNewFileHandler = function(data) {
        window.currentFileID = data.id;
    };
    
    
    var fileName = 'QUCUCU';
    testAndAdd(Protocol.createNewFile, 'Create New File', createNewFileHandler);
    
    alert("projectID " + window.projectID + " paerntID " + window.parentID);
    
    Protocol.createNewFile.request.send(fileName, window.projectID, window.parentID);
    
    
    /*
    testAndAdd(Protocol.uploadFile, 'Upload file');
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
    
    */
    
    
    
    
    
    
    
    
});
