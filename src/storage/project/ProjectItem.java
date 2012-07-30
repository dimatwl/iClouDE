package storage.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseObject;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class ProjectItem extends DatabaseObject {
	
//	@Persistent
//	private List<ProjectItem> children = new ArrayList<ProjectItem>();
//	
//	
//	public List<ProjectItem> getChildren() {
//		return children;
//	}
//	
//	public void set(List<ProjectItem> childern) {
//		this.children = childern;
//	}
	
	@Persistent
	private ProjectItem parent;
	
	public ProjectItem getParent() {
		return parent;
	}
	
	public void setParent(ProjectItem parent) {
		this.parent = parent;
	}
	
}
