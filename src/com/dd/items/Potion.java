package com.dd.items;

public class Potion extends Item {
	
	public Potion(String name, int healthGain) {
		super(name);
		super.setStatChange(healthGain, 0, 0, 0);
	}
}
