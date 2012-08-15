/**
 * Upload file request
 */
Protocol.uploadFile = {
	URL: "rest/uploadfile",
    method: POST,
    requestType: "uploadfile",
    responseFields: Protocol.response.STANDART
};


var uploadRequest = Protocol.uploadFile;

uploadRequest.request = new Request(uploadRequest.method, uploadRequest.URL, uploadRequest.requestType);

uploadRequest.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(uploadRequest, resp).correct;
});

uploadRequest.request.send = function (projectID, content) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
	
	info['content'] = content;
    
    uploadRequest.request.sendMap(info);    	
};
