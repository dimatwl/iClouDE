package storage.sourcefile;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.appengine.api.files.FileWriteChannel;

public class SourceFileWriter {
	
	private final PrintWriter writer;
	private final FileWriteChannel writeChannel;
	
	SourceFileWriter(PrintWriter writer, FileWriteChannel writeChannel) {
		this.writer = writer;
		this.writeChannel = writeChannel;
	}

	/**
     * Prints an object.  The string produced by the <code>{@link
     * java.lang.String#valueOf(Object)}</code> method is translated into bytes
     * according to the platform's default character encoding, and these bytes
     * are written in exactly the manner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @param      obj   The <code>Object</code> to be printed
     */
	public void print(Object obj) {
		writer.print(obj);
	}
	
	/**
     * Prints an Object and then terminates the line.  This method calls
     * at first String.valueOf(obj) to get the printed object's string value,
     * then behaves as
     * though it invokes <code>{@link #print(String)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param obj  The <code>Object</code> to be printed.
     */
	public void println(Object obj) {
		writer.println(obj);
	}
	
	/**
     * Terminates the current line by writing the line separator string.  The
     * line separator string is defined by the system property
     * <code>line.separator</code>, and is not necessarily a single newline
     * character (<code>'\n'</code>).
     */
	public void println() {
		writer.println();
	}
	
	/**
     * Closes the stream and releases any system resources associated
     * with it. Closing a previously closed stream has no effect.
	 * @throws IOException 
	 * @throws IllegalStateException 
     *
     * @see #checkError()
     */
	public void close() throws IllegalStateException, IOException {
		writer.close();
		writeChannel.closeFinally();
	}
}
