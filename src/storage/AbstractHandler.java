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
}
