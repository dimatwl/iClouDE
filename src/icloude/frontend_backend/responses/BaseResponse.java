package icloude.frontend_backend.responses;

/**
 * @author DimaTWL 
 * This class describes general part of response from protocol.
 */
public class BaseResponse {
	private String requestID;
	private Boolean result;
	private String description;

	/**
	 * @param requestID
	 * @param result
	 * @param description
	 */
	public BaseResponse(String requestID, Boolean result, String description) {
		super();
		this.requestID = requestID;
		this.result = result;
		this.description = description;
	}
	

	protected BaseResponse() {
	}


	/**
	 * @return the requestID
	 */
	public String getRequestID() {
		return requestID;
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
