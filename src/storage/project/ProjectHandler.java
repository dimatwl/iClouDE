package storage.project;

import java.util.Date;

import javax.jdo.PersistenceManager;

import storage.DatabaseException;
import storage.PMF;

/**
 * This class provides implementations of all database operations
 * with Project objects (create, get, update, delete).
 * @author Sergey
 *
 */
public class ProjectHandler extends CompositeProjectItemHandler {

	
	public ProjectHandler() {
		super(Project.class);
	}

	/**
	 * Creates new Project object.
	 * <br/><br/>
	 * There should be 2 parameters:<br/>
	 * String name - name of the project to create<br/>
	 * String type - type of the project to create
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
		pm.close();
		
		return project.getKey();
	}

}
