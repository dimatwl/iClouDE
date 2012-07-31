package icloude.requests;

/**
 * @author DimaTWL
 * This class describes 'Download file' request from protocol.
 *
 */
public class DownloadFileRequest extends BaseRequest {
	private String projectID;
	private String filePath;

	private DownloadFileRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

}