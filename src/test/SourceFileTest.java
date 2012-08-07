package test;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.project.Project;
import storage.sourcefile.SourceFile;

public class SourceFileTest extends Test {
	
	private static final String testString = "hello world";
	

	@Override
	public List<String> test() {
		List<String> result = new ArrayList<String>();
		result.add(testNewFileCreating());
		result.add(testWritingAndReading());
		result.add(testFileDeleting());
		
		return result;
	}
	
	
	
	
	// test methods

	private String testNewFileCreating() {
		String result = "Creating new file: ";
		
		String projectKey = null;
		String rootKey = null;
		String fileKey = null;
		SourceFile file = null;
		try {
			projectKey = createProject("ProjectName", "ProjectType");
			Project project = getProject(projectKey);
			rootKey = project.getRootKey();
			fileKey = createFile("file", projectKey, rootKey);
			file = getFile(fileKey);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}
		
		if (!file.getKey().equals(fileKey)) {
			return (result + Test.FAILED + 
					" - file wasn't created or it's " +
					"impossible to get it from database");
		}

		return result + Test.PASSED;
	}

	private String testWritingAndReading() {
		String result = "Writing and reading source file: ";
		
		String fileContent = null;
		try {
			String projectKey = createProject("ProjectName", "ProjectType");
			Project project = getProject(projectKey);
			String rootKey = project.getRootKey();
			String fileKey = createFile("file", projectKey, rootKey);
			SourceFile file = getFile(fileKey);
			
			Writer writer = openFileForWriting(file);
			writeToFile(writer);
			closeWriter(writer);
			saveFile(file);
			
			file = getFile(fileKey);
			Reader reader = openFileForReading(file);
			fileContent = readFile(reader);
			closeReader(reader);
		} catch (TestException e) {
			return result + Test.FAILED + " - " + e.getMessage();
		}
		
		if (!testString.equals(fileContent)) {
			return (result + Test.FAILED + 
					" - there must be '" + testString + "' in the file, but '" +
					fileContent + "' found");
		}
		
		return result + Test.PASSED;
	}
	
	private String testFileDeleting() {
		String result = "Deleting file: ";
		
		String fileKey = null;
		try {
			String projectKey = createProject("ProjectName", "ProjectType");
			Project project = getProject(projectKey);
			String rootKey = project.getRootKey();
			fileKey = createFile("file", projectKey, rootKey);
			getFile(fileKey);
			
			deleteFile(fileKey);
		} catch (TestException e) {
			return (result + Test.FAILED + " - " + e.getMessage());
		}

		try {
			getFile(fileKey);
		} catch (TestException e) {
			return result + Test.PASSED;
		}
		
		return result + Test.FAILED + " - file wasn't deleted";
	}

	
	
	
	
	// utility methods

	
	

	private void closeReader(Reader reader) throws TestException {
		try {
			reader.close();
		} catch (IOException e) {
			throw new TestException( 
					"IOException while closing file after reading. " +
					"Error message: " + e.getMessage());
		}
	}

	private String readFile(Reader reader) throws TestException {
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
			throw new TestException(
					"IOException while reading file. " +
					"Error message: " + e.getMessage());
		}
		return fileContent;
	}

	private Reader openFileForReading(SourceFile file) throws TestException {
		try {
			return file.openForReading();
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while opening file for reading. " +
					"Error message: " + e.getMessage());
		}
	}

	private void saveFile(SourceFile file) throws TestException {
		try {
			Database.update(StoringType.SOURCE_FILE, file);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while saving file to database. " +
					"Error message: " + e.getMessage());
		}
	}

	private void closeWriter(Writer writer) throws TestException {
		try {
			writer.close();
		} catch (IOException e) {
			throw new TestException( 
					"IOException while closing file after writing. " +
					"Error message: " + e.getMessage());
		}
	}

	private void writeToFile(Writer writer) throws TestException {
		try {
			writer.write(testString);
		} catch (IOException e) {
			throw new TestException(
					"IOException while writing to the file. " +
					"Error message: " + e.getMessage());
		}
	}

	private Writer openFileForWriting(SourceFile file) throws TestException {
		try {
			return file.openForWriting();
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while opening file for writing. " +
					"Error message: " + e.getMessage());
		}
	}
	

	private void deleteFile(String key) throws TestException {
		try {
			Database.delete(StoringType.SOURCE_FILE, key);
		} catch (DatabaseException e) {
			throw new TestException(
					"DatabaseException while deleting file. " +
					"Error message: " + e.getMessage());
		}
	}

	
}
