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
	
	@Persistent
	private List<Key> childrenKeys = new ArrayList<Key>();
	
	
	public List<ProjectItem> getChildren() {
		List<ProjectItem> children = new ArrayList<ProjectItem>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		for (Key key: childrenKeys) {
			children.add(pm.getObjectById(ProjectItem.class, key));
		}
		return children;
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
