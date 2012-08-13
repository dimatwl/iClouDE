package storage.taskqueue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseObject;

/**
 * Class representing some task in application.
 * Tasks are stored in database until they are finished.
 * Each task has status and result.
 * @author Sergey
 *
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Task extends DatabaseObject {

	public Task() {
		super(null);
	}
	
	@Persistent
	private String taskID;

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	@Persistent
	private TaskStatus status = TaskStatus.NEW;
	
	@Persistent
	private Date modificationTime = new Date();
	
	@Persistent
	private List<String> result = new ArrayList<String>();
	
	
	public List<String> getResult() {
		return result;
	}

	public void addToResult(String s) {
		result.add(s);
	}
	
	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
		modificationTime = new Date();
	}

	public Date getModificationTime() {
		return modificationTime;
	}
}
