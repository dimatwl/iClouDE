package icloude.request_handlers;

import icloude.requests.BaseRequest;
import icloude.requests.DeleteFileRequest;
import icloude.requests.DownloadCodeRequest;
import icloude.responses.BaseResponse;
import icloude.responses.StandartResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import storage.Child;
import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;
import storage.projectitem.CompositeProjectItem;
import storage.projectitem.CompositeProjectItemType;
import storage.projectitem.ProjectItem;
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
			byte[] buf = zipProject(project);
			response = new ByteArrayInputStream(buf);
		} catch (DatabaseException e) {
			response = null;
		} catch (IOException e) {
			response = null;
		}
		return response;
	}

	private byte[] zipProject(Project project) throws IOException,
			DatabaseException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(
				DEFAULT_BUFFER_SIZE);
		ZipOutputStream zipOut = new ZipOutputStream(outStream);

		CompositeProjectItem root = (CompositeProjectItem) Database.get(
				StoringType.COMPOSITE_PROJECT_ITEM, project.getRootKey());
		StringBuilder path = new StringBuilder('/');
		addToZip(root, path, zipOut);

		zipOut.flush();
		zipOut.close();

		return outStream.toByteArray();
	}

	private void addToZip(CompositeProjectItem item, StringBuilder path,
			ZipOutputStream zipOut) throws IOException, DatabaseException {
		if (item.getItemType().equals(CompositeProjectItemType.PACKAGE)) {
			path.append(item.getName().replace('.', '/'));
		} else {
			path.append(item.getName());
		}
		path.append('/');
		zipOut.putNextEntry(new ZipEntry(path.toString()));
		for (Child child : item.getChildren()) {
			if (child.getType().equals(StoringType.COMPOSITE_PROJECT_ITEM)) {
				CompositeProjectItem compositeItem = (CompositeProjectItem) Database
						.get(StoringType.COMPOSITE_PROJECT_ITEM, child.getKey());
				addToZip(compositeItem, new StringBuilder(path.toString()),
						zipOut);
			} else {
				SourceFile file = (SourceFile) Database.get(
						StoringType.SOURCE_FILE, child.getKey());
				addToZip(file, new StringBuilder(path.toString()), zipOut);
			}
		}
	}

	private void addToZip(SourceFile file, StringBuilder path,
			ZipOutputStream zipOut) throws IOException, DatabaseException {
		path.append(file.getName());
		zipOut.putNextEntry(new ZipEntry(path.toString()));
		SourceFileReader reader = ((SourceFile) file).openForReading();
		char[] buf = new char[DEFAULT_BUFFER_SIZE];
		int charsReaded;
		while ((charsReaded = reader.read(buf)) >= 0) {
			zipOut.write(new String(buf).getBytes(), 0, charsReaded);
		}
		reader.close();
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
		DownloadCodeRequest castedRequest = (DownloadCodeRequest) request;
		return (null != castedRequest.getProjectID());
	}

}
