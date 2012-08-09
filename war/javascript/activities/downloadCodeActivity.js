var downloadCodeActivity = function() {
    alert("downloading!! " + projectID);
    Protocol.downloadCode.request.send(projectID);
    
}