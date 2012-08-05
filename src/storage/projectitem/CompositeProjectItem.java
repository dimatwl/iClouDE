package storage.projectitem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

/**
 * Class representing composite items of the project such as folder or package.
 * Composite project items can contain another project items.<br/>
 * This class provides access to the children of this CompositeProjectItem through
 * the list of children's database keys
 * @author Sergey
 *
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class CompositeProjectItem extends ProjectItem {
	
	@Persistent(defaultFetchGroup = "true")
	private List<Key> childrenKeys = new ArrayList<Key>();

	public CompositeProjectItem(String name, String projectKey, String parentKey) {
		super(name, projectKey, parentKey);
	}

	public List<Key> getChildren() {
		return Collections.unmodifiableList(childrenKeys);
	}
	
	public void addChild(Key childKey) {
		childrenKeys.add(childKey);
	}
}
