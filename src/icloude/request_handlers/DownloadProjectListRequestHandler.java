package icloude.request_handlers;

import icloude.contents.ProjectListEntry;
import icloude.requests.DownloadProjectListRequest;
import icloude.responses.ProjectListResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL 
 * Handling all requests on "rest/downloadprojectlist" 
 * URL: rest/downloadprojectlist
 * Method: GET
 * Required response: Project list
 */
@Path("/downloadprojectlist")
public class DownloadProjectListRequestHandler {
	private final static Gson gson = new Gson();

	/**
	 * This method used to handle all GET request on "rest/downloadprojectlist"
	 * 
	 * @return the StandartResponse witch will be sent to client
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@QueryParam("json") String json) {
		ProjectListResponse responce;
		if (json == null) {
			responce = new ProjectListResponse("Error", false,
					"No 'json' parameter in http request.", null);
		} else {
			try {
				DownloadProjectListRequest fromJSON = gson.fromJson(json,
						DownloadProjectListRequest.class);
				if (fromJSON.getRequestType().equals("downloadprojectlist")) {
					List<ProjectListEntry> content = new ArrayList<ProjectListEntry>();
					content.add(new ProjectListEntry("project1", "user1", new Date(), "java"));
					content.add(new ProjectListEntry("project2", "user2", new Date(), "C++"));
					content.add(new ProjectListEntry("project3", "user3", new Date(), "Python"));
					content.add(new ProjectListEntry("project4", "user4", new Date(), "Haskell"));
					responce = new ProjectListResponse(fromJSON.getRequestID(),
							true, "Request 'Download project list' recieved.", content);
				} else {
					responce = new ProjectListResponse(
							fromJSON.getRequestID(),
							true,
							"Request type '"
									+ fromJSON.getRequestType()
									+ "' not allowed on rest/downloadprojectlist. Use 'downloadprojectlist' request type." , null);
				}
			} catch (JsonSyntaxException e) {
				responce = new ProjectListResponse("Error", false,
						"Bad JSON syntax.", null);
			}
		}
		return gson.toJson(responce);
	}

	/**
	 * This method used to handle all GET request on "rest/downloadprojectlist"
	 * 
	 * @return error message
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String post() {
		return new String("POST method is not allowed here.");
	}
	
	/**
	 * This method used to handle all PUT request on "rest/downloadprojectlist"
	 * 
	 * @return error message
	 */
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String put() {
		return new String("PUT method is not allowed here.");
	}
	
	/**
	 * This method used to handle all DELETE request on "rest/downloadprojectlist"
	 * 
	 * @return error message
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String delete() {
		return new String("DELETE method is not allowed here.");
	}
}
