package io.github.omn0mn0m.dungeoncrawler.entity;

/**
 * This is the class for all attacks. For the sake of organisation,
 * all attacks are saved as a class.
 * @author Nam Tran
 *
 */
public class Attack {
	
	private String attackName;	// Name of the attack
	private String requires;
	private int attackBuff;		// How much the attack adds to base attack stat
	
	/**
	 * Constructor with the attack name, and the buff to basic attack stat.
	 * @param attackName
	 * @param attackBuff
	 */
	public Attack(String attackName, String requires, int attackBuff) {
		setAttackName(attackName);
		setAttackRequires(requires);
		setAttackBuff(attackBuff);
	}
	
	/**
	 * Copy-constructor
	 * @param attack
	 */
	public Attack(Attack attack) {
		this(attack.getAttackName(), attack.getAttackRequires(), attack.getAttackBuff());
	}
	
	/**
	 * Returns the amount of the buff the attack does to the attack.
	 * @return attack buff
	 */
	public int getAttackBuff() {
		return attackBuff;
	}
	
	/**
	 * Sets the attack buff to a new value that is not default.
	 * @param attackBuff
	 */
	public void setAttackBuff(int attackBuff) {
		this.attackBuff = attackBuff;
	}

	/**
	 * Returns the name of the attack
	 * @return attack name
	 */
	public String getAttackName() {
		return attackName;
	}
	
	public String getAttackRequires() {
		return requires;
	}

	/**
	 * Sets a new name to the attack that is not the default.
	 * @param attackName
	 */
	public void setAttackName(String attackName) {
		this.attackName = attackName;
	}
	
	public void setAttackRequires(String requires) {
		this.requires = requires;
	}
	
	public boolean isTarget(String target) {
    	return this.getAttackName().equalsIgnoreCase(target);
    }
}
