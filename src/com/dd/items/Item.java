package com.dd.items;

import java.io.Serializable;

import com.dd.Stats;
import com.dd.StatModifier;

public class Item implements Serializable {
	
	protected String name;
	protected StatModifier statModifier;
	
	public Item(String name) {
		setName(name);
	}
	
	public Item() {
		setName("blank item");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStatChange(int health, int maxHealth, int attack, int defense) {
		statModifier=new StatModifier(health,maxHealth,attack,defense);
	}
	
	public Stats getStatChange() { 
		return statModifier;
	}
	
	public Stats getNegStatChange() {
		int negHealth = 0;
		int negMaxHealth = -1 * statModifier.getMaxHealth();
		int negAttack = -1 * statModifier.getAttack();
		int negDefense = -1 * statModifier.getDefense();
		Stats negStats = new StatModifier(negHealth, negMaxHealth, negAttack, negDefense);
		return negStats;
	}
	
	public String statModToString() {
		StringBuilder modStr=new StringBuilder("[");
		if(getStatChange().getHealth() > 0){
			modStr.append("(+" + getStatChange().getHealth() + " Health)");
		}
		if(getStatChange().getMaxHealth() > 0){
			modStr.append("(+" + getStatChange().getMaxHealth() + " Max Health)");
		}
		if(getStatChange().getAttack() > 0) {
			modStr.append("(+" + getStatChange().getAttack() + " Attack)");
		}
		if(getStatChange().getDefense() > 0){
			modStr.append("(+" + getStatChange().getDefense() + " Defense)");
		}
		modStr.append("]");
		return modStr.toString();
	}
	
	public String typeToString() {
		if(getClass().toString().length() > 19) {
			return getClass().toString().substring(19);
		}
		else {
			return "item";
		}
	}
	
	public String examineToString(){
		return statModToString() + " (" + typeToString() + ")";
	}
	
	public String titleToString() {
		return "\"" + getName() + "\" the " + typeToString();
	}
}