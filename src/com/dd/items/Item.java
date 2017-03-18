package com.dd.items;

import com.dd.Stats;

public class Item {
	private String name;
	private Stats StatModifyer;
	public Item(String name) {
		this.name=name;
		StatModifyer=new Stats(0,0,0,0);
	}
	public String getName() {
		return name;
	}
	
	public void setStatChange(int health,int maxHealth,int attack,int defense){
		StatModifyer=new Stats(health,maxHealth,attack,defense);
	}
	public Stats getStatChange(){
		return StatModifyer;
	}
	public Stats getNegStatChange(){
		int negHealth=-1*StatModifyer.getHealth();
		int negMaxHealth=-1*StatModifyer.getMaxHealth();
		int negAttack=-1*StatModifyer.getAttack();
		int negDefense=-1*StatModifyer.getDefense();
		Stats negStats=new Stats(negHealth,negMaxHealth,negAttack,negDefense);
		return negStats;
	}
	
	@Override
	public String toString(){
		return name;
	}
}