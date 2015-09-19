import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class Test1 {

	@Test
	public void test() throws IOException {
		//TextBuddy.main("test.txt".split(""));
		testOneCommand("add cmd", "added to : \"task a\"", "add task a");
		testOneCommand("add cmd", "Invalid command, please type again", "aaadd rampage");
		testOneCommand("add cmd", "added to : \"task b\"", "add task b");
		testOneCommand("add cmd", "added to : \"task c\"", "add task c");
		
	}
	private void testOneCommand(String description, String expected, String command) throws IOException {
		assertEquals(description, expected, TextBuddy.executeCmd(command)); 
	}
}
