var downloadFileActivity = function(projectID, fileID) {
	alert("Opening project: " + projectID + " file: " + fileID);
	
	Protocol.downloadFile.request.setResponseHandler(function(response) {
	    if (response.result) {
	        var fileContent = response.content;
	        var fileText = fileContent.text;
	        
	        // save current file ID
	        
	        
	        editor.setValue(fileText);
            currentFileID = fileID;     
            addToConsole('File ' +  fileContent.fileName + ' downloaded!');
            //alert(currentFileID);
                        
	    } else {
	    	addToConsole('File was not downloaded. Description: ' + response.description);
	    }  
	});
	
	Protocol.downloadFile.request.setErrorHandler(function() {
        addToConsole('Error while requesting server!');    		
        		
	});
	
	Protocol.downloadFile.request.send(projectID, fileID);
    
};
