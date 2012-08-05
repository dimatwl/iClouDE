package storage.projectitem;

import java.util.Map;

import javax.jdo.PersistenceManager;

import storage.DatabaseException;
import storage.PMF;
import storage.project.Project;

/**
 * Class for handlig composite project items such as project, folder, package.
 * Allows to handle current project item and all its subitems.
 * @author Sergey
 *
 */
public abstract class CompositeProjectItemHandler extends ProjectItemHandler {

	protected CompositeProjectItemHandler(Class<?> handlingType) {
		super(handlingType);
	}

	/**
	 * Deletes project item with given key. Also deletes all its subitems.
	 * @param key - database key of the project item to delete.
	 * @throws DatabaseException if some error occurs while deleting
	 * current project item or its subitems
	 */
	@Override
	public void delete(String key) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ProjectItem toDelete = (ProjectItem) pm.getObjectById(getHandlingType(), key);
		String projectKey = null;
		if (toDelete instanceof Project) {
			projectKey = key;
		} else {
			projectKey = toDelete.getProjectKey();
		}
		Project project = pm.getObjectById(Project.class, projectKey);
		
		Map<String, ProjectItem> map = project.getContent();
		for (ProjectItem item: map.values()) {
			
			String currentKey = item.getKey();
			while (!currentKey.equals(key) && !currentKey.equals(projectKey)) {
				currentKey = map.get(currentKey).getParentKey();
			}
			
			if (currentKey.equals(key)) {
				pm.deletePersistent(pm.getObjectById(item.getClass(), item.getKey()));
			}
		}
		
		pm.close();
	}
	
}
