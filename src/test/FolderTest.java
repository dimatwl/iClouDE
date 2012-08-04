package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.folder.Folder;

public class FolderTest implements Test {
	
	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(createNewFolder());
		
		return result;
	}

	
	// test methods
	
	private String createNewFolder() {
		String result = "Creating new folder: ";
		
		String key = null;
		Folder folder = null;
		try {
			key = createFolder("FolderName", "ProjectKey", "ParentKey");
			folder = getFolder(key);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}
		
		if (!folder.getKey().equals(key)) {
			return (result + Test.FAILED + 
					" - folder wasn't created or it's " +
					"impossible to get it from database");
		}
		
		return result + Test.PASSED;
	}

	
	
	
	
	// utility methods
	
	private Folder getFolder(String key) throws TestException {
		try {
			return (Folder) Database.get(StoringType.FOLDER, key);
		} catch (DatabaseException e) {
			throw new TestException( 
					"DatabaseException while getting folder from database. " +
					"Error message: " + e.getMessage());
		}
	}

	private String createFolder(String folderName, String projectKey, String parentKey) throws TestException {
		try {
			String key = Database.create(StoringType.FOLDER, folderName, projectKey, parentKey);
			return key;
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while creating folder. " +
					"Error message: " + e.getMessage());
		}
	}

}
