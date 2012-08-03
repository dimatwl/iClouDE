package icloude.requests;

/**
 * @author DimaTWL This class describes 'New project' request from protocol.
 */
public class NewProjectRequest extends BaseRequest {
	private String projectName;
	private String projectType;

	private NewProjectRequest() {
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @return the projectType
	 */
	public String getProjectType() {
		return projectType;
	}

	/**
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectName
	 * @param projectType
	 */
	public NewProjectRequest(String requestID, String requestType,
			String userID, String projectName, String projectType) {
		super(requestID, requestType, userID);
		this.projectName = projectName;
		this.projectType = projectType;
	}
}
