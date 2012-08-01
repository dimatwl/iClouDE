/**
 * create new project request
 */
Protocol.createNewProject = {
    URL: "rest/newproject",
    method: POST,
    requestType: "newproject",
    responseFields: Protocol.response.STANDART
};


var createNewProject = Protocol.createNewProject;

createNewProject.request = new Request(createNewProject.method, createNewProject.URL, createNewProject.requestType);

createNewProject.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(createNewProject, resp);
});


createNewProject.request.send = function (projectName, projectType) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID);
	
	delete info.projectID;
	
	info['projectName'] = projectName;
    info['projectType'] = projectType;
    
    createNewProject.request.sendMap(info);    	
}
