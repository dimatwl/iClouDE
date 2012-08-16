package icloude.backend_amazon.requests;

public class BaseRequest {
	private Integer protocolVersion;
	private String zipID;
	
	/**
	 * @param protocolVersion
	 * @param requestID
	 */
	public BaseRequest(Integer protocolVersion, String zipID) {
		super();
		this.protocolVersion = protocolVersion;
		this.zipID = zipID;
	}
}
