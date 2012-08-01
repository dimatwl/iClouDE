package storage.project;

import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Package extends ProjectItem {

	public Package(String name, Key projectKey, ProjectItem parent) {
		super(name, projectKey, parent);
		// TODO Auto-generated constructor stub
	}

}
