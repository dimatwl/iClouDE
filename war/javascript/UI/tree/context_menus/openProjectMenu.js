/**
 * Open project right button context menu
 * @param node project on what we pressed right button
 * @returns object with items to be pictured in menu and actions, connected with them
 */

function openProjectMenu(node) {
    var items = {
        openItem: {
           	label: "Open",
           	action: function() {
           		openProjectActivity(node.attr('projectID'));
           	}
        },
        deleteItem: {
        	label: "Delete",
        	action: function() {
        	    alert("Deleting!");	
        	}
        }        
    };

    return items;
};