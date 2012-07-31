/**
 * 
 */
package icloude.contents;

import java.util.Date;

/**
 * @author DimaTWL
 * This class represents "FileContent" entity from protocol
 */
public class ProjectContent {
	private String ownerID;
	private String projectID;
	private Date creationDate;
	private String projectType;
	private FileTree files;
	
	/**
	 * @param ownerID
	 * @param projectID
	 * @param creationDate
	 * @param projectType
	 * @param files
	 */
	public ProjectContent(String ownerID, String projectID, Date creationDate,
			String projectType, FileTree files) {
		this.ownerID = ownerID;
		this.projectID = projectID;
		this.creationDate = creationDate;
		this.projectType = projectType;
		this.files = files;
	}

	/**
	 * @return the ownerID
	 */
	public String getOwnerID() {
		return ownerID;
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the projectType
	 */
	public String getProjectType() {
		return projectType;
	}

	/**
	 * @return the files
	 */
	public FileTree getFiles() {
		return files;
	}

}
