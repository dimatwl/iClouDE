package icloude.requests;

/**
 * @author DimaTWL This class describes 'Build project' request from protocol.
 * 
 */
public class BuildProjectRequest extends BaseRequest {
	private String projectID;
	private String parameters;

	private BuildProjectRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the parameters
	 */
	public String getParameters() {
		return parameters;
	}
}
