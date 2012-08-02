package storage;


/**
 * Interface for handlind common database operations
 * with any supported types
 * @author Sergey
 *
 */
public interface Handler {
	
	/**
	 * Creates new object in database
	 * @param params - parameters defining properties of new object
	 * @return String key of created object
	 * @throws DatabaseException 
	 */
	public String create(Object... params) throws DatabaseException;

	/**
	 * Gets object from database
	 * @param params - parameters defining how to get object
	 * @return - new object or object from database
	 * @throws DatabaseException if it's impossible to get required object
	 */
	public Object get(Object... params) throws DatabaseException;
	
	/**
	 * Saves object to database
	 * @param toSave - object which should be saved to database
	 * @throws DatabaseException if an error occurs while saving object to
	 * database 
	 */
	public void save(Object toSave) throws DatabaseException;
}
