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
	FOLDER,
	PACKAGE
}
