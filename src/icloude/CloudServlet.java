package icloude;

import java.io.IOException;
import javax.servlet.http.*;

import storage.Database;
import storage.TestData;

@SuppressWarnings("serial")
public class CloudServlet extends HttpServlet {
	
	private final Database db = new Database();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello from GET");
		
		if (req.getParameterMap().containsKey("testWord")) {
			String testWord = req.getParameter("testWord");
			resp.getWriter().println("Parameter 'testWord' recieved.");
			if (db.contains(testWord)) {
				resp.getWriter().println("Word '" + testWord + "' found in database.");
			} else {
				resp.getWriter().println("Word '" + testWord + "' not found in database.");
			}
		} else {
			resp.getWriter().println("There is no parameter 'testWord'.");
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello from POST");
		
		if (req.getParameterMap().containsKey("testWord")) {
			String testWord = req.getParameter("testWord");
			resp.getWriter().println("Parameter 'testWord' recieved.");
			db.add(new TestData(testWord));
			resp.getWriter().println("Word '" + testWord + "' added to database.");
		} else {
			resp.getWriter().println("There is no parameter 'testWord'.");
		}
	}
}
