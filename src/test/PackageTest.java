package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.pack.Package;

public class PackageTest extends Test {
	
	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(testPackageCreating());
		result.add(testPackageDeleting());
		
		return result;
	}
	
	
	private String testPackageCreating() {
		String result = "Creating new package: ";
		
		String key = null;
		Package pack = null;
		try {
			key = createPackage("PackageName", "ProjectKey", "ParentKey");
			pack = getPackage(key);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}
		
		if (!pack.getKey().equals(key)) {
			return (result + Test.FAILED + 
					" - package wasn't created or it's " +
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
	 *     package2<br/>
	 *       file4<br/>
	 *       file5<br/>
	 *   
	 * and deletes folder1
	 */
	private String testPackageDeleting() {
		String result = "Deleting package: ";
		
		String projectKey = null;
		String folder1Key = null;
		String package1Key = null;
		String package2Key = null;
		String file1Key = null;
		String file2Key = null;
		String file3Key = null;
		String file4Key = null;
		try {
			projectKey = createProject("ProjectName", "ProjectType");
			folder1Key = createFolder("folder1", projectKey, projectKey);
			package1Key = createPackage("package1", projectKey, folder1Key);
			package2Key = createPackage("package2", projectKey, folder1Key);
			file1Key = createFile("file1", projectKey, package1Key);
			file2Key = createFile("file2", projectKey, package1Key);
			file3Key = createFile("file3", projectKey, package2Key);
			file4Key = createFile("file4", projectKey, package2Key);

			deletePackage(package1Key);
			getPackage(package2Key);
			getFile(file3Key);
			getFile(file4Key);
			getFolder(folder1Key);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}
		
		try {
			getPackage(package1Key);
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
		
		return result + Test.PASSED;
	}
	
	
	
	// utility methods
	
	private void deletePackage(String key) throws TestException {
		try {
			Database.delete(StoringType.PACKAGE, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while deleting package. " +
					"Error message: " + e.getMessage());
		}
	}
}
