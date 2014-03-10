package util;

<<<<<<< HEAD
import game.Game;

=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

<<<<<<< HEAD
/**
 * This is a parser for the .nam file type. It uses a key number to
 * distinguish different objects' data, then a keyword afterwards to
 * determine what variable to write to.
 * <p>
 * It is best to load .nam files as a map at the startup of a program.
 * @author Nam Tran
 * 
 */
public class NamReader {
	
	// The target file to be read
	File file;
	// Scanner to parse the file
	Scanner scanner;
	
	// String for the location of the file
	private String filepath;
	// Data found in the file that is to be returned to the program
	private String data;
	// Whether the data being search for has been found
	private boolean foundElement = false;
	
	/**
	 * Constructor
	 */
=======
public class NamReader {
	
	private String filepath;
	File file;
	Scanner scanner;
	
	private String data;
	private boolean foundElement = false;
	
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public NamReader() {
		
	}
	
<<<<<<< HEAD
	/**
	 * Loads a file from a specified filepath for the NamReader to use.
	 * It returns a missing file message if the filepath is not correct.
	 * @param filepath
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public void loadFile(String filepath) {
		this.setFilepath(filepath);
		try {
			file = new File(filepath);
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
<<<<<<< HEAD
			Game.print("A file is missing...");
		}
	}
	
	/**
	 * Finds a data value for a specific search element. The format in the .nam file is:
	 * (key)-(element):
	 * with the key being a number and the element being the one searched for. The entire
	 * string is put in as the element parameter.
	 * @param element
	 */
=======
			System.out.println("A file is missing...");
		}
	}
	
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public void findData(String element) {
		while (scanner.hasNextLine()) {
			if (scanner.nextLine().equalsIgnoreCase(element + ":")) {
				data = scanner.nextLine();
				foundElement = true;
				break;
			} else {
				foundElement = false;
			}
		}
	}
	
<<<<<<< HEAD
	/**
	 * Returns the data as a string.
	 * @return String data
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public String getStringData() {
		String getData = "";
		if (foundElement) {
			return data;
		}
		return getData;
	}
	
<<<<<<< HEAD
	/**
	 * Returns the data as an integer.
	 * @return Int data
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public int getIntData() {
		int getData = 0;
		if (foundElement) {
			return Integer.parseInt(data);
		}
		return getData;
	}
	
<<<<<<< HEAD
	/**
	 * Returns the data as a double.
	 * @return Double data
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public double getDoubleData() {
		double getData = 0.0;
		if (foundElement) {
			return Double.parseDouble(data);
		}
		return getData;
	}
	
<<<<<<< HEAD
	/**
	 * Unloads the file from memory and closes the scanner.
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public void unloadFile() {
		this.setFilepath(null);
		scanner.close();
	}

<<<<<<< HEAD
	/**
	 * Returns the current filepath.
	 * @return Current filepath
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public String getFilepath() {
		return filepath;
	}

<<<<<<< HEAD
	/**
	 * Sets the current filepath.
	 * @param filepath
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

<<<<<<< HEAD
	/**
	 * Returns if the desired element has been found.
	 * @return If the element has been found.
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public boolean isFoundElement() {
		return foundElement;
	}
}
