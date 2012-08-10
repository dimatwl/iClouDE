var Protocol = {};


/**
 * Responses
 */

Protocol.response = {
    STANDART: ['requestID', 'result', 'description'],
    ID: ['requestID', 'result', 'description', 'entityID', 'projectID'],
    FILE: ['requestID', 'result', 'description', 'content'],
    PROJECT: ['requestID', 'result', 'description', 'content']
};


Protocol.makeBasicRequestInfo = function (requestID, method, userID, pojectID) {
    return {
    	requestID: requestID,
    	requestType: method,
    	userID: userID,
    	projectID: projectID,    
    	protocolVersion : 2
    };	
}

Protocol.getRequestID = function() {
	return (new Date()).getTime();
}

/**
 * Checks if all fields exists
 * @param response
 * @param responseFields
 * @returns {Array} tuple with boolean result and field name which was not found
 */
Protocol.correctnessInfo = function (response, responseFields) {
	var responseInfo = {
	    correct: false		
	};
	for (i = 0; i < responseFields.length; ++i) {
		if (response[responseFields[i]] == null) {
    		responseInfo.description = "Field: " + responseFields[i] + " is missing!"; 
			return responseInfo;
		}
	}
	if (Object.keys(response).length != responseFields.length) {
	    responseInfo.description = "On receiving expected " + responseFields.length + " fields, received: " + Object.keys(response).length + " fields";	
        return responseInfo;
	}
	
	responseInfo.correct = true;
	return responseInfo;
}


Protocol.checkResponse = function (response) {
    var info = Protocol.correctnessInfo(response, this.responseFields);
    
    if (info.correct) {
    	alert("response ok!\nresult: " + response.result + "\ndescription: " + response.description);
    	return info;
    } 
    alert("error!" + "\n" + info.description);
    return info;
}



