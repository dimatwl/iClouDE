$(document).ready(function() {
	
	
	Protocol.downloadProjectsList.request.setResponseHandler(function(data) {
		arr = [];
		for (obj in data.content) {
		    arr[obj.name] = obj.value;
		}
		
		var str = "";
		for (i in arr)
			str += i.projectName;
		
	    alert(arr.length);	
				
	    /*
		$('#tree').jstree({
		    plugins: ["themes", "json_data", "ui", "crrm", "hotkeys", "contextmenu"],
		    json_data: arr,
		    contextmenu: {
		        items: customMenu    
		    }
		});
		*/		
	
	});
	
	
	Protocol.downloadProjectsList.request.send(userID);
	
	
	/*
	
	$('#tree').jstree({
	    plugins: ["themes", "json_data", "ui", "crrm", "hotkeys", "contextmenu"],
	    json_data: data;
	    contextmenu: {
	        items: customMenu    
	    }
	});
	
	*/
	
});