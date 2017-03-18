package old;

public class Stats {
	private int health;
	private int maxHealth;
	private int attack;
	private int defense;
	public Stats(int health,int maxHealth,int attack,int defense) {
		this.health=health;
		this.maxHealth=maxHealth;
		this.attack=attack;
		this.defense=defense;
	}
	
	public void changeStat(Stats statChange){
		health+=statChange.getHealth();
		maxHealth+=statChange.getMaxHealth();
		attack+=statChange.getAttack();
		defense+=statChange.getDefense();
		if(health>maxHealth){
			health=maxHealth;
		}
	}
	
	public void addMaxHealth(int i){
		maxHealth+=i;
	}
	public void addHealth(int i){
		health+=i;
		if(health>maxHealth)
			health=maxHealth;
	}
	public void addAttack(int i){
		attack+=i;
	}
	public void addDefense(int i){
		defense+=i;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	@Override
	public String toString(){
		return "Health:\t\t"+health+"/"+maxHealth+"\nAttack/Defense\t"+attack+"/"+defense;
	}
}
