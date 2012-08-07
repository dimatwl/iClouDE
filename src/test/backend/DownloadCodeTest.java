package test.backend;

import icloude.contents.FileContent;
import icloude.request_handlers.DownloadCodeRequestHandler;
import icloude.request_handlers.NewFileRequestHandler;
import icloude.request_handlers.NewProjectRequestHandler;
import icloude.request_handlers.UploadFileRequestHandler;
import icloude.requests.DownloadCodeRequest;
import icloude.requests.NewFileRequest;
import icloude.requests.NewProjectRequest;
import icloude.requests.UploadFileRequest;
import icloude.responses.IDResponse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DownloadCodeTest extends Test {
	
	List<InnerTest> tests = new ArrayList<InnerTest>();
	
	public DownloadCodeTest(){
		tests.add(new OneFileTest());   //All tests added here!!!
	}
	
	public List<TestResult> launch(){
		List<TestResult> results = new ArrayList<TestResult>();
		for (InnerTest test : tests){
			results.addAll(test.launch());
		}
		return results;
	}
	
	private abstract class InnerTest extends Test {
		public List<TestResult> launch(){
			String projectID = createProject();
			List<TestResult> result = new ArrayList<TestResult>();
			result.add(new TestResult(compareZIP(getZIP(projectID), projectID), this.getClass().getName()));
			return result;
		}
		protected InputStream getZIP(String projectID){
			DownloadCodeRequestHandler dcrh = new DownloadCodeRequestHandler();
			DownloadCodeRequest dcr = new DownloadCodeRequest(1,"DownloadCodeRequest", "downloadcode", "UserIDZIP", projectID);
			return dcrh.get(GSON.toJson(dcr));
		}
		
		protected Boolean compareZIP(InputStream zip, String projectID){
			return false;
		}
		
		protected abstract String createProject();
	}
	
	private class OneFileTest extends InnerTest {
		
		protected String createProject(){
			String json;
			
			NewProjectRequestHandler nprh = new NewProjectRequestHandler();
			NewFileRequestHandler nfrh = new NewFileRequestHandler();
			UploadFileRequestHandler ufrh = new UploadFileRequestHandler();
			
			NewProjectRequest npr = new NewProjectRequest(1, "NewProjectRequest", "newproject", "userIDZIP", "projectZIP", "typeZIP");
			json = nprh.post(GSON.toJson(npr));
			IDResponse idrProj = GSON.fromJson(json, IDResponse.class);
			NewFileRequest nfr = new NewFileRequest(1, "NewFileRequest", "newfile", "userIDZIP", idrProj.getProjectID(), idrProj.getEntityID(), "fileZIP", "typeZIP");
			json = nfrh.post(GSON.toJson(nfr));
			IDResponse idrFile = GSON.fromJson(json, IDResponse.class);
			FileContent content = new FileContent("file", idrFile.getEntityID(), "Hello, I am text of this file!!!", "textFile", "userIDZIP", "ZIPRevision", (new Date()).getTime(), (new Date()).getTime());
			UploadFileRequest ufr = new UploadFileRequest(1, "UploadFileRequest", "uploadfile", "userIDZIP", idrProj.getProjectID(), content);
			json = ufrh.post(GSON.toJson(ufr));
			
			return idrProj.getProjectID();
		}
	}
	
	

}
