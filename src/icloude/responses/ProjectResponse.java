/**
 * 
 */
package icloude.responses;

import icloude.contents.ProjectContent;

/**
 * @author DimaTWL
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

}
