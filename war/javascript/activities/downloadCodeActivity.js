/**
 * Getting zip of current project 
 */
var downloadCodeActivity = function() {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
    alert('projectID ' + projectID );
	info['projectID'] = projectID;
    
	info['requestType'] = "downloadcode"; 
    	
	json = $.toJSON(info);
	
	document.location.href = Request.prototype.SERVER_URL + Protocol.downloadCode.URL + "?json=" + json;
}