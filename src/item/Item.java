package item;

<<<<<<< HEAD
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
=======
public class Item {
	
	protected String name, type;
	protected int attackBuff, defenseBuff, healthBuff;
	
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public Item() {
		
	}
	
<<<<<<< HEAD
	/**
	 * Constructor with full item information parameters.
	 * @param name
	 * @param type
	 * @param attackBuff
	 * @param defenseBuff
	 * @param healthBuff
	 */
=======
	public Item(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public Item(String name, String type, int attackBuff, int defenseBuff, int healthBuff) {
		this.name = name;
		this.type = type;
		this.attackBuff = attackBuff;
		this.defenseBuff = defenseBuff;
		this.healthBuff = healthBuff;
	}
	
<<<<<<< HEAD
	/**
	 * Returns a specified stat buff about the item.
	 * @param stat
	 * @return Specified stat
	 */
	public int getStatBuff(String stat) {
=======
	public int getStateBuff(String stat) {
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
		int statToDisplay = 0;
		switch (stat) {
			case "attack":
				return attackBuff;
			case "defense":
				return defenseBuff;
			case "health":
				return healthBuff;
			default:
<<<<<<< HEAD
=======
				System.out.println("That is not a valid stat");
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
				break;
		}
		return statToDisplay;
	}
	
<<<<<<< HEAD
	/**
	 * Returns the name of the item.
	 * @return name
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public String getName() {
		return name;
	}
	
<<<<<<< HEAD
	/**
	 * Returns the type of the item.
	 * @return type
	 */
=======
>>>>>>> 6b665932e073efc86b8311c776e2e473258f624b
	public String getType() {
		return type;
	}
}
