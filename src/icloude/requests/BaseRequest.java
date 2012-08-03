package icloude.requests;

/**
 * @author DimaTWL This class describes general part of request from protocol.
 */
public abstract class BaseRequest {
	private String requestID;
	private String requestType;
	private String userID;

	/**
	 * @return the requestID
	 */
	public String getRequestID() {
		return requestID;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param requestID
	 * @param requestType
	 * @param userID
	 */
	public BaseRequest(String requestID, String requestType, String userID) {
		super();
		this.requestID = requestID;
		this.requestType = requestType;
		this.userID = userID;
	}

	/**
	 * 
	 */
	protected BaseRequest() {
		super();
	}
}
