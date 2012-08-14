package icloude.frontend_backend_requests;

/**
 * @author DimaTWL This class describes general part of request from protocol.
 */
public abstract class BaseRequest {
	private Integer protocolVersion;
	private String requestID;
	private String requestType;
	private String userID;
	
	/**
	 * @return the protocolVersion
	 */
	public Integer getProtocolVersion() {
		return protocolVersion;
	}

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
	 * 
	 */
	protected BaseRequest() {
		super();
	}

	/**
	 * @param protocolVersion
	 * @param requestID
	 * @param requestType
	 * @param userID
	 */
	protected BaseRequest(Integer protocolVersion, String requestID,
			String requestType, String userID) {
		super();
		this.protocolVersion = protocolVersion;
		this.requestID = requestID;
		this.requestType = requestType;
		this.userID = userID;
	}
}
