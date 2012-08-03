package icloude.requests;

/**
 * @author DimaTWL This class describes 'New file' request from protocol.
 */
public class NewFileRequest extends BaseRequest {
	private String projectID;
	private String parentID;
	private String fileName;
	private String fileType;

	private NewFileRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the parentID
	 */
	public String getParentID() {
		return parentID;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 * @param parentID
	 * @param fileName
	 * @param fileType
	 */
	public NewFileRequest(String requestID, String requestType, String userID,
			String projectID, String parentID, String fileName, String fileType) {
		super(requestID, requestType, userID);
		this.projectID = projectID;
		this.parentID = parentID;
		this.fileName = fileName;
		this.fileType = fileType;
	}


}
