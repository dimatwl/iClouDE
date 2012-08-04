package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;

public class ProjectTest implements Test {
	
	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(testNewProjectCreating());
		
		return result;
	}
	
	
	
	// test methods
	
	private String testNewProjectCreating() {
		String result = "Creating new project: ";
		
		String key = null;
		Project project = null;
		try {
			key = createProject("ProjectName", "ProjectType");
			project = getProject(key);
		} catch (TestException e) {
			return (result + Test.FAILED + e.getMessage());
		}
		
		if (!project.getKey().equals(key)) {
			return (result + Test.FAILED + 
					" - project wasn't created or it's " +
					"impossible to get it from database");
		}
		
		return result + Test.PASSED;
	}
	
	
	
	
	
	// utility methods

	private Project getProject(String key) throws TestException {
		try {
			return (Project) Database.get(StoringType.PROJECT, key);
		} catch (DatabaseException e) {
			throw new TestException( 
					"DatabaseException while getting project from database. " +
					"Error message: " + e.getMessage());
		}
	}

	private String createProject(String projectName, String projectType) throws TestException {
		try {
			String key = Database.create(StoringType.PROJECT, projectName, projectType);
			return key;
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while creating project. " +
					"Error message: " + e.getMessage());
		}
	}

}
