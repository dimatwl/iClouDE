package icloude.frontend_backend.requests;

/**
 * @author DimaTWL This class describes 'Delete file' request from protocol.
 * 
 */
public class DeleteProjectRequest extends BaseRequest {
	private String projectID;

	private DeleteProjectRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @param protocolVersion
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 */
	public DeleteProjectRequest(Integer protocolVersion, String requestID,
			String requestType, String userID, String projectID) {
		super(protocolVersion, requestID, requestType, userID);
		this.projectID = projectID;
	}


}
