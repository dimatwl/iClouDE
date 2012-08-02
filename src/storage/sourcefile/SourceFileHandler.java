package storage.sourcefile;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;

import storage.AbstractHandler;
import storage.Database;
import storage.DatabaseException;
import storage.PMF;
import storage.StoringType;

public class SourceFileHandler extends AbstractHandler {

	/**
	 * Creates new SourceFile object.
	 * There should be 3 parameters: (String name, String projectKey, String parentKey)
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
		Database.save(StoringType.SOURCE_FILE, tmp);
		
		return sourceFile.getKey();
	}
	
	/**
	 * Finds SourceFile with given key.
	 */
	@Override
	public SourceFile get(String key) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		SourceFile sourceFile = pm.getObjectById(SourceFile.class, key);
		if (sourceFile == null) {
			throw new DatabaseException("No such file");
		}
		
		SourceFile result = pm.detachCopy(sourceFile);
		pm.close();
		
		return result;
	}

	@Override
	public void delete(String key) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(key));
		pm.close();
	}

}
