package icloude.frontend_backend_requests;

import icloude.frontend_backend_contents.FileContent;

/**
 * @author DimaTWL This class describes 'Autocomplete' request from protocol.
 * 
 */
public class AutocompleteRequest extends BaseRequest {
	private String projectID;
	private FileContent content;
	private Long caretPosition;

	private AutocompleteRequest() {
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
	 * @return the caretPosition
	 */
	public Long getCaretPosition() {
		return caretPosition;
	}

	/**
	 * @param protocolVersion
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 * @param content
	 * @param caretPosition
	 */
	public AutocompleteRequest(Integer protocolVersion, String requestID,
			String requestType, String userID, String projectID,
			FileContent content, Long caretPosition) {
		super(protocolVersion, requestID, requestType, userID);
		this.projectID = projectID;
		this.content = content;
		this.caretPosition = caretPosition;
	}



}
