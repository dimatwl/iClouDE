package icloude.requests;

/**
 * @author DimaTWL This class describes 'Run project' request from protocol.
 * 
 */
public class RunProjectRequest extends BaseRequest {
	private String projectID;
	private String entryPointID;
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
	 * @return the entryPointID
	 */
	public String getEntryPointID() {
		return entryPointID;
	}

	/**
	 * @return the inputData
	 */
	public String getInputData() {
		return inputData;
	}
}
