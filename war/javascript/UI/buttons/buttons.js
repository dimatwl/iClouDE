$(function() {
    $( "#new_file" ).button({
        text: false,
        icons: {
            primary: "ui-icon-document"
        }
    }).click(function() {
    	createNewFileActivity();
    });
    
    $( "#save_file" ).button({
        text: false,
        icons: {
            primary: "ui-icon-disk"
        }
    }).click(function() {
    	saveFileActivity(currentProjectID, currentFileID);
    });
    
    
    
    $( "#import_file" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-circle-arrow-n"
    	}
    });

    var importer = document.getElementById('import_file');

    upclick({
        element: importer,
        onstart: function(filename) {
            alert('Start upload: '+ filename);
        },
        oncomplete: function(response_data) 
        {
            alert(response_data);
        }
    });
    
    
    
    $( "#run" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-play"
    	}
    }).click(function() {
        runActivity();
    });

    
    $( "#build" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-wrench"
    	}
    }).click(function() {
    	buildActivity();
    });
    
    
    $( "#new_project" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-newwin"
    	}
    }).click(function() {
    	createNewProjectActivity();
    });
    
    
    
    $( "#open_project" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-folder-open"
    	}
    }).click(function() {
    	openProjectActivity();
    });
    
    $( "#downloadCode" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-arrowthick-1-s"
    	}
    }).click(function() {
    	downloadCodeActivity();
    });
    
    $( "#logout" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-power"
    	}
    }).click(function() {
    	logoutActivity();
    });

    
});