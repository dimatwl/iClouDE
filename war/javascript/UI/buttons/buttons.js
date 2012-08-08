$(function() {
    $( "#new_file" ).button({
        text: false,
        icons: {
            primary: "ui-icon-document"
        }
    });
    
    $( "#save_file" ).button({
        text: false,
        icons: {
            primary: "ui-icon-disk"
        }
    });
    
    $( "#import_file" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-circle-arrow-n"
    	}
    })
    
            .click(function() {
                alert("Importing!!!");
            });

    
    $( "#run" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-play"
    	}
    })
            .click(function() {
                alert("Running!!!");
            });

    
    $( "#build" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-wrench"
    	}
    });
    
    
    $( "#new_project" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-newwin"
    	}
    });
    
    
    
    $( "#open_project" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-folder-open"
    	}
    });
    
    
    $( "#logout" ).button({
    	text: false,
    	icons: {
    		primary: "ui-icon-power"
    	}
    });

    
});