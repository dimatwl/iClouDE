package storage.project;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import storage.DatabaseException;
import storage.Handler;
import storage.PMF;
import storage.ProjectItem;
import storage.folder.Folder;
import storage.pack.Package;
import storage.sourcefile.SourceFile;

public class ProjectHandler implements Handler {

	
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
		pm.close();
		
		return project.getKey();
	}

	/**
	 * Finds Project with given key.
	 * There should be 1 parameter: (String key)
	 */
	@Override
	public Project get(Object... params) throws DatabaseException {
		if (params.length != 1) {
			throw new DatabaseException("Incorrect number of parameters to get project." +
					" There should be 1 parameter of String type - project Key");
		}
		
		if (!(params[0] instanceof String)) {
			throw new DatabaseException("Incorrect first parameter type to get project." +
					" Type of the first parameter should be String.");
		}
		
		String projectKey = (String) params[0];
		List<ProjectItem> result = getObjectsOfType(projectKey, SourceFile.class);
		result.addAll(getObjectsOfType(projectKey, Folder.class));
		result.addAll(getObjectsOfType(projectKey, Package.class));
		
		for (ProjectItem pi: result) {
			System.err.println(pi.getName());
		}
		
		
		return null;
	}
	
	private List<ProjectItem> getObjectsOfType(String projectKey, Class<?> type) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(type);
		q.setFilter("projectKey == key");
		q.declareParameters("String key");
		
		@SuppressWarnings("unchecked")
		List<ProjectItem> result = (List<ProjectItem>)q.execute(projectKey);
		
		pm.close();
		return result;
	}

	@Override
	public void save(Object toSave) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

}
