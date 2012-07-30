package icloude.requests;


/**
 * @author DimaTWL
 * This class describes 'Change file type' request from protocol.
 *
 */
public class ChangeFileTypeRequest extends BaseRequest {
	private String projectID;
	private String filePath;
	private String newFileType;

	private ChangeFileTypeRequest() {
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

	/**
	 * @return the newFileType
	 */
	public String getNewFileType() {
		return newFileType;
	}
	
	

}
