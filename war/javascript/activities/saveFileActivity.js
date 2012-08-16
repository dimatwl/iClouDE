/**
 * Saving file to server.
 * It saves text from textarea (editor - variable) 
 */

var saveFileActivity = function(projectID, fileID) {
	alert("saveFileActivity " + projectID + " " + fileID);
	
    var testFileContent = {
        type: 'file',
        fileID: fileID,
        text: editor.getValue(),
        fileType: 'file',
        revisionID: 'revision ID',
        creationDate: (new Date()).getTime(),
        modificationDate: (new Date()).getTime(),
        size: '100'
    };
		
    Protocol.uploadFile.request.setResponseHandler(function(response) {
    	if (response.result) {
    	    addToConsole('File saved.');	
    	} else {
    		addToConsole('File not saved. Description: ' + response.description);
    	}
    });
    
    Protocol.uploadFile.request.send(projectID, testFileContent);
};