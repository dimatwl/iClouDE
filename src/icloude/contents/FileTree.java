/**
 * 
 */
package icloude.contents;

import java.util.Date;
import java.util.List;

/**
 * @author DimaTWL
 * This class represents "FileTree" entity from protocol
 */
public class FileTree {
	private String rootName;
	private String rootType;
	private String revisionID;
	private Date creationDate;
	private Date modificationDate;
	private List<FileTree> children;
	
	/**
	 * @param rootName
	 * @param rootType
	 * @param revisionID
	 * @param creationDate
	 * @param modificationDate
	 * @param children
	 */
	public FileTree(String rootName, String rootType,
			String revisionID, Date creationDate, Date modificationDate, 
			List<FileTree> children) {
		this.rootName = rootName;
		this.rootType = rootType;
		this.revisionID = revisionID;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.children = children;
	}

	/**
	 * @return the rootName
	 */
	public String getRootName() {
		return rootName;
	}

	/**
	 * @return the rootType
	 */
	public String getRootType() {
		return rootType;
	}


	/**
	 * @return the revisionID
	 */
	public String getRevisionID() {
		return revisionID;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the modificationDate
	 */
	public Date getModificationDate() {
		return modificationDate;
	}

	/**
	 * @return the children
	 */
	public List<FileTree> getChildren() {
		return children;
	}

}
