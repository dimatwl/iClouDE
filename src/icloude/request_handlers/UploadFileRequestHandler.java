/**
 * 
 */
package icloude.request_handlers;

import icloude.requests.UploadFileRequest;
import icloude.responses.StandartResponse;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL Handling all requests on "rest/uploadfile" URL:
 *         rest/uploadfile Method: POST Required response: Standart
 */
@Path("/uploadfile")
public class UploadFileRequestHandler {
	private final static Gson gson = new Gson();

	/**
	 * This method used to handle all POST request on "rest/uploadfile"
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
				UploadFileRequest fromJSON = gson.fromJson(inpJSON,
						UploadFileRequest.class);
				responce = new StandartResponse(fromJSON.getRequestID(), true,
						"Request for upload file creation recieved.");
			} catch (JsonSyntaxException e) {
				responce = new StandartResponse("Error", false,
						"Bad JSON syntax.");
			}
		}
		return gson.toJson(responce);
	}

	/**
	 * This method used to handle all POST request on "rest/newfile"
	 * 
	 * @return error message
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		return new String("GET method is not allowed here.");
	}
}
