Protocol.getResults.request.setResponseHandler(function(response) {
    if (response.result) {
        
    	if (response.taskResults.length == 0) {
        	addToConsole("build not ready!!!");
        } else {
            addToConsole('build ready!');    	
        }
        
    } else {
    	addToConsole('Error while requesting server.');
    }    	
    	
    	
});


Protocol.buildAndRun.request.setResponseHandler(function(response) {
    if (response.result) {
        
    	setTimeout('Protocol.getResults.request.send', 100);
    	addToConsole('Trying to get results...');
    	
    } else {
    	addToConsole('Error while requesting server.');	    	
    }    	
    	
});


var buildActivity = function() {
	
		
	
	Protocol.buildAndRun.request.send(currentProjectID);
	
	
};