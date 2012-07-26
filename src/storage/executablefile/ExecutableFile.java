package storage.executablefile;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import storage.user.User;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class ExecutableFile {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	public Key getKey() {
		return key;
	}
	
	@Persistent
	private String name;
	
	@Persistent
	private User user;
	
	@Persistent
	private Date creationTime;
	
	@Persistent
	private BlobKey file;
}
