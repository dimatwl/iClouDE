package storage;

/**
 * Object of these types can be stored in database.
 * Pass this typenames to Database class to indicate
 * which object you are working with
 * @author Sergey
 *
 */
public enum StoringType {

	SOURCE_FILE,
	PROJECT,
	COMPOSITE_PROJECT_ITEM,
	PROJECTS_LIST,
	BUILD_AND_RUN_TASK
}
