var Protocol = {};

/**
 * Responses
 */
Protocol.STANDART_RESPONSE = ['requestID', 'result', 'description'];

Protocol.makeBasicRequestInfo = function (requestID, requestTypeID, userID, pojectID) {
    return {
    	requestID: requestID,
    	requestType: requestTypeID,
    	userID: userID,
    	projectID: projectID    	
    };	
}

Protocol.requestID = function() {
	return (new Date()).toString();
}


/**
 * create new file request
 */
Protocol.createNewFile = {
    URL: "rest/newfile",
    method: POST,
    requestType: "newfile",
    responseFields: Protocol.STANDART_RESPONSE
};


var current = Protocol.createNewFile;

current.request = new Request(current.method, current.URL, current.requestID);

current.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(current, resp);
});

current.request.send = function (filePath, fileType) {
	var info = Protocol.makeBasicRequestInfo(Protocol.requestID(), current.requestType, userID, projectID);
	
	info['filePath'] = filePath;
    info['fileType'] = fileType;
    
    current.request.sendMap(info);    	
}





/**
 * Upload file request
 */
Protocol.uploadFile = {
	URL: "rest/uploadfile",
    method: POST,
    requestType: "uploadfile",
    responseFields: Protocol.STANDART_RESPONSE
};


var current = Protocol.uploadFile;

current.request = new Request(current.method, current.URL, current.requestID);

current.request.setResponseHandler(function(resp) {
	Protocol.checkResponse.call(current, resp);
});

current.request.send = function (content) {
	var info = Protocol.makeBasicRequestInfo(Protocol.requestID(), current.requestType, userID, projectID);
	
	info['content'] = content;
    
    current.request.sendMap(info);    	
}




















/**
 * Checks if all fields exists
 * @param response
 * @param responseFields
 * @returns {Array} tuple with boolean result and field name which was not found
 */
Protocol.correctnessInfo = function (response, responseFields) {
	for (i = 0; i < responseFields.length; ++i) {
		if (response[responseFields[i]] == null)
			return [false, responseField[i]];
	}
	return [true, ''];	
}


Protocol.checkResponse = function (response) {
    var info = Protocol.correctnessInfo(response, this.responseFields);
    
    if (info)
        alert("response ok!");
    else
    	alert("error!" + "\n" + "field not found: " + info[1]);
}

