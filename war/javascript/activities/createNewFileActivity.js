var createNewFileActivity = function(node) {
	var fileName = prompt('Enter name: ', '');
    
	
	Protocol.createNewFile.request.setResponseHandler(function(resp) {
		alert('Response ok!!!');
		if (resp.result) {
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
		    alert("request was bad!");
		}
		
	});
	
	Protocol.createNewFile.request.send(fileName, "type", node.attr('projectID'), node.attr('entityID'));	
};