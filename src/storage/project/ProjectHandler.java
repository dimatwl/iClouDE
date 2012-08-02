package storage.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import storage.AbstractHandler;
import storage.DatabaseException;
import storage.PMF;
import storage.ProjectItem;
import storage.folder.Folder;
import storage.pack.Package;
import storage.sourcefile.SourceFile;

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

	/**
	 * Finds Project with given key.
	 * There should be 1 parameter: (String key)
	 * @return map of all project items
	 */
	@Override
	public Map<String, ProjectItem> get(String key) throws DatabaseException {
		List<ProjectItem> result = new ArrayList<ProjectItem>();
		result.addAll(getObjectsOfType(key, SourceFile.class));
		result.addAll(getObjectsOfType(key, Folder.class));
		result.addAll(getObjectsOfType(key, Package.class));
		result.addAll(getObjectsOfType(key, Project.class));
		
		
		Map<String, ProjectItem> map = new HashMap<String, ProjectItem>();
		for (ProjectItem pi: result) {
			map.put(pi.getKey(), pi);
			System.err.println(pi.getName());
		}
		return map;
	}
	
	private List<ProjectItem> getObjectsOfType(String projectKey, Class<?> type) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(type);
		q.setFilter("projectKey == key");
		q.declareParameters("String key");
		
		@SuppressWarnings("unchecked")
		List<ProjectItem> result = new ArrayList<ProjectItem>((List<ProjectItem>)q.execute(projectKey));
		
		pm.close();
		return result;
	}

	@Override
	public void delete(String key) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

}
