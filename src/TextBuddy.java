import java.io.*;
import java.util.*;
public class TextBuddy {

	public static String fileName = new String();
	public static ArrayList<String> list = new ArrayList<String>();

	public static final String WELCOME_MESSAGE =  "Welcome to TextBuddy. %s is ready for use";
	public static final String FILE_NOT_FOUND_MESSAGE = "File not found";
	public static final String INVALID_COMMAND_MESSAGE = "Invalid command, please type again";
	public static final String ADDED_TO_LIST_MESSAGE = "added to %s: ";
	public static final String EMPTY_LIST_MESSAGE = "%s is empty";
	public static final String CLEAR_LIST_MESSAGE = "all content deleted from %s";
	public static final String DELETE_MESSAGE = "deleted from %s: \"%s\"";

	public static void main(String[ ] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		fileName = args[0];
	
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			System.out.println(String.format(WELCOME_MESSAGE, fileName));
			while (true) {
				System.out.print("command: ");
				String input = sc.nextLine();
				System.out.println(executeCmd(input));
			}
		} catch (IOException e){
			System.out.println(FILE_NOT_FOUND_MESSAGE);
		}
	}
	
	public static void checkFile(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
	}

	static String executeCmd(String input) throws IOException {
		if (input.substring(0, 3).equals("add")) {
			String taskName = input.substring(4);
			return add(taskName);

		} else if (input.equals("display")) {
			display();
		} else if (input.equals("clear")) {
			return clear();
		} else if (input.equals("exit")) {
			exit();
		} else if (input.substring(0, 6).equals("delete")) {
			String taskIndex = input.substring(7);
			int index = Integer.parseInt(taskIndex);
			return delete(index);
		} else {
			return INVALID_COMMAND_MESSAGE;
		}
		return "";

	}
	
	/**
	 * This operation lets the user exit
	 * from the program. The program will save the file
	 *  before it exits
	 * @throws IOException 
	 */
	static void exit() throws IOException {
		update();
		System.exit(0);
	}
	/**
	 * This operation deletes the line that the user
	 * inputs from the list
	 */
	private static String delete(int index) {
		String ans;
		ans = String.format(DELETE_MESSAGE, fileName, list.get(index - 1));
		list.remove(index - 1);
		return ans;
	}
	/**
	 * This operation clears everything from the list
	 */
	private static String clear() {
		list.clear();
		return String.format(CLEAR_LIST_MESSAGE, fileName);
	}
	/**
	 * This operation prints out the content of the list
	 * in the order of which they are put in
	 */
	private static void display() {
		if (list.isEmpty()) {
			System.out.println(String.format(EMPTY_LIST_MESSAGE,fileName));
		} else {
			int index = 1;
			for (String e: list) {
				System.out.println(index + ". " + e);
				index ++;
			}
		}
	}
	/**
	 * This operation adds an item into the list
	 */
	private static String add(String taskName) {
		list.add(taskName);
		return String.format(ADDED_TO_LIST_MESSAGE, fileName) + "\"" + taskName + "\"";
	}
	/**
	 * This operation saves the content of the list in 
	 * the text file, in the order of which they are put in,
	 * an index number from 1 will be given to each item
	 */
	private static void update() throws IOException {
		FileWriter writer = new FileWriter(fileName);
		//BufferedWriter output = new BufferedWriter(writer);
		for (int i = 0; i < list.size(); i ++) {
			int index = i + 1;
			writer.write(index + ". " + list.get(i));
			writer.write(System.lineSeparator());
		}
		writer.close();
	}

}