package storage;

public interface Handler {

	public Object create(Object... params);
	
	public void save(Object toSave);
}
