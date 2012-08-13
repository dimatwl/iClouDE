package icloude.request_handlers;

import icloude.contents.ProjectListEntry;
import icloude.requests.BaseRequest;
import icloude.requests.DownloadProjectListRequest;
import icloude.responses.BaseResponse;
import icloude.responses.ProjectListResponse;
import icloude.responses.StandartResponse;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL Handling all requests on "rest/downloadprojectlist" URL:
 *         rest/downloadprojectlist Method: GET Required response: Project list
 */
@Path("/downloadprojectlist")
public class DownloadProjectListRequestHandler extends BaseRequestHandler {

	/**
	 * This method used to handle all GET request on "rest/downloadprojectlist"
	 * 
	 * @return the StandartResponse witch will be sent to client
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String post(@QueryParam("json") String json) {
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
		return GSON.fromJson(json, DownloadProjectListRequest.class);
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
		return "downloadprojectlist".equals(requestType);
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
		DownloadProjectListRequest castedRequest = (DownloadProjectListRequest) request;
		try {
			List<Project> projects = (List<Project>) Database.get(StoringType.PROJECTS_LIST);
			List<ProjectListEntry> entries = new ArrayList<ProjectListEntry>();
			for (Project project : projects) {
				entries.add(new ProjectListEntry(project.getName() ,"HARDCODED", project.getKey(), project.getCreationTime(), project.getProjectType()));
			}
			response = new ProjectListResponse(request.getRequestID(), true, "Project list downloaded.", entries);
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
		return true;
	}
}
