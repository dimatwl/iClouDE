package icloude.backend_amazon.responses;

/**
 * @author DimaTWL 
 * This class describes general part of response from protocol.
 */
public class BaseResponse {
	private Boolean result;
	private String description;
	
	/**
	 * @param result
	 * @param description
	 */
	public BaseResponse(Boolean result, String description) {
		super();
		this.result = result;
		this.description = description;
	}

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
