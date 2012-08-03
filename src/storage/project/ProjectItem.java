package storage.project;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseObject;


@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class ProjectItem extends DatabaseObject {
	
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

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}
	
	public String getParentKey() {
		return parentKey;
	}
	
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

}
