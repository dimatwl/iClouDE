package storage.sourcefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;

import storage.DatabaseException;

import com.google.appengine.api.files.FileReadChannel;

public class SourceFileReader {
	
	private final BufferedReader reader;
	private final FileReadChannel readChannel;

	public SourceFileReader(FileReadChannel readChannel) {
		reader = new BufferedReader(Channels.newReader(readChannel, "UTF8"));
		this.readChannel = readChannel;
	}

	public String readLine() throws DatabaseException {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public int read() throws DatabaseException {
		try {
			return reader.read();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void close() throws DatabaseException {
		try {
			reader.close();
			readChannel.close();
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
