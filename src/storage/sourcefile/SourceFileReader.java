package storage.sourcefile;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.appengine.api.files.FileReadChannel;

public class SourceFileReader {

	private final BufferedReader reader;
	private final FileReadChannel readChannel;
	
	SourceFileReader(BufferedReader reader, FileReadChannel readChannel) {
		this.reader = reader;
		this.readChannel = readChannel;
	}
	
	/**
     * Reads a line of text.  A line is considered to be terminated by any one
     * of a line feed ('\n'), a carriage return ('\r'), or a carriage return
     * followed immediately by a linefeed.
     *
     * @return     A String containing the contents of the line, not including
     *             any line-termination characters, or null if the end of the
     *             stream has been reached
     *
     * @exception  IOException  If an I/O error occurs
     */
	public String getLine() throws IOException {
		return reader.readLine();
	}
	
	/**
     * Reads a single character.
     *
     * @return The character read, as an integer in the range
     *         0 to 65535 (<tt>0x00-0xffff</tt>), or -1 if the
     *         end of the stream has been reached
     * @exception  IOException  If an I/O error occurs
     */
	public int read() throws IOException {
		return reader.read();
	}
	
	/**
	 * Closes input stream
	 * @throws IOException If an I/O error occurs
	 */
	public void close() throws IOException {
		reader.close();
		readChannel.close();
	}
}
