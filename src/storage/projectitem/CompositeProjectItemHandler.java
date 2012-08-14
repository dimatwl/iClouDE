package storage.projectitem;

import java.util.List;

import javax.jdo.PersistenceManager;

import storage.AbstractHandler;
import storage.Child;
import storage.DatabaseException;
import storage.PMF;
import storage.StoringType;
import storage.file.File;
import storage.project.Project;

/**
 * Class for handlig composite project items such as folder, package.
 * Allows to handle current project item and all its subitems.
 * @author Sergey
 *
 */
public class CompositeProjectItemHandler extends AbstractHandler {

	public CompositeProjectItemHandler() {
		super(CompositeProjectItem.class);
	}
	
	
	/**
	 * Creates new ComopsiteProjectItem object.
	 * <br/><br/>
	 * There should be 4 parameters:<br/>
	 * String name - name of the item to create<br/>
	 * String projectKey - database key of the project where this item should be created<br/>
	 * String parentKey - database key of the project item in which this item should be created. 
	 * It can be null if this project item doesn't have parent.<br/>
	 * ComopsiteProjectItemType itemType - type of the item to create (folder, package, project)
	 */
	@Override
	public String create(Object... params) throws DatabaseException {
		if (params.length == 4 &&
				params[0] instanceof String &&
				params[1] instanceof String &&
				(params[2] == null || params[2] instanceof String) &&
				params[3] instanceof CompositeProjectItemType) {
			
			String name = (String) params[0];
			if ("".equals(name)) {
				throw new DatabaseException("Empty item name");
			}
			
			String projectKey = (String) params[1];
			String parentKey = (String) params[2];
			CompositeProjectItemType itemType = (CompositeProjectItemType) params[3];
			
			return createCompositeProjectItem(name, projectKey, parentKey,
					itemType);
		} else {
			throw new DatabaseException("Incorrect parameter for creating" +
					" CompositeProjectItem.");
		}
			
	}


	private String createCompositeProjectItem(
			String name, String projectKey, String parentKey,
			CompositeProjectItemType itemType) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		CompositeProjectItem item;
		if (parentKey != null) {
			CompositeProjectItem parent = pm.getObjectById(CompositeProjectItem.class, parentKey);
			if (!parent.getProjectKey().equals(projectKey)) {
				throw new DatabaseException("Different projectKey and parent.projectKey");
			}
			
			for (Child child: parent.getChildren()) {
				ProjectItem pi = null;
				if (child.getType().equals(StoringType.FILE)) {
					pi = pm.getObjectById(File.class, child.getKey());
				} else if (child.getType().equals(StoringType.COMPOSITE_PROJECT_ITEM)) {
					pi = pm.getObjectById(CompositeProjectItem.class, child.getKey());
				} else {
					throw new DatabaseException("Unsupported child type");
				}
				
				if (pi.getName().equals(name)) {
					pm.close();
					throw new DatabaseException("Trying to create item with duplicate name");
				}
			}
			
			item = new CompositeProjectItem(name, projectKey, parentKey, itemType);
			pm.makePersistent(item);
			parent.addChild(new Child(item.getKey(), StoringType.COMPOSITE_PROJECT_ITEM));
		} else {
			item = new CompositeProjectItem(name, projectKey, parentKey, itemType);
			pm.makePersistent(item);
		}
		
		pm.close();
		return item.getKey();
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
		
		CompositeProjectItem projectItem = pm.getObjectById(CompositeProjectItem.class, key);
		if (projectItem.getParentKey() == null) {
			Project project = pm.getObjectById(Project.class, projectItem.getProjectKey());
			pm.deletePersistent(project);
		} else {
			CompositeProjectItem parent = pm.getObjectById(
					CompositeProjectItem.class, projectItem.getParentKey());
			parent.removeChild(new Child(key, StoringType.COMPOSITE_PROJECT_ITEM));
		}
		
		delete(pm, key, StoringType.COMPOSITE_PROJECT_ITEM);
		pm.close();
	}
	
	private void delete(PersistenceManager pm, String key, StoringType type) throws DatabaseException {
		if (type.equals(StoringType.COMPOSITE_PROJECT_ITEM)) {
			CompositeProjectItem projectItem = pm.getObjectById(CompositeProjectItem.class, key);
			List<Child> children = projectItem.getChildren();
			for (Child child: children) {
				delete(pm, child.getKey(), child.getType());
			}
			pm.deletePersistent(projectItem);
		} else if (type.equals(StoringType.FILE)) {
			File file = pm.getObjectById(File.class, key);
			pm.deletePersistent(file);
		} else {
			throw new DatabaseException("Unsupported proejct item type: " + type);
		}
		
	}
	
}
