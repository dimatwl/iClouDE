package icloude.responses;

/**
 * @author DimaTWL This class describes 'Standart' response from protocol.
 */
public class StandartResponse extends BaseResponse {
	
	public StandartResponse(String inpRequestID, Boolean inpResult,
			String inpDescription) {
		super(inpRequestID, inpResult, inpDescription);
	}
}
