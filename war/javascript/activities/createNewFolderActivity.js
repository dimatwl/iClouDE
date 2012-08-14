var createNewFolderActivity = function(node) {
	var folderName = prompt('Enter name: ', '');
    
	
	Protocol.createNewFolder.request.setResponseHandler(function(resp) {
		if (resp.result) {
			addToConsole('New folder ' + folderName + ' created');
			$("#tree").jstree("create", node, "inside",  { 
				data: folderName,
				attr: {
				    projectID: resp.projectID,
				    parentID: node.attr('entityID'),
				    entityID: resp.entityID,				 
				    type: 'folder'
				}			    
			});
		} else {
		    addToConsole('New folder ' + folderName + ' was not created. Description: ' + resp.description);
		}
		
	});

	Protocol.createNewFolder.request.setErrorHandler(function() {
        addToConsole('Error while requesting server!');	    	
	});
	
	Protocol.createNewFolder.request.send(folderName, node.attr('projectID'), node.attr('entityID'));	
};