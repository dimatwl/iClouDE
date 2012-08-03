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

	/**
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 * @param parentID
	 * @param folderName
	 */
	public NewFolderRequest(String requestID, String requestType,
			String userID, String projectID, String parentID, String folderName) {
		super(requestID, requestType, userID);
		this.projectID = projectID;
		this.parentID = parentID;
		this.folderName = folderName;
	}

}
