package storage.executablefile;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import storage.project.ProjectItem;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class ExecutableFile extends ProjectItem {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
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
	
	public void setKey(Key key) {
		this.key = key;
	}

	public Key getKey() {
		return key;
	}
}

