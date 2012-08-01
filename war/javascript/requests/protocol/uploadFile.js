/**
 * Upload file request
 */
Protocol.uploadFile = {
	URL: "rest/uploadfile",
    method: POST,
    requestType: "uploadfile",
    responseFields: Protocol.response.STANDART
};


var current = Protocol.uploadFile;

current.request = new Request(current.method, current.URL, current.requestID);

current.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(current, resp);
});

current.request.send = function (content) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), current.requestType, userID, projectID);
	
	info['content'] = content;
    
    current.request.sendMap(info);    	
}
