package storage.sourcefile;

import java.io.IOException;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;

import storage.DatabaseException;
import storage.Handler;
import storage.PMF;

public class SourceFileHandler implements Handler {

	@Override
	public SourceFile get(Object... params) throws DatabaseException {
		Key key = KeyFactory.stringToKey((String) params[0]);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		SourceFile sourceFile = (SourceFile)pm.getObjectById(key);
		if (sourceFile == null) {
			throw new DatabaseException("No such file");
		}
		return sourceFile;
	}

	@Override
	public void save(Object toSave) throws DatabaseException {
		((SourceFile)toSave).close();
	}

	@Override
	public String create(Object... params) throws DatabaseException {
		
		
		SourceFile sourceFile = new SourceFile();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(sourceFile);
		pm.close();
		
		return KeyFactory.keyToString(sourceFile.getKey());
	}

}
