package storage.sourcefile;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;

import storage.DatabaseException;
import storage.Handler;
import storage.PMF;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class SourceFileHandler implements Handler {

	@Override
	public SourceFile get(Object... params) throws DatabaseException {
		Key key = KeyFactory.stringToKey((String) params[0]);
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
	 * There should be 5 parameters: (String name, Key projectKey, Key parentKey,
	 * Date creationTime, String language)
	 */
	@Override
	public String create(Object... params) throws DatabaseException {
		if (params.length != 5) {
			throw new DatabaseException("Incorrent number of parameters" +
					" for creating new file. There should be 5 parameters, but" +
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
		
		if (!(params[3] instanceof Date)) {
			throw new DatabaseException("Fourth parameter should be time of" +
					" creating file. Its type must be Date");
		}
		
		if (!(params[4] instanceof String)) {
			throw new DatabaseException("Fifth parameter should be the language." +
					" Its type must be String");
		}
			
		String name = (String)params[0];
		Key projectKey = null;//KeyFactory.stringToKey((String)params[1]);
		Key parentKey = null;//KeyFactory.stringToKey((String)params[2]);
		Date creationTime = (Date)params[3];
		String language = (String)params[4];
		
		SourceFile sourceFile = new SourceFile(name, projectKey, parentKey,
				creationTime, language);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(sourceFile);
		pm.close();
		SourceFileWriter writer = sourceFile.openForWriting();
		try {
			writer.close();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
		
		return KeyFactory.keyToString(sourceFile.getKey());
	}

}
