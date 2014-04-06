package io.github.omn0mn0m.dungeoncrawler.entity;

import io.github.omn0mn0m.dungeoncrawler.Game;

public class DialoguePart {
	
	private String npcLine;
	private int selectedResponse;
	private String[] choices;
	private String[] responses;
	private boolean finished;
	
	public DialoguePart(String npcLine, int numberOfChoices) {
		this.npcLine = npcLine;
		choices = new String[numberOfChoices];
		responses = new String[numberOfChoices];
	}
	
	public void showDialogue() {
		Game.print(npcLine);
		for(int i = 0; i < choices.length; i++) {
			Game.print(choices[i]);
		}
	}
	
	public void giveNpcResponse() {
		Game.print(responses[selectedResponse]);
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
	
	public void setSelectedResponse(int selectedResponse) {
		this.selectedResponse = selectedResponse;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
