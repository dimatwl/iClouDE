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

	protected BuildAndRunTaskHandler() {
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
		if (params.length != 2) {
			throw new DatabaseException("Incorrect number of parameters for " +
					"creating build&run task. There should be 2 parameters, " +
					"but " + params.length + " parameters given");
		}
		
		if (!(params[0] instanceof String)) {
			throw new DatabaseException("Incorrect first parameter type for " +
					"creating build&run task.");
		}

		if (!(params[1] instanceof TaskType)) {
			throw new DatabaseException("Incorrect second parameter type for " +
					"creating build&run task.");
		}
		
		String projectKey = (String) params[0];
		TaskType type = (TaskType) params[1];
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		BuildAndRunTask task = new BuildAndRunTask(projectKey, type);
		pm.makePersistent(task);
		
		pm.close();
		
		return task.getKey();
	}
}
