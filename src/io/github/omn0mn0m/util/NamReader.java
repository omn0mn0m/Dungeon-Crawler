package io.github.omn0mn0m.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    private String rootPath = (System.getProperty("os.name").toLowerCase().contains("win")) ? "resources/"
                                : "storage/emulated/0/AppProjects/Dungeon-Crawler/resources/";

    // Scanner to parse the file
    private Scanner scanner;
	
	// String for the location of the file
	private String filepath;
	// Data found in the file that is to be returned to the program
	private String data;
	// Whether the data being search for has been found
	private boolean foundElement = false;
	
	/**
	 * Constructor
	 */
	public NamReader() {
		
	}
	
	/**
	 * Loads a file from a specified filepath for the NamReader to use.
	 * It returns a missing file message if the filepath is not correct.
	 * @param fileName - The name of the file
	 */
	public void loadFile(String fileName) {
		this.setFilepath(fileName);
		try {
            // The target file to be read
            File file = new File(filepath);
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			TextPrinter.print("A file is missing...");
		}
	}

    /**
     * Finds a data value for a specific search element. The format in the .nam file is:
     * (key)-(element):
     * with the key being a number and the element being the one searched for. The entire
     * string is put in as the element parameter.
     * @param element - The element to be searched
     */
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

    /**
     * Returns the data as an integer.
     * @return Int data
     */
    public int getIntData() {
        int getData = 0;
        if (foundElement) {
            return Integer.parseInt(data);
        }
        return getData;
    }

	/**
	 * Gets the total blocks to parse in .nam file.
	 * @param fileName - The name of the file
	 * @return Total - The total amount found in the list
	 */
	public int getTotal(String fileName) {
		loadFile(fileName);
		findData("Total");
		int total = getIntData();
		unloadFile();
		return total;
	}
	
	/**
	 * Returns the data as a string.
	 * @return String data
	 */
	public String getStringData() {
		String getData = "";
		if (foundElement) {
			return data;
		}
		return getData;
	}
	
	/**
	 * Returns the data as a double.
	 * @return Double data
	 */
	public double getDoubleData() {
		double getData = 0.0;
		if (foundElement) {
			return Double.parseDouble(data);
		}
		return getData;
	}
	
	/**
	 * Unloads the file from memory and closes the scanner.
	 */
	public void unloadFile() {
		this.setFilepath(null);
		scanner.close();
	}

	/**
	 * Returns the current filepath.
	 * @return Current filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * Sets the current filepath.
	 * @param filepath - The path to the file
	 */
	public void setFilepath(String filepath) {
		this.filepath = rootPath + filepath;
	}

	/**
	 * Returns if the desired element has been found.
	 * @return If the element has been found.
	 */
	public boolean isFoundElement() {
		return foundElement;
	}
}
