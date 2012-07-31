/**
 * 
 */
package icloude.responses;

import icloude.contents.ProjectListEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DimaTWL This class describes 'Project list' response from protocol.
 */
public class ProjectListResponse extends BaseResponse {

	private List<ProjectListEntry> content = new ArrayList<ProjectListEntry>();

	/**
	 * @param requestID
	 * @param result
	 * @param description
	 */
	public ProjectListResponse(String requestID, Boolean result,
			String description, List<ProjectListEntry> content) {
		super(requestID, result, description);
		this.content = content;
	}

}
