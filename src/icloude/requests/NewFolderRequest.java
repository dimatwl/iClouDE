package icloude.requests;

/**
 * @author DimaTWL 
 * This class describes 'New folder' request from protocol.
 */
public class NewFolderRequest extends BaseRequest {
	private String projectID;
	private String parentID;
	private String folderName;

	private NewFolderRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the parentID
	 */
	public String getParentID() {
		return parentID;
	}

	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}

}
