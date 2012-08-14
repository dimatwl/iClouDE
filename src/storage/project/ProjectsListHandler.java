package storage.project;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import storage.AbstractHandler;
import storage.DatabaseException;
import storage.PMF;

/**
 * Allows to get list of projects from database.
 * All other database operations are forbidden and will cause an
 * exception.
 * @author Sergey
 *
 */
public class ProjectsListHandler extends AbstractHandler {

	public ProjectsListHandler() {
		super(null);
	}

	@Override
	public String create(Object... params) throws DatabaseException {
		throw new DatabaseException("Not supported operation. " +
				"Impossible to create projects list");
	}

	@Override
	public void delete(String key) throws DatabaseException {
		throw new DatabaseException("Not supported operation. " +
				"Impossible to delete projects list");
	}
	
	@Override
	public void update(Object toSave) throws DatabaseException {
		throw new DatabaseException("Not supported operation. " +
				"Impossible to update projects list");
	}

	/**
	 * Gets list of all projects from database. 
	 * @return Returns list of all projects from database.
	 */
	@Override
	public List<Project> get(Object... params) throws DatabaseException {
		if (params.length == 0) {
			return getProjectsList();
		} else {
			throw new DatabaseException("Incorrect parameters for getting " +
					"projects list");
		}
	}

	private List<Project> getProjectsList() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Project.class);
		@SuppressWarnings("unchecked")
		List<Project> result = new ArrayList<Project>((List<Project>)q.execute());
		pm.close();
		
		return result;
	}

}
