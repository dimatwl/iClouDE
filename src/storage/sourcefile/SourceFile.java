package storage.sourcefile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import storage.DatabaseException;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.FinalizationException;
import com.google.appengine.api.files.LockException;

@PersistenceCapable
public class SourceFile {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	public Key getKey() {
		return key;
	}
	
//	@Persistent
//	private Date creationTime;
//	
//	@Persistent
//	private Date modificationTime;
//	
	@Persistent
	private BlobKey content;
//	
//	@Persistent
//	private int revision;
//	
//	@Persistent
//	private String language;
//	
//	@Persistent
//	private Project project;
//	
//	@Persistent
//	private ProjectItem parent;
//	
//	
//	public Date getCreationTime() {
//		return creationTime;
//	}
//	
//	public void setCreationTime(Date creationTime) {
//		this.creationTime = creationTime;
//	}
//	
//	public Date getModificationTime() {
//		return modificationTime;
//	}
//	
//	public void setModificationTime(Date modificationTime) {
//		this.modificationTime = modificationTime;
//	}
//	
//	public BlobKey getContent() {
//		return content;
//	}
//	
//	public void setContent(BlobKey content) {
//		this.content = content;
//	}
//	
//	public int getRevision() {
//		return revision;
//	}
//	
//	public void setRevision(int revision) {
//		this.revision = revision;
//	}
//	
//	public String getLanguage() {
//		return language;
//	}
//	
//	public void setLanguage(String language) {
//		this.language = language;
//	}
//	
//	public Project getProject() {
//		return project;
//	}
//	
//	public void setProject(Project project) {
//		this.project = project;
//	}
//	
//	public ProjectItem getParent() {
//		return parent;
//	}
//	
//	public void setParent(ProjectItem parent) {
//		this.parent = parent;
//	}
//	
//	public void setKey(Key key) {
//		this.key = key;
//	}
//	
//	
//	
	@NotPersistent
	private FileWriteChannel writeChannel;
	
	@NotPersistent
	private FileReadChannel readChannel;
	
	@NotPersistent
	private PrintWriter writer;
	
	@NotPersistent
	private BufferedReader reader;
	
	@NotPersistent
	private AppEngineFile file;
	
	public PrintWriter getWriter() throws DatabaseException {
		if (writer != null) {
			return writer;
		}
		
		if (reader != null) {
			throw new DatabaseException("Can't read and write to the same file");
		}
		
		FileService fileService = FileServiceFactory.getFileService();
		try {
			file = fileService.createNewBlobFile("text/plain");
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
		
		try {
			writeChannel = fileService.openWriteChannel(file, true);
		} catch (FileNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		} catch (FinalizationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (LockException e) {
			throw new DatabaseException(e.getMessage());
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
		
		writer = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));

		return writer;
	}
	
	public BufferedReader getReader() throws DatabaseException {
		if (reader != null) {
			return reader;
		}
		
		if (writer != null) {
			throw new DatabaseException("Can't read and write to the same file");
		}
		
		FileService fileService = FileServiceFactory.getFileService();
		AppEngineFile file = fileService.getBlobFile(content);
		
		try {
			readChannel = fileService.openReadChannel(file, true);
		} catch (FileNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		} catch (FinalizationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (LockException e) {
			throw new DatabaseException(e.getMessage());
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
		
		reader = new BufferedReader(Channels.newReader(readChannel, "UTF8"));

		return reader;
	}
	
	public void close() throws DatabaseException {
		try {
			if (writer != null) {
				writer.close();
				writeChannel.closeFinally();
				FileService fileService = FileServiceFactory.getFileService();
				content = fileService.getBlobKey(file); 
			} else if (reader != null) {
				reader.close();
				readChannel.close();
			}
		} catch (IllegalStateException e) {
			throw new DatabaseException(e.getMessage());
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			writer = null;
			reader = null;
			writeChannel = null;
			readChannel = null;
			
		}
	}
}
