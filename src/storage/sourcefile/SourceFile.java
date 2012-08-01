package storage.sourcefile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseException;
import storage.ProjectItem;

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

@PersistenceCapable(detachable = "true")
public class SourceFile extends ProjectItem {

	public SourceFile(String name, String projectKey, String parentKey,
			Date creationTime) {
		super(name, projectKey, parentKey);
		this.creationTime = creationTime;
		this.modificationTime = creationTime;
	}


	@Persistent(defaultFetchGroup = "true")
	private Date creationTime;
	
	@Persistent
	private Date modificationTime;
	
	@Persistent(defaultFetchGroup = "true")
	private BlobKey content;
	
	
	
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
	
	public SourceFileWriter openForWriting() throws DatabaseException {
		if (fileExists()) {
			clearFile();
		}

		FileService fileService = FileServiceFactory.getFileService();
		AppEngineFile file = createNewFile(fileService);
		FileWriteChannel writeChannel = openWriteChannel(fileService, file);
		return new SourceFileWriter(writeChannel, file, this);
	}

	private FileWriteChannel openWriteChannel(FileService fileService, AppEngineFile file)
			throws DatabaseException {
		try {
			FileWriteChannel writeChannel = fileService.openWriteChannel(file, true);
			return writeChannel;
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

	private AppEngineFile createNewFile(FileService fileService)
			throws DatabaseException {
		try {
			AppEngineFile file = fileService.createNewBlobFile("text/plain");
			return file;
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

	public SourceFileReader openForReading() throws DatabaseException {
		FileService fileService = FileServiceFactory.getFileService();
		AppEngineFile file = fileService.getBlobFile(content);
		FileReadChannel readChannel = openReadChannel(fileService, file);
		return new SourceFileReader(readChannel);
	}


	private FileReadChannel openReadChannel(FileService fileService, AppEngineFile file)
			throws DatabaseException {
		try {
			FileReadChannel readChannel = fileService.openReadChannel(file, true);
			return readChannel;
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
	
}
