package item;

public class Item {
	
	protected String name, type;
	protected int attackBuff, defenseBuff, healthBuff;
	
	public Item() {
		
	}
	
	public Item(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public Item(String name, String type, int attackBuff, int defenseBuff, int healthBuff) {
		this.name = name;
		this.type = type;
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
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
}
