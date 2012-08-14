var downloadFileActivity = function(projectID, fileID) {
	alert("Opening project: " + projectID + " file: " + fileID);
	
	Protocol.downloadFile.request.setResponseHandler(function(response) {
	    if (response.result) {
	        var fileContent = response.content;
	        var fileText = fileContent.text;
	        
	        // save current file ID
	        
	        editor.setValue(fileText);
            currentFileID = fileID;     
            //alert(currentFileID);
                        
	    }   
	    
	    	
	});
	
	Protocol.downloadFile.request.send(projectID, fileID);
    
};
