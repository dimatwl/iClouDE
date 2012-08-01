package storage.project;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.ProjectItem;

@PersistenceCapable(detachable="true")
public class Project extends ProjectItem {

	public Project(String name, Date creationTime, String type) {
		super(name, null, null);
		this.creationTime = creationTime;
		this.type = type;
	}

	@Persistent
	private Date creationTime;
	
	@Persistent
	private Date modificationTime;
	
	@Persistent
	private String type;
	

	public String getProjectType() {
		return type;
	}

	public void setProjectType(String projectType) {
		this.type = projectType;
	}

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

}
