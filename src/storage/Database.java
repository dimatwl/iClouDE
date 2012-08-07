package storage;

import java.util.HashMap;
import java.util.Map;

import storage.project.ProjectHandler;
import storage.projectitem.CompositeProjectItemHandler;
import storage.sourcefile.SourceFileHandler;


/**
 * Class for operating with database. Using this class it's possible
 * to create, read, update and delete objects from database.
 * <br/><br/>
 * For creating object you need to provide some parameters depending on
 * type of the object which should be created. For information about parameters
 * for object creating see the handler class of the type you are working with
 * (for example SourceFileHandler). After creating new object you will
 * receive database key of this object. This key should be used for some
 * operations with this object.
 * <br/><br/>
 * For getting object from database you should provide only database key
 * of the object you need.
 * <br/><br/>
 * For updating object it must be saved using save method of this class.
 * <br/><br/>
 * Deleting object is peformed by the database key.
 * @author Sergey
 *
 */
public class Database {
	
	private static final Map<StoringType, Handler> handlers = 
			new HashMap<StoringType, Handler>();
	
	static {
		handlers.put(StoringType.SOURCE_FILE, new SourceFileHandler());
		handlers.put(StoringType.PROJECT, new ProjectHandler());
		handlers.put(StoringType.COMPOSITE_PROJECT_ITEM, new CompositeProjectItemHandler());
	}

	
	/**
	 * Creates new object and saves it to database
	 * @param type - type of the object to create
	 * @param params - parameters required for creating object
	 * @return database key of the created object
	 * @throws DatabaseException if some occurs while creating object
	 */
	public static String create(StoringType type, Object... params) throws DatabaseException {
		try {
			return handlers.get(type).create(params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	
	/**
	 * Returns object of required type according to specified parameters
	 * @param type - type of the object required
	 * @param params - parameters to determine object which should be returned
	 * @return object of required type according to specified parameters
	 * @throws DatabaseException if parameters are incorrect
	 */
	public static Object get(StoringType type, String key) throws DatabaseException {
		try {
			return handlers.get(type).get(key);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * Saves changes in given object
	 * @param type - type of the object to save
	 * @param toSave - object which should be saved
	 * @throws DatabaseException if and error occurs while saving object to database
	 */
	public static void update(StoringType type, Object toSave) throws DatabaseException {
		try {
			handlers.get(type).update(toSave);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * Deletes object from database.
	 * @param type - type of the object to delete
	 * @param key - database key of the object to delete
	 * @throws DatabaseException if some error occurs while deleting object
	 */
	public static void delete(StoringType type, String key) throws DatabaseException {
		try {
			handlers.get(type).delete(key);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
