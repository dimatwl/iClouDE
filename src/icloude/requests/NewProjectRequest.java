package icloude.requests;

/**
 * @author DimaTWL This class describes 'New project' request from protocol.
 */
public class NewProjectRequest extends BaseRequest {
	private String projectID;
	private String projectType;

	private NewProjectRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the projectType
	 */
	public String getProjectType() {
		return projectType;
	}
}
