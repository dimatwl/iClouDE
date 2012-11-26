package icloude.backend_buildserver.responses;

/**
 * @author DimaTWL 
 * This class describes general part of response from protocol.
 */
public class BaseResponse {
	private Boolean result;
	private String description;
	

	/**
	 * 
	 */
	protected BaseResponse() {
		super();
	}

	/**
	 * @return the result
	 */
	public Boolean getResult() {
		return result;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
