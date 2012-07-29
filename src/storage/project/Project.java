package storage.project;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseObject;

@PersistenceCapable
public class Project extends DatabaseObject {

	@Persistent
	private Date creationTime;
	
	@Persistent
	private Date modificationTime;
	
	@Persistent
	private ProjectItem root;

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public ProjectItem getRoot() {
		return root;
	}

	public void setRoot(ProjectItem root) {
		this.root = root;
	}
	
}
