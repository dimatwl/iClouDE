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
import storage.projectitem.ProjectItem;

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
	 * Creates new File object.
	 * <br/><br/>
	 * It's possible to create two type of files:<br/><br/>
	 * 1. File in project.<br/>
	 * There should be 3 parameters:<br/>
	 * String name - name of the file to create<br/>
	 * String projectKey - database key of the project where this file should be created<br/>
	 * String parentKey - database key of the project item in which this file should be created<br/><br/>
	 * 2. Simple file.<br/>
	 * There should be 1 parameter - name of the file
	 */
	@Override
	public String create(Object... params) throws DatabaseException {
		if (params.length == 3 &&
				params[0] instanceof String &&
				params[1] instanceof String &&
				params[2] instanceof String) {
			
			String name = (String) params[0];
			String projectKey = (String) params[1];
			String parentKey = (String) params[2];
			return createFileInProject(name, projectKey, parentKey);
		} else if (params.length == 1 &&
				params[0] instanceof String) {
			
			String name = (String) params[0];
			return createSimpleFile(name);
		} else {
			throw new DatabaseException("Incorrect parameters for creating file");
		}
		
	}

	private String createSimpleFile(String name) throws DatabaseException {
		if (name.isEmpty()) {
			throw new DatabaseException("Empty filename");
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		File file = new File(name, null, null, new Date());
		pm.makePersistent(file);
		pm.close();
		return file.getKey();
	}

	private String createFileInProject(String name, String projectKey,
			String parentKey) throws DatabaseException {
		if (name.isEmpty()) {
			throw new DatabaseException("Empty filename");
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			CompositeProjectItem parent = getParent(pm, parentKey);
			
			if (!parent.getProjectKey().equals(projectKey)) {
				throw new DatabaseException("Different projectKey and parent.projectKey");
			}
	
			if (!duplicateName(pm, name, parent)) {
				File sourceFile = createFile(name, projectKey, parentKey, pm,
						parent);
				return sourceFile.getKey();
			} else {
				throw new DatabaseException("Can't create file with name " + name);
			}
		} finally {
			pm.close();
		}
	}

	private File createFile(String name, String projectKey, String parentKey,
			PersistenceManager pm, CompositeProjectItem parent)
			throws DatabaseException {
		File sourceFile = new File(name, projectKey, parentKey,
				new Date());
		pm.makePersistent(sourceFile);
		parent.addChild(new Child(sourceFile.getKey(), StoringType.FILE));
		createEmptyContent(sourceFile);
		return sourceFile;
	}

	private CompositeProjectItem getParent(PersistenceManager pm,
			String parentKey) {
		CompositeProjectItem parent = pm.getObjectById(
				CompositeProjectItem.class, parentKey);
		return parent;
	}

	private boolean duplicateName(PersistenceManager pm, String name,
			CompositeProjectItem parent) {
		boolean duplicateName = false;
		for (Child child: parent.getChildren()) {
			ProjectItem item = null;
			
			if (child.getType().equals(StoringType.FILE)) {
				item = pm.getObjectById(File.class, child.getKey());
			} else {
				item = pm.getObjectById(CompositeProjectItem.class, child.getKey());
			}

			if (item.getName().equals(name)) {
				duplicateName = true;
			}
		}
		
		return duplicateName;
	}

	private void createEmptyContent(File sourceFile) throws DatabaseException {
		FileWriter writer = sourceFile.openForWriting();
		try {
			writer.close();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
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
		
		File file = pm.getObjectById(File.class, key);
		
		if (file.getParentKey() != null) {
			CompositeProjectItem parent = pm.getObjectById(
					CompositeProjectItem.class, file.getParentKey());
			parent.removeChild(new Child(key, StoringType.FILE));
		}
		
		pm.deletePersistent(file);
		pm.close();
	}

}
