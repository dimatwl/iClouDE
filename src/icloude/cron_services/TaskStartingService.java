package icloude.cron_services;

import icloude.backend_buildserver.requests.NewBuildAndRunRequest;
import icloude.backend_buildserver.responses.AcceptResultResponse;
import icloude.backend_buildserver.responses.IDResponse;
import icloude.helpers.Logger;
import icloude.helpers.ProjectZipper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;
import storage.taskqueue.BuildAndRunTask;
import storage.taskqueue.TaskStatus;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;


/**
 * @author DimaTWL 
 * Handling all requests on "rest/taskstartingservice" 
 * URL: rest/taskstartingservice 
 * Method: GET
 */
@Path("/taskstartingservice")
public class TaskStartingService extends BaseService {

	/**
	 * This fields used to determine build server address
	 */
	public final static String UPLOAD_ZIP_ADDRESS = BUILD_SERVER_ADDRESS
			+ "build_service/rest/uploadzipfile";  //TODO: Hardcoded
	public final static String NEW_BUILD_AND_RUN_TASK_ADDRESS = BUILD_SERVER_ADDRESS
			+ "build_service/rest/newbuildandruntask"; //TODO: Hardcoded

	/**
	 * This method used to handle all GET request on "rest/taskstartingservice"
	 * 
	 * @return the StandartResponse witch will be sent to client
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void get() {
		BuildAndRunTask task = null;
		try {
			task = (BuildAndRunTask) Database.get(
					StoringType.BUILD_AND_RUN_TASK, TaskStatus.NEW);
			if (task != null) {
				// 0.Get project
				Project project = (Project) Database.get(StoringType.PROJECT,
						task.getProjectKey());
				// 1.Get zipped project
				byte[] zippedProject = ProjectZipper.zipProject(project);
				// 2.Send zipped project

				IDResponse idResponse = uploadZippedProject(UPLOAD_ZIP_ADDRESS, zippedProject);
				if (!iDResponseCheck(idResponse)) {
					Logger.toLog("Some fields in ID response are not presented.");
				} else if (!idResponse.getResult()) {
					Logger.toLog("Got negative result in ID response with description: "
							+ idResponse.getDescription());
				} else {
					// 3.Send build&run info
					NewBuildAndRunRequest buildAndRunRequest = new NewBuildAndRunRequest(
							PROTOCOL_VERSION, idResponse.getZipID(),
							"HARDCODED", "BUILD&RUN", "HARDCODED", "HARDCODED",
							"HARDCODED", "HARDCODED");
					AcceptResultResponse acceptResultResponse = newBuildAndRunTask(buildAndRunRequest);
					if (!acceptResultResponseCheck(acceptResultResponse)) {
						Logger.toLog("Some fields in AcceptResult response are not presented.");
					} else if (!acceptResultResponse.getResult()) {
						Logger.toLog("Got negative result in AcceptResult response with description: "
								+ acceptResultResponse.getDescription());
					} else {
						// 4.Set TaskStatus.RUNNING
						task.setStatus(TaskStatus.RUNNING);
						// 5.Set new taskID
						task.setTaskID(idResponse.getZipID());
						// 6.Update task
						Database.update(StoringType.BUILD_AND_RUN_TASK, task);
					}
				}
			} else {
				Logger.toLog("No available tasks.");
			}
		} catch (DatabaseException e) {
			Logger.toLog("Got DatabaseException with message: "
					+ e.getMessage());
		} catch (IOException e) {
			Logger.toLog("Got IOException with message: " + e.getMessage());
		} catch (Exception e) {
			Logger.toLog("Got Exception with message: " + e.getMessage());
		}
	}

	/*
	private IDResponse sendZippedProject(byte[] zippedProject)
			throws IOException {
		URL url = new URL(UPLOAD_ZIP_ADDRESS);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("content-type",
				"application/x-zip-compressed");

		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(zippedProject);
		outputStream.close();

		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuilder jsonBuilder = new StringBuilder();
			String input;
			while ((input = in.readLine()) != null) {
				jsonBuilder.append(input);
			}
			return GSON.fromJson(jsonBuilder.toString(), IDResponse.class);
		} else {
			throw new ProtocolException("Got HTTP error on " + UPLOAD_ZIP_ADDRESS + " with code: "
					+ connection.getResponseCode());
		}
	}
	*/
	
	public IDResponse uploadZippedProject(String url, byte[] zippedProject) throws IOException {
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(MultiPartWriter.class);
		Client client = Client.create(cc);
		
	    InputStream stream = new ByteArrayInputStream(zippedProject);
	    FormDataMultiPart part = new FormDataMultiPart();
	    part.field("file", stream, MediaType.TEXT_PLAIN_TYPE);
	    WebResource resource = client.resource(url);
	    String response = resource.type(MediaType.MULTIPART_FORM_DATA_TYPE).post(String.class, part);
	    stream.close();
	    part.close();
	    return GSON.fromJson(response, IDResponse.class);
	}

	private Boolean iDResponseCheck(IDResponse response) {
		return (null != response.getResult())
				&& (null != response.getDescription())
				&& (null != response.getZipID());
	}

	private Boolean acceptResultResponseCheck(AcceptResultResponse response) {
		return (null != response.getResult())
				&& (null != response.getDescription());
	}

	private AcceptResultResponse newBuildAndRunTask(
			NewBuildAndRunRequest request) throws IOException {
		URL url = new URL(NEW_BUILD_AND_RUN_TASK_ADDRESS);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("content-type", "application/JSON");

		OutputStream outputStream = connection.getOutputStream();
		outputStream.write((GSON.toJson(request)).getBytes());
		outputStream.close();

		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuilder jsonBuilder = new StringBuilder();
			String input;
			while ((input = in.readLine()) != null) {
				jsonBuilder.append(input);
			}
			return GSON.fromJson(jsonBuilder.toString(),
					AcceptResultResponse.class);
		} else {
			throw new ProtocolException("Got HTTP error on " + NEW_BUILD_AND_RUN_TASK_ADDRESS + " with code: "
					+ connection.getResponseCode());
		}
	}
	
	/**
	 * This class is for testing purposes ONLY.
	 * Please do not use it for evil =)))
	 * 
	 * So, I know that it is bad practice 
	 * but testing code should be in separated package.
	 * 
	 * @author DimaTWL
	 */
	public class Tester {
		
		/**
		 * This method allow to call private method sendZippedProject
		 * from outside. For testing purposes ONLY.
		 * 
		 * @param zippedProject
		 * @return result of sendZippedProject
		 * @throws IOException
		 */
		public IDResponse publicUploadZippedProject(String url, byte[] zippedProject) throws IOException {
			return uploadZippedProject(url, zippedProject);
		}
		
		/**
		 * This method allow to call private method iDResponseCheck
		 * from outside. For testing purposes ONLY.
		 * 
		 * @param IDResponse
		 * @return result of iDResponseCheck
		 */
		public Boolean publicIDResponseCheck(IDResponse response) {
			return iDResponseCheck(response);
		}
		
		/**
		 * This method allow to call private method newBuildAndRunTask
		 * from outside. For testing purposes ONLY.
		 * 
		 * @param NewBuildAndRunRequest
		 * @return result of newBuildAndRunTask
		 */
		public AcceptResultResponse publicNewBuildAndRunTask(NewBuildAndRunRequest request) throws IOException {
			return newBuildAndRunTask(request);
		}
		
		/**
		 * This method allow to call private method acceptResultResponseCheck
		 * from outside. For testing purposes ONLY.
		 * 
		 * @param AcceptResultResponse
		 * @return result of acceptResultResponseCheck
		 */
		public Boolean publicAcceptResultResponseCheck(AcceptResultResponse response) { 
			return acceptResultResponseCheck(response);
		}
	}

}
