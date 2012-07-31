package storage.project;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

import storage.DatabaseObject;

@PersistenceCapable
public class Project extends DatabaseObject {

	public Project(String name, Date creationTime, Key rootKey) {
		super(name);
		this.creationTime = creationTime;
		this.rootKey = rootKey;
		this.modificationTime = creationTime;
	}

	@Persistent
	private Date creationTime;
	
	@Persistent
	private Date modificationTime;
	
	@Persistent
	private Key rootKey;

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

	public Key getRootKey() {
		return rootKey;
	}

	public void setRootKey(Key rootKey) {
		this.rootKey = rootKey;
	}

	
}
