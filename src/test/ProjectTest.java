package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;
import storage.projectitem.CompositeProjectItem;
import storage.projectitem.CompositeProjectItemType;
import storage.projectitem.ProjectItem;

public class ProjectTest extends Test {
	
	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(testProjectCreating());
		result.add(testProjectDeleting());
		result.add(testProjectContent());
		result.add(testProjectsList());
		
		return result;
	}
	
	
	
	// test methods
	
	/**
	 * Creates project. Checks its availability in database and
	 * if it has root directory
	 */
	private String testProjectCreating() {
		String result = "Creating new project: ";
		
		String key = null;
		Project project = null;
		CompositeProjectItem root = null;
		try {
			key = createProject("ProjectName", "ProjectType");
			project = getProject(key);
			root = getCompositeProjectItem(project.getRootKey());
		} catch (TestException e) {
			return (result + Test.FAILED + e.getMessage());
		}
		
		if (!project.getKey().equals(key)) {
			return (result + Test.FAILED + 
					" - project wasn't created or it's " +
					"impossible to get it from database");
		}
		
		if (!root.getItemType().equals(CompositeProjectItemType.PROJECT)) {
			return (result + Test.FAILED +
					" - root proejct directory has invalid type " + root.getItemType());
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
	 * Then deletes project. Checks that none of created objects is
	 * available in database.
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
		String rootKey = null;
		try {
			projectKey = createProject("ProjectName", "ProjectType");
			Project project = getProject(projectKey);
			rootKey = project.getRootKey();
			
			folder1Key = createCompositeProjectItemType("folder1", projectKey,
					rootKey, CompositeProjectItemType.FOLDER);
			package1Key = createCompositeProjectItemType("package1", projectKey,
					rootKey, CompositeProjectItemType.PACKAGE);
			package2Key = createCompositeProjectItemType("package2", projectKey,
					rootKey, CompositeProjectItemType.PACKAGE);
			file1Key = createFile("file1", projectKey, package1Key);
			file2Key = createFile("file2", projectKey, package1Key);
			file3Key = createFile("file3", projectKey, package2Key);
			file4Key = createFile("file4", projectKey, package2Key);

			deleteProject(projectKey);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
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
			getCompositeProjectItem(package2Key);
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
			getCompositeProjectItem(folder1Key);
			return result + Test.FAILED + " - folder1 wasn't deleted";
		} catch (TestException e) {
		}
		try {
			getCompositeProjectItem(rootKey);
			return result + Test.FAILED + " - root folder wasn't deleted";
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
	 * Then gets project content and checks that all created objects
	 * are in given map.
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
		String rootKey = null;
		Map<String, ProjectItem> map = null;
		try {
			projectKey = createProject("ProjectName", "ProjectType");
			Project project = getProject(projectKey);
			rootKey = project.getRootKey();
			
			folder1Key = createCompositeProjectItemType("folder1", projectKey,
					rootKey, CompositeProjectItemType.FOLDER);
			package1Key = createCompositeProjectItemType("package1", projectKey,
					rootKey, CompositeProjectItemType.PACKAGE);
			package2Key = createCompositeProjectItemType("package2", projectKey,
					rootKey, CompositeProjectItemType.PACKAGE);
			file1Key = createFile("file1", projectKey, package1Key);
			file2Key = createFile("file2", projectKey, package1Key);
			file3Key = createFile("file3", projectKey, package2Key);
			file4Key = createFile("file4", projectKey, package2Key);
			
			project = getProject(projectKey);
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
		if (!set.contains(rootKey)) {
			return result + Test.FAILED + " - root folder not in project content"; 
		}
		
		return result + Test.PASSED;
	}
	
	
	/**
	 * Creates several projects and then gets projects list from
	 * database. Checks if this list contains all created projects
	 * and only them.
	 */
	private String testProjectsList() {
		String result = "Testing project list: ";
		
		System.err.println();
		System.err.println();
		System.err.println();
		Set<String> projectKeys = new HashSet<String>();
		List<Project> projects = null;
		try {
			for (int i = 0; i < 10; i++) {
				projectKeys.add(createProject(Integer.toString(i), "type"));
			}
			
			projects = getProjectsList();
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}
		
		for (Project p: projects) {
			projectKeys.remove(p.getKey());
		}
		
		if (!projectKeys.isEmpty()) {
			return result + Test.FAILED + " - some created projects aren't" +
					" in projects list";
		}
		
		
		return result + Test.PASSED;
	}

	
	
	
	// utility methods
	
	
	@SuppressWarnings("unchecked")
	private List<Project> getProjectsList() throws TestException {
		try {
			return (List<Project>) Database.get(StoringType.PROJECTS_LIST);
		} catch (DatabaseException e) {
			throw new TestException("Database exception while extracting " +
					"projcts list. " +
					"Error message: " + e.getMessage());
		}
	}

	
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
	
}
