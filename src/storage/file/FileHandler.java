package storage.file;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;

import storage.AbstractHandler;
import storage.Child;
import storage.DatabaseException;
import storage.PMF;
import storage.StoringType;
import storage.projectitem.CompositeProjectItem;

/**
 * This class provides implementations of all database operations
 * with SourceFile objects (create, get, update, delete).
 * @author Sergey
 *
 */
public class FileHandler extends AbstractHandler {
	
	public FileHandler() {
		super(File.class);
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
		checkFileCreateParams(params);
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String name = (String)params[0];
		if ("".equals(name)) {
			throw new DatabaseException("Empty filename");
		}
		
		
		String projectKey = (String)params[1];
		String parentKey = (String)params[2];
		File sourceFile = new File(name, projectKey, parentKey,
				new Date());
		
		
		CompositeProjectItem parent = pm.getObjectById(
				CompositeProjectItem.class, parentKey);
		
		if (!parent.getProjectKey().equals(projectKey)) {
			throw new DatabaseException("Different projectKey and parent.projectKey");
		}

		for (Child child: parent.getChildren()) {
			if (child.getType().equals(StoringType.FILE)) {
				File file = pm.getObjectById(File.class, child.getKey());
				if (file.getName().equals(name)) {
					pm.close();
					throw new DatabaseException("Trying to create duplicate file");
				}
			}
		}
		
		
		pm.makePersistent(sourceFile);
		parent.addChild(new Child(sourceFile.getKey(), StoringType.FILE));
		
		createEmptyContent(sourceFile);
		
		pm.close();
		
		return sourceFile.getKey();
	}

	private void createEmptyContent(File sourceFile) throws DatabaseException {
		FileWriter writer = sourceFile.openForWriting();
		try {
			writer.close();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	private void checkFileCreateParams(Object... params) throws DatabaseException {
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
		File file = pm.getObjectById(File.class, ((File)toSave).getKey());
		file.setModificationTime(new Date());
		pm.close();
	}
	
	
	/**
	 * Deletes object with specified key from database
	 * @param key - key of the object to delete
	 * @throws DatabaseException if object wasn't found
	 */
	public void delete(String key) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		File sourceFile = pm.getObjectById(File.class, key);
		
		CompositeProjectItem parent = pm.getObjectById(
				CompositeProjectItem.class, sourceFile.getParentKey());
		parent.removeChild(new Child(key, StoringType.FILE));
		
		pm.deletePersistent(sourceFile);
		pm.close();
	}

}
