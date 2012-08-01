package storage.executablefile;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.project.ProjectItem;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class ExecutableFile extends ProjectItem {

	public ExecutableFile(String name, Key projectKey, ProjectItem parent,
			Date creationTime) {
		super(name, projectKey, parent);
		this.creationTime = creationTime;
	}

	private Date creationTime;
	
	@Persistent
	private BlobKey file;

	
	public Date getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	public BlobKey getFile() {
		return file;
	}
	
	public void setFile(BlobKey file) {
		this.file = file;
	}
}

