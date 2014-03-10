package item;

/**
 * This is a basic item in the game. It has a name, type, and various stat buffs.
 * @author Nam Tran
 *
 */
public class Item {
	
	private String name, type; // String info for the item
	private int attackBuff, defenseBuff, healthBuff; // Stat buffs for the item
	
	/**
	 * Constructor
	 */
	public Item() {
		
	}
	
	/**
	 * Constructor with full item information parameters.
	 * @param name
	 * @param type
	 * @param attackBuff
	 * @param defenseBuff
	 * @param healthBuff
	 */
	public Item(String name, String type, int attackBuff, int defenseBuff, int healthBuff) {
		this.name = name;
		this.type = type;
		this.attackBuff = attackBuff;
		this.defenseBuff = defenseBuff;
		this.healthBuff = healthBuff;
	}
	
	/**
	 * Returns a specified stat buff about the item.
	 * @param stat
	 * @return Specified stat
	 */
	public int getStatBuff(String stat) {
		int statToDisplay = 0;
		switch (stat) {
			case "attack":
				return attackBuff;
			case "defense":
				return defenseBuff;
			case "health":
				return healthBuff;
			default:
				break;
		}
		return statToDisplay;
	}
	
	/**
	 * Returns the name of the item.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the type of the item.
	 * @return type
	 */
	public String getType() {
		return type;
	}
}
