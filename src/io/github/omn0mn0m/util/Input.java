package io.github.omn0mn0m.util;

import java.util.Scanner;

/**
 * This class is used to read the user input from a keyboard.
 * @author Nam Tran
 *
 */
public class Input {
	
	private Scanner input = new Scanner(System.in);
	private String[] splitInput;
	
	/**
	 * Constructor
	 */
	public Input() {
		
	}
	
	/**
	 * Returns whatever is inputed into the computer, regardless of what it says
	 * or how many words are used. It automatically lower-cases the string.
	 * @return input
	 */
	public String getSimpleInput() {
    	return input.nextLine().toLowerCase();
    }

	/**
	 * Sets an array of strings to each word in an input.
	 */
	public void createSplitInput() {
		splitInput = getSimpleInput().split(" ");
	}
	
	/**
	 * Returns a given word in a sentence, provided that a word already exists there.
	 * @param wordNumber
	 * @return
	 */
	public String getInputWord(int wordNumber) {
		String str = "";
		
		if (wordNumber < splitInput.length) {
			return splitInput[wordNumber];
		} else {
			TextPrinter.print("There is a word missing in the input...");
		}
		
		return str;
	}
	
	/**
	 * Gets the input from the user as an array of words, then looks for the word
	 * at a given point in the sentence.
	 * @param wordNumber
	 * @return word
	 */
	public String splitAndGetInput(int wordNumber) {
		createSplitInput();
		return getInputWord(wordNumber);
	}
	
	/**
	 * Returns the length of a multi-word input.
	 * @return length
	 */
	public int getSplitLength() {
		return splitInput.length;
	}
	
	public boolean isSplitWordTarget(int wordNumber, String target) {
		String comparedString = "";
		
		if (wordNumber < splitInput.length) {
			comparedString = getInputWord(wordNumber);
		}
		
		return comparedString.equals(target);
	}
}
