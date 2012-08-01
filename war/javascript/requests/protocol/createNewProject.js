/**
 * create new project request
 */
Protocol.createNewProject = {
    URL: "rest/newproject",
    method: POST,
    requestType: "newproject",
    responseFields: Protocol.response.STANDART
};


var current = Protocol.createNewProject;

current.request = new Request(current.method, current.URL, current.requestID);

current.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(current, resp);
});


current.request.send = function (projectName, projectType) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), current.requestType, userID);
	
	delete info.projectID;
	
	info['projectName'] = projectName;
    info['projectType'] = projectType;
    
    current.request.sendMap(info);    	
}
