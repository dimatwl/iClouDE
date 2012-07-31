package icloude.contents;

import java.util.Date;

/**
 * @author DimaTWL This class represents "ProjectListEntry" entity from protocol
 */
public class ProjectListEntry {
	private String ownerID;
	private String projectID;
	private Date creationDate;
	private String projectType;

	/**
	 * @param ownerID
	 * @param projectID
	 * @param creationDate
	 * @param projectType
	 */
	public ProjectListEntry(String ownerID, String projectID,
			Date creationDate, String projectType) {
		super();
		this.ownerID = ownerID;
		this.projectID = projectID;
		this.creationDate = creationDate;
		this.projectType = projectType;
	}

}