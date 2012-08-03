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

	/**
	 * @param requestID
	 * @param requestType
	 * @param userID
	 * @param projectID
	 * @param entryPointID
	 * @param inputData
	 */
	public RunProjectRequest(String requestID, String requestType,
			String userID, String projectID, String entryPointID,
			String inputData) {
		super(requestID, requestType, userID);
		this.projectID = projectID;
		this.entryPointID = entryPointID;
		this.inputData = inputData;
	}
}
