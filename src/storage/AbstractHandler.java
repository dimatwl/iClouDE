package storage;

import javax.jdo.PersistenceManager;



/**
 * This class provides implementations of common database
 * operations for project items.
 * @author Sergey
 *
 */
public abstract class AbstractHandler implements Handler {
	
	private final Class<?> handlingType;
	
	protected AbstractHandler(Class<?> handlingType) {
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
	public Object get(Object... params) throws DatabaseException {
		checkGetParams(params);
		
		String key = (String) params[0];
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Object obj = pm.getObjectById(getHandlingType(), key);
		Object result = pm.detachCopy(obj);
		pm.close();
		
		return result;
	}

	private void checkGetParams(Object... params) throws DatabaseException {
		if (params.length != 1) {
			throw new DatabaseException("Incorrent number of parameters for " +
					"getting " + getHandlingType().getName() + ". Should be 1 parameter," +
							" but " + params.length + " given");
		}
	}
	
}
