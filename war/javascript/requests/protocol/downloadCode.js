/**
 * Download file
 */
Protocol.downloadCode = {
    URL: "rest/downloadcode",
    method: GET,
    requestType: "downloadcode",
};


var downloadCode = Protocol.downloadCode;

downloadCode.request = new Request(downloadCode.method, downloadCode.URL, downloadCode.requestType);

downloadCode.request.setResponseHandler(function(resp) {
	("OK!");
});


downloadCode.request.send = function (projectID) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
    info['projectID'] = projectID;
	
    alert($.toJSON(info ));
    
    //downloadCode.request.sendMap(info);
	
	
};
