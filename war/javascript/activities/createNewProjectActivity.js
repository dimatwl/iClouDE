/**
 * Create new project option
 */
var createNewProjectActivity = function() {
	//alert("createNewProjectActivity");
	projectName = prompt('Enter project name', '');
	
	Protocol.createNewProject.request.setResponseHandler(function(resp) {
	    if (resp.result) {
	        addToConsole('New project ' + projectName + ' was succesfully created!');    
			projectID = resp.projectID;
	        rootProjectDirectory = resp.entityID;	    
            $('#tree').jstree({
            		plugins : ["themes", "json_data", "ui", "crrm", "hotkeys", "contextmenu", "types"],
                    json_data : {
                    	data : {
                	        data: projectName,
                	        attr: {entityID: rootProjectDirectory, projectID: projectID, itemType: 'FOLDER'},            	    
                        }
                    },                   
                    types : {
                        types: projectItemTypes,               	
                        type_attr: "itemType"
                    },               
                    contextmenu : {
                	    items : projectStructureMenu    
                    }
            });
            
        } else {
	    	addToConsole('New project was not created. Description: ' + resp.description);
	    }
	
	});
	
	
	Protocol.createNewProject.request.send(projectName, "web-appl");    	


};   	