var saveFileActivity = function(projectID, fileID) {
	alert("saveFileActivity");
	
    var testFileContent = {
        type: 'file',
        fileID: fileID,
        text: editor.getValue(),
        fileType: 'file',
        revisionID: 'revision ID',
        creationDate: (new Date()).getTime(),
        modificationDate: (new Date()).getTime(),
        size: '100'
    };
		

    Protocol.uploadFile.request.send(projectID, testFileContent);
};