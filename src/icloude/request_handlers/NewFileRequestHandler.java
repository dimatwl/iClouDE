package icloude.request_handlers;

import icloude.requests.NewFileRequest;
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
 * Handling all requests on "rest/newfile" 
 * URL: rest/newfile
 * Method: POST 
 * Required response: Standart
 */
@Path("/newfile")
public class NewFileRequestHandler {
	private final static Gson gson = new Gson();

	/**
	 * This method used to handle all POST request on "rest/newfile"
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
				NewFileRequest fromJSON = gson.fromJson(inpJSON,
						NewFileRequest.class);
				responce = new StandartResponse(fromJSON.getRequestID(), true,
						"Request 'New file' recieved.");
			} catch (JsonSyntaxException e) {
				responce = new StandartResponse("Error", false,
						"Bad JSON syntax.");
			}
		}
		return gson.toJson(responce);
	}
	
	/**
	 * This method used to handle all GET request on "rest/newfile"
	 * 
	 * @return error message
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		return new String("GET method is not allowed here.");
	}
	
	/**
	 * This method used to handle all PUT request on "rest/newfile"
	 * 
	 * @return error message
	 */
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String put() {
		return new String("PUT method is not allowed here.");
	}
	
	/**
	 * This method used to handle all DELETE request on "rest/newfile"
	 * 
	 * @return error message
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String delete() {
		return new String("DELETE method is not allowed here.");
	}


//	/**
//	 * This method used to handle all GET request on "rest/newfile"
//	 * 
//	 * @return error message
//	 * @throws DatabaseException 
//	 * @throws IOException 
//	 */
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String get() throws DatabaseException, IOException {
//		//OMG I didn't write that code... It look like example for working with database. So I use it in another place. 
//		//String key = Database.create(StoringType.SOURCE_FILE);
//		//System.err.println(key);
//		//
//		//SourceFile file = (SourceFile)Database.get(StoringType.SOURCE_FILE, key);
//		//PrintWriter writer = file.getWriter();
//		//writer.println("hello");
//		//Database.save(StoringType.SOURCE_FILE, file);
//		//
//		//BufferedReader reader = file.getReader();
//		//System.err.println(reader.readLine());
//		return new String("GET method is not allowed here.");
//	}
}
