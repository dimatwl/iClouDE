package storage.project;

import javax.jdo.PersistenceManager;

import storage.DatabaseException;
import storage.PMF;

/**
 * This class provides implementations of common database
 * operations for simple project items. 
 * @author Sergey
 *
 */
public abstract class SimpleProjectItemHandler extends ProjectItemHandler {

	protected SimpleProjectItemHandler(Class<?> handlingType) {
		super(handlingType);
	}

	
	/**
	 * Deletes object with specified key from database
	 * @param key - key of the object to delete
	 * @throws DatabaseException if object wasn't found
	 */
	public void delete(String key) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(getHandlingType(), key));
		pm.close();
	}
}
