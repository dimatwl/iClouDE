package storage.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.DatabaseException;
import storage.DatabaseObject;
import storage.PMF;
import storage.file.File;
import storage.projectitem.CompositeProjectItem;
import storage.projectitem.ProjectItem;

/**
 * Class representing projects in database.
 * <br/><br/>
 * For creating new Project you need to provide its name,
 * and its type.
 * @author Sergey
 *
 */
@PersistenceCapable(detachable="true")
public class Project extends DatabaseObject {

	public Project(String name, Date creationTime, String type) {
		super(name);
		this.creationTime = creationTime;
		this.type = type;
	}

	@Persistent
	private Date creationTime;
	
	@Persistent
	private String type;
	
	@Persistent
	private String rootKey;
	
	@Persistent
	private String buildKey;
	

	public String getBuildKey() {
		return buildKey;
	}

	public void setBuildKey(String buildKey) {
		this.buildKey = buildKey;
	}

	public String getType() {
		return type;
	}

	public String getProjectType() {
		return type;
	}

	public Date getCreationTime() {
		return creationTime;
	}
	
	public String getRootKey() {
		return rootKey;
	}

	// used only by ProjectHandler
	protected void setRootKey(String rootKey) {
		this.rootKey = rootKey;
	}

	private List<ProjectItem> getObjectsOfType(String projectKey, Class<?> type) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(type);
		q.setFilter("projectKey == key");
		q.declareParameters("String key");
		
		@SuppressWarnings("unchecked")
		List<ProjectItem> result = new ArrayList<ProjectItem>((List<ProjectItem>)q.execute(projectKey));
		
		pm.close();
		return result;
	}
	
	/**
	 * Returns Map containg all project items which are located in this project.
	 * Keys in this map are database keys of project items, and values are
	 * project items with corresponding key.
	 * @return Returns Map containg all project items which are located in this project.
	 * @throws DatabaseException if some error occurs in database or some
	 * project item wasn't found.
	 */
	public Map<String, ProjectItem> getContent() throws DatabaseException {
		List<ProjectItem> result = new ArrayList<ProjectItem>();
		result.addAll(getObjectsOfType(getKey(), File.class));
		result.addAll(getObjectsOfType(getKey(), CompositeProjectItem.class));
		
		
		Map<String, ProjectItem> map = new HashMap<String, ProjectItem>();
		for (ProjectItem pi: result) {
			map.put(pi.getKey(), pi);
		}
		return map;
	}


}
