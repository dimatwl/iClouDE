package storage.pack;

import javax.jdo.annotations.PersistenceCapable;

import storage.project.CompositeProjectItem;

@PersistenceCapable(detachable="true")
public class Package extends CompositeProjectItem {

	public Package(String name, String projectKey, String parentKey) {
		super(name, projectKey, parentKey);
	}


}
