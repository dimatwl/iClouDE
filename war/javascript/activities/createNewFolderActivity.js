var createNewFolderActivity = function(node) {
	var folderName = prompt('Enter name: ', '');
    
	
	Protocol.createNewFolder.request.setResponseHandler(function(resp) {
		if (resp.result) {
			$("#tree").jstree("create", node, "inside",  { 
				data: folderName,
				attr: {
				    projectID: resp.projectID,
				    parentID: node.attr('entityID'),
				    entityID: resp.entityID				 
			    },
			    type: 'folder'
			});
		} else {
		    alert("request was bad!");
		}
		
	});
	
	Protocol.createNewFolder.request.send(folderName, node.attr('projectID'), node.attr('entityID'));	
};