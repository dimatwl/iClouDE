package storage.sourcefile;

import storage.DatabaseException;
import storage.Handler;

/**
 * Handles common operations with source files (creating,
 * opening)
 * @author Sergey
 *
 */
public class SourceFileHandler implements Handler {

	@Override
	/**
	 * Creates or opens source file.
	 * @param params - parameters defining what should be done:
	 * (String, FileOpeningMode) - create or open file with specified name
	 */
	public SourceFile get(Object... params) throws DatabaseException {
		if (params.length == 2) {
			if (params[0] instanceof String && params[1] instanceof FileOpeningMode) {
				String name = (String) params[0];
				FileOpeningMode mode = (FileOpeningMode) params[1];
				return get(name, mode);
			} else {
				throw new DatabaseException("Incorrect parameters types");
			}
		} else {
			throw new DatabaseException("Incorrect parameters number");
		}
	}
	
	private SourceFile get(String filename, FileOpeningMode mode) throws DatabaseException {
		if (FileOpeningMode.WRITE.equals(mode)) {
			return null;
		} else if (FileOpeningMode.READ.equals(mode)) {
			return null;
		} else {
			throw new DatabaseException("Incorrect file opening mode: " + mode);
		}
	}
	

	@Override
	/**
	 * Saves specified file to database.
	 * @param toSave - source file to be saved
	 */
	public void save(Object toSave) {
		// TODO Auto-generated method stub
		
	}

}
