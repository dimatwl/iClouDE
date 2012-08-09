var saveFileActivity = function() {
	alert("saveFileActivity");
	
    var testFileContent = {
        type: 'file',
        fileID: currentFileID,
        text: editor.getValue(),
        fileType: 'what is filetype?',
        revisionID: 'revision ID',
        creationDate: (new Date()).getTime(),
        modificationDate: (new Date()).getTime(),
        size: '1000'
    };
		

    Protocol.uploadFile.request.send(testFileContent);
};