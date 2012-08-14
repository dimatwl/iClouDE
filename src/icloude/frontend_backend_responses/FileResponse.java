package icloude.frontend_backend_responses;

import icloude.frontend_backend_contents.FileContent;

/**
 * @author DimaTWL 
 * This class describes 'File' response from protocol.
 */
public class FileResponse extends BaseResponse {

	private FileContent content;

	/**
	 * @param requestID
	 * @param result
	 * @param description
	 * @param content
	 */
	public FileResponse(String requestID, Boolean result, String description,
			FileContent content) {
		super(requestID, result, description);
		this.content = content;
	}

	
	private FileResponse() {
	}


	/**
	 * @return the content
	 */
	public FileContent getContent() {
		return content;
	}
}
