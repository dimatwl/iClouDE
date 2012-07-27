/**
 * Sending update file requests interval
 */
const interval = 1000;



/**
 * Additional file info
 */
const fileType = "some extra info";



/**
 * Requests
 */
const postRequest = "POST";
const getRequest = "GET";



/**
 * Protocols URL and request types
 */
const newFileURL = "rest/newfile";
const newFileRequestType = postRequest;






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
	var newFileInfo = {
        requestID: requestID,
        requestType: "newfile",
        userID: userID,
        projectID: projectID,
        filePath: filePath,
        fileType: fileType
	};
	++requestID;
    return [newFileURL, newFileRequestType, newFileInfo];	
}


/**
 * Success handler for new file request
 * @param response
 */
function newFileResponseHandler(response) {
	alert("requestID: " + response.requestID + "\n result: " + response.result + "\n description: " + response.description);    	                            
}







$(document).ready(function() {
    
        
    $('#checkNewFileBtn').click(function() {
    		
            sendRequest(newFileRequestInfo("project/sample/start.java"),
            newFileResponseHandler,
            function() {
                alert("error!");
            })
    });
    
    
    
    
    
    /*
    setInterval(function() {
        post($('#codeArea').val());	
    }, interval);
    */
    
});
