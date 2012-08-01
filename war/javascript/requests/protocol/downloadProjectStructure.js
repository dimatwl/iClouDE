/**
 * Download project structure
 */
Protocol.downloadProjectStructure = {
    URL: "rest/downloadprojectstructure",
    method: GET,
    requestType: "downloadprojectstructure",
    responseFields: Protocol.response.PROJECT
};


var current = Protocol.downloadProjectStructure;

current.request = new Request(current.method, current.URL, current.requestID);

current.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(current, resp);
    
	alert("i got new project!");
    
});


current.request.send = function (wantedProjectID) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), current.requestType, userID, wantedProjectID);
	current.request.sendMap(info);    	
}
