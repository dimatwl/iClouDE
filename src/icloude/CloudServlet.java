package icloude;

import java.io.IOException;
import javax.servlet.http.*;

import storage.Database;
import storage.TestData;

@SuppressWarnings("serial")
public class CloudServlet extends HttpServlet {
	
	private final Database db = new Database();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		for (int i = 0; i < 10; i++) {
			db.add(new TestData("" + i));
		}
		
		for (int i = 0; i < 10; i++) {
			resp.getWriter().println(db.contains((i * 2) + ""));
		}
	}
}
