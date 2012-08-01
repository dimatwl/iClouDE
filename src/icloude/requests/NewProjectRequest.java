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
}
