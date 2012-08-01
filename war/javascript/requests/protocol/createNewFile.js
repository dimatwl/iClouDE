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
	Protocol.checkResponse.call(createRequest, resp);
});

createRequest.request.send = function (filePath, fileType) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
	
    info['filePath'] = filePath;
    info['fileType'] = fileType;
    
    createRequest.request.sendMap(info);    	
}