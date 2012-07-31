/**
 * 
 */
package icloude.request_handlers;

import icloude.requests.BaseRequest;
import icloude.responses.BaseResponse;
import icloude.responses.StandartResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL
 * Represents some base behavior and interface for all request handlers
 */
public abstract class BaseRequestHandler {
	/**
	 * This field used to do all JSON staff.
	 */
	protected final static Gson gson = new Gson();
	
	/**
	 * Realization of this method expected to convert JSON representation to concrete request object.
	 * @param json is JSON string from client.
	 * @return concrete request object.
	 */
	protected abstract BaseRequest jsonToRequest(String json) throws JsonSyntaxException;
	
	/**
	 * Realization of this method expected to check if 'requestType' in request is allowed on some address. 
	 * @param requestType is 'requestType' field from request.
	 * @return 'true' if request is allowed and 'false' otherwise.
	 */
	protected abstract Boolean requestTypeCheck(String requestType);
	
	/**
	 * Realization of this method expected to do all specific staff (save/read DB) and generate some response witch will be sent to client. 
	 * @param request is concrete request object.
	 * @return response witch will be sent to client.
	 */
	protected abstract BaseResponse handleRequest(BaseRequest request);
	
	
	/**
	 * This method should be called when request received.
	 * @param json is JSON string from client.
	 * @return response witch will be sent to client.
	 */
	protected final BaseResponse getResult(String json){
		try{
			BaseRequest fromJSON = jsonToRequest(json);
			if (requestTypeCheck(fromJSON.getRequestType())) {
				return handleRequest(fromJSON);
			} else {
				return new StandartResponse(fromJSON.getRequestID(), false, "Request type mismatch.");
			}
		} catch (JsonSyntaxException e) {
			return new StandartResponse("error", false, "Bad JSON syntax.");
		}
	}

}
