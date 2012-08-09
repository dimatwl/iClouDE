package icloude.responses;

import java.util.List;

/**
 * @author DimaTWL This class describes 'Results' response from protocol.
 */
public class ResultsResponse extends BaseResponse {

	private List<String> taskResults;

	/**
	 * @param requestID
	 * @param result
	 * @param description
	 * @param taskResults
	 */
	public ResultsResponse(String requestID, Boolean result,
			String description, List<String> taskResults) {
		super(requestID, result, description);
		this.taskResults = taskResults;
	}

}