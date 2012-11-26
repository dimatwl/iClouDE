package icloude.testing.backend;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

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

		/**
		 * @return the result
		 */
		public Boolean getResult() {
			return result;
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
		 */
		public TestResult(Boolean result, String name) {
			super();
			this.result = result;
			this.name = name;
		}
	}

	protected String getName() {
		return this.getClass().getSimpleName();
	}
}
