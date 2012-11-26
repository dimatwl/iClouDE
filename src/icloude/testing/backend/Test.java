package icloude.testing.backend;

import icloude.frontend_backend.contents.FileContent;
import icloude.frontend_backend.request_handlers.NewFileRequestHandler;
import icloude.frontend_backend.request_handlers.NewProjectRequestHandler;
import icloude.frontend_backend.request_handlers.UploadFileRequestHandler;
import icloude.frontend_backend.requests.NewFileRequest;
import icloude.frontend_backend.requests.NewProjectRequest;
import icloude.frontend_backend.requests.UploadFileRequest;
import icloude.frontend_backend.responses.IDResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

/**
 * @author DimaTWL
 *
 */
public abstract class Test {
	/**
	 * This field used to do all JSON staff.
	 */
	protected final static Gson GSON = new Gson();

	/**
	 * This field used to determine current protocol version.
	 */
	protected final static Integer PROTOCOL_VERSION = 4;

	/**
	 * This field used to determine size for all buffers.
	 */
	protected final static Integer DEFAULT_BUFFER_SIZE = 1024;

	protected List<Test> tests = new ArrayList<Test>();

	public List<TestResult> launch() {
		List<TestResult> results = new ArrayList<TestResult>();
		for (Test test : tests) {
			results.addAll(test.launch());
		}
		return results;
	}

	public class TestResult {
		private Boolean result;
		private String name;
		private String description;

		/**
		 * @return the result
		 */
		public Boolean getResult() {
			return result;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param result
		 * @param name
		 * @param description
		 */
		public TestResult(Boolean result, String name, String description) {
			super();
			this.result = result;
			this.name = name;
			this.description = description;
		}
	}

	protected String getName() {
		return this.getClass().getSimpleName();
	}
	
	protected String createOneFileProject(String projectName) {
		String json;

		NewProjectRequestHandler nprh = new NewProjectRequestHandler();
		NewFileRequestHandler nfrh = new NewFileRequestHandler();
		UploadFileRequestHandler ufrh = new UploadFileRequestHandler();

		NewProjectRequest npr = new NewProjectRequest(PROTOCOL_VERSION,
				"NewProjectRequest", "newproject", "userIDZIP",
				projectName, "typeZIP");
		json = nprh.post(GSON.toJson(npr));
		IDResponse idrProj = GSON.fromJson(json, IDResponse.class);
		NewFileRequest nfr = new NewFileRequest(PROTOCOL_VERSION,
				"NewFileRequest", "newfile", "userIDZIP",
				idrProj.getProjectID(), idrProj.getEntityID(), "fileZIP",
				"typeZIP");
		json = nfrh.post(GSON.toJson(nfr));
		IDResponse idrFile = GSON.fromJson(json, IDResponse.class);
		FileContent content = new FileContent("file", "New name",
				idrFile.getEntityID(), "Hello, I am text of this file!!!",
				"textFile", "userIDZIP", "ZIPRevision",
				(new Date()).getTime(), (new Date()).getTime());
		UploadFileRequest ufr = new UploadFileRequest(PROTOCOL_VERSION,
				"UploadFileRequest", "uploadfile", "userIDZIP",
				idrProj.getProjectID(), content);
		json = ufrh.post(GSON.toJson(ufr));

		return idrProj.getProjectID();
	}
}
