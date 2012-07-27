/**
 * 
 */
package icloude.requests;

import icloude.contents.FileContent;

/**
 * @author DimaTWL This class describes 'New file' request from protocol.
 * 
 */
public class UploadFileRequest extends BaseRequest {
	private String projectID;
	private String filePath;
	private String fileType;
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
	}

	/**
	 * @return the content
	 */
	public FileContent getContent() {
		return content;
	};
}
