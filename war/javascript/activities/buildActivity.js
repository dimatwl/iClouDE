/**
 * Build activity - we are sending to server request to build and after starting 
 * to ask server if our build is ready
 */


/**
 * Setting response to getResult request
 * If results ready it prints to console
 * If results not ready waits and asks again
 */
Protocol.getResults.request.setResponseHandler(function(response) {
    if (response.result) {
    	if (response.taskResults.length == 0) {
        	addToConsole("Build not ready.");
        	setTimeout(function() {Protocol.getResults.request.send();}, 1000);
    	} else {
            addToConsole('Build ready.');  
            for (i in response.taskResults) {
            	addToConsole(response.taskResults[i]);
            }
        }
    } else {
    	addToConsole('Error while requesting server.');
    }    	
});


/**
 * Response habdler for build request
 * Actually we send getResult request    
 */
Protocol.buildAndRun.request.setResponseHandler(function(response) {
    if (response.result) {
    	setTimeout(function() {Protocol.getResults.request.send();}, 100);
    	addToConsole('Trying to get results...');
    } else {
    	addToConsole('Error while requesting server.');	    	
    }    	
});



/**
 * Invoked when we want to build and run project       
 */
var buildActivity = function() {
	Protocol.buildAndRun.request.send(currentProjectID);
};