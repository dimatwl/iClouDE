function standartResponseHandler(response, msg) {
	//var t = response.ququ;
	//alert(t==null);
	//alert("requestID: " + response.requestID + "\n result: " + response.result + "\n description: " + response.description);    	                            
	alert(msg + ' response is correct: ' + correctInfo(response, newFileResponseFields)[0] + '\n requestID: ' + response.requestID);
}



