/**
 * Getting zip of current project 
 */
var downloadCodeActivity = function(projectToDownload) {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), 'downloadcode', userID, projectToDownload);
    
	//alert('projectID ' + projectToDownload );
	
	//info['requestType'] = "downloadcode"; 
    	
	json = $.toJSON(info);
	
	document.location.href = Request.prototype.SERVER_URL + Protocol.downloadCode.URL + "?json=" + json;
}