package com.dd.items;

public class Artifact extends Item {
	
	public Artifact(String name, int healthGain, int maxHealthGain, int attackGain,int defenseGain) {
		super(name);
		super.setStatChange(healthGain, maxHealthGain, attackGain, defenseGain);
	}
}
