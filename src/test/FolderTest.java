package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.folder.Folder;

public class FolderTest implements Test {
	
	private Folder folder;
	private String key;
	
	
	private String createNewFolder() throws TestException {
		String result = "Creating new folder: ";
		
		createFolder(result);
		getFolder(result);
		
		if (!folder.getKey().equals(key)) {
			throw new TestException(result + Test.FAILED + 
					" - folder wasn't created or it's " +
					"impossible to get it from database");
		}
		
		return result + Test.PASSED;
	}

	private void getFolder(String result) throws TestException {
		try {
			folder = (Folder) Database.get(StoringType.FOLDER, key);
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while getting folder from database. " +
					"Error message: " + e.getMessage());
		}
	}

	private void createFolder(String result) throws TestException {
		try {
			key = Database.create(StoringType.FOLDER, "Test folder", "Project key", "Parent key");
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while creating folder. " +
					"Error message: " + e.getMessage());
		}
	}

	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		try {
			result.add(createNewFolder());
		} catch (TestException e) {
			result.add(e.getMessage());
		}
		
		return result;
	}

}
