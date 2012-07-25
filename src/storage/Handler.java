package storage;

/**
 * Interface for handlind common database operations
 * with any supported types
 * @author Sergey
 *
 */
public interface Handler {

	/**
	 * Creates new object or gets it from database
	 * @param params - parameters defining how to get object
	 * @return - new object or object from database
	 * @throws DatabaseException if it's impossible to get required object
	 */
	public Object get(Object... params) throws DatabaseException;
	
	/**
	 * Saves object to database
	 * @param toSave - object which should be saved to database
	 */
	public void save(Object toSave);
}
