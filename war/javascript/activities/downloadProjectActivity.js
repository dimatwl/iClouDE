var downloadProjectActivity = function() {
	alert("downloadProjectActivity");
	var projectID = prompt('Enter project ID: ');
	Protocol.downloadProjectStructure.request.send(projectID);    	
};