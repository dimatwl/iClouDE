package icloude.requests;

/**
 * @author DimaTWL This class describes 'Download file' request from protocol.
 * 
 */
public class DownloadProjectListRequest extends BaseRequest {

	private DownloadProjectListRequest() {
	}

	/**
	 * @param protocolVersion
	 * @param requestID
	 * @param requestType
	 * @param userID
	 */
	public DownloadProjectListRequest(Integer protocolVersion,
			String requestID, String requestType, String userID) {
		super(protocolVersion, requestID, requestType, userID);
		// TODO Auto-generated constructor stub
	}



}