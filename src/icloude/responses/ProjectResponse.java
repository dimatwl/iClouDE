/**
 * 
 */
package icloude.responses;

import icloude.contents.ProjectContent;

/**
 * @author DimaTWL
 * This class describes 'Project' response from protocol.
 * 
 */
public class ProjectResponse extends BaseResponse {

	private ProjectContent content;

	/**
	 * @param requestID
	 * @param result
	 * @param description
	 * @param content
	 */
	public ProjectResponse(String requestID, Boolean result,
			String description, ProjectContent content) {
		super(requestID, result, description);
		this.content = content;
	}
	
	private ProjectResponse() {
	}

	/**
	 * @return the content
	 */
	public ProjectContent getContent() {
		return content;
	}

}
