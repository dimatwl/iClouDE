$(function () {
    $("#sidebar").jstree({
        "plugins" : ["themes","json_data","ui","crrm","hotkeys"],
        "json_data" : {
        	"data" : [
                 {
                	 "data" : "directory1",
                	 "attr" : {"key" : "ABCDE"},
                	 "children" : ["file1", "file2", {"attr" : {"key" : "QWER"}, "data" : "directory2", "children" : ["file5", "file6"] }]
                 },
            
                 {
                 	 "data" : "file3"
                 }]
                  
        }
    })
        
        .bind("select_node.jstree", function (event, data) {
        	alert(data.rslt.obj.attr("key"));
        })

        .bind("loaded.jstree", function (event, data) {
        	alert("loaded.jstree");
        });

});