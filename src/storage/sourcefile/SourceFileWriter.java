package storage.sourcefile;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.Channels;

import storage.DatabaseException;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;

public class SourceFileWriter {
	
	private final PrintWriter writer;
	private final AppEngineFile file;
	private final FileWriteChannel writeChannel;
	private final SourceFile sourceFile;
	
	public SourceFileWriter(FileWriteChannel writeChannel,
			AppEngineFile file,	SourceFile sourceFile) {
		writer = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));
		this.file = file;
		this.writeChannel = writeChannel;
		this.sourceFile = sourceFile;
	}

	public void print(Object obj) {
		writer.print(obj);
	}
	
	public void println(Object obj) {
		writer.println(obj);
	}
	
	public void println() {
		writer.println();
	}
	
	public void close() throws DatabaseException {
		writer.close();
		try {
			writeChannel.closeFinally();
		} catch (IllegalStateException e) {
			throw new DatabaseException(e.getMessage());
		} catch (IOException e) {
			throw new DatabaseException(e.getMessage());
		}
		FileService fileService = FileServiceFactory.getFileService();
		sourceFile.setContent(fileService.getBlobKey(file));
	}
}
