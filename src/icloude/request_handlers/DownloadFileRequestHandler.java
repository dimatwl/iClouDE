package icloude.request_handlers;

import icloude.contents.FileContent;
import icloude.requests.DownloadFileRequest;
import icloude.responses.FileResponse;

import java.util.Date;

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
 * Handling all requests on "rest/downloadfile" 
 * URL: rest/downloadfile
 * Method: GET
 * Required response: File
 */
@Path("/downloadfile")
public class DownloadFileRequestHandler {
	private final static Gson gson = new Gson();

	/**
	 * This method used to handle all GET request on "rest/downloadfile"
	 * 
	 * @return the StandartResponse witch will be sent to client
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@FormParam("json") String json) {
		FileResponse responce;
		if (json == null) {
			responce = new FileResponse("Error", false,
					"No 'json' parameter in http request.", null);
		} else {
			try {
				DownloadFileRequest fromJSON = gson.fromJson(json,
						DownloadFileRequest.class);
				if (fromJSON.getRequestType().equals("downloadfile")) {
					FileContent content = new FileContent("file", fromJSON.getFilePath(), "Hello! I'm text of the file!", "txt", "Vasya Pupkin", "1", new Date(), new Date());
					responce = new FileResponse(fromJSON.getRequestID(),
							true, "Request 'Download file' recieved.", content);
				} else {
					responce = new FileResponse(
							fromJSON.getRequestID(),
							true,
							"Request type '"
									+ fromJSON.getRequestType()
									+ "' not allowed on rest/downloadfile. Use 'downloadfile' request type." , null);
				}
			} catch (JsonSyntaxException e) {
				responce = new FileResponse("Error", false,
						"Bad JSON syntax.", null);
			}
		}
		return gson.toJson(responce);
	}

	/**
	 * This method used to handle all GET request on "rest/downloadfile"
	 * 
	 * @return error message
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String post() {
		return new String("POST method is not allowed here.");
	}
	
	/**
	 * This method used to handle all PUT request on "rest/downloadfile"
	 * 
	 * @return error message
	 */
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String put() {
		return new String("PUT method is not allowed here.");
	}
	
	/**
	 * This method used to handle all DELETE request on "rest/downloadfile"
	 * 
	 * @return error message
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String delete() {
		return new String("DELETE method is not allowed here.");
	}
}

