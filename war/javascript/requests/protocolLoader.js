//alert("protocol Loader!");

/**
 * Requests loader
 */


var requestsPath = "javascript/requests/protocol/";

loadJS(requestsPath + "createNewFile.js");
loadJS(requestsPath + "createNewFolder.js");
loadJS(requestsPath + "createNewProject.js");
loadJS(requestsPath + "downloadFile.js");
loadJS(requestsPath + "downloadProjectStructure.js");
loadJS(requestsPath + "uploadFile.js");
loadJS(requestsPath + "downloadCode.js");
loadJS(requestsPath + "downloadProjectsList.js");
loadJS(requestsPath + "buildAndRun.js");
loadJS(requestsPath + "getResults.js");

//alert('all loaded!');