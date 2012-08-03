package storage;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Base class for objects stored in database. Contains the most common
 * properties such as Key and Name.
 * @author Sergey
 *
 */
@PersistenceCapable(identityType = IdentityType.DATASTORE, detachable = "true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class DatabaseObject {
	
	public DatabaseObject(String name) {
		this.name = name;
	}

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.UUIDSTRING)
	private String key;
	
	@Persistent
	private String name;
	

	public String getKey() {
		return key;
	}
	
	public String getName() {
		return name;
	}
}
