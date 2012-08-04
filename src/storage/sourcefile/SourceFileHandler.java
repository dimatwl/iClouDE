package storage.sourcefile;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;

import storage.Database;
import storage.DatabaseException;
import storage.PMF;
import storage.StoringType;
import storage.project.SimpleProjectItemHandler;

/**
 * This class provides implementations of all database operations
 * with SourceFile objects (create, get, update, delete).
 * @author Sergey
 *
 */
public class SourceFileHandler extends SimpleProjectItemHandler {
	
	public SourceFileHandler() {
		super(SourceFile.class);
	}

	/**
	 * Creates new SourceFile object.
	 * <br/><br/>
	 * There should be 3 parameters:<br/>
	 * String name - name of the file to create<br/>
	 * String projectKey - database key of the project where this file should be created<br/>
	 * String parentKey - database key of the project item in which this file should be created
	 */
	@Override
	public String create(Object... params) throws DatabaseException {
		if (params.length != 3) {
			throw new DatabaseException("Incorrent number of parameters" +
					" for creating new file. There should be 3 parameters, but" +
					params.length + " parameters are given");
		}
		
		if (!(params[0] instanceof String)) {
			throw new DatabaseException("First parameter should be the name of" +
					" the file. Its type must be String");
		}
		
		if (!(params[1] instanceof String)) {
			throw new DatabaseException("Second parameter should be the key of" +
					" the project. Its type must be String");
		}
		
		if (!(params[2] instanceof String)) {
			throw new DatabaseException("Third parameter should be the key of" +
					" the parent ProjectItem. Its type must be String");
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String name = (String)params[0];
		String projectKey = (String)params[1];
		String parentKey = projectKey;
		
		SourceFile sourceFile = new SourceFile(name, projectKey, parentKey,
				new Date());
		pm.makePersistent(sourceFile);
		SourceFile tmp = pm.detachCopy(sourceFile);
		pm.close();
		
		SourceFileWriter writer = tmp.openForWriting();
		try {
			writer.close();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
		Database.update(StoringType.SOURCE_FILE, tmp);
		
		return sourceFile.getKey();
	}
	
	/**
	 * Saves changes in file to database.
	 * @param toSave - SourceFile to be saved
	 * @throws DatabaseException if some error occurs in database
	 */
	@Override
	public void update(Object toSave) throws DatabaseException {
		super.update(toSave);
		updateModificationTime(toSave);
	}

	private void updateModificationTime(Object toSave) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		SourceFile file = pm.getObjectById(SourceFile.class, ((SourceFile)toSave).getKey());
		file.setModificationTime(new Date());
		pm.close();
	}

}
