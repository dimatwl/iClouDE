package storage;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = 1138506267834203677L;

	public DatabaseException() {
		super();
	}
	
	public DatabaseException(String message) {
		super(message);
	}
}
