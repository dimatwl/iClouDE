/**
 * On rigth mouse button New File option invoke this method
 * Creates new file on server and displays it on tree viewer
 */
var createNewFileActivity = function(node) {
	var fileName = prompt('Enter name: ', '');
    
	
	Protocol.createNewFile.request.setResponseHandler(function(resp) {
		if (resp.result) {
		    addToConsole('File ' + fileName + ' created');
			
		    $("#tree").jstree("create", node, "inside",  { 
				data: fileName,
				attr: {
				    projectID: resp.projectID,
				    parentID: node.attr('entityID'),
				    entityID: resp.entityID,			 
				    itemType: 'SOURCE_FILE'
				}			    
			});
		    
		    currentFileID = resp.entityID;
		    //alert('Create new file activity - file created! File ID: ' + currentFileID);
			
		} else {
		    addToConsole('File was not created. Description: ' + resp.description);
		}		
	});
	
	Protocol.createNewFile.request.setErrorHandler(function() {
		addToConsole('Error while requesting server!');
	});
	
	Protocol.createNewFile.request.send(fileName, "type", node.attr('projectID'), node.attr('entityID'));	

};
	