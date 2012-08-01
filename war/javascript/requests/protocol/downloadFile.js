/**
 * Download file
 */
Protocol.downloadFile = {
    URL: "rest/downloadfile",
    method: GET,
    requestType: "downloadfile",
    responseFields: Protocol.response.FILE
};


var current = Protocol.downloadFile;

current.request = new Request(current.method, current.URL, current.requestID);

current.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(current, resp);
    
	alert("i got a file!");
    
});


current.request.send = function (filePath) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), current.requestType, userID, projectID);
    info['filePath'] = filePath;
	current.request.sendMap(info);    	
}
