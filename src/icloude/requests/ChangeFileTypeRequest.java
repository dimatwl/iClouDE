package icloude.requests;

/**
 * @author DimaTWL This class describes 'Change file type' request from
 *         protocol.
 * 
 */
public class ChangeFileTypeRequest extends BaseRequest {
	private String projectID;
	private String fileID;
	private String newFileType;

	private ChangeFileTypeRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the fileID
	 */
	public String getFileID() {
		return fileID;
	}

	/**
	 * @return the newFileType
	 */
	public String getNewFileType() {
		return newFileType;
	}

	/**
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 * @param fileID
	 * @param newFileType
	 */
	public ChangeFileTypeRequest(String requestID, String requestType,
			String userID, String projectID, String fileID, String newFileType) {
		super(requestID, requestType, userID);
		this.projectID = projectID;
		this.fileID = fileID;
		this.newFileType = newFileType;
	}

}
