import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class Test1 {

	@Test
	public void test() throws IOException {
		testOneCommand("add cmd", "added to : \"task c\"", "add task c");
		testOneCommand("add invalid cmd", "Invalid command, please type again", "aaadd rampage");
		testOneCommand("add cmd", "added to : \"task b\"", "add task b");
		testOneCommand("add cmd", "added to : \"task a\"", "add task a");
		assertEquals("test display", "1. task c" + System.lineSeparator() + "2. task b" + System.lineSeparator() + "3. task a", TextBuddy.getList());
		testOneCommand("sort cmd", "All tasks have been sorted in alphabetical order", "sort");
		assertEquals("test result after sort", "1. task a" + System.lineSeparator() + "2. task b" + System.lineSeparator() + "3. task c", TextBuddy.getList());
		
	}
	private void testOneCommand(String description, String expected, String command) throws IOException {
		assertEquals(description, expected, TextBuddy.executeCmd(command)); 
	}
}
