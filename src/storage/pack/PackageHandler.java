package storage.pack;

import javax.jdo.PersistenceManager;

import storage.DatabaseException;
import storage.PMF;
import storage.project.CompositeProjectItemHandler;

/**
 * This class provides implementations of all database operations
 * with Package objects (create, get, update, delete).
 * @author Sergey
 *
 */
public class PackageHandler extends CompositeProjectItemHandler {

	/**
	 * Creates new Package object.
	 * <br/><br/>
	 * There should be 3 parameters:<br/>
	 * String name - name of the package to create<br/>
	 * String projectKey - database key of the project where this package should be created<br/>
	 * String parentKey - database key of the project item in which package file should be created
	 */
	@Override
	public String create(Object... params) throws DatabaseException {
		if (params.length != 3) {
			throw new DatabaseException("Incorrent number of parameters" +
					" for creating new file. There should be 3 parameters, but " +
					params.length + " parameters are given");
		}
		
		if (!(params[0] instanceof String)) {
			throw new DatabaseException("First parameter should be the name of" +
					" the file. Its type must be String");
		}
		
		if (!(params[1] instanceof String)) {
			throw new DatabaseException("Second parameter should be the key of" +
					" the project. Its type must be String");
		}

		if (!(params[2] instanceof String)) {
			throw new DatabaseException("Third parameter should be the key of" +
					" the parent ProjectItem. Its type must be String");
		}
			
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String name = (String)params[0];
		String projectKey = (String)params[1];
		String parentKey = projectKey;
		
		Package pack = new Package(name, projectKey, parentKey);
		pm.makePersistent(pack);
		pm.close();
		
		return pack.getKey();
	}

	/**
	 * Finds package with given key.
	 * @param key - database key of the package to get
	 * @return package found
	 * @throws DatabaseException if some error occurs in database or
	 * package wasn't found
	 */
	@Override
	public Object get(String key) throws DatabaseException {
		return get(key, Package.class);
	}
	
}
