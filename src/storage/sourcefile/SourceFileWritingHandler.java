package storage.sourcefile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.FinalizationException;
import com.google.appengine.api.files.LockException;

import storage.DatabaseException;
import storage.Handler;

/**
 * Handles opening and closing source files for writing.
 * @author Sergey
 *
 */
public class SourceFileWritingHandler implements Handler {

	@Override
	/**
	 * Opens source file for writing
	 * @param params - parameters defining which file should be opened
	 * @throws DatabaseException is parameters are incorrect or file
	 * not found
	 */
	public SourceFileWriter get(Object... params) throws DatabaseException {

		// creating new file
		FileService fileService = FileServiceFactory.getFileService();
		AppEngineFile file = null;
		try {
			file = fileService.createNewBlobFile("text/plain");
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
		
		// or opening existing file
		// TODO
		
		FileWriteChannel writeChannel = null;
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
		
		PrintWriter writer = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));

		return new SourceFileWriter(writer, writeChannel);
	}
	
	

	@Override
	/**
	 * Save changes to database and closes specified file.
	 * @param toSave - source file to be saved
	 */
	public void save(Object toSave) throws DatabaseException {
		try {
			((SourceFileWriter)toSave).close();
		} catch (IllegalStateException e) {
			throw new DatabaseException(e.getMessage());
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
