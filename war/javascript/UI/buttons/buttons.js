


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
    	saveFileActivity();
    });
    
    $( "#import_file" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-circle-arrow-n"
    	}
    }).click(function() {
        importFileActivity();
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
    
    
    $( "#logout" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-power"
    	}
    }).click(function() {
    	logoutActivity();
    });

    
});