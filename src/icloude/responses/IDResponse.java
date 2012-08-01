package icloude.responses;

/**
 * @author DimaTWL 
 * This class describes 'ID' response from protocol.
 */
public class IDResponse extends BaseResponse {

	private String id;

	/**
	 * @param requestID
	 * @param result
	 * @param description
	 * @param id
	 */
	public IDResponse(String requestID, Boolean result, String description, String id) {
		super(requestID, result, description);
		this.id = id;
	}

}
