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
public class ProjectItem extends DatabaseObject {
	
	public ProjectItem(String name, Key projectKey, Key parentKey) {
		super(name);
		this.projectKey = projectKey;
		this.parentKey = parentKey;
	}
	
	
	@Persistent
	private List<Key> childrenKeys = new ArrayList<Key>();
	
	@Persistent
	private Key projectKey;
	
	
	public List<ProjectItem> getChildren() {
		List<ProjectItem> children = new ArrayList<ProjectItem>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		for (Key key: childrenKeys) {
			children.add(pm.getObjectById(ProjectItem.class, key));
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
	private Key parentKey;
	
	public Key getParent() {
		return parentKey;
	}
	
	public void setParent(Key parentKey) {
		this.parentKey = parentKey;
	}

}
