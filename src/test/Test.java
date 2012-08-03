package test;

import java.util.List;

public interface Test {
	
	public static final String PASSED = "passed";
	public static final String FAILED = "failed";

	public List<String> test();
}
