var createNewProjectActivity = function() {
	alert("createNewProjectActivity");
	var projectName = prompt('Enter project name', '');
	Protocol.createNewProject.request.send(projectName, "web-appl");    	
};	
    	