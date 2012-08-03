package storage.sourcefile;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.channels.Channels;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;

/**
 * Class for writing files which are located in database.
 * Provides standart Java Writer interface. Shouldn't be created
 * manually, it's only created in SourceFile class.
 * @author Sergey
 *
 */
public class SourceFileWriter extends Writer {
	
	private final Writer writer;
	private final AppEngineFile file;
	private final FileWriteChannel writeChannel;
	private final SourceFile sourceFile;
	
	protected SourceFileWriter(FileWriteChannel writeChannel,
			AppEngineFile file,	SourceFile sourceFile) {
		writer = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));
		this.file = file;
		this.writeChannel = writeChannel;
		this.sourceFile = sourceFile;
	}

	@Override
	public void close() throws IOException {
		writer.close();
		writeChannel.closeFinally();
		FileService fileService = FileServiceFactory.getFileService();
		sourceFile.setContent(fileService.getBlobKey(file));
		System.err.println("Content = " + sourceFile.getContent());
		
//		sourceFile.setProjectKey("new key");
//		PersistenceManager pm = PMF.get().getPersistenceManager();
//		SourceFile tmp = pm.makePersistent(sourceFile);
//		pm.makePersistent(tmp);
//		pm.close();
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		writer.write(cbuf, off, len);
	}

	@Override
	public void flush() throws IOException {
		writer.flush();
	}
}
