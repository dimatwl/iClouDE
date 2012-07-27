package storage.sourcefile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.Channels;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.LockException;

import storage.DatabaseException;
import storage.Handler;

/**
 * Handles opening and closing source files for reading.
 * @author Sergey
 *
 */
public class SourceFileReadingHandler implements Handler {

	@Override
	/**
	 * Opens source file for reading
	 * @param params - parameters defining which file should be opened
	 * @throws DatabaseException is parameters are incorrect or file
	 * not found
	 */
	public SourceFileReader get(Object... params) throws DatabaseException {
		FileService fileService = FileServiceFactory.getFileService();
		AppEngineFile file = null; // TODO
		
		FileReadChannel readChannel = null;
		try {
			readChannel = fileService.openReadChannel(file, true);
		} catch (FileNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		} catch (LockException e) {
			throw new DatabaseException(e.getMessage());
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
		BufferedReader reader = new BufferedReader(Channels.newReader(readChannel, "UTF8"));
		
		return new SourceFileReader(reader, readChannel);
	}
	

	@Override
	/**
	 * Closes specified file.
	 * @param toSave - source file to be saved
	 */
	public void save(Object toSave) throws DatabaseException {
		try {
			((SourceFileReader)toSave).close();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
