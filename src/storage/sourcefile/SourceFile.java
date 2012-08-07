package storage.sourcefile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseException;
import storage.projectitem.ProjectItem;

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

/**
 * Class representing source files in database.
 * <br/><br/>
 * For creating new SourceFile you need to provide its name,
 * database key of the project where this file should be created,
 * database key of the parent project item in which this file should be created,
 * and time of creation.
 * <br/><br/>
 * For writing to the file use method openForWriting which returns 
 * SourceFileWriter. After writing it should be closed to save written
 * information.
 * <br/><br/>
 * For reading file use method openForReading which returns SourceFileReader.
 * After reading it should be closed.
 * @author Sergey
 *
 */
@PersistenceCapable(detachable = "true")
public class SourceFile extends ProjectItem {

	public SourceFile(String name, String projectKey, String parentKey,
			Date creationTime) {
		super(name, projectKey, parentKey);
		this.creationTime = creationTime;
		this.modificationTime = creationTime;
	}


	@Persistent
	private Date creationTime;
	
	@Persistent
	private Date modificationTime;
	
	@Persistent
	private BlobKey content;
	
	
	
	public Date getCreationTime() {
		return creationTime;
	}
	
	public Date getModificationTime() {
		return modificationTime;
	}
	
	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	
	protected void setContent(BlobKey content) {
		this.content = content;
	}
	
	/**
	 * Returns SourceFileWriter for writing to this file. This writer
	 * should be closed after writing to save written information.
	 * @return SourceFileWriter which can be used for writing to the file
	 * @throws DatabaseException if some errors occurs while creating writer
	 */
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

	/**
	 * Returns SourceFileReader for reading this file. It should be closed
	 * after reading.
	 * @return SourceFileReader which can be used for reading file
	 * @throws DatabaseException if some error occurs while creating reader
	 */
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
