package icloude.request_handlers;

import java.io.IOException;

import icloude.requests.BaseRequest;
import icloude.requests.NewProjectRequest;
import icloude.requests.UploadFileRequest;
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
import storage.sourcefile.SourceFile;
import storage.sourcefile.SourceFileReader;
import storage.sourcefile.SourceFileWriter;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL Handling all requests on "rest/uploadfile" URL:
 *         rest/uploadfile Method: POST Required response: Standart
 */
@Path("/uploadfile")
public class UploadFileRequestHandler extends BaseRequestHandler {

	/**
	 * This method used to handle all POST request on "rest/uploadfile"
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
		return gson.fromJson(json, UploadFileRequest.class);
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
		return "uploadfile".equals(requestType);
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
		UploadFileRequest castedRequest = (UploadFileRequest)request;
		SourceFileWriter writer = null;
		try{
			SourceFile file = (SourceFile)Database.get(StoringType.SOURCE_FILE, castedRequest.getContent().getFileID());
			writer = file.openForWriting();
			writer.write(castedRequest.getContent().getText());
			
			
			writer.close();
			Database.save(StoringType.SOURCE_FILE, file);
			System.err.println(castedRequest.getContent().getText());
			SourceFile newfile = (SourceFile)Database.get(StoringType.SOURCE_FILE, castedRequest.getContent().getFileID());
			SourceFileReader reader = newfile.openForReading();
			char[] cbuf = new char[10];
			reader.read(cbuf);
			System.err.println(new String(cbuf));
			reader.close();
			
			
			response = new StandartResponse(request.getRequestID(), true, "File uploaded.");
		} catch (DatabaseException e){
			response = new StandartResponse(request.getRequestID(), false, "DB error. " + e.getMessage());
		} catch (IOException e) {
			response = new StandartResponse(request.getRequestID(), false, "IO error. " + e.getMessage());
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					response = new StandartResponse(request.getRequestID(), false, "IO error. " + e.getMessage());
				}
			}
		}
		return response;
	}

}
