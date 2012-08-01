package icloude.requests;

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
}
