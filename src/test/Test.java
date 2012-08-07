package test;

import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;
import storage.projectitem.CompositeProjectItemType;
import storage.projectitem.CompositeProjectItem;
import storage.sourcefile.SourceFile;

public abstract class Test {
	
	public static final String PASSED = "passed";
	public static final String FAILED = "failed";

	public abstract List<String> test();
	
	
	
	protected String createCompositeProjectItemType(String folderName, String projectKey,
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
			String key = Database.create(StoringType.SOURCE_FILE, filename, projectKey, parentKey);
			return key;
		} catch (DatabaseException e) {
			throw new TestException( 
					"DatabaseException while creating file. " +
					"Error message: " + e.getMessage());
		}
	}
	
//	protected String createPackage(String packageName, String projectKey, String parentKey) throws TestException {
//		try {
//			String key =  Database.create(StoringType.PACKAGE, packageName, projectKey, parentKey);
//			return key;
//		} catch (DatabaseException e) {
//			throw new TestException(
//					"DatabaseException while creating package. " +
//					"Error message: " + e.getMessage());
//		}
//	}
//	
//	
//	
//	protected Folder getFolder(String key) throws TestException {
//		try {
//			return (Folder) Database.get(StoringType.FOLDER, key);
//		} catch (DatabaseException e) {
//			throw new TestException(
//					"DatabaseException while getting folder from database. " +
//					"Error message: " + e.getMessage());
//		}
//	}
//	
	protected CompositeProjectItem getCompositeProjectItem(String key) throws TestException {
		try {
			return (CompositeProjectItem) Database.get(StoringType.COMPOSITE_PROJECT_ITEM, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while getting project item from database. " +
					"Error message: " + e.getMessage());
		}
	}
	
	protected SourceFile getFile(String key) throws TestException {
		try {
			SourceFile file = (SourceFile) Database.get(StoringType.SOURCE_FILE, key);
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
}
