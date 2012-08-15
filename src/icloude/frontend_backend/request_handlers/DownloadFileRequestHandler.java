package icloude.frontend_backend.request_handlers;

import icloude.frontend_backend.contents.FileContent;
import icloude.frontend_backend.requests.BaseRequest;
import icloude.frontend_backend.requests.DownloadFileRequest;
import icloude.frontend_backend.responses.BaseResponse;
import icloude.frontend_backend.responses.FileResponse;
import icloude.frontend_backend.responses.StandartResponse;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.file.File;
import storage.file.FileReader;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL 
 * Handling all requests on "rest/downloadfile" 
 * URL: rest/downloadfile 
 * Method: GET 
 * Required response: File
 */
@Path("/downloadfile")
public class DownloadFileRequestHandler extends BaseRequestHandler {

	/**
	 * This method used to handle all GET request on "rest/downloadfile"
	 * 
	 * @return the StandartResponse witch will be sent to client
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@QueryParam("json") String json) {
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
		return GSON.fromJson(json, DownloadFileRequest.class);
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
		return "downloadfile".equals(requestType);
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
		DownloadFileRequest castedRequest = (DownloadFileRequest) request;
		File file = null;
		FileReader reader = null;
		try {
			file = (File) Database.get(StoringType.FILE,
					castedRequest.getFileID());
			reader = file.openForReading();
			StringBuilder fileTextBuilder = new StringBuilder(
					DEFAULT_BUFFER_SIZE);
			char[] buf = new char[DEFAULT_BUFFER_SIZE];
			int charsReaded;
			while ((charsReaded = reader.read(buf)) >= 0) {
				fileTextBuilder.append(buf, 0, charsReaded);
			}
			String fileText = fileTextBuilder.toString();
			FileContent content = new FileContent("file", file.getName(), file.getKey(),
					fileText, "HARDCODED", "HARDCODED",
					"HARDCODED", file.getCreationTime().getTime(), file
							.getModificationTime().getTime());
			response = new FileResponse(request.getRequestID(), true,
					"File downloaded.", content);
		} catch (DatabaseException e) {
			response = new StandartResponse(request.getRequestID(), false,
					"DB error. " + e.getMessage());
		} catch (IOException e) {
			response = new StandartResponse(request.getRequestID(), false,
					"IO error. " + e.getMessage());
		} finally {
			if (reader != null && file != null) {
				try {
					reader.close();
				} catch (IOException e) {
					response = new StandartResponse(request.getRequestID(),
							false, "IO error. " + e.getMessage());
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
		DownloadFileRequest castedRequest = (DownloadFileRequest) request;
		return (null != castedRequest.getProjectID()) &&
				(null != castedRequest.getFileID());
	}
}
