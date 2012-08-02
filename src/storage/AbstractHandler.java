package storage;

import javax.jdo.PersistenceManager;


public abstract class AbstractHandler implements Handler {

	@Override
	public void save(Object toSave) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Object tmp = pm.makePersistent(toSave);
		pm.makePersistent(tmp);
		pm.close();
	}
	
	
	protected Object get(String key, Class<?> type) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Object obj = pm.getObjectById(type, key);
		if (obj == null) {
			throw new DatabaseException("No such entity in database");
		}
		
		Object result = pm.detachCopy(obj);
		pm.close();
		
		return result;
	}
}
