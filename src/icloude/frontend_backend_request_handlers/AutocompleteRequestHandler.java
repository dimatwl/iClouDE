package icloude.frontend_backend_request_handlers;

import icloude.frontend_backend_requests.AutocompleteRequest;
import icloude.frontend_backend_requests.BaseRequest;
import icloude.frontend_backend_responses.BaseResponse;
import icloude.frontend_backend_responses.StandartResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL 
 * Handling all requests on "rest/autocomplete" 
 * URL: rest/autocomplete 
 * Method: GET 
 * Required response: Autocomplete
 */
@Path("/autocomplete")
public class AutocompleteRequestHandler extends BaseRequestHandler {

	/**
	 * This method used to handle all GET request on "rest/autocomplete"
	 * 
	 * @return the StandartResponse witch will be sent to client
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@QueryParam("json") String json) {
		return getResponce(json);
	}

	/**
	 * Realization of this method expected to convert JSON representation to
	 * concrete request object.
	 * 
	 * @param json
	 *            is JSON string from client.
	 * @return concrete request object.
	 */
	@Override
	protected BaseRequest jsonToRequest(String json) throws JsonSyntaxException {
		return GSON.fromJson(json, AutocompleteRequest.class);
	}

	/**
	 * Realization of this method expected to check if 'requestType' in request
	 * is allowed on some address.
	 * 
	 * @param requestType
	 *            is 'requestType' field from request.
	 * @return 'true' if request is allowed and 'false' otherwise.
	 */
	@Override
	protected Boolean requestTypeCheck(String requestType) {
		return "autocomplete".equals(requestType);
	}

	/**
	 * Realization of this method expected to do all specific staff (save/read
	 * DB) and generate some response witch will be sent to client.
	 * 
	 * @param request
	 *            is concrete request object.
	 * @return response witch will be sent to client.
	 */
	@Override
	protected BaseResponse handleRequest(BaseRequest request) {
		return new StandartResponse(request.getRequestID(), true,
				"Request 'Autocomplete' recieved.");
	}
	
	/**
	 * Realization of this method expected to check all specific fields
	 * in concrete request for not null. Check of BaseRequest field is redundant. 
	 * 
	 * @param request
	 *            is concrete request object.
	 * @return True if ALL specific fields != null
	 * 		   False otherwise.
	 */
	@Override
	protected Boolean concreteRequestNullCheck(BaseRequest request){
		AutocompleteRequest castedRequest = (AutocompleteRequest) request;
		return (null != castedRequest.getProjectID()) &&
				(null != castedRequest.getContent()) &&
				(null != castedRequest.getCaretPosition());
	}
}