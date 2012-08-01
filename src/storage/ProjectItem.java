package storage;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class ProjectItem extends DatabaseObject {
	
	public ProjectItem(String name, String projectKey, String parentKey) {
		super(name);
		this.projectKey = projectKey;
		this.parentKey = parentKey;
	}
	
	@Persistent
	private List<String> childrenKeys = new ArrayList<String>();
	
	@Persistent(defaultFetchGroup = "true")
	private String projectKey;
	
	
	public List<ProjectItem> getChildren() {
		List<ProjectItem> children = new ArrayList<ProjectItem>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		for (String key: childrenKeys) {
			children.add((ProjectItem)pm.getObjectById(key));
		}
		return children;
	}
	
	public String getProject() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public void addChild(ProjectItem child) {
		childrenKeys.add(child.getKey());
	}
	
	@Persistent
	private String parentKey;
	
	public String getParent() {
		return parentKey;
	}
	
	public void setParent(String parentKey) {
		this.parentKey = parentKey;
	}

}
