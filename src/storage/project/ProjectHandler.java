package storage.project;

import java.util.Date;

import javax.jdo.PersistenceManager;

import storage.AbstractHandler;
import storage.Database;
import storage.DatabaseException;
import storage.PMF;
import storage.StoringType;
import storage.projectitem.CompositeProjectItemType;
import storage.projectitem.CompositeProjectItem;

/**
 * This class provides implementations of database operations
 * with Project objects (create, get, update, delete).
 * @author Sergey
 *
 */
public class ProjectHandler extends AbstractHandler {

	
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
		if (params.length == 2 &&
				params[0] instanceof String &&
				params[1] instanceof String) {
			
			String name = (String)params[0];
			String type = (String)params[1];
			
			return createProject(name, type);
		} else {
			throw new DatabaseException("Incorrect parameters for creating" +
					" Project");
		}
	}

	private String createProject(String name, String type) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Project project = new Project(name, new Date(), type);
		pm.makePersistent(project);
		
		CompositeProjectItem rootFolder = new CompositeProjectItem(name,
				project.getKey(), null, CompositeProjectItemType.PROJECT);
		pm.makePersistent(rootFolder);
		
		project.setRootKey(rootFolder.getKey());
		pm.close();
		
		return project.getKey();
	}


	/**
	 * Deletes project from dababase.
	 * @param key - database key of the project to delete
	 * @throws DatabaseException if some error occurs while
	 * deleting project
	 */
	@Override
	public void delete(String key) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Project project = pm.getObjectById(Project.class, key);
		pm.close();
		
		Database.delete(StoringType.COMPOSITE_PROJECT_ITEM, project.getRootKey());
	}

}
