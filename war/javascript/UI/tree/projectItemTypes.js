projectItemTypes = {
	'default' : {
	    icon : {
            image : FOLDER_IMAGE_URL
       	}
	},
		
	'SOURCE_FILE' : {
	    valid_children: "none",
	    icon: {
	        image : FILE_IMAGE_URL
	    },
	    select_node: function(node) {
	        downloadFileActivity(node.attr('projectID'), node.attr('entityID'));	
	    }
	},
       
   'FOLDER' : {
      	valid_children: ["default", "folder"],
       	icon : {
            image : FOLDER_IMAGE_URL
       	}
    },
        
    'PACKAGE' : {
       	valid_children: ["default", "folder"],
       	icon : {
           image : FOLDER_IMAGE_URL
       	}	
    }
}
