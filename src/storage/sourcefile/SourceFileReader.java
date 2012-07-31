package storage.sourcefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.channels.Channels;

import com.google.appengine.api.files.FileReadChannel;

/**
 * Class for reading source files which are located in database
 * @author Sergey
 *
 */
public class SourceFileReader extends Reader {
	
	private final Reader reader;
	private final FileReadChannel readChannel;

	protected SourceFileReader(FileReadChannel readChannel) {
		reader = new BufferedReader(Channels.newReader(readChannel, "UTF8"));
		this.readChannel = readChannel;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return reader.read(cbuf, off, len);
	}
	
	@Override
	public void close() throws IOException {
		reader.close();
		readChannel.close();
	}
}
