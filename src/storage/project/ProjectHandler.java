package storage.project;

import java.util.Date;

import javax.jdo.PersistenceManager;

import storage.AbstractHandler;
import storage.DatabaseException;
import storage.PMF;

public class ProjectHandler extends AbstractHandler {

	
	/**
	 * Creates new Project object.
	 * There should be 2 parameters: (String name, String type)
	 */
	@Override
	public String create(Object... params) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		if (params.length != 2) {
			throw new DatabaseException("Incorrect number of parameters to create new project." +
					" There should be 2 parameters.");
		}
		
		if (!(params[0] instanceof String)) {
			throw new DatabaseException("Incorrect first parameter to create new project." +
					" Type of the first paramater should be String");
		}
		
		if (!(params[1] instanceof String)) {
			throw new DatabaseException("Incorrect second parameter to create new project." +
					" Type of the second paramater should be String");
		}
		
		String name = (String)params[0];
		String type = (String)params[1];
		
		Project project = new Project(name, new Date(), type);
		pm.makePersistent(project);
		project.setProjectKey(project.getKey());
		pm.close();
		
		return project.getKey();
	}
	
	@Override
	public Object get(String key) throws DatabaseException {
		return get(key, Project.class);
	}


	/**
	 * Deletes
	 */
	@Override
	public void delete(String key) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

}
