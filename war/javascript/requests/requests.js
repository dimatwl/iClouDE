/**
 * Server URL's
 */
const serverURL = "http://localhost:8888/"; 
//const serverURL = "http://spbau-icloude.appspot.com/";


/**
 * User and project info
 */
var userID = 1;
var projectID = 1;
var requestID = 1;


const fileType = "for smth in future :)";


function sendRequest(data, responseHandler, errorHandler) {
	var url = data[0];
	var type = data[1];
	var jsonMsg = $.toJSON(data[2]);
	
	var request = {
	    url: serverURL + url,
	    type: type,
	    success: responseHandler, 
        error: errorHandler
	}
	
	if (type == postRequest) {
		request['dataType'] = 'json';
		request['data'] = {json: jsonMsg};
	} 
	    
    $.ajax(request);
}



function newFileRequestInfo(filePath) {
	var requestInfo = newFileRequest(requestID, filePath, fileType);
	++requestID;
	return [newFileURL, newFileRequestType, requestInfo];	
}


function uploadFileRequestInfo(filePath, content) {
	var requestInfo = uploadFileRequest(requestID, filePath, content);
	++requestID;
	return [uploadFileURL, uploadFileRequestType, requestInfo];
}


