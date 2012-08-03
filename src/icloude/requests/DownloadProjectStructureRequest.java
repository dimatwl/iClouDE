package icloude.requests;

/**
 * @author DimaTWL This class describes 'Download project structure' request
 *         from protocol.
 * 
 */
public class DownloadProjectStructureRequest extends BaseRequest {
	private String projectID;

	private DownloadProjectStructureRequest() {
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
	public DownloadProjectStructureRequest(String requestID,
			String requestType, String userID, String projectID) {
		super(requestID, requestType, userID);
		this.projectID = projectID;
	}
}
