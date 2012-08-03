package icloude.requests;

/**
 * @author DimaTWL 
 * This class describes 'Download code' request from protocol.
 * 
 */
public class DownloadCodeRequest extends BaseRequest {
	private String projectID;

	private DownloadCodeRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 */
	public DownloadCodeRequest(String requestID, String requestType,
			String userID, String projectID) {
		super(requestID, requestType, userID);
		this.projectID = projectID;
	}
}
