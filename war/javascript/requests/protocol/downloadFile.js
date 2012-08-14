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
	if (Protocol.checkResponse.call(downloadFile, resp).correct) {
		alert(resp.content.text);
		//$('#status').text(resp.content.text);		
	}
});


downloadFile.request.send = function (projectID, fileID) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
    info['fileID'] = fileID;
	downloadFile.request.sendMap(info);    	
}
