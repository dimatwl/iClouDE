package storage;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class Database {


	public boolean contains(String name) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(TestData.class);
		q.setFilter("name == nameParam");
	    q.declareParameters("String nameParam");

	    @SuppressWarnings("unchecked")
		List<TestData> results = (List<TestData>) q.execute(name);
//	    pm.close();
    	return !results.isEmpty();
	}
	
	public void add(TestData td) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(td);
		pm.close();
	}
}
