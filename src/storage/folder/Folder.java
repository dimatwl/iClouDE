package storage.folder;

import javax.jdo.annotations.PersistenceCapable;

import storage.ProjectItem;

@PersistenceCapable
public class Folder extends ProjectItem {

	public Folder(String name, String projectKey, String parentKey) {
		super(name, projectKey, parentKey);
	}
}
