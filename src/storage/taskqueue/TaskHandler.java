package storage.taskqueue;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import storage.AbstractHandler;
import storage.DatabaseException;
import storage.PMF;

/**
 * Handles common database operations with tasks.
 * @author Sergey
 *
 */
public abstract class TaskHandler extends AbstractHandler {

	
	protected TaskHandler(Class<?> handlingType) {
		super(handlingType);
	}
	
	
	/**
	 * Returns task of required type with minimal last modification time.
	 */
	@Override
	public Task get(Object... params) throws DatabaseException {
		if (params.length != 1) {
			throw new DatabaseException("Incorrect number of parameters to get " +
					"task. There should be 1 parameter, but " + params.length + " parameters given");
			
		}
		
		if (!(params[0] instanceof TaskStatus)) {
			throw new DatabaseException("Incorrect first parameter type for getting " +
					"task from database. It must has type TaskStatus");
		}

		TaskStatus status = (TaskStatus) params[0];
		Task result = null;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery("select key from " + getHandlingType().getName());
		q.setOrdering("modificationTime asc");
		q.setFilter("status == statusParam");
		q.declareParameters("storage.taskqueue.TaskStatus statusParam");
		
		@SuppressWarnings("unchecked")
		List<String> resultKeys = (List<String>)q.execute(status);
		if (!resultKeys.isEmpty()) {
			result = (Task) pm.getObjectById(getHandlingType(), resultKeys.get(0));
		}
		
		pm.close();
		
		return result;
	}


	/**
	 * Deletes task from database. Task should be deleted after it
	 * has become finished and result of this task has been sent to user.
	 */
	@Override
	public void delete(String key) throws DatabaseException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Task task = (Task) pm.getObjectById(getHandlingType(), key);
		pm.deletePersistent(task);
		pm.close();
	}

}
