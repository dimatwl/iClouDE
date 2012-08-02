package storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import storage.executablefile.ExecutableFileHandler;
import storage.project.ProjectHandler;
import storage.sourcefile.SourceFileHandler;



public class Database {
	
	private static final Map<StoringType, Handler> handlers = 
			new HashMap<StoringType, Handler>();
	
	static {
		handlers.put(StoringType.SOURCE_FILE, new SourceFileHandler());
		handlers.put(StoringType.EXECUTABLE_FILE, new ExecutableFileHandler());
		handlers.put(StoringType.PROJECT, new ProjectHandler());
	}

	
	public static String create(StoringType type, Object... params) throws DatabaseException {
		try {
			return handlers.get(type).create(params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	
	/**
	 * Returns object of required type according to specified parameters
	 * @param user - current user
	 * @param type - type of the object required
	 * @param params - parameters to determine object which should be returned
	 * @return object of required type according to specified parameters
	 * @throws DatabaseException if parameters are incorrect
	 */
<<<<<<< HEAD
	public static Object get(StoringType type, String key) throws DatabaseException {
		try {
			return handlers.get(type).get(key);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
=======
	public static Object get(StoringType type, Object... params) throws DatabaseException {
		return handlers.get(type).get(params);
>>>>>>> 35d8ea8c191b1dce83f6b8c87d02957a9976a2b3
	}
	
	/**
	 * Saves changes in given object
	 * @param user - current user
	 * @param type - type of the object to save
	 * @param toSave - object which should be saved
	 * @throws DatabaseException if and error occurs while saving object to database
	 */
	public static void save(StoringType type, Object toSave) throws DatabaseException {
		try {
			handlers.get(type).save(toSave);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/**
	 * Finds all objects of specified type according to specified parameters
	 * @param user - current user
	 * @param type - type of the objects to find
	 * @param params - parameters to determine objects which should be returned
	 * @return list of object of required type according to specified parameters
	 * @throws DatabaseException if parameters are incorrect
	 */
	public static <T> List<T> find(StoringType type,
			Object... params) throws DatabaseException {
		return null;
	}
<<<<<<< HEAD
	
	public static void delete(StoringType type, String key) throws DatabaseException {
		try {
			handlers.get(type).delete(key);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
=======
>>>>>>> 35d8ea8c191b1dce83f6b8c87d02957a9976a2b3
}
