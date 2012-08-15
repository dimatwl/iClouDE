var ERROR_MESSAGE = '#FF0000';
var BUILD_MESSAGE = '#6495ED';
var RUN_MESSAGE = '#000000';
var COMMON_MESSAGE = '#228B22';


var addToConsole = function(message, messageType) {

	if (!messageType) {
    	messageType = COMMON_MESSAGE;
    }
    
	$('#messages').append('<font color="' + messageType + '">' + message + '</font></br>');    
    	
};