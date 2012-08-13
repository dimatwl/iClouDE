





$(document).ready(function() {
    Protocol.downloadProjectsList.request.setResponseHandler(function(data) {
	    arr = [];
	    for (i in data.content) {
	        var tmp = data.content[i];
	    	obj = {
	    	    data: tmp.projectName,
	    	    attr: {type: 'file', projectID: tmp.projectID}	    	    		
	    	} 	
	    	arr.push(obj);       
	    }		
	    
	    $('#tree').jstree({
		    plugins: ["themes", "json_data", "ui", "crrm", "hotkeys", "contextmenu", "types"],
		    json_data: {data: arr},
		    contextmenu: {
		        items: openProjectMenu    
		    },
		    types: {
		    	types: {
		    	    'default': {
		    	    	icon : {
                            image : "http://static.jstree.com/v.1.0pre/_demo/folder.png"
                    	},
		                select_node: function(node) {
		                	openProjectActivity(node.attr('projectID'));	
		                }
		    	    }
		    	}
		    }
		});
	});
    
	Protocol.downloadProjectsList.request.send(userID);
});