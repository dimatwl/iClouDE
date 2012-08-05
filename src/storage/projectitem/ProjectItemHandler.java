package storage.projectitem;

import javax.jdo.PersistenceManager;

import storage.DatabaseException;
import storage.Handler;
import storage.PMF;


/**
 * This class provides implementations of common database
 * operations for all project items. 
 * @author Sergey
 *
 */
public abstract class ProjectItemHandler implements Handler {
	
	private final Class<?> handlingType;
	
	protected ProjectItemHandler(Class<?> handlingType) {
		this.handlingType = handlingType;
	}
	
	public Class<?> getHandlingType() {
		return handlingType;
	}

	
	/**
	 * Saves object to database
	 * @param toSave - object which should be saved to database
	 * @throws DatabaseException if an error occurs while saving object to
	 * database 
	 */
	@Override
	public void update(Object toSave) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Object tmp = pm.makePersistent(toSave);
		pm.makePersistent(tmp);
		pm.close();
	}
	
	/**
	 * Gets object with specified database key.
	 * @param key - key of the object to get
	 * @return object found
	 * @throws DatabaseException if it's impossible to get required object
	 */
	public Object get(String key) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Object obj = pm.getObjectById(getHandlingType(), key);
		if (obj == null) {
			throw new DatabaseException("No such entity in database");
		}
		
		Object result = pm.detachCopy(obj);
		pm.close();
		
		return result;
	}
	
}
