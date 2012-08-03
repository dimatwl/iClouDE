package icloude.requests;

/**
 * @author DimaTWL This class describes 'Delete file' request from protocol.
 * 
 */
public class DeleteFileRequest extends BaseRequest {
	private String projectID;
	private String fileID;

	private DeleteFileRequest() {
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
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 * @param fileID
	 */
	public DeleteFileRequest(String requestID, String requestType,
			String userID, String projectID, String fileID) {
		super(requestID, requestType, userID);
		this.projectID = projectID;
		this.fileID = fileID;
	}

}
