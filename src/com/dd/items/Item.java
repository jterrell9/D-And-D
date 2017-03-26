package com.dd.items;

import com.dd.Stats;

<<<<<<< HEAD
public abstract class Item {
=======
public class Item {
>>>>>>> refs/remotes/origin/Testing
	
	protected String name;
	protected Stats StatModifyer;
	
	public Item(String name) {
		this.name=name;
<<<<<<< HEAD
=======
		StatModifyer=new Stats();
>>>>>>> refs/remotes/origin/Testing
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStatChange(int health, int maxHealth, int attack, int defense) {
		StatModifyer=new Stats(health,maxHealth,attack,defense);
	}
	
	public Stats getStatChange() { 
		return StatModifyer;
	}
	
	public Stats getNegStatChange() {
<<<<<<< HEAD
		int negHealth = -1 * StatModifyer.getHealth();
		int negMaxHealth = -1 * StatModifyer.getMaxHealth();
		int negAttack = -1 * StatModifyer.getAttack();
		int negDefense = -1 * StatModifyer.getDefense();
=======
		int negHealth = -1*StatModifyer.getHealth();
		int negMaxHealth = -1*StatModifyer.getMaxHealth();
		int negAttack = -1*StatModifyer.getAttack();
		int negDefense = -1*StatModifyer.getDefense();
>>>>>>> refs/remotes/origin/Testing
		Stats negStats = new Stats(negHealth, negMaxHealth, negAttack, negDefense);
		return negStats;
	}
	
	public String statModToString() {
		StringBuilder modStr=new StringBuilder();
<<<<<<< HEAD
		if(getStatChange().getHealth() > 0){
			modStr.append("+" + getStatChange().getHealth() + " Health");
		}
		if(getStatChange().getMaxHealth() > 0){
			modStr.append("+" + getStatChange().getMaxHealth() + " Max Health");
		}
		if(getStatChange().getAttack() > 0) {
			modStr.append("+" + getStatChange().getAttack() + " Attack");
		}
		if(getStatChange().getDefense() > 0){
			modStr.append("+" + getStatChange().getDefense() + " Defense");
=======
		if(getStatChange().getHealth()>0){
			modStr.append("[+" + getStatChange().getHealth() + " Health]");
		}
		if(getStatChange().getMaxHealth()>0){
			modStr.append("[+" + getStatChange().getMaxHealth() + " Max Health]");
		}
		if(getStatChange().getAttack()>0) {
			modStr.append("[+" + getStatChange().getAttack() + " Attack]");
		}
		if(getStatChange().getDefense()>0){
			modStr.append("[+" + getStatChange().getDefense() + " Defense]");
>>>>>>> refs/remotes/origin/Testing
		}
		return modStr.toString();
	}
	
	public String typeToString() {
		return getClass().toString().substring(19);
	}
	
	public String examineToString(){
<<<<<<< HEAD
		return "[" + statModToString() + ", " + typeToString() + "]";
=======
		return "("+typeToString()+") " + getName() + " " + statModToString();
>>>>>>> refs/remotes/origin/Testing
	}
}