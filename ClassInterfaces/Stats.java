/**
 * Stats are numbers representing maximum health, current health, attack, and defense.
 */

public class Stats {

	private int maxHealth;
	private int health;
	private int attack;
	private int defense;
	
	/**
	 * Constructs stats with the provided maximum health, health, attack, and defense.
	 * @pre maxHealth >= health
	 */
	public Stats(int maxHealth, int health, int attack, int defense) {
		
	}
	
	/**
	 * Increases all of the stats.
	 */
	public Stats increaseStats(Stats amount) {
		
	}
	
	/**
	 * Increases the maximum health stat.
	 */
	public int increaseMaxHealth(int amount) {
		
	}
	
	/**
	 * Increases the health stat.
	 */
	public int increaseHealth(int amount) {
		
	}
	
	/**
	 * Increases the attack stat.
	 */
	public int increaseAttack(int amount) {
		
	}
	
	/**
	 * Increases the defense stat.
	 */
	public int increaseDefense(int amount) {
		
	}
	
	/**
	 * Returns the maximum health stat.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Sets the maximum health stat.
	 * @pre maxHealth >= 0
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	/**
	 * Returns the health stat.
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets the health stat.
	 * @pre health >= 0
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Returns the attack stat.
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * Sets the attack stat.
	 * @pre attack >= 0
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	/**
	 * Returns the defense stat.
	 */
	public int getDefense() {
		return defense;
	}
	
	/**
	 * Sets the defense stat.
	 * @pre defense >= 0
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	/**
	 * Returns a string representation of stats.
	 */
	public String toString() {
		
	}

}
