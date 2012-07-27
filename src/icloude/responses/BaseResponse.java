/**
 * 
 */
package icloude.responses;

/**
 * @author DimaTWL This class describes general part of response from protocol.
 */
public class BaseResponse {
	private String requestID;
	private Boolean result;
	private String description;
	
	public BaseResponse(String inpRequestID, Boolean inpResult,
			String inpDescription) {
		requestID = inpRequestID;
		result = inpResult;
		description = inpDescription;
	}
}
