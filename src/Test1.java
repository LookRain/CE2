import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class Test1 {

	@Test
	public void test() throws IOException {
		testOneCommand("add cmd1", "added to : \"task c\"", "add task c");
		testOneCommand("add invalid cmd", "Invalid command, please type again", "aaadd rampage");
		testOneCommand("add cmd2", "added to : \"task b\"", "add task b");
		testOneCommand("add cmd3", "added to : \"task a\"", "add task a");
		assertEquals("test display", "1. task c" + System.lineSeparator() + "2. task b" + System.lineSeparator() + "3. task a", TextBuddy.getList());

		testOneCommand("sort cmd", "All tasks have been sorted in alphabetical order", "sort");
		assertEquals("test result after sort", "1. task a" + 
				System.lineSeparator() + "2. task b" + 
				System.lineSeparator() + "3. task c", 
				TextBuddy.getList());
		testOneCommand("add cmd4", "added to : \"happy tree friends\"", "add happy tree friends");
		testOneCommand("add cmd5", "added to : \"we bare bears\"", "add we bare bears");
		testOneCommand("add cmd6", "added to : \"happyness\"", "add happyness");
		testOneCommand("sort cmd", "All tasks have been sorted in alphabetical order", "sort");
		assertEquals("test result after sort", "1. happy tree friends" + 
				System.lineSeparator() + "2. happyness"+
				System.lineSeparator() + "3. task a" +
				System.lineSeparator() + "4. task b" +
				System.lineSeparator() + "5. task c" +
				System.lineSeparator() + "6. we bare bears",
				TextBuddy.getList());
		testOneCommand("add cmd7", "added to : \"im happy\"", "add im happy");
		testOneCommand("search cmd", "1. happy tree friends" + 
				System.lineSeparator() + "2. happyness"+
				System.lineSeparator() + "7. im happy", 
				"search happy");
		testOneCommand("search cmd", "No task contains such keyword", "search sad");

	}
	private void testOneCommand(String description, String expected, String command) throws IOException {
		assertEquals(description, expected, TextBuddy.executeCmd(command)); 
	}
}
