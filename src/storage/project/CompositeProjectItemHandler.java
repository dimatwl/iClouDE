package storage.project;

import storage.DatabaseException;

/**
 * Class for handlig composite project items such as project, folder, package.
 * Allows to handle current project item and all its subitems.
 * @author Sergey
 *
 */
public abstract class CompositeProjectItemHandler extends ProjectItemHandler {

	/**
	 * Recursively deletes project item and all its subitems.
	 * @param String key - key of the project item to delete
	 */
	@Override
	public void delete(String key) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}
	
}
