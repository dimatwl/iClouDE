function projectStructureMenu(node) {
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
                createNewFolderActivity(node);
            }
        },
        openItem: {
        	label: "Open",
        	action: function() {
        		downloadFileActivity(node.attr('projectID'), node.attr('itemID'));
        	}
        },
        deleteItem: {
        	label: "Delete",
        	action: function() {
        	    alert("Deleting!");	
        	}
        }
    }
	
    if (node.attr('itemType') != 'SOURCE_FILE') {
        delete items.openItem;        
    }
    
    if (node.attr('itemType') == 'SOURCE_FILE') {
        delete items.newFileItem;
        delete items.newFolderItem;
    }
    

    return items;
};