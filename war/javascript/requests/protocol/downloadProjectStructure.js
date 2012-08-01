/**
 * Download project structure
 */
Protocol.downloadProjectStructure = {
    URL: "rest/downloadprojectstructure",
    method: GET,
    requestType: "downloadprojectstructure",
    responseFields: Protocol.response.PROJECT
};


var downloadPS = Protocol.downloadProjectStructure;

downloadPS.request = new Request(downloadPS.method, downloadPS.URL, downloadPS.requestType);

downloadPS.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(downloadPS, resp);
});


downloadPS.request.send = function (wantedProjectID) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, wantedProjectID);
	downloadPS.request.sendMap(info);    	
}