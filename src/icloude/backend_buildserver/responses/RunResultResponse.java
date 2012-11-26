package icloude.backend_buildserver.responses;

/**
 * @author DimaTWL This class describes 'RunResult' response from protocol.
 */
public class RunResultResponse extends BaseResponse {

	private String runResult;

	private RunResultResponse() {
	}


	/**
	 * @return the runResult
	 */
	public String getRunResult() {
		return runResult;
	}

}
