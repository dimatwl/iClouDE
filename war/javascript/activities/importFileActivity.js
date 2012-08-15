var uploader = document.getElementById('import_file');

upclick({
    element: uploader,
    onstart: function(filename) {
        alert('Start upload: '+ filename);
    },
    oncomplete: function(response_data) 
    {
        alert(response_data);
    }
});


var importFileActivity = function() {
	alert("Importting file activity launched!");	
};