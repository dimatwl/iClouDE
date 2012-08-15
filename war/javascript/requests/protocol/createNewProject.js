/**
 * create new project request
 */
Protocol.createNewProject = {
    URL: "rest/newproject",
    method: POST,
    requestType: "newproject",
    responseFields: Protocol.response.ID
};


var createNewProject = Protocol.createNewProject;

createNewProject.request = new Request(createNewProject.method, createNewProject.URL, createNewProject.requestType);

createNewProject.request.setResponseHandler(function(resp) {
	if (Protocol.checkResponse.call(createNewProject, resp).correct) {
		projectID = resp.projectID;
		rootProjectDirectory = resp.entityID;
	}
});


createNewProject.request.send = function (projectName, projectType) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID);
	
	delete info.projectID;
	
	info['projectName'] = projectName;
    info['projectType'] = projectType;
    
    createNewProject.request.sendMap(info);    	
};
