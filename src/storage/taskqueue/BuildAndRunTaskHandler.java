package storage.taskqueue;

import javax.jdo.PersistenceManager;

import storage.DatabaseException;
import storage.PMF;

/**
 * Handles database operations with build&run tasks.
 * @author Sergey
 *
 */
public class BuildAndRunTaskHandler extends TaskHandler {

	public BuildAndRunTaskHandler() {
		super(BuildAndRunTask.class);
	}

	/**
	 * Creates build&run task.
	 * For creating you should provide 2 parameters:<br/>
	 * String projectKey - database of the project to build
	 * TaskType type - type of the task (build&run, run, build)
	 */
	@Override
	public String create(Object... params) throws DatabaseException {
		if (params.length == 2 &&
				params[0] instanceof String &&
				params[1] instanceof TaskType) {
			String projectKey = (String) params[0];
			TaskType type = (TaskType) params[1];
			return createBuildAndRunTask(projectKey, type);
		} else {
			throw new DatabaseException("Incorrect parameters for creating " +
					"task");
		}
	}

	private String createBuildAndRunTask(String projectKey, TaskType type) {
		BuildAndRunTask task = new BuildAndRunTask(projectKey, type);
		saveBuildAndRunTask(task);
		return task.getKey();
	}

	private void saveBuildAndRunTask(BuildAndRunTask task) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(task);
		pm.close();
	}
}
