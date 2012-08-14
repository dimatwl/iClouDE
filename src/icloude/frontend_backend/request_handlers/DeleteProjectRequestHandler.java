package icloude.frontend_backend.request_handlers;

import icloude.frontend_backend.requests.BaseRequest;
import icloude.frontend_backend.requests.DeleteFileRequest;
import icloude.frontend_backend.requests.DeleteProjectRequest;
import icloude.frontend_backend.responses.BaseResponse;
import icloude.frontend_backend.responses.StandartResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL 
 * Handling all requests on "rest/deleteproject" 
 * URL: rest/deleteproject 
 * Method: DELETE 
 * Required response: Standart
 */
@Path("/deleteproject")
public class DeleteProjectRequestHandler extends BaseRequestHandler {

	/**
	 * This method used to handle all GET request on "rest/deleteproject"
	 * 
	 * @return the StandartResponse witch will be sent to client
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String post(@FormParam("json") String json) {
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
		return GSON.fromJson(json, DeleteProjectRequest.class);
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
		return "deleteproject".equals(requestType);
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
				"Request 'Delete project' recieved.");
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
		DeleteFileRequest castedRequest = (DeleteFileRequest) request;
		return (null != castedRequest.getProjectID());
	}
}