package storage.pack;

import javax.jdo.annotations.PersistenceCapable;

import storage.projectitem.CompositeProjectItem;

/**
 * Class representing package in database.
 * <br/><br/>
 * For creating new Package you need to provide its name,
 * database key of the project where this package should be created,
 * database key of the parent project item in which this package should be created.
 */
@PersistenceCapable(detachable="true")
public class Package extends CompositeProjectItem {

	public Package(String name, String projectKey, String parentKey) {
		super(name, projectKey, parentKey);
	}


}
