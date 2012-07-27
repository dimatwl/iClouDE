package icloude.responses;

/**
 * @author DimaTWL
 * This class describes 'Standart' response from protocol.
 */
public class StandartResponse {
	private String requestID;
	private Boolean result;
	private String description;
	
	public StandartResponse(String inpRequestID, Boolean inpResult, String inpDescription){
		requestID = inpRequestID;
		result = inpResult;
		description = inpDescription;
	}
}
