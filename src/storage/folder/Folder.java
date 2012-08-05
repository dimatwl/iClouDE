package storage.folder;

import javax.jdo.annotations.PersistenceCapable;

import storage.projectitem.CompositeProjectItem;

/**
 * Class representing folders in database.
 * <br/><br/>
 * For creating new Folder you need to provide its name,
 * database key of the project where this folder should be created,
 * database key of the parent project item in which this folder should be created.
 */
@PersistenceCapable(detachable="true")
public class Folder extends CompositeProjectItem {

	public Folder(String name, String projectKey, String parentKey) {
		super(name, projectKey, parentKey);
	}
}
