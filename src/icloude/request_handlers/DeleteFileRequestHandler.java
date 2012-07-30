package icloude.request_handlers;

import icloude.requests.DeleteFileRequest;
import icloude.responses.StandartResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL 
 * Handling all requests on "rest/deletefile" 
 * URL: rest/deletefile
 * Method: POST 
 * Required response: Standart
 */
@Path("/deletefile")
public class DeleteFileRequestHandler {
	private final static Gson gson = new Gson();

	/**
	 * This method used to handle all POST request on "rest/deletefile"
	 * 
	 * @return the StandartResponse witch will be sent to client
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String post(@FormParam("json") String inpJSON) {
		StandartResponse responce;
		if (inpJSON == null) {
			responce = new StandartResponse("Error", false,
					"No 'json' parameter in http request.");
		} else {
			try {
				DeleteFileRequest fromJSON = gson.fromJson(inpJSON,
						DeleteFileRequest.class);
				if (fromJSON.getRequestType().equals("deletefile")) {
					responce = new StandartResponse(fromJSON.getRequestID(),
							true, "Request 'Delete file' recieved.");
				} else {
					responce = new StandartResponse(
							fromJSON.getRequestID(),
							true,
							"Request type '"
									+ fromJSON.getRequestType()
									+ "' not allowed on rest/deletefile. Use 'deletefile' request type.");
				}
			} catch (JsonSyntaxException e) {
				responce = new StandartResponse("Error", false,
						"Bad JSON syntax.");
			}
		}
		return gson.toJson(responce);
	}

	/**
	 * This method used to handle all GET request on "rest/deletefile"
	 * 
	 * @return error message
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		return new String("GET method is not allowed here.");
	}
	
	/**
	 * This method used to handle all PUT request on "rest/deletefile"
	 * 
	 * @return error message
	 */
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String put() {
		return new String("PUT method is not allowed here.");
	}
	
	/**
	 * This method used to handle all DELETE request on "rest/deletefile"
	 * 
	 * @return error message
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String delete() {
		return new String("DELETE method is not allowed here.");
	}
}
