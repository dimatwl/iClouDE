package storage.sourcefile;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;

import storage.DatabaseException;
import storage.Handler;
import storage.PMF;

public class SourceFileHandler implements Handler {

	/**
	 * Finds SourceFile with given key.
	 * There should be 1 parameter: (String key)
	 */
	@Override
	public SourceFile get(Object... params) throws DatabaseException {
		if (params.length != 1) {
			throw new DatabaseException("Incorrect number of parameters to get source file." +
					" There should be 1 parameter of String type - project Key");
		}
		
		if (!(params[0] instanceof String)) {
			throw new DatabaseException("Incorrect first parameter type to get source file." +
					" Type of the first parameter should be String.");
		}
		
		String key = (String) params[0];
		PersistenceManager pm = PMF.get().getPersistenceManager();
		SourceFile sourceFile = pm.getObjectById(SourceFile.class, key);
		if (sourceFile == null) {
			throw new DatabaseException("No such file");
		}
		return sourceFile;
	}

	@Override
	public void save(Object toSave) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.refresh(toSave);
		pm.close();
	}

	/**
	 * Creates new SourceFile object.
	 * There should be 3 parameters: (String name, Key projectKey, Key parentKey)
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
		pm.close();
		SourceFileWriter writer = sourceFile.openForWriting();
		try {
			writer.close();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
		
		return sourceFile.getKey();
	}

}
