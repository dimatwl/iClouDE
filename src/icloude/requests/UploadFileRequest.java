/**
 * 
 */
package icloude.requests;

import icloude.contents.FileContent;

/**
 * @author DimaTWL This class describes 'Upload file' request from protocol.
 * 
 */
public class UploadFileRequest extends BaseRequest {
	private String projectID;
	private FileContent content;

	private UploadFileRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}


	/**
	 * @return the content
	 */
	public FileContent getContent() {
		return content;
	}

	/**
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 * @param content
	 */
	public UploadFileRequest(String requestID, String requestType,
			String userID, String projectID, FileContent content) {
		super(requestID, requestType, userID);
		this.projectID = projectID;
		this.content = content;
	};
}
