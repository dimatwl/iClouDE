var openProjectActivity = function(openingProjectID) {
	alert('Opening activity' + openingProjectID);
	
	
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
	                attr: {projectID: response.content.projectID, parentID: item.parentID, itemID: item.itemID, itemType: item.itemType},
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
                	types: {
                		'default' : {
                		    icon : {
                                image : "http://static.jstree.com/v.1.0pre/_demo/folder.png"
                        	}
                		},
                		
                		'SOURCE_FILE' : {
                		    valid_children: "none",
                		    icon: {
                		        image : "http://static.jstree.com/v.1.0pre/_demo/file.png"
                		    },
                		    select_node: function(node) {
                		        downloadFileActivity(node.attr('projectID'), node.attr('itemID'));	
                		    }
                		},
                        
                        'FOLDER' : {
                        	valid_children: ["default", "folder"],
                        	icon : {
                                image : "http://static.jstree.com/v.1.0pre/_demo/folder.png"
                        	}
                        },
                        
                        'PACKAGE' : {
                        	valid_children: ["default", "folder"],
                        	icon : {
                                image : "/static/v.1.0pre/_demo/folder.png"
                        	}	
                        }
                	},
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