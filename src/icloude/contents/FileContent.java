package icloude.contents;

import java.util.Date;

/**
 * @author DimaTWL
 * This class represents "FileContent" entity from protocol
 */
public class FileContent {
	private String type;
	private String filePath;
	private String text;
	private String fileType;
	private String ownerID;
	private String revisionID;
	private Date creationDate;
	private Date modificationDate;
	
	/**
	 * @param type
	 * @param filePath
	 * @param text
	 * @param fileType
	 * @param ownerID
	 * @param revisionID
	 * @param creationDate
	 * @param modificationDate
	 */
	public FileContent(String type, String filePath, String text,
			String fileType, String ownerID, String revisionID,
			Date creationDate, Date modificationDate) {
		super();
		this.type = type;
		this.filePath = filePath;
		this.text = text;
		this.fileType = fileType;
		this.ownerID = ownerID;
		this.revisionID = revisionID;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}
	
	private FileContent(){
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @return the ownerID
	 */
	public String getOwnerID() {
		return ownerID;
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

}
