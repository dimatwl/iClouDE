package storage.projectitem;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseObject;


/**
 * Class representing some item of the project such as folder or source file.
 * Contains two properties:<br/>
 * String projectKey - database key of the project where this item is located<br/>
 * String parentKey - database key of the another project item in which this item is located
 * @author Sergey
 *
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class ProjectItem extends DatabaseObject {
	
	public ProjectItem(String name, String projectKey, String parentKey) {
		super(name);
		this.projectKey = projectKey;
		this.parentKey = parentKey;
	}
	
	

	@Persistent
	private String projectKey;
	
	@Persistent
	private String parentKey;
	

	public String getProjectKey() {
		return projectKey;
	}
	
	public String getParentKey() {
		return parentKey;
	}
}
