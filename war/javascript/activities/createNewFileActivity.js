var createNewFileActivity = function(node) {
	var fileName = prompt('Enter name: ', '');
    
	
	Protocol.createNewFile.request.setResponseHandler(function(resp) {
		if (resp.result) {
		    addToConsole('File ' + fileName + 'created');
			$("#tree").jstree("create", node, "inside",  { 
				data: fileName,
				attr: {
				    projectID: resp.projectID,
				    parentID: node.attr('entityID'),
				    entityID: resp.entityID,			 
				    type: 'file'
				}			    
			});
		} else {
		    addToConsole('File was not created. Description: ' + resp.description);
		}		
	});
	
	Protocol.createNewFile.request.setErrorHandler(function() {
		addToConsole('Error while requesting server!');
	});
	
	Protocol.createNewFile.request.send(fileName, "type", node.attr('projectID'), node.attr('entityID'));	
};