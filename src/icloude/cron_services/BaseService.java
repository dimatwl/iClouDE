package icloude.cron_services;

import com.google.gson.Gson;

/**
 * @author DimaTWL Represents some base behavior and interface for all services
 */
public abstract class BaseService {
	/**
	 * This field used to do all JSON staff.
	 */
	public final static Gson GSON = new Gson();

	/**
	 * This field used to determine current protocol version.
	 */
	public final static Integer PROTOCOL_VERSION = 1;

	/**
	 * This field used to determine size for all buffers.
	 */
	public final static Integer DEFAULT_BUFFER_SIZE = 1024;

	/**
	 * This field used to determine build server address
	 */
	public final static String BUILD_SERVER_ADDRESS = "194.85.238.22:5555"; //TODO: HARDCODED

}
