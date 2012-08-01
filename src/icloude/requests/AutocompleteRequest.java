package icloude.requests;

import icloude.contents.FileContent;

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

}
