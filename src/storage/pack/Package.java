package storage.pack;

import javax.jdo.annotations.PersistenceCapable;

import storage.ProjectItem;

@PersistenceCapable
public class Package extends ProjectItem {

	public Package(String name, String projectKey, String parentKey) {
		super(name, projectKey, parentKey);
		// TODO Auto-generated constructor stub
	}


}
