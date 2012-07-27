$(document).ready(function() {
    $('#checkNewFileBtn').click(function() {
    		
            sendRequest(newFileRequestInfo("project/sample/start.java"),
            function(data) {
            	standartResponseHandler(data, "New file")
            },
            function() {
                alert("New file error!");
            });
    });
    
    $('#checkUploadFileBtn').click(function() {
		
        sendRequest(uploadFileRequestInfo("project/sample/start.java", "content"),
        function(data) {
    		standartResponseHandler(data, "Upload file");
        },
        function() {
            alert("Upload file error!");
        });
    });
    
    
});