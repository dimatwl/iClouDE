var downloadCodeActivity = function() {
	
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID, projectID);
    alert('projectID ' + projectID );
	info['projectID'] = projectID;
    json = $.toJSON(info);
	
	//document.location.href = 
    alert(Request.prototype.SERVER_URL + Protocol.downloadCode.URL + "?json=" + json);
	
    //document.location.href = "http://ya.ru";
    
}