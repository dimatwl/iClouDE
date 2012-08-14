package icloude.frontend_backend_requests;

/**
 * @author DimaTWL This class describes 'Download file' request from protocol.
 * 
 */
public class DownloadFileRequest extends BaseRequest {
	private String projectID;
	private String fileID;

	private DownloadFileRequest() {
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
	 * @param protocolVersion
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 * @param fileID
	 */
	public DownloadFileRequest(Integer protocolVersion, String requestID,
			String requestType, String userID, String projectID, String fileID) {
		super(protocolVersion, requestID, requestType, userID);
		this.projectID = projectID;
		this.fileID = fileID;
	}



}