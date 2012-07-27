package icloude.requests;

/**
 * @author DimaTWL
 * This class describes 'New file' request from protocol.
 */
public class NewFileRequest {
	private String requestID;
	private String requestType;
	private String userID;
	private String projectID;
	private String filePath;
	private String fileType;
	
	private NewFileRequest(){}

	/**
	 * @return the requestID
	 */
	public String getRequestID() {
		return requestID;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}; 
}
