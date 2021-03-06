package icloude.testing.database;

import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import storage.Database;
import storage.DatabaseException;
import storage.DatabaseObject;
import storage.PMF;
import storage.StoringType;
import storage.file.File;
import storage.project.Project;
import storage.projectitem.CompositeProjectItemType;
import storage.projectitem.CompositeProjectItem;

public abstract class Test {
	
	public static final String PASSED = "<font color=\"green\">passed";
	public static final String FAILED = "<font color=\"red\">failed";

	public abstract List<String> test();
	
	
	
	protected String createCompositeProjectItem(String folderName, String projectKey,
			String parentKey, CompositeProjectItemType type) throws TestException {
		try {
			String key = Database.create(StoringType.COMPOSITE_PROJECT_ITEM,
					folderName, projectKey, parentKey, type);
			return key;
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while creating project item. " +
					"Error message: " + e.getMessage());
		}
	}
	
	protected String createProject(String projectName, String projectType) throws TestException {
		try {
			String key = Database.create(StoringType.PROJECT, projectName, projectType);
			return key;
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while creating project. " +
					"Error message: " + e.getMessage());
		}
	}

	protected String createFile(String filename, String projectKey, String parentKey) throws TestException {
		try {
			String key = Database.create(StoringType.FILE, filename, projectKey, parentKey);
			return key;
		} catch (DatabaseException e) {
			throw new TestException( 
					"DatabaseException while creating file. " +
					"Error message: " + e.getMessage());
		}
	}
	
	protected CompositeProjectItem getCompositeProjectItem(String key) throws TestException {
		try {
			return (CompositeProjectItem) Database.get(StoringType.COMPOSITE_PROJECT_ITEM, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while getting project item from database. " +
					"Error message: " + e.getMessage());
		}
	}
	
	protected File getFile(String key) throws TestException {
		try {
			File file = (File) Database.get(StoringType.FILE, key);
			return file;
		} catch (DatabaseException e) {
			throw new TestException( 
					"DatabaseException while getting file from database. " +
					"Error message: " + e.getMessage());
		}
	}
	
	protected Project getProject(String key) throws TestException {
		try {
			return (Project) Database.get(StoringType.PROJECT, key);
		} catch (DatabaseException e) {
			throw new TestException( 
					"DatabaseException while getting project from database. " +
					"Error message: " + e.getMessage());
		}
	}
	
	protected void clearDatabase() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Extent<DatabaseObject> extent = pm.getExtent(DatabaseObject.class);
		Iterator<DatabaseObject> i = extent.iterator();
		while (i.hasNext()) {
			DatabaseObject obj = i.next();
			System.err.println(obj.getName());
			pm.deletePersistent(obj);
		}
		pm.close();
	}
}
