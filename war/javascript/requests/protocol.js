var Protocol = {};

/**
 * Responses
 */
Protocol.response = {
    STANDART: ['requestID', 'result', 'description'],
    FILE: ['requestID', 'result', 'description', 'content'],
    PROJECT: ['requestID', 'result', 'description', 'content']
};


Protocol.makeBasicRequestInfo = function (requestID, method, userID, pojectID) {
    return {
    	requestID: requestID,
    	requestType: method,
    	userID: userID,
    	projectID: projectID    	
    };	
}

Protocol.getRequestID = function() {
	return (new Date()).toString();
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
    
    if (info) {
    	alert("response ok!\nresult: " + response.result + "\ndescription: " + response.description);
    } else
    	alert("error!" + "\n" + "field not found: " + info[1]);
}



