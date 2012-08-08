package icloude.request_handlers;

import icloude.requests.BaseRequest;
import icloude.requests.UploadFileRequest;
import icloude.responses.BaseResponse;
import icloude.responses.StandartResponse;

import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.sourcefile.SourceFile;
import storage.sourcefile.SourceFileWriter;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL 
 * Handling all requests on "rest/uploadfile" 
 * URL: rest/uploadfile 
 * Method: POST 
 * Required response: Standart
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
		return GSON.fromJson(json, UploadFileRequest.class);
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
		UploadFileRequest castedRequest = (UploadFileRequest) request;
		SourceFile file = null;
		SourceFileWriter writer = null;
		try {
			file = (SourceFile) Database.get(StoringType.SOURCE_FILE,
					castedRequest.getContent().getFileID());
			writer = file.openForWriting();
			writer.write(castedRequest.getContent().getText());
			response = new StandartResponse(request.getRequestID(), true,
					"File uploaded.");
		} catch (DatabaseException e) {
			response = new StandartResponse(request.getRequestID(), false,
					"DB error. " + e.getMessage());
		} catch (IOException e) {
			response = new StandartResponse(request.getRequestID(), false,
					"IO error. " + e.getMessage());
		} finally {
			if (writer != null && file != null) {
				try {
					writer.close();
					Database.update(StoringType.SOURCE_FILE, file);
				} catch (IOException e) {
					response = new StandartResponse(request.getRequestID(),
							false, "IO error. " + e.getMessage());
				} catch (DatabaseException e) {
					response = new StandartResponse(request.getRequestID(),
							false, "DB error. " + e.getMessage());
				}
			}
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
		UploadFileRequest castedRequest = (UploadFileRequest) request;
		return (null != castedRequest.getProjectID()) &&
				(null != castedRequest.getContent());
	}

}
