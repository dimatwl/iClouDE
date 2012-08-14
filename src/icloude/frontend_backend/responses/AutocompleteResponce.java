package icloude.frontend_backend.responses;

import icloude.frontend_backend.contents.Choise;

import java.util.List;

/**
 * @author DimaTWL 
 * This class describes 'Autocomplete' response from protocol.
 */
public class AutocompleteResponce extends BaseResponse {

	private List<Choise> choises;

	/**
	 * @param requestID
	 * @param result
	 * @param description
	 * @param choises
	 */
	public AutocompleteResponce(String requestID, Boolean result,
			String description, List<Choise> choises) {
		super(requestID, result, description);
		this.choises = choises;
	}
	

	private AutocompleteResponce() {
	}


	/**
	 * @return the choises
	 */
	public List<Choise> getChoises() {
		return choises;
	}


}
