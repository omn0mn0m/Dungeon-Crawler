package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NamReader {
	
	private String filepath;
	File file;
	Scanner scanner;
	
	private String data;
	private boolean foundElement = false;
	
	public NamReader() {
		
	}
	
	public void loadFile(String filepath) {
		this.setFilepath(filepath);
		try {
			file = new File(filepath);
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("A file is missing...");
		}
	}
	
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
	
	public String getStringData() {
		String getData = "";
		if (foundElement) {
			return data;
		}
		return getData;
	}
	
	public int getIntData() {
		int getData = 0;
		if (foundElement) {
			return Integer.parseInt(data);
		}
		return getData;
	}
	
	public double getDoubleData() {
		double getData = 0.0;
		if (foundElement) {
			return Double.parseDouble(data);
		}
		return getData;
	}
	
	public void unloadFile() {
		this.setFilepath(null);
		scanner.close();
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public boolean isFoundElement() {
		return foundElement;
	}
}
