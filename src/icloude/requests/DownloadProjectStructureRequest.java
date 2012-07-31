package icloude.requests;

/**
 * @author DimaTWL
 * This class describes 'Download project structure' request from protocol.
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
}
