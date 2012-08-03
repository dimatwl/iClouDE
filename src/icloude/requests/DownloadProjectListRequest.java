package icloude.requests;

/**
 * @author DimaTWL This class describes 'Download file' request from protocol.
 * 
 */
public class DownloadProjectListRequest extends BaseRequest {

	private DownloadProjectListRequest() {
	}

	/**
	 * @param requestID
	 * @param requestType
	 * @param userID
	 */
	public DownloadProjectListRequest(String requestID, String requestType,
			String userID) {
		super(requestID, requestType, userID);
		// TODO Auto-generated constructor stub
	}

}