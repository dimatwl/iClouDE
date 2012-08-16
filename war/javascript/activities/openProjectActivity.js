/**
 * Downloading project structure to client and 
 * displaying it in tree viewer
 */

var openProjectActivity = function(openingProjectID) {
	
	Protocol.downloadProjectStructure.request.setResponseHandler(function(response) {
	    if (response.result) {
	    	addToConsole('Project ' + response.content.projectName + ' opened.');
	    	
	    	var makeTree = function(item) {
	        	var childrenNodes = [];
	            
	        	for (i in item.children) {
	            	childrenNodes.push(makeTree(item.children[i]));
	            }
	        	
	            var node = {
	                data: item.itemName,
	                attr: {projectID: response.content.projectID, parentID: item.parentID, entityID: item.itemID, itemType: item.itemType},
	                children: childrenNodes,
	                //type: item.itemType
                };   
	            
	            return node;
	        };
	    	
	    	        
	        tree.jstree({
			    plugins: ["themes", "json_data", "ui", "crrm", "hotkeys", "contextmenu", "types"],
			    json_data: {data: makeTree(response.content.fileTree)},
			    contextmenu: {
			        items: projectStructureMenu    
			    },
			    /*
			    themes : { 
                    theme : "classic", 
                    dots : true, 
                    icons : true 
                },*/
                types : {
                	
                    types: projectItemTypes,               	
                	
                    type_attr: "itemType"
                }
                
                
                /*
                "start_drag" : false,
                "move_node" : false,
                "delete_node" : false,
                "remove" : false
                */
			});
	        
	        currentProjectID = response.content.projectID;
	    }
	    
	});
	
	Protocol.downloadProjectStructure.request.send(openingProjectID);
	
	
	
	
};