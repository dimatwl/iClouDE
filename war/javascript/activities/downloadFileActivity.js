/**
 * Downloading file to client
 * Used to get file text and print it to the editor(textarea)
 */


var downloadFileActivity = function(projectID, fileID) {
	//alert("Opening project: " + projectID + " file: " + fileID);
	
	Protocol.downloadFile.request.setResponseHandler(function(response) {
	    if (response.result) {
	        var fileContent = response.content;
	        var fileText = fileContent.text;
	        
	        // save current file ID

	        editor.setValue(fileText);
            currentFileID = fileID;     
            addToConsole('File ' +  fileContent.fileName + ' downloaded!');
	    } else {
	    	addToConsole('File was not downloaded. Description: ' + response.description);
	    }  
	});
	
	Protocol.downloadFile.request.setErrorHandler(function() {
        addToConsole('Error while requesting server!');    		
	});
	
	Protocol.downloadFile.request.send(projectID, fileID);
};
