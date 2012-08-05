package storage.projectitem;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Class representing simple item of the project - item which doesn't contain
 * other items.
 * @author Sergey
 *
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class SimpleProjectItem extends ProjectItem {

	public SimpleProjectItem(String name, String projectKey, String parentKey) {
		super(name, projectKey, parentKey);
	}
}
