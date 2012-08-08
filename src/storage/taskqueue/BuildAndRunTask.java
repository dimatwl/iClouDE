package storage.taskqueue;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Task for building and running program.
 * Contains information about project to build and
 * type of the task (run&build, only run, only build)
 * @author Sergey
 *
 */
@PersistenceCapable(detachable = "true")
public class BuildAndRunTask extends Task {
	
	public BuildAndRunTask(String projectKey, TaskType type) {
		this.projectKey = projectKey;
		this.type = type;
	}
	
	@Persistent
	private TaskType type;

	@Persistent
	private String projectKey;

	public String getProjectKey() {
		return projectKey;
	}

	public TaskType getType() {
		return type;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}
}
