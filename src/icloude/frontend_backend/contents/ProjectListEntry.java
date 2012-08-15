package icloude.frontend_backend.contents;

import java.util.Date;

/**
 * @author DimaTWL This class represents "ProjectListEntry" entity from protocol
 */
public class ProjectListEntry {
	private String projectName;
	private String ownerID;
	private String projectID;
	private Date creationDate;
	private String projectType;
	
	/**
	 * @param projectName
	 * @param ownerID
	 * @param projectID
	 * @param creationDate
	 * @param projectType
	 */
	public ProjectListEntry(String projectName, String ownerID,
			String projectID, Date creationDate, String projectType) {
		super();
		this.projectName = projectName;
		this.ownerID = ownerID;
		this.projectID = projectID;
		this.creationDate = creationDate;
		this.projectType = projectType;
	}

}