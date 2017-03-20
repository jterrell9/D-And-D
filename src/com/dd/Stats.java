package com.dd;

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
	public Stats(){
		health = 20;
		maxHealth = 20;
		attack = 1;
		defense = 0;
	}
	
	public void changeStat(Stats statChange){
		health += statChange.getHealth();
		maxHealth += statChange.getMaxHealth();
		attack += statChange.getAttack();
		defense += statChange.getDefense();
		if(health > maxHealth){
			health = maxHealth;
		}
	}
	
	public void addMaxHealth(int maxHealthInc){
		maxHealth += maxHealthInc;
	}
	public void addHealth(int healthInc){
		health += healthInc;
		if(health > maxHealth)
			health = maxHealth;
	}
	public void addAttack(int attackInc){
		attack += attackInc;
	}
	public void addDefense(int defenseInc){
		defense += defenseInc;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		if(maxHealth < 1)
			maxHealth = 1;
		this.maxHealth = maxHealth;
		if(health > this.maxHealth)
			health = this.maxHealth;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		if(health < 0)
			this.health = 0;
		else
			this.health = health;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		if(attack < 1)
			this.attack = 1;
		else
			this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		if(defense < 0)
			this.defense = 0;
		else
			this.defense = defense;

	}
	@Override
	public String toString(){
		return "\tHealth:\t\t"+health+"/"+maxHealth+"\n\tAttack/Defense\t"+attack+"/"+defense;
	}
}
