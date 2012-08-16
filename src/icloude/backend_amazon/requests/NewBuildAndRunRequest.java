package icloude.backend_amazon.requests;

public class NewBuildAndRunRequest extends BaseRequest {
	private String languageType;
	private String operation;
	private String compileParameters;
	private String inputData;
	private String entryPointPath;
	private String compilator;
	
	/**
	 * @param protocolVersion
	 * @param requestID
	 * @param languageType
	 * @param operation
	 * @param compileParameters
	 * @param inputData
	 * @param entryPointPath
	 * @param compilator
	 */
	public NewBuildAndRunRequest(Integer protocolVersion, String zipID,
			String languageType, String operation, String compileParameters,
			String inputData, String entryPointPath, String compilator) {
		super(protocolVersion, zipID);
		this.languageType = languageType;
		this.operation = operation;
		this.compileParameters = compileParameters;
		this.inputData = inputData;
		this.entryPointPath = entryPointPath;
		this.compilator = compilator;
	}
}
