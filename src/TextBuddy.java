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
	public static final String SORTED_MESSAGE = "All tasks have been sorted in alphabetical order";
	public static final String NO_SEARCH_RESULT_MESSAGE = "No task contains such keyword";

	public static void main(String[ ] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		fileName = args[0];
		if (checkFile(fileName)) {		
			System.out.println(String.format(WELCOME_MESSAGE, fileName));
			while (true) {
				System.out.print("command: ");
				String input = sc.nextLine();
				System.out.println(executeCmd(input));
			}
		}
	}

	/**
	 * This operation checks if the specified file
	 * exits in directory.
	 */
	public static boolean checkFile(String fileName) {
		boolean isExistent = true;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				isExistent = false;
				throw new FileNotFoundException();
			}
		} catch (IOException e){
			System.out.println(FILE_NOT_FOUND_MESSAGE);
		}
		return isExistent;
	}

	/**
	 * This operation checks reads the command input and carries out
	 * each operation.
	 */
	static String executeCmd(String input) throws IOException {
		String output = "";
		if (input.split(" ")[0].equals("add")) {
			String taskName = input.substring(4);
			output = add(taskName);
		} else if (input.equals("display")) {
			display();
		} else if (input.equals("clear")) {
			output = clear();
		} else if (input.equals("exit")) {
			exit();
		} else if (input.split(" ")[0].equals("delete")) {
			String taskIndex = input.substring(7);
			int index = Integer.parseInt(taskIndex);
			output = delete(index);
		} else if (input.equals("sort")) {
			sort();
			output = SORTED_MESSAGE;
		} else if (input.split(" ")[0].equals("search")) {
			String keyWord = input.substring(7);
			output = search(keyWord);
		} else {
			output = INVALID_COMMAND_MESSAGE;
		}

		return output;

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

	public static String getList() {
		String result = "";
		if (list.isEmpty()) {
			return String.format(EMPTY_LIST_MESSAGE,fileName);
		} else {
			result = "1. " + list.get(0);
			for (int i = 2; i <= list.size(); i++) {
				result += System.lineSeparator() + i + ". " + list.get(i - 1) ;
			}
		}
		return result;
	}
	/**
	 * This operation prints out the content of the list
	 * in the order of which they are put in
	 */
	private static void display() {
		System.out.print(getList());
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

	public static void sort() {
		Collections.sort(list);
	}

	public static String search(String key) {
		String result = "";
		for (int i = 0; i < list.size(); i ++) {
			if (list.get(i).contains(key)) {
				result += i + 1 + ". " + list.get(i) + System.lineSeparator();
			} 
		}
		if (result.length() == 0) {
			return NO_SEARCH_RESULT_MESSAGE;
		}
		return result.trim();
	}
}
