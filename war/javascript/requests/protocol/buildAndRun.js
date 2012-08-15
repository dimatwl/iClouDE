/**
 * build and run request
 */
Protocol.buildAndRun = {
    URL: "rest/newbuildandruntask",
    method: POST,
    requestType: "newbuildandruntask",
    responseFields: Protocol.response.STANDART
};


var bRunRequest = Protocol.buildAndRun;

bRunRequest.request = new Request(bRunRequest.method, bRunRequest.URL, bRunRequest.requestType);

bRunRequest.request.setResponseHandler(function(resp) {
	if (Protocol.checkResponse.call(bRunRequest, resp).correct) {
        currentFileID = resp.entityID;
	}
	
});

bRunRequest.request.send = function (projectID, entryPointID, inputData, compileParameters) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
	
	if (!entryPointID)
		entryPointID = '';
	if (!inputData)
	    inputData = '';
	if (!compileParameters)
		compileParameters = '';
	
	info['projectID'] = projectID;
    info['entryPointID'] = entryPointID;
    info['inputData'] = inputData;
    info['compileParameters'] = compileParameters;
    
    bRunRequest.request.sendMap(info);    	
};