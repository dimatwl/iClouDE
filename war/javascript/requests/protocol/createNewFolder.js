/**
 * create new file request
 */
Protocol.createNewFolder = {
    URL: "rest/newfolder",
    method: POST,
    requestType: "newfolder",
    responseFields: Protocol.response.ID
};


var createFolderRequest = Protocol.createNewFolder;

createFolderRequest.request = new Request(createFolderRequest.method, createFolderRequest.URL, createFolderRequest.requestType);

createFolderRequest.request.setResponseHandler(function(resp) {
	if (Protocol.checkResponse.call(createFolderRequest, resp).correct) {
        currentFileID = resp.entityID;
	}
	
});

createFolderRequest.request.send = function (folderName, projectID, parentID) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
	
	/**
	 * WHAT A F?
	 */
	
	/*
	if (parentID == null)
		parentID = window.projectID;
	if (projectID == null)
		projectID = window.projectID;
	*/
	
    info['parentID'] = parentID;
    info['projectID'] = projectID;
    info['folderName'] = folderName;
    
    
    createFolderRequest.request.sendMap(info);    	
}