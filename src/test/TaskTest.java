package test;

import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.taskqueue.BuildAndRunTask;
import storage.taskqueue.TaskStatus;
import storage.taskqueue.TaskType;

public class TaskTest extends Test {

	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(testTaskCreating());
		result.add(testTaskGetting());
		result.add(testTaskDeleting());
		
		return result;
	}

	
	
	// test methods
	
	private String testTaskCreating() {
		String result = "Creating task: ";
		
		try {
			createTask("ProjectKey", TaskType.BUILD_AND_RUN);
		} catch (TestException e) {
			return (result + Test.FAILED + e.getMessage());
		}
		
		return result + Test.PASSED;
	}

	private String testTaskGetting() {
		String result = "Getting task: ";
		
		BuildAndRunTask task;
		try {
			createTask("ProjectKey", TaskType.BUILD_AND_RUN);
			task = getTask(TaskStatus.NEW);
		} catch (TestException e) {
			return (result + Test.FAILED + e.getMessage());
		}
		
		if (!task.getStatus().equals(TaskStatus.NEW)) {
			return result + Test.FAILED + " - incorrect status";
		}
		
		return result + Test.PASSED;
	}
	
	private String testTaskDeleting() {
		String result = "Deleting task: ";
		
		try {
			String taskKey = createTask("ProjectKey", TaskType.BUILD_AND_RUN);
			deleteTask(taskKey);
		} catch (TestException e) {
			return (result + Test.FAILED + e.getMessage());
		}
		
		return result + Test.PASSED;
	}
	
	
	
	// utility methods
	
	private String createTask(String projectKey, TaskType type) throws TestException {
		try {
			return Database.create(
					StoringType.BUILD_AND_RUN_TASK, projectKey, type);
		} catch (DatabaseException e) {
			throw new TestException("DatabaseException while creating " +
					"BuildAndRunTask. Error message: " + e.getMessage());
		}
	}
	
	private void deleteTask(String taskKey) throws TestException {
		try {
			Database.delete(StoringType.BUILD_AND_RUN_TASK, taskKey);
		} catch (DatabaseException e) {
			throw new TestException("DatabaseException while creating " +
					"BuildAndRunTask. Error message: " + e.getMessage());
		}
	}
	
	private BuildAndRunTask getTask(TaskStatus status) throws TestException {
		try {
			return (BuildAndRunTask) Database.get(
					StoringType.BUILD_AND_RUN_TASK, status);
		} catch (DatabaseException e) {
			throw new TestException("DatabaseException while getting " +
					"BuildAndRunTask. Error message: " + e.getMessage());
		}
	}
}
