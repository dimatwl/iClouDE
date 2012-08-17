/**
 * Function returning items to display, depending on what have we clicked - file or folder
 * @param node - item on what we clicked on right mouse button
 * @returns object with items to display on context menu.
 */

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
        		downloadFileActivity(node.attr('projectID'), node.attr('entityID'));
        	}
        },
        importItem: {
        	label: "Import file",
        	action: function() {
        		alert("Importing!");
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
        delete items.importItem;
    }
    

    return items;
};