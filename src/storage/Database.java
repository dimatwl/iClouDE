package storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import storage.executablefile.ExecutableFileHandler;
import storage.sourcefile.SourceFileReadingHandler;
import storage.user.User;
import storage.user.UserHandler;



public class Database {
	
	private static final Map<StoringType, Handler> handlers = 
			new HashMap<StoringType, Handler>();
	
	static {
		handlers.put(StoringType.SOURCE_FILE_READER, new SourceFileReadingHandler());
		handlers.put(StoringType.EXECUTABLE_FILE, new ExecutableFileHandler());
		handlers.put(StoringType.USER, new UserHandler());
	}


	
	/**
	 * Returns object of required type according to specified parameters
	 * @param user - current user
	 * @param type - type of the object required
	 * @param params - parameters to determine object which should be returned
	 * @return object of required type according to specified parameters
	 * @throws DatabaseException if parameters are incorrect
	 */
	public static Object get(User user, StoringType type, Object... params) throws DatabaseException {
		return handlers.get(type).get(params);
	}
	
	/**
	 * Saves changes in given object
	 * @param user - current user
	 * @param type - type of the object to save
	 * @param toSave - object which should be saved
	 * @throws DatabaseException if and error occurs while saving object to database
	 */
	public static void save(User user, StoringType type, Object toSave) throws DatabaseException {
		handlers.get(type).save(toSave);
	}
	
	/**
	 * Finds all objects of specified type according to specified parameters
	 * @param user - current user
	 * @param type - type of the objects to find
	 * @param params - parameters to determine objects which should be returned
	 * @return list of object of required type according to specified parameters
	 * @throws DatabaseException if parameters are incorrect
	 */
	public static <T> List<T> find(User user, StoringType type,
			Object... params) throws DatabaseException {
		return null;
	}
}
