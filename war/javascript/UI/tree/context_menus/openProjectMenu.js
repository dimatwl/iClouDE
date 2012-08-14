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