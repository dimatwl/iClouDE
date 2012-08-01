/**
 * create new file request
 */
Protocol.createNewFile = {
    URL: "rest/newfile",
    method: POST,
    requestType: "newfile",
    responseFields: Protocol.response.STANDART
};


var createRequest = Protocol.createNewFile;

createRequest.request = new Request(createRequest.method, createRequest.URL, createRequest.requestType);

createRequest.request.setResponseHandler(function(resp) {
	if (Protocol.checkResponse.call(createRequest, resp)) {
        currentFileID = resp.id;
	}
	
});

createRequest.request.send = function (fileName, parentID) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
	
	if (parentID == null)
		parentID = projectID;
	
    info['parentID'] = parentID;
    info['fileName'] = fileName;
    
    createRequest.request.sendMap(info);    	
}