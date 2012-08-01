var STANDART_RESPONSE_HANDLER = function() {alert("response OK!");};
var STANDART_ERROR_HANDLER = function() {alert("response gone sadly!");}; 

var POST = "POST";
var GET = "GET";



function Request(method, URL, type) {
    this.requestMethod = method;
    this.requestURL = URL;
    this.requestType = type;  
    
    this.setResponseHandler(STANDART_RESPONSE_HANDLER); 
    this.setErrorHandler(STANDART_ERROR_HANDLER);    
}


Request.prototype.sendMap = sendMap;
Request.prototype.setResponseHandler = setResponseHandler;
Request.prototype.setErrorHandler = setErrorHandler;

Request.prototype.SERVER_URL = "http://localhost:8888/";


function setResponseHandler(responseHandler) {
	this.responseHandler = responseHandler;
}

function setErrorHandler(errorHandler) {
	this.errorHandler = errorHandler;
}


function sendMap(requestMap) {
	var jsonMsg = $.toJSON(requestMap);
	
	var request = {
	    url: this.SERVER_URL + this.requestURL,
	    type: this.requestMethod,
	    success: this.responseHandler, 
        error: this.errorHandler
	}
	
	
	//if (this.requestMethod == POST) {
	request['dataType'] = 'json';
	request['data'] = {json: jsonMsg};
	//} 
	    
    $.ajax(request);
}



