package storage.project;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseObject;
import storage.PMF;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class ProjectItem extends DatabaseObject {
	
	public ProjectItem(String name, Key projectKey, ProjectItem parent) {
		super(name);
		this.projectKey = projectKey;
		this.parent = parent;
	}
	
	
	@Persistent
	private List<Key> childrenKeys = new ArrayList<Key>();
	
	@Persistent
	private Key projectKey;
	
	
	public List<ProjectItem> getChildren() {
		List<ProjectItem> children = new ArrayList<ProjectItem>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		for (Key key: childrenKeys) {
			children.add((ProjectItem)pm.getObjectById(key));
		}
		return children;
	}
	
	public Key getProject() {
		return projectKey;
	}

	public void setProject(Key projectKey) {
		this.projectKey = projectKey;
	}

	public void addChild(ProjectItem child) {
		childrenKeys.add(child.getKey());
	}
	
	@Persistent
	private ProjectItem parent;
	
	public ProjectItem getParent() {
		return parent;
	}
	
	public void setParent(ProjectItem parent) {
		this.parent = parent;
	}

}
