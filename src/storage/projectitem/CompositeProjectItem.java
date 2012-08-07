package storage.projectitem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import storage.Child;

/**
 * Class representing composite items of the project such as folder or package.
 * Composite project items can contain another project items.<br/>
 * This class provides access to the children of this CompositeProjectItem through
 * the list of children's database keys
 * @author Sergey
 *
 */
@PersistenceCapable(detachable = "true")
//@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class CompositeProjectItem extends ProjectItem {
	
	@Persistent
	private CompositeProjectItemType itemType;

	@Persistent(defaultFetchGroup = "true")
	private List<Child> children = new ArrayList<Child>();
	
	
	public CompositeProjectItem(String name, String projectKey,
			String parentKey, CompositeProjectItemType itemType) {
		super(name, projectKey, parentKey);
		this.itemType = itemType;
	}
	

	public List<Child> getChildren() {
		return Collections.unmodifiableList(children);
	}

	public void addChild(Child child) {
		children.add(child);
	}

	public void removeChild(Child child) {
		children.remove(child);
	}

	public CompositeProjectItemType getItemType() {
		return itemType;
	}
}
