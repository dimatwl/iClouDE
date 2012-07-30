package icloude;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.sourcefile.SourceFile;
import storage.sourcefile.SourceFileReader;
import storage.sourcefile.SourceFileWriter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Path("/api/info")
public class CloudService {

	Gson gson = new Gson();

	private class SimpleMessage {
		public String text;

		public SimpleMessage(String inpWord) {
			text = inpWord;
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getInfoJSON() throws DatabaseException {
		
		String key = Database.create(StoringType.SOURCE_FILE);
		System.err.println(key);
		
		SourceFile file = (SourceFile)Database.get(StoringType.SOURCE_FILE, key);
		SourceFileWriter writer = file.openForWriting();
		writer.println("hello");
		writer.close();
		
		SourceFileReader reader = file.openForReading();
		System.err.println(reader.readLine());
		reader.close();
		
		System.err.println("get");
		SimpleMessage msg = new SimpleMessage("Hello from GET in JSON.");
		return gson.toJson(msg);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.TEXT_PLAIN)
	public String postInfoJSON(@FormParam("json") String inpJSON) {
		System.err.println("post");
		SimpleMessage msg;
		if (inpJSON == null) {
			msg = new SimpleMessage("No 'json' parameter.");
		} else {
			try {
				SimpleMessage fromJSON = gson.fromJson(inpJSON,
						SimpleMessage.class);
				msg = new SimpleMessage(
						"Recieved 'json' parameter containing:'" + inpJSON
								+ "'. After encoding JSON got: '"
								+ fromJSON.text + "'");
			} catch (JsonSyntaxException e) {
				msg = new SimpleMessage(
						"Recieved 'json' parameter containing:'" + inpJSON
								+ "'. But can't decode JSON.");
			}
		}
		return gson.toJson(msg);
	}
}

