package storage;


/**
 * Interface for handlind common database operations
 * with any supported types. Each type should have its own
 * handler implementation for these operations.
 * @author Sergey
 *
 */
public interface Handler {
	
	/**
	 * Creates new object in database
	 * @param params - parameters required for creating object
	 * @return String key of created object
	 * @throws DatabaseException 
	 */
	public String create(Object... params) throws DatabaseException;

	/**
	 * Gets object from database
	 * @return - object from database
	 * @throws DatabaseException if it's impossible to get required object
	 */
	public Object get(String key) throws DatabaseException;
	
	/**
	 * Saves object to database
	 * @param toSave - object which should be saved to database
	 * @throws DatabaseException if an error occurs while saving object to
	 * database 
	 */
	public void save(Object toSave) throws DatabaseException;
	
	/**
	 * Deletes object from database.
	 * @param key - database key of the object to delete
	 * @throws DatabaseException if some error occurs while deleting object
	 */
	public void delete(String key) throws DatabaseException;
}
