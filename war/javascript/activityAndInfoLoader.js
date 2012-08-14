var userID = "Yarik";
var projectID;
var currentFileID;
var rootProjectDirectory;
var projectName;
var tree;

var scriptsPath = "javascript/activities/";

loadJS(scriptsPath + "createNewProjectActivity.js");
loadJS(scriptsPath + "createNewFolderActivity.js");
loadJS(scriptsPath + "downloadFileActivity.js");
loadJS(scriptsPath + "logoutActivity.js");
loadJS(scriptsPath + "createNewFileActivity.js");
loadJS(scriptsPath + "saveFileActivity.js");
loadJS(scriptsPath + "buildActivity.js");
loadJS(scriptsPath + "runActivity.js");
loadJS(scriptsPath + "openProjectActivity.js");
loadJS(scriptsPath + "importFileActivity.js");
loadJS(scriptsPath + "downloadCodeActivity.js");


$(document).ready(function() {
    tree = $('#tree');	
});