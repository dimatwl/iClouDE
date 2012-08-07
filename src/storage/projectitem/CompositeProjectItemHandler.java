package storage.projectitem;

import java.util.List;

import javax.jdo.PersistenceManager;

import storage.AbstractHandler;
import storage.Child;
import storage.Database;
import storage.DatabaseException;
import storage.PMF;
import storage.StoringType;
import storage.project.Project;
import storage.sourcefile.SourceFile;

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
	 * String parentKey - database key of the project item in which this item should be created
	 * ProjectItemType itemType - type of the item to create (folder, package, project)
	 */
	@Override
	public String create(Object... params) throws DatabaseException {
		if (params.length != 4) {
			throw new DatabaseException("Incorrent number of parameters" +
					" for creating new CompositeProjectItem. There should be 4 parameters, but " +
					params.length + " parameters are given");
		}
		
		if (!(params[0] instanceof String)) {
			throw new DatabaseException("First parameter should be the name of" +
					" the CompositeProjectItem. Its type must be String");
		}
		
		if (!(params[1] instanceof String)) {
			throw new DatabaseException("Second parameter should be the key of" +
					" the project. Its type must be String");
		}

		if (params[2] != null && !(params[2] instanceof String)) {
			throw new DatabaseException("Third parameter should be the key of" +
					" the parent CompositeProjectItem. Its type must be String");
		}
		
		if (!(params[3] instanceof CompositeProjectItemType)) {
			throw new DatabaseException("Fourth parameter should be the type of" +
					" the CompositeProjectItem to create. Its type must be ComopsiteProjectItemType");
		}
			
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String name = (String) params[0];
		String projectKey = (String) params[1];
		String parentKey = (String) params[2];
		CompositeProjectItemType itemType = (CompositeProjectItemType) params[3];
		
		CompositeProjectItem item = new CompositeProjectItem(name, projectKey, parentKey, itemType);
		pm.makePersistent(item);
		pm.close();
		
		if (parentKey != null) {
			CompositeProjectItem parent = (CompositeProjectItem)
					Database.get(StoringType.COMPOSITE_PROJECT_ITEM, parentKey);
			parent.addChild(new Child(item.getKey(), StoringType.COMPOSITE_PROJECT_ITEM));
			Database.update(StoringType.COMPOSITE_PROJECT_ITEM, parent);
		}
		
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
		} else if (type.equals(StoringType.SOURCE_FILE)) {
			SourceFile file = pm.getObjectById(SourceFile.class, key);
			pm.deletePersistent(file);
		} else {
			throw new DatabaseException("Unsupported proejct item type: " + type);
		}
		
	}
	
}
