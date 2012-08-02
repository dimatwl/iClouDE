package storage.folder;

import javax.jdo.PersistenceManager;

import storage.DatabaseException;
import storage.Handler;
import storage.PMF;

public class FolderHandler implements Handler {

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
		
		Folder folder = new Folder(name, projectKey, parentKey);
		pm.makePersistent(folder);
		pm.close();
		
		return folder.getKey();
	}

	@Override
	public Object get(Object... params) throws DatabaseException {
		if (params.length != 1) {
			throw new DatabaseException("Incorrect number of parameters to get folder." +
					" There should be 1 parameter of String type - project Key");
		}
		
		if (!(params[0] instanceof String)) {
			throw new DatabaseException("Incorrect first parameter type to get folder." +
					" Type of the first parameter should be String.");
		}
		
		String key = (String) params[0];
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Folder folder = pm.getObjectById(Folder.class, key);
		if (folder == null) {
			throw new DatabaseException("No such folder");
		}
		return folder;
	}

	@Override
	public void save(Object toSave) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

}
