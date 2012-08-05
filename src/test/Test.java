package test;

import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.folder.Folder;
import storage.pack.Package;
import storage.sourcefile.SourceFile;

public abstract class Test {
	
	public static final String PASSED = "passed";
	public static final String FAILED = "failed";

	public abstract List<String> test();
	
	
	
	protected String createFolder(String folderName, String projectKey, String parentKey) throws TestException {
		try {
			String key = Database.create(StoringType.FOLDER, folderName, projectKey, parentKey);
			return key;
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while creating folder. " +
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
	
	protected String createPackage(String packageName, String projectKey, String parentKey) throws TestException {
		try {
			String key =  Database.create(StoringType.PACKAGE, packageName, projectKey, parentKey);
			return key;
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while creating package. " +
					"Error message: " + e.getMessage());
		}
	}
	
	
	
	protected Folder getFolder(String key) throws TestException {
		try {
			return (Folder) Database.get(StoringType.FOLDER, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while getting folder from database. " +
					"Error message: " + e.getMessage());
		}
	}
	
	protected Package getPackage(String key) throws TestException {
		try {
			return (Package) Database.get(StoringType.PACKAGE, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while getting package from database. " +
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
}
