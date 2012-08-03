package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.pack.Package;

public class PackageTest implements Test {
	
	private Package pack;
	private String key;
	
	
	private String createNewPackage() throws TestException {
		String result = "Creating new package: ";
		
		createPackage(result);
		getPackage(result);
		
		if (!pack.getKey().equals(key)) {
			throw new TestException(result + Test.FAILED + 
					" - package wasn't created or it's " +
					"impossible to get it from database");
		}
		
		return result + Test.PASSED;
	}

	private void getPackage(String result) throws TestException {
		try {
			pack = (Package) Database.get(StoringType.PACKAGE, key);
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while getting package from database. " +
					"Error message: " + e.getMessage());
		}
	}

	private void createPackage(String result) throws TestException {
		try {
			key = Database.create(StoringType.PACKAGE, "Test package", "Project key", "Parent key");
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while creating package. " +
					"Error message: " + e.getMessage());
		}
	}

	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		try {
			result.add(createNewPackage());
		} catch (TestException e) {
			result.add(e.getMessage());
		}
		
		return result;
	}

}
