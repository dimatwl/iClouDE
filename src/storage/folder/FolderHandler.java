package storage.folder;

import javax.jdo.PersistenceManager;

import storage.AbstractHandler;
import storage.DatabaseException;
import storage.PMF;

public class FolderHandler extends AbstractHandler {

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
	public Object get(String key) throws DatabaseException {
		return get(key, Folder.class);
	}

	@Override
	public void delete(String key) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

}
