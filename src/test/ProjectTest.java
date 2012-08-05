package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;
import storage.projectitem.ProjectItem;

public class ProjectTest extends Test {
	
	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(testProjectCreating());
		result.add(testProjectDeleting());
		result.add(testProjectContent());
		
		return result;
	}
	
	
	
	// test methods
	
	private String testProjectCreating() {
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
	private String testProjectDeleting() {
		String result = "Deleting project: ";
		
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

			deleteProject(projectKey);
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
		try {
			getPackage(package2Key);
			return result + Test.FAILED + " - package2 wasn't deleted";
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
		try {
			getFolder(folder1Key);
			return result + Test.FAILED + " - folder1 wasn't deleted";
		} catch (TestException e) {
		}
		try {
			getProject(projectKey);
			return result + Test.FAILED + " - project wasn't deleted";
		} catch (TestException e) {
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
	private String testProjectContent() {
		String result = "Getting project content: ";
		
		String projectKey = null;
		String folder1Key = null;
		String package1Key = null;
		String package2Key = null;
		String file1Key = null;
		String file2Key = null;
		String file3Key = null;
		String file4Key = null;
		Map<String, ProjectItem> map = null;
		try {
			projectKey = createProject("ProjectName", "ProjectType");
			folder1Key = createFolder("folder1", projectKey, projectKey);
			package1Key = createPackage("package1", projectKey, folder1Key);
			package2Key = createPackage("package2", projectKey, folder1Key);
			file1Key = createFile("file1", projectKey, package1Key);
			file2Key = createFile("file2", projectKey, package1Key);
			file3Key = createFile("file3", projectKey, package2Key);
			file4Key = createFile("file4", projectKey, package2Key);

			Project project = getProject(projectKey);
			map = getProjectContent(project);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}

		Set<String> set = map.keySet();
		
		if (!set.contains(file1Key)) {
			return result + Test.FAILED + " - file1 not in project content"; 
		}
		if (!set.contains(file2Key)) {
			return result + Test.FAILED + " - file2 not in project content"; 
		}
		if (!set.contains(file3Key)) {
			return result + Test.FAILED + " - file3 not in project content"; 
		}
		if (!set.contains(file4Key)) {
			return result + Test.FAILED + " - file4 not in project content"; 
		}
		if (!set.contains(package1Key)) {
			return result + Test.FAILED + " - package1 not in project content"; 
		}
		if (!set.contains(package2Key)) {
			return result + Test.FAILED + " - package2 not in project content"; 
		}
		if (!set.contains(folder1Key)) {
			return result + Test.FAILED + " - folder1 not in project content"; 
		}
		
		return result + Test.PASSED;
	}

	
	
	
	// utility methods

	
	private Map<String, ProjectItem> getProjectContent(Project project) throws TestException {
		try {
			Map<String, ProjectItem> map = project.getContent();
			return map;
		} catch (DatabaseException e) {
			throw new TestException("Database exception while extracting " +
					"projct content. " +
					"Error message: " + e.getMessage());
		}
	}
	
	private void deleteProject(String key) throws TestException {
		try {
			Database.delete(StoringType.PROJECT, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while deleting project. " +
					"Error message: " + e.getMessage());
		}
	}
	
	
	private Project getProject(String key) throws TestException {
		try {
			return (Project) Database.get(StoringType.PROJECT, key);
		} catch (DatabaseException e) {
			throw new TestException( 
					"DatabaseException while getting project from database. " +
					"Error message: " + e.getMessage());
		}
	}

}
