package icloude.backend_amazon.responses;

/**
 * @author DimaTWL This class describes 'ID' response from protocol.
 */
public class IDResponce extends BaseResponse {

	private String zipID;

	private IDResponce() {
	}

	/**
	 * @param result
	 * @param description
	 * @param zipID
	 */
	public IDResponce(Boolean result, String description, String zipID) {
		super(result, description);
		this.zipID = zipID;
	}

	/**
	 * @return the zipID
	 */
	public String getZipID() {
		return zipID;
	}

}
