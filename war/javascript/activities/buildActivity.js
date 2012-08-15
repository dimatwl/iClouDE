Protocol.getResults.request.setResponseHandler(function(response) {
    if (response.result) {
        
    	if (response.taskResults.length == 0) {
        	addToConsole("build not ready!!!");
        	setTimeout(function() {Protocol.getResults.request.send();}, 1000);
    	} else {
            addToConsole('build ready!');  
            for (i in repsponse.taskResults) {
            	addToConsole(response.taskResults[i]);
            }
        }
        
    } else {
    	addToConsole('Error while requesting server.');
    }    	
    	
    	
});


Protocol.buildAndRun.request.setResponseHandler(function(response) {
    if (response.result) {
        
    	setTimeout(function() {Protocol.getResults.request.send();}, 100);
    	addToConsole('Trying to get results...');
    	
    } else {
    	addToConsole('Error while requesting server.');	    	
    }    	
    	
});


var buildActivity = function() {
	
		
	
	Protocol.buildAndRun.request.send(currentProjectID);
	
	
};