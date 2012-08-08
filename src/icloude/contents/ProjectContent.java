/**
 * 
 */
package icloude.contents;

import java.util.Date;

/**
 * @author DimaTWL This class represents "FileContent" entity from protocol
 */
public class ProjectContent {
	private String projectID;
	private String projectName;
	private String ownerID;
	private Date creationDate;
	private String projectType;
	private FileTree fileTree;
	
	/**
	 * @param projectID
	 * @param projectName
	 * @param ownerID
	 * @param creationDate
	 * @param projectType
	 * @param fileTree
	 */
	public ProjectContent(String projectID, String projectName, String ownerID,
			Date creationDate, String projectType, FileTree fileTree) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.ownerID = ownerID;
		this.creationDate = creationDate;
		this.projectType = projectType;
		this.fileTree = fileTree;
	}

	/**
	 * @return the projectID
	 */
	public String getProjectID() {
		return projectID;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @return the ownerID
	 */
	public String getOwnerID() {
		return ownerID;
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
	 * @return the fileTree
	 */
	public FileTree getFileTree() {
		return fileTree;
	}



}
