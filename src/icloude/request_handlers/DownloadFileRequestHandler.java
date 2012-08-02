package icloude.request_handlers;

import icloude.contents.FileContent;
import icloude.requests.BaseRequest;
import icloude.requests.DownloadFileRequest;
import icloude.responses.BaseResponse;
import icloude.responses.FileResponse;
import icloude.responses.StandartResponse;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.sourcefile.SourceFile;
import storage.sourcefile.SourceFileReader;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL Handling all requests on "rest/downloadfile" URL:
 *         rest/downloadfile Method: GET Required response: File
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
		SourceFile file = null;
		SourceFileReader reader = null;
		try {
			file = (SourceFile) Database.get(StoringType.SOURCE_FILE,
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
			FileContent content = new FileContent("file", file.getKey(),
					fileText, "I am fileType", "I am ownerID",
					"I amr revisionID", file.getCreationTime().getTime(), file
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
}
