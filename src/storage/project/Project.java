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
import storage.PMF;
import storage.folder.Folder;
import storage.pack.Package;
import storage.sourcefile.SourceFile;

/**
 * Class representing projects in database.
 * <br/><br/>
 * For creating new Project you need to provide its name,
 * and its type.
 * @author Sergey
 *
 */
@PersistenceCapable(detachable="true")
public class Project extends CompositeProjectItem {

	public Project(String name, Date creationTime, String type) {
		super(name, null, null);
		this.creationTime = creationTime;
		this.type = type;
	}

	@Persistent
	private Date creationTime;
	
	@Persistent
	private Date modificationTime;
	
	@Persistent
	private String type;
	

	public String getProjectType() {
		return type;
	}

	public void setProjectType(String projectType) {
		this.type = projectType;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
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
		result.addAll(getObjectsOfType(getKey(), SourceFile.class));
		result.addAll(getObjectsOfType(getKey(), Folder.class));
		result.addAll(getObjectsOfType(getKey(), Package.class));
		
		
		Map<String, ProjectItem> map = new HashMap<String, ProjectItem>();
		for (ProjectItem pi: result) {
			map.put(pi.getKey(), pi);
		}
		return map;
	}

}
