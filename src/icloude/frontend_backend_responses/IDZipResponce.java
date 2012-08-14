package icloude.frontend_backend_responses;

/**
 * @author DimaTWL This class describes 'ID' response from protocol.
 */
public class IDZipResponce  {

	private String projectID;
	private String entityID;

	private IDZipResponce() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the entityID
	 */
	public String getEntityID() {
		return entityID;
	}

	/**
	 * @param requestID
	 * @param result
	 * @param description
	 * @param projectID
	 * @param entityID
	 */
	public IDZipResponce(String requestID, Boolean result, String description,
			String projectID, String entityID) {
		super(requestID, result, description);
		this.projectID = projectID;
		this.entityID = entityID;
	}

}
