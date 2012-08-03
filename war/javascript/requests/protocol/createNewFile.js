/**
 * create new file request
 */
Protocol.createNewFile = {
    URL: "rest/newfile",
    method: POST,
    requestType: "newfile",
    responseFields: Protocol.response.ID
};


var createRequest = Protocol.createNewFile;

createRequest.request = new Request(createRequest.method, createRequest.URL, createRequest.requestType);

createRequest.request.setResponseHandler(function(resp) {
	if (Protocol.checkResponse.call(createRequest, resp).correct) {
        currentFileID = resp.id;
	}
	
});

createRequest.request.send = function (fileName, projectID, parentID) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
	
	/**
	 * WHAT A F?
	 */
	
	/*
	if (parentID == null)
		parentID = window.projectID;
	if (projectID == null)
		projectID = window.projectID;
	*/
	
    info['parentID'] = parentID;
    info['projectID'] = projectID;
    info['fileName'] = fileName;
    
    createRequest.request.sendMap(info);    	
}