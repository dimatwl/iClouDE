/**
 * Requests
 */
const postRequest = "POST";
const getRequest = "GET";


/**
 * Protocols URL and request types
 */
function basicInfo(requestID, userID, pojectID, requestTypeID) {
    return {
    	requestID: requestID,
        userID: userID,
    	projectID: projectID,
    	requestType: requestTypeID
    };	
}


const standartResponse = ['requestID', 'result', 'description'];



/**
 * New file request
 */
const newFileURL = "rest/newfile";
const newFileRequestType = postRequest;
const newFileRequestID = "newfile";

function newFileRequest(requestID, filePath, fileType) {
	var newFileInfo = basicInfo(requestID, userID, projectID, newFileRequestID);
	
	newFileInfo['filePath'] = filePath;
    newFileInfo['fileType'] = fileType;
	
    return newFileInfo;	
}





const newFileResponseFields = standartResponse;

/*


function newFileResponseChecker(response) {
	var correctInfo = correctInfo(response, newFileResponseFields);
	if (correctInfo[0])
		return true;
	alert("New file response not correct!\n" + correctInfo[1]);
	return false;	
}
*/





/**
 * Upload file request
 */
const uploadFileURL = "rest/uploadfile";
const uploadFileRequestType = postRequest;
const uploadFileRequestTypeID = "uploadfile";

function uploadFileRequest(requestID, filePath, content) {
	var uploadFileInfo = basicInfo(requestID, userID, projectID, uploadFileRequestTypeID);
	
	uploadFileInfo['filePath'] = filePath;
    uploadFileInfo['content'] = content;
	
    return uploadFileInfo;	
}

const uploadFileResponseFields = standartResponse;













/**
 * Checks if all fields exists
 * @param response
 * @param responseFields
 * @returns {Array} tuple with boolean result and field name which was not found
 */
function correctInfo(response, responseFields) {
	for (i = 0; i < responseFields.length; ++i) {
		if (response[responseFields[i]] == null)
			return [false, responseField[i]];
	}
	return [true, ''];	
}





