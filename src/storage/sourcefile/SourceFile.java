package storage.sourcefile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.util.Date;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseException;
import storage.project.Project;
import storage.project.ProjectItem;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.FinalizationException;
import com.google.appengine.api.files.LockException;

@PersistenceCapable
public class SourceFile extends ProjectItem {
	
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

		if (fileExists()) {
			clearFile();
		}			

		FileService fileService = FileServiceFactory.getFileService();
		createNewFile(fileService);
		openWriteChannel(fileService);
		createWriter();

		return writer;
	}

	private void createWriter() {
		writer = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));
	}

	private void openWriteChannel(FileService fileService)
			throws DatabaseException {
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
	}

	private void createNewFile(FileService fileService)
			throws DatabaseException {
		try {
			file = fileService.createNewBlobFile("text/plain");
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	private void clearFile() {
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		blobstoreService.delete(content);
	}
	
	private boolean fileExists() {
		return content != null;
	}

	public BufferedReader getReader() throws DatabaseException {
		if (reader != null) {
			return reader;
		}
		
		if (writer != null) {
			throw new DatabaseException("Can't read and write to the same file");
		}
		
		FileService fileService = FileServiceFactory.getFileService();
		file = fileService.getBlobFile(content);
		openReadChannel(fileService, file);
		createReader();

		return reader;
	}

	private void createReader() {
		reader = new BufferedReader(Channels.newReader(readChannel, "UTF8"));
	}

	private void openReadChannel(FileService fileService, AppEngineFile file)
			throws DatabaseException {
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
