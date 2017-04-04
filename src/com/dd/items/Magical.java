package com.dd.items;

public class Magical extends Item {
	
	public Magical(String name, int healthGain, int maxHealthGain, int attackGain,int defenseGain) {
		super(name);
		super.setStatChange(healthGain, maxHealthGain, attackGain, defenseGain);
	}
	
	public Magical() {
		super();
	}
}