package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.sourcefile.SourceFile;
import storage.sourcefile.SourceFileReader;
import storage.sourcefile.SourceFileWriter;

public class SourceFileTest implements Test {
	
	private static final String testString = "hello world";
	
	private String key;
	private SourceFile file;
	private SourceFileWriter writer;
	private SourceFileReader reader;
	
	private String createNewFile() throws TestException {
		String result = "Creating new file: ";
		
		createFile(result);
		getFile(result);
		
		if (!file.getKey().equals(key)) {
			throw new TestException(result + Test.FAILED + 
					" - file wasn't created or it's " +
					"impossible to get it from database");
		}

		return result + Test.PASSED;
	}

	private void createFile(String result) throws TestException {
		try {
			key = Database.create(StoringType.SOURCE_FILE, "TestFile", "projectKey", "parentKey");
			
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while creating file. " +
					"Error message: " + e.getMessage());
		}
	}

	private void getFile(String result) throws TestException {
		try {
			file = (SourceFile) Database.get(StoringType.SOURCE_FILE, key);
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while getting file from database. " +
					"Error message: " + e.getMessage());
		}
	}
	
	private String writeAndRead() throws TestException {
		String result = "Writing and reading source file: ";
		
		getFile(result);
		openFileForWriting(result);
		writeToFile(result);
		closeWriter(result);
		saveFile(result);
		getFile(result);
		openFileForReading(result);

		String fileContent = readFile(result);
		closeReader(result);
		
		if (!testString.equals(fileContent)) {
			throw new TestException(result + Test.FAILED + 
					" - there must be '" + testString + "' in the file, but '" +
							fileContent + "' found");
		}
		
		return result + Test.PASSED;
	}

	private void closeReader(String result) throws TestException {
		try {
			reader.close();
		} catch (IOException e) {
			throw new TestException(result + Test.FAILED + 
					" due to IOException while closing file after reading. " +
					"Error message: " + e.getMessage());
		}
	}

	private String readFile(String result) throws TestException {
		char[] cbuf = new char[5];
		String fileContent = "";
		try {
			int count;
			while ((count = reader.read(cbuf)) != -1) {
				for (int i = 0; i < count; i++) {
					fileContent += (char)cbuf[i];
				}
			}
		} catch (IOException e) {
			throw new TestException(result + Test.FAILED + 
					" due to IOException while reading file. " +
					"Error message: " + e.getMessage());
		}
		return fileContent;
	}

	private void openFileForReading(String result) throws TestException {
		try {
			reader = file.openForReading();
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while opening file for reading. " +
					"Error message: " + e.getMessage());
		}
	}

	private void saveFile(String result) throws TestException {
		try {
			Database.update(StoringType.SOURCE_FILE, file);
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while saving file to database. " +
					"Error message: " + e.getMessage());
		}
	}

	private void closeWriter(String result) throws TestException {
		try {
			writer.close();
		} catch (IOException e) {
			throw new TestException(result + Test.FAILED + 
					" due to IOException while closing file after writing. " +
					"Error message: " + e.getMessage());
		}
	}

	private void writeToFile(String result) throws TestException {
		try {
			writer.write(testString);
		} catch (IOException e) {
			throw new TestException(result + Test.FAILED + 
					" due to IOException while writing to the file. " +
					"Error message: " + e.getMessage());
		}
	}

	private void openFileForWriting(String result) throws TestException {
		try {
			writer = file.openForWriting();
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while opening file for writing. " +
					"Error message: " + e.getMessage());
		}
	}
	
//	private String updateFields() throws TestException {
//		String result = "Updating fields: ";
//
//		getFile(result);
//		Date modificationDate = new Date(0);
//		file.setModificationTime(modificationDate);
//		saveFile(result);
//		
//		getFile(result);
//		if (!modificationDate.equals(file.getModificationTime())) {
//			throw new TestException(result + Test.FAILED + 
//					" - file field hasn't changed");
//		}
//		
//		return result + Test.PASSED;
//	}
	
	private String deleteFile() throws TestException {
		String result = "Deleting file: ";
		
		deleteFile(result);
		
		try {
			getFile("");
		} catch (TestException e) {
			return result + Test.PASSED;
		}
		
		return result + Test.FAILED + " - file wasn't deleted";
	}

	private void deleteFile(String result) throws TestException {
		try {
			Database.delete(StoringType.SOURCE_FILE, key);
		} catch (DatabaseException e) {
			throw new TestException(result + Test.FAILED + 
					" due to DatabaseException while deleting file. " +
					"Error message: " + e.getMessage());
		}
	}

	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		try {
			result.add(createNewFile());
			result.add(writeAndRead());
//			result.add(updateFields());
			result.add(deleteFile());
		} catch (TestException e) {
			result.add(e.getMessage());
		}
		
		return result;
	}
}
