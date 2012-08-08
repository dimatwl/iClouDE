package icloude.request_handlers;

import icloude.requests.BaseRequest;
import icloude.requests.NewProjectRequest;
import icloude.responses.BaseResponse;
import icloude.responses.IDResponse;
import icloude.responses.StandartResponse;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL Handling all requests on "rest/newproject" URL:
 *         rest/newproject Method: POST Required response: ID
 */
@Path("/newproject")
public class NewProjectRequestHandler extends BaseRequestHandler {

	/**
	 * This method used to handle all POST request on "rest/newproject"
	 * 
	 * @return the StandartResponse witch will be sent to client
	 */
	@POST
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
		return GSON.fromJson(json, NewProjectRequest.class);
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
		return "newproject".equals(requestType);
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
		BaseResponse response;
		try {
			String key = Database.create(StoringType.PROJECT,
					((NewProjectRequest) request).getProjectName(),
					((NewProjectRequest) request).getProjectType());
			Project project = (Project) Database.get(StoringType.PROJECT, key);
			response = new IDResponse(
					request.getRequestID(),
					true,
					"New project created. Here is your ID. Please do not use it for evil.",
					project.getKey(), project.getRootKey());
		} catch (DatabaseException e) {
			response = new StandartResponse(request.getRequestID(), false,
					"DB error. " + e.getMessage());
		}
		return response;
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
		NewProjectRequest castedRequest = (NewProjectRequest) request;
		return (null != castedRequest.getProjectName()) &&
				(null != castedRequest.getProjectType());
	}

}