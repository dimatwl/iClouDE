package icloude;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import test.CompositeProjectItemTest;
import test.ProjectTest;
import test.SourceFileTest;

@Path("/testing/database")
public class TestingResource {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getInfoJSON() {
		StringBuilder outputMessageBuilder = new StringBuilder();
		outputMessageBuilder.append("Test results: <br/>");
			
		sourceFileTest(outputMessageBuilder);
		projectTest(outputMessageBuilder);
		compositeProjectItemTest(outputMessageBuilder);
	
		return outputMessageBuilder.toString();
	}

	private void compositeProjectItemTest(StringBuilder outputMessageBuilder) {
		outputMessageBuilder.append("<br/>CompositeProjectItemTest<br/>");
		CompositeProjectItemTest compositeProjectItemTest = new CompositeProjectItemTest();
		for (String s: compositeProjectItemTest.test()) {
			outputMessageBuilder.append(s + "</font><br/>");
		}
	}

	private void projectTest(StringBuilder outputMessageBuilder) {
		outputMessageBuilder.append("<br/>ProjectTest<br/>");
		ProjectTest projectTest = new ProjectTest();
		for (String s: projectTest.test()) {
			outputMessageBuilder.append(s + "</font><br/>");
		}
	}

	private void sourceFileTest(StringBuilder outputMessageBuilder) {
		outputMessageBuilder.append("<br/>SourceFileTest<br/>");
		SourceFileTest SourceFileTest = new SourceFileTest();
		for (String s: SourceFileTest.test()) {
			outputMessageBuilder.append(s + "</font><br/>");
		}
	}

}
