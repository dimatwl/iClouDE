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
		    plugins: ["themes", "json_data", "ui", "crrm", "hotkeys", "contextmenu"],
		    json_data: {data: arr},
		    contextmenu: {
		        items: customMenu    
		    }
		});
	});
    
	Protocol.downloadProjectsList.request.send(userID);
});