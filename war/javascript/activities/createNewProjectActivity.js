var createNewProjectActivity = function() {
	alert("createNewProjectActivity");
	projectName = prompt('Enter project name', '');
	
	Protocol.createNewProject.request.setResponseHandler(function(resp) {
	    alert("NEw response!");
		if (resp.result) {
	        projectID = resp.projectID;
	        rootProjectDirectory = resp.entityID;	    
            $('#tree').jstree({
            		plugins : ["themes", "json_data", "ui", "crrm", "hotkeys", "contextmenu"],
                    json_data : {
                    	data : {
                	        data: projectName,
                	        attr: {entityID: rootProjectDirectory, projectID: projectID},            	    
                            type: "folder"
                        }       
                    },                   
                    
                    contextmenu : {
                	    items : customMenu    
                    }
            });
            
        } else {
	    	alert("Response result gone sadly!");
	    }
	
	});
	
	
	Protocol.createNewProject.request.send(projectName, "web-appl");    	


};	
    	