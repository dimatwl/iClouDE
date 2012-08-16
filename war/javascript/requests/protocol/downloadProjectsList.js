Protocol.downloadProjectsList = {
    URL: "rest/downloadprojectlist",
    method: GET,
    requestType: "downloadprojectlist",
    responseFields: Protocol.response.PROJECT_LIST
};


var downloadProjectsList = Protocol.downloadProjectsList;

downloadProjectsList.request = new Request(downloadProjectsList.method, downloadProjectsList.URL, downloadProjectsList.requestType);

downloadProjectsList.request.setResponseHandler(function(resp) {
	if (Protocol.checkResponse.call(downloadProjectsList, resp).correct) {
		alert(resp.content.text);
		//$('#status').text(resp.content.text);		
	}
});


downloadProjectsList.request.send = function (userID) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
    info['userID'] = userID;
	downloadProjectsList.request.sendMap(info);    	
};
