package storage.project;

import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Folder extends ProjectItem {

	public Folder(String name, Key projectKey, ProjectItem parent) {
		super(name, projectKey, parent);
		// TODO Auto-generated constructor stub
	}


}
