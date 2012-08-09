function customMenu(node) {
    var items = {
        newFileItem: {
            label: "New File",
            action: function() {
                createNewFileActivity(node);            	
            }
        },
        newFolderItem: {
            label: "New Folder",
            action: function() {
                alert("Creating new folder!");
            }
        },
        deleteItem: {
        	label: "Delete",
        	action: function() {
        	    alert("Deleting!");	
        	}
        },
        openItem: {
        	label: "Open",
        	action: function() {
        		alert("Opening!");
        	}
        }
    }
	
    if (node.attr('type') == 'folder') {
        delete items.openItem;        
    }
    
    if (node.attr('type') == 'file') {
        delete items.newFileItem;
        delete items.newFolderItem;
    }
    

    return items;
};



/*
$(function () {
    
	$("#tree").jstree({
        "plugins" : ["themes", "json_data", "ui", "crrm", "hotkeys", "contextmenu"],
        "json_data" : {
        	"data" : [
                 {
                	 data: "directory1",
                	 attr: {key: "ABCDE", type: "folder"},
                	 children: [{data: "file1", attr: {type: 'file'}}, {data: "file2", attr: {type: 'file'}}, {attr : {key : "QWER", type: "folder"}, data: "directory2", type: "folder" }]
                 },
            
                 {
                 	 data: "file3",
                     type: "file"
                 }]
                  
        },
        
        
        'contextmenu' : {
    	    'items' : customMenu    
        }    	
    
    })
        
        .bind("select_node.jstree", function (event, data) {
        	alert(data.rslt.obj.attr("key"));
        });
    
        
}); */