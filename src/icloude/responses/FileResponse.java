/**
 * 
 */
package icloude.responses;

import icloude.contents.FileContent;

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
	 */
	public FileResponse(String requestID, Boolean result, String description, FileContent content) {
		super(requestID, result, description);
		this.content = content;
	}

}
