
package icloude.contents;

import java.util.Date;
import java.util.List;

/**
 * @author DimaTWL This class represents "FileTree" entity from protocol
 */
public class FileTree {
	private String itemName;
	private String itemType;
	private String itemID;
	private String parentID;
	private String revisionID;
	private Date creationDate;
	private Date modificationDate;
	private Long size;
	private List<FileTree> children;
	
	/**
	 * @param itemName
	 * @param itemType
	 * @param itemID
	 * @param parentID
	 * @param revisionID
	 * @param creationDate
	 * @param modificationDate
	 * @param size
	 * @param children
	 */
	public FileTree(String itemName, String itemType, String itemID,
			String parentID, String revisionID, Date creationDate,
			Date modificationDate, Long size, List<FileTree> children) {
		super();
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemID = itemID;
		this.parentID = parentID;
		this.revisionID = revisionID;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.size = size;
		this.children = children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<FileTree> children) {
		this.children = children;
	}

}
