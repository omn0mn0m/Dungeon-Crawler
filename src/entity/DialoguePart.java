package entity;

import java.util.HashMap;
import java.util.Map;

public class DialoguePart {
	
	private String npcLine;
	private int selectedResponse;
	private String[] choices;
	private String[] responses;
	
	public DialoguePart(String npcLine, int numberOfChoices) {
		this.npcLine = npcLine;
		choices = new String[numberOfChoices];
		responses = new String[numberOfChoices];
	}
	
	public String getNpcLine() {
		return npcLine;
	}
	
	public void setNpcLine(String npcLine) {
		this.npcLine = npcLine;
	}
	
	public int getSelectedResponse() {
		return selectedResponse;
	}
}
