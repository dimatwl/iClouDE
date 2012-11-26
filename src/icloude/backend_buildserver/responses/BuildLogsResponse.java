package icloude.backend_buildserver.responses;

/**
 * @author DimaTWL This class describes 'BuildLogs' response from protocol.
 */
public class BuildLogsResponse extends BaseResponse {

	private String buildLogs;

	private BuildLogsResponse() {
	}


	/**
	 * @return the buildLogs
	 */
	public String getBuildLogs() {
		return buildLogs;
	}

}
