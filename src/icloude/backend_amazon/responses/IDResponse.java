package icloude.backend_amazon.responses;

/**
 * @author DimaTWL This class describes 'ID' response from protocol.
 */
public class IDResponse extends BaseResponse {

	private String zipID;

	private IDResponse() {
	}


	/**
	 * @return the zipID
	 */
	public String getZipID() {
		return zipID;
	}

}
