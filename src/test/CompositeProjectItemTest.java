package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;
import storage.projectitem.CompositeProjectItem;
import storage.projectitem.CompositeProjectItemType;

public class CompositeProjectItemTest extends Test {
	
	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(testCompositeProjectItemCreating());
		result.add(testCompositeProjectItemDeleting());
		
		return result;
	}

	
	// test methods
	
	private String testCompositeProjectItemCreating() {
		String result = "Creating new composite project item: ";
		
		String folderKey = null;
		CompositeProjectItem folder = null;
		try {
			String projectKey = createProject("ProjectName", "ProjectType");
			Project project = getProject(projectKey);
			String rootKey = project.getRootKey();
			
			folderKey = createCompositeProjectItemType("item", projectKey,
					rootKey, CompositeProjectItemType.FOLDER);
			folder = getCompositeProjectItem(folderKey);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}
		
		if (!folder.getKey().equals(folderKey)) {
			return (result + Test.FAILED + 
					" - composite project item wasn't created or it's " +
					"impossible to get it from database");
		}
		
		return result + Test.PASSED;
	}

	
	/**
	 * Creates project with structure:<br/><br/>
	 * project/<br/>
	 *   folder1/<br/>
	 *     package1/<br/>
	 *       file1<br/>
	 *       file2<br/>
	 *     file3<br/>
	 *     file4<br/>
	 *   folder2/<br/>
	 *     file5<br/>
	 *   file6<br/><br/>
	 *   
	 * and deletes folder1
	 */
	private String testCompositeProjectItemDeleting() {
		String result = "Deleting composite project item: ";
		
		String projectKey = null;
		String folder1Key = null;
		String folder2Key = null;
		String package1Key = null;
		String file1Key = null;
		String file2Key = null;
		String file3Key = null;
		String file4Key = null;
		String file5Key = null;
		String file6Key = null;
		try {
			projectKey = createProject("ProjectName", "ProjectType");
			Project project = getProject(projectKey);
			String rootKey = project.getRootKey();
			
			folder1Key = createCompositeProjectItemType("folder1", projectKey,
					rootKey, CompositeProjectItemType.FOLDER);
			package1Key = createCompositeProjectItemType("package1", projectKey,
					folder1Key, CompositeProjectItemType.PACKAGE);
			file1Key = createFile("file1", projectKey, package1Key);
			file2Key = createFile("file2", projectKey, package1Key);
			file3Key = createFile("file3", projectKey, folder1Key);
			file4Key = createFile("file4", projectKey, folder1Key);

			folder2Key = createCompositeProjectItemType("folder2", projectKey,
					rootKey, CompositeProjectItemType.FOLDER);
			file5Key = createFile("file5", projectKey, folder2Key);
			file6Key = createFile("file6", projectKey, rootKey);

			
			deleteCompositeProjectItem(folder1Key);
			getCompositeProjectItem(folder2Key);
			getFile(file5Key);
			getFile(file6Key);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}

		try {
			getCompositeProjectItem(folder1Key);
			return result + Test.FAILED + " - folder1 wasn't deleted";
		} catch (TestException e) {
		}
		try {
			getCompositeProjectItem(package1Key);
			return result + Test.FAILED + " - package1 wasn't deleted";
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
	
	private void deleteCompositeProjectItem(String key) throws TestException {
		try {
			Database.delete(StoringType.COMPOSITE_PROJECT_ITEM, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while deleting project item. " +
					"Error message: " + e.getMessage());
		}
	}

	
}
