package icloude.request_handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import icloude.requests.BaseRequest;
import icloude.requests.DownloadCodeRequest;
import icloude.requests.UploadFileRequest;
import icloude.responses.BaseResponse;
import icloude.responses.StandartResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import storage.ProjectItem;
import storage.project.Project;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL 
 * Handling all requests on "rest/downloadcode" 
 * URL: rest/downloadcode 
 * Method: GET 
 * Required response: Project
 */
@Path("/downloadcode")
public class DownloadCodeRequestHandler extends BaseRequestHandler {

	/**
	 * This method used to handle all GET request on
	 * "rest/downloadcode"
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
		return GSON.fromJson(json, DownloadCodeRequest.class);
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
		return "downloadcode".equals(requestType);
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
		DownloadCodeRequest castedRequest = (DownloadCodeRequest) request;
		return null;
	}
//	
//	private void zipProject(Map<String, ProjectItem> project) throws IOException {
//		ZipOutputStream zipOut = new ZipOutputStream(new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE));
//		for (String key : project.keySet()) {
//			addToZip(key, project, zipOut);
//		} 
//		zipOut.flush();
//		zipOut.close();
//
//		System.out.println("Successfully created ");
//	}
//
//	private void addToZip(String key, Map<String, ProjectItem> project, ZipOutputStream zipOut) throws IOException {
//		File file = new File(srcFile);
//		String filePath = "".equals(path) ? file.getName() : path + "/"
//				+ file.getName();
//			zipOut.putNextEntry(new ZipEntry(filePath));
//			FileInputStream in = new FileInputStream(srcFile);
//
//			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
//			int len;
//			while ((len = in.read(buffer)) != -1) {
//				zipOut.write(buffer, 0, len);
//			}
//
//			in.close();
//	}
//	
//	private void getFullPath(String key, Map<String, ProjectItem> project) {
//		String currentItemKey = key;
//		StringBuilder fullPath = new StringBuilder();
//		while (! (project.get(currentItemKey) instanceof Project)) {
//			
//		}
//	}

}
