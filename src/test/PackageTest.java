package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.pack.Package;

public class PackageTest implements Test {
	
	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(createNewPackage());
		
		return result;
	}
	
	
	private String createNewPackage() {
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

	private Package getPackage(String key) throws TestException {
		try {
			return (Package) Database.get(StoringType.PACKAGE, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while getting package from database. " +
					"Error message: " + e.getMessage());
		}
	}

	private String createPackage(String packageName, String projectKey, String parentKey) throws TestException {
		try {
			String key =  Database.create(StoringType.PACKAGE, packageName, projectKey, parentKey);
			return key;
		} catch (DatabaseException e) {
			throw new TestException( 
					"DatabaseException while creating package. " +
					"Error message: " + e.getMessage());
		}
	}

}
