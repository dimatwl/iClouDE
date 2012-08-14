package icloude.frontend_backend_responses;

/**
 * @author DimaTWL 
 * This class describes 'Standart' response from protocol.
 */
public class StandartResponse extends BaseResponse {

	/**
	 * @param requestID
	 * @param result
	 * @param description
	 */
	public StandartResponse(String requestID, Boolean result, String description) {
		super(requestID, result, description);
	}
	

	private StandartResponse() {
	}

}
