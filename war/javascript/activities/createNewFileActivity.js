var createNewFileActivity = function() {
	alert("createNewFile Activity launched!");
	var fileName = prompt('Enter name: ', '');
	Protocol.createNewFile.request.send(fileName);	
};