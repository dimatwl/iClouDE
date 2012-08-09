var downloadFileActivity = function() {
	alert("downloadFileActivity");
	var fileID = currentFileID;
	Protocol.downloadFile.request.send(fileID);
};
