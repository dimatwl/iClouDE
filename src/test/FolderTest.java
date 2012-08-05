package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.folder.Folder;

public class FolderTest extends Test {
	
	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(testFolderCreating());
		result.add(testFolderDeleting());
		
		return result;
	}

	
	// test methods
	
	private String testFolderCreating() {
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

	
	/**
	 * Creates project with structure:<br/><br/>
	 * project/<br/>
	 *   folder1/<br/>
	 *     folder2/<br/>
	 *       file1<br/>
	 *       file2<br/>
	 *     file3<br/>
	 *     file4<br/>
	 *   folder3/<br/>
	 *     file5<br/>
	 *   file6<br/><br/>
	 *   
	 * and deletes folder1
	 */
	private String testFolderDeleting() {
		String result = "Deleting folder: ";
		
		String projectKey = null;
		String folder1Key = null;
		String folder2Key = null;
		String folder3Key = null;
		String file1Key = null;
		String file2Key = null;
		String file3Key = null;
		String file4Key = null;
		String file5Key = null;
		String file6Key = null;
		try {
			projectKey = createProject("ProjectName", "ProjectType");
			folder1Key = createFolder("folder1", projectKey, projectKey);
			folder2Key = createFolder("folder2", projectKey, folder1Key);
			folder3Key = createFolder("folder3", projectKey, projectKey);
			file1Key = createFile("file1", projectKey, folder2Key);
			file2Key = createFile("file2", projectKey, folder2Key);
			file3Key = createFile("file3", projectKey, folder1Key);
			file4Key = createFile("file4", projectKey, folder1Key);
			file5Key = createFile("file5", projectKey, folder3Key);
			file6Key = createFile("file6", projectKey, projectKey);

			deleteFolder(folder1Key);
			getFolder(folder3Key);
			getFile(file5Key);
			getFile(file6Key);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}
		
		try {
			getFolder(folder1Key);
			return result + Test.FAILED + " - folder1 wasn't deleted";
		} catch (TestException e) {
		}
		try {
			getFolder(folder2Key);
			return result + Test.FAILED + " - folder2 wasn't deleted";
		} catch (TestException e) {
		}
		try {
			getFile(file1Key);
			return result + Test.FAILED + " - file1 wasn't deleted";
		} catch (TestException e) {
		}
		try {
			getFile(file2Key);
			return result + Test.FAILED + " - file2 wasn't deleted";
		} catch (TestException e) {
		}
		try {
			getFile(file3Key);
			return result + Test.FAILED + " - file3 wasn't deleted";
		} catch (TestException e) {
		}
		try {
			getFile(file4Key);
			return result + Test.FAILED + " - file4 wasn't deleted";
		} catch (TestException e) {
		}
		
		return result + Test.PASSED;
	}
	
	
	
	// utility methods
	
	private void deleteFolder(String key) throws TestException {
		try {
			Database.delete(StoringType.FOLDER, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while deleting folder. " +
					"Error message: " + e.getMessage());
		}
	}

	
}
