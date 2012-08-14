package icloude.testing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import test.CompositeProjectItemTest;
import test.ProjectTest;
import test.SourceFileTest;
import test.TaskTest;

@Path("/testing/database")
public class TestingDB {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getInfoJSON() {
		StringBuilder outputMessageBuilder = new StringBuilder();
		outputMessageBuilder.append("Test results: <br/>");
			
		sourceFileTest(outputMessageBuilder);
		projectTest(outputMessageBuilder);
		compositeProjectItemTest(outputMessageBuilder);
		taskTest(outputMessageBuilder);
	
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
		SourceFileTest sourceFileTest = new SourceFileTest();
		for (String s: sourceFileTest.test()) {
			outputMessageBuilder.append(s + "</font><br/>");
		}
	}

	private void taskTest(StringBuilder outputMessageBuilder) {
		outputMessageBuilder.append("<br/>TaskTest<br/>");
		TaskTest taskTest = new TaskTest();
		for (String s: taskTest.test()) {
			outputMessageBuilder.append(s + "</font><br/>");
		}
	}
}
