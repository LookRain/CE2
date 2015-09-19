import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class Test1 {

	@Test
	public void test() throws IOException {
		//TextBuddy.main("test.txt".split(""));
		testOneCommand("add cmd", "added to test.txt: \"rampage\"", "add rampage");
		
	}
	private void testOneCommand(String description, String expected, String command) throws IOException {
		assertEquals(description, expected, TextBuddy.execute(command)); 
	}
}
