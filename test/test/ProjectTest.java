package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;

public class ProjectTest implements Test {
	
	private Project project;
	private String key;
	
	
	private String createNewProject() throws TestException {
		String result = "Creating new project: ";
		
		createProject(result);
		getProject(result);
		
		if (!project.getKey().equals(key)) {
			throw new TestException(result + Test.FAILED + 
					" - project wasn't created or it's " +
					"impossible to get it from database");
		}
		
		return result + Test.PASSED;
	}

	private void getProject(String result) throws TestException {
		try {
			project = (Project) Database.get(StoringType.PROJECT, key);
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while getting project from database. " +
					"Error message: " + e.getMessage());
		}
	}

	private void createProject(String result) throws TestException {
		try {
			key = Database.create(StoringType.PROJECT, "Test project", "Project type");
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while creating project. " +
					"Error message: " + e.getMessage());
		}
	}

	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		try {
			result.add(createNewProject());
		} catch (TestException e) {
			result.add(e.getMessage());
		}
		
		return result;
	}

}
