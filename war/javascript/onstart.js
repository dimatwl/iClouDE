var userID = 1;
var projectID = 1;






$(document).ready(function() {
    

	
	
	$('#CreateNewFileBtn').click(function() {
		var filePath = prompt('Enter path: ', '');
		Protocol.createNewFile.request.send(filePath, "file type");
    });
    
    $('#SaveFileBtn').click(function() {
    	
    	var testFileContent = {
    	    type: 'file',
    	    filePath: 'project/sample.start.java',
    	    text: 'some text ya!',
    	    fileType: 'what is filetype?',
    	    revisionID: 'revision ID',
    	    creationDate: (new Date()).getTime(),
            modificationDate: (new Date()).getTime(),
            size: '1000'
    	};
		
    	    	
    	Protocol.uploadFile.request.send(testFileContent);
    	
    });
    
    
    $('#CreateNewProjectBtn').click(function() {
    	var projectName = prompt('Enter project name', '');
    	Protocol.createNewProject.request.send(projectName, "web-appl");    	
    });
    
    $('#DownloadProjectStructureBtn').click(function() {
    	var projectID = prompt('Enter project ID: ');
    	Protocol.downloadProjectStructure.request.send(projectID);    	
    });
    
    $('#DownloadFileBtn').click(function() {
    	var filePath = prompt ('Enter file path to download');
    	Protocol.downloadFile.request.send(filePath);
    });
    
    
    
    
    
    
    
    
    
    
    
    
});