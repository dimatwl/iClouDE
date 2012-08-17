/**
 * On downloadProjectsList request - we download all available projects and build tree of projects, 
 * where we can select project to be downloaded
 * After selecting project structure is downloading to the client and represent as tree to user
 */

var prepareDownloadProjectList = function() {

Protocol.downloadProjectsList.request.setResponseHandler(function(data) {
    if (data.result) { 
        addToConsole('Project list downloaded.');
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
                             image : FOLDER_IMAGE_URL
                    	 },
		                 select_node: function(node) {
		                	 openProjectActivity(node.attr('projectID'));	
		                 }
		    	     }		    	     
		    	 }
		    }
		});
	    
        } else {
	    	addToConsole('Project list not downloaded. Description: ' + data.description);
	    }
});

};