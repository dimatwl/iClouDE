package test.backend;

import icloude.contents.FileContent;
import icloude.request_handlers.DownloadCodeRequestHandler;
import icloude.request_handlers.NewFileRequestHandler;
import icloude.request_handlers.NewProjectRequestHandler;
import icloude.request_handlers.UploadFileRequestHandler;
import icloude.requests.BaseRequest;
import icloude.requests.DownloadCodeRequest;
import icloude.requests.NewFileRequest;
import icloude.requests.NewProjectRequest;
import icloude.requests.UploadFileRequest;
import icloude.responses.IDResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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

public class DownloadCodeTest extends Test {

	
	public DownloadCodeTest(){
		tests.add(new OneFileTest());   //All tests added here!!!
	}
	
	
	private abstract class InnerTest extends Test {
		public List<TestResult> launch(){
			String projectID = createProject();
			List<TestResult> result = new ArrayList<TestResult>();
			if (projectID != null) {
				result.add(new TestResult(compareZIP(getZIP(projectID), projectID), getName()));
			} else {
				result.add(new TestResult(false, getName()));
			}
			return result;
		}
		protected InputStream getZIP(String projectID){
			DownloadCodeRequestHandler dcrh = new DownloadCodeRequestHandler();
			DownloadCodeRequest dcr = new DownloadCodeRequest(1,"DownloadCodeRequest", "downloadcode", "UserIDZIP", projectID);
			return dcrh.get(GSON.toJson(dcr));
		}
		
		protected Boolean compareZIP(InputStream zip, String projectID){
			Boolean passed = false;
			ZipInputStream zin = new ZipInputStream(zip);
			ZipEntry entry;
			try {
				Project project = (Project) Database.get(StoringType.PROJECT, projectID);
				Map<String, ProjectItem> projectStructure = project.getContent();
				while((entry = zin.getNextEntry()) != null) {
					System.err.println(entry.getName());
				}
			} catch (IOException e) {
				passed = false;
			} catch (DatabaseException e) {
				passed = false;
			}
			return passed;
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

		
		protected abstract String createProject();
	}
	
	private class OneFileTest extends InnerTest {
		
		protected String createProject(){
			String json;
			
			NewProjectRequestHandler nprh = new NewProjectRequestHandler();
			NewFileRequestHandler nfrh = new NewFileRequestHandler();
			UploadFileRequestHandler ufrh = new UploadFileRequestHandler();
			
			NewProjectRequest npr = new NewProjectRequest(PROTOCOL_VERSION, "NewProjectRequest", "newproject", "userIDZIP", "projectZIP", "typeZIP");
			json = nprh.post(GSON.toJson(npr));
			IDResponse idrProj = GSON.fromJson(json, IDResponse.class);
			NewFileRequest nfr = new NewFileRequest(PROTOCOL_VERSION, "NewFileRequest", "newfile", "userIDZIP", idrProj.getProjectID(), idrProj.getEntityID(), "fileZIP", "typeZIP");
			json = nfrh.post(GSON.toJson(nfr));
			IDResponse idrFile = GSON.fromJson(json, IDResponse.class);
			FileContent content = new FileContent("file", idrFile.getEntityID(), "Hello, I am text of this file!!!", "textFile", "userIDZIP", "ZIPRevision", (new Date()).getTime(), (new Date()).getTime());
			UploadFileRequest ufr = new UploadFileRequest(PROTOCOL_VERSION, "UploadFileRequest", "uploadfile", "userIDZIP", idrProj.getProjectID(), content);
			json = ufrh.post(GSON.toJson(ufr));
			
			return idrProj.getProjectID();
		}
	}
	
	

}
