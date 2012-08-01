/**
 * create new file request
 */
Protocol.createNewFile = {
    URL: "rest/newfile",
    method: POST,
    requestType: "newfile",
    responseFields: Protocol.STANDART_RESPONSE
};


var current = Protocol.createNewFile;

current.request = new Request(current.method, current.URL, current.requestID);

current.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(current, resp);
});

current.request.send = function (filePath, fileType) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), current.requestType, userID, projectID);
	
	info['filePath'] = filePath;
    info['fileType'] = fileType;
    
    current.request.sendMap(info);    	
}