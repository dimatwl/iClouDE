package storage.sourcefile;

import storage.Handler;

public class SourceFileHandler implements Handler {

	@Override
	public Object create(Object... params) {
		if (params.length != 2) {
			throw new RuntimeException("Incorrect parameters number");
		}
		
		String name = (String) params[0];
		String mode = (String) params[1];
		
		if ("w".equals(mode)) {
			
		} else if ("r".equals(mode)) {
			
		} else {
			throw new RuntimeException("Incorrect file opening mode: " + mode);
		}
		
		return null;
	}

	@Override
	public void save(Object toSave) {
		// TODO Auto-generated method stub
		
	}

}
