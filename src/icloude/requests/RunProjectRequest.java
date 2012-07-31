package icloude.requests;

/**
 * @author DimaTWL This class describes 'Run project' request from protocol.
 * 
 */
public class RunProjectRequest extends BaseRequest {
	private String projectID;
	private String entryPointPath;
	private String inputData;

	private RunProjectRequest() {
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the entryPointPath
	 */
	public String getEntryPointPath() {
		return entryPointPath;
	}

	/**
	 * @return the inputData
	 */
	public String getInputData() {
		return inputData;
	}
}
