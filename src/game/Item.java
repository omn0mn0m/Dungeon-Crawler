package game;

public class Item {
	
	protected String name;
	protected String description;
	
	protected int attackBuff;
	protected int defenseBuff;
	protected int healthBuff;
	
	public Item() {
		
	}
	
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Item (String name, String description, int attackBuff, int defenseBuff, int healthBuff) {
		this.name = name;
		this.description = description;
		this.attackBuff = attackBuff;
		this.defenseBuff = defenseBuff;
		this.healthBuff = healthBuff;
	}
	
	public int getStateBuff(String stat) {
		int statToDisplay = 0;
		switch (stat) {
			case "attack":
				return attackBuff;
			case "defense":
				return defenseBuff;
			case "health":
				return healthBuff;
			default:
				System.out.println("That is not a valid stat");
				break;
		}
		return statToDisplay;
	}
	
	public String getInfo(String info) {
		String infoToDisplay = "";
		switch (info) {
			case "name":
				return name;
			case "description":
				return description;
			default:
				System.out.println("That information is nonexistent");
				break;
		}
		return infoToDisplay;
	}
}
