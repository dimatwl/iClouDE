package icloude.request_handlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import icloude.contents.FileContent;
import icloude.requests.BaseRequest;
import icloude.requests.DownloadCodeRequest;
import icloude.requests.DownloadFileRequest;
import icloude.requests.DownloadProjectStructureRequest;
import icloude.responses.BaseResponse;
import icloude.responses.FileResponse;
import icloude.responses.StandartResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;
import storage.project.ProjectItem;
import storage.sourcefile.SourceFile;
import storage.sourcefile.SourceFileReader;

import com.google.gson.JsonSyntaxException;

/**
 * @author DimaTWL 
 * Handling all requests on "rest/downloadcode" 
 * URL: rest/downloadcode 
 * Method: GET 
 * Required response: ????
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
	@Produces("application/x-zip-compressed")
	public InputStream get(@QueryParam("json") String json) {
		return doZip(jsonToRequest(json));
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
		return new StandartResponse(request.getRequestID(), true,
				"Request 'Download code' recieved.");
	}
	
	private InputStream doZip(BaseRequest request) {
		InputStream response = null;
		DownloadCodeRequest castedRequest = (DownloadCodeRequest) request;
		Project project = null;
		try {
			project = (Project) Database.get(StoringType.PROJECT,
					castedRequest.getProjectID());
			byte[] buf = zipProject(project.getContent(), castedRequest.getProjectID(), project.getName());
			response = new ByteArrayInputStream(buf);
		} catch (DatabaseException e) {
			response = null;
		} catch (IOException e) {
			response = null;
		}
		return response;
	}
	
	
	private byte[] zipProject(Map<String, ProjectItem> project, String projectKey, String projectName) throws IOException, DatabaseException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE);
		ZipOutputStream zipOut = new ZipOutputStream(outStream);
		
		for (String key : project.keySet()) {
			System.err.println(key + " : " + project.get(key));
		} 
		
		for (String key : project.keySet()) {
			addToZip(key, project, zipOut, projectKey, projectName);
		} 
		zipOut.flush();
		zipOut.close();

		return outStream.toByteArray();
	}

	private void addToZip(String key, Map<String, ProjectItem> project, ZipOutputStream zipOut, String projectKey, String projectName) throws IOException, DatabaseException {
		ProjectItem currentItem = project.get(key);
		String fullPath = getFullPath(key, project, projectKey, projectName);
		zipOut.putNextEntry(new ZipEntry(fullPath));
		if (currentItem instanceof SourceFile) {
			SourceFileReader reader = ((SourceFile)currentItem).openForReading();
			char[] buf = new char[DEFAULT_BUFFER_SIZE];
			int charsReaded;
			while ((charsReaded = reader.read(buf)) >= 0) {
				zipOut.write(new String(buf).getBytes(), 0, charsReaded);
			}
			reader.close();
		}

	}
	
	private String getFullPath(String key, Map<String, ProjectItem> project, String projectKey, String projectName) {
		String currentItemKey = key;
		ProjectItem currentItem = project.get(currentItemKey);
		StringBuilder fullPath = new StringBuilder();
		if (! (currentItem instanceof SourceFile)) {
			fullPath.insert(0, '/');
		}
		while (! currentItem.getParentKey().equals(projectKey)) {
			fullPath.insert(0, currentItem.getName());
			fullPath.insert(0, '/');
			currentItemKey = currentItem.getParentKey();
			currentItem = project.get(currentItemKey);
		}
		fullPath.insert(0, currentItem.getName());
		fullPath.insert(0, '/');
		fullPath.insert(0, projectName);
		fullPath.insert(0, '/');
		return fullPath.toString();
	}

}
