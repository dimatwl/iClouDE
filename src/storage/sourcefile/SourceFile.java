package storage.sourcefile;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import storage.project.Project;
import storage.project.ProjectItem;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class SourceFile extends ProjectItem {
	

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	public Key getKey() {
		return key;
	}
	
	@Persistent
	private Date creationTime;
	
	@Persistent
	private Date modificationTime;
	
	@Persistent
	private BlobKey content;
	
	@Persistent
	private int revision;
	
	@Persistent
	private String language;
	
	@Persistent
	private Project project;
	
	@Persistent
	private ProjectItem parent;
	
	
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
	
	public BlobKey getContent() {
		return content;
	}
	
	public void setContent(BlobKey content) {
		this.content = content;
	}
	
	public int getRevision() {
		return revision;
	}
	
	public void setRevision(int revision) {
		this.revision = revision;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public ProjectItem getParent() {
		return parent;
	}
	
	public void setParent(ProjectItem parent) {
		this.parent = parent;
	}
	
	public void setKey(Key key) {
		this.key = key;
	}
}
