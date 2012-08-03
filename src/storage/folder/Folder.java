package storage.folder;

import javax.jdo.annotations.PersistenceCapable;

import storage.project.CompositeProjectItem;

@PersistenceCapable(detachable="true")
public class Folder extends CompositeProjectItem {

	public Folder(String name, String projectKey, String parentKey) {
		super(name, projectKey, parentKey);
	}
}
