/**
 * Download file
 */
Protocol.downloadFile = {
    URL: "rest/downloadfile",
    method: GET,
    requestType: "downloadfile",
    responseFields: Protocol.response.FILE
};


var downloadFile = Protocol.downloadFile;

downloadFile.request = new Request(downloadFile.method, downloadFile.URL, downloadFile.requestType);

downloadFile.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(downloadFile, resp);
    
	alert("i got a file!");
    
});


downloadFile.request.send = function (filePath) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
    info['filePath'] = filePath;
	downloadFile.request.sendMap(info);    	
}
