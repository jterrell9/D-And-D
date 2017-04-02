package com.dd;

import java.io.Serializable;

public class Stats implements Serializable{
	
	protected int health;
	protected int maxHealth;
	protected int attack;
	protected int defense;
	
	public Stats(int health, int maxHealth, int attack, int defense) {
		setMaxHealth(maxHealth);
		setHealth(health);
		setAttack(attack);
		setDefense(defense);
	}
	
	public void changeStat(Stats statChange){
		addMaxHealth(statChange.getMaxHealth());
		addHealth(statChange.getHealth());
		addAttack(statChange.getAttack());
		addDefense(statChange.getDefense());
	}
	
	public void addMaxHealth(int maxHealthInc){
		maxHealth += maxHealthInc;
	}
	
	public void addHealth(int healthInc){
		setHealth(getHealth() + healthInc);
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
		if(maxHealth < 0) {
			this.maxHealth = 0;
		}
		else {
			this.maxHealth = maxHealth;
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		if(health > maxHealth){
			this.health = getMaxHealth();
		}
		else if(health < 0){
			this.health = 0;
		}
		else{
			this.health = health;
		}
	}
	
	public int getAttack() {
		return attack;
	}
	
	public void setAttack(int attack) {
		if(attack < 0) {
			this.attack = 0;
		}
		else {
			this.attack = attack;
		}
	}
	
	public int getDefense() {
		return defense;
	}
	
	public void setDefense(int defense) {
		if(defense < 0) {
			this.defense = 0;
		}
		else {
			this.defense = defense;
		}
	}
	
	@Override
	public String toString(){
		return "Health:\t\t" 
				+ getHealth()
				+ "/" + getMaxHealth()
				+ "\nAttack/Defense\t"
				+ getAttack()
				+ "/" + getDefense();
	}
}
