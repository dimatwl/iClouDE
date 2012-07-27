package icloude.requests;

/**
 * @author DimaTWL
 * This class describes 'New file' request from protocol.
 */
public class NewFileRequest extends BaseRequest {
	private String projectID;
	private String filePath;
	private String fileType;
	
	private NewFileRequest(){}

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

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}; 
}
