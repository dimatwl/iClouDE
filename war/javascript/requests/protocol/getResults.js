/**
 * Get Results request
 */
Protocol.getResults = {
    URL: "rest/downloadavailableresults",
    method: GET,
    requestType: "downloadavailableresults",
    responseFields: Protocol.response.RESULTS
};


var getResultsRequests = Protocol.getResults;

getResultsRequests.request = new Request(getResultsRequests.method, getResultsRequests.URL, getResultsRequests.requestType);

getResultsRequests.request.setResponseHandler(function(resp) {
	if (Protocol.checkResponse.call(getResultsRequests, resp).correct) {
        currentFileID = resp.entityID;
	}
	
});

getResultsRequests.request.send = function () {
	var info = Protocol.makeBasicRequestInfo(Protocol.getRequestID(), this.requestType, userID);
    getResultsRequests.request.sendMap(info);    	
};