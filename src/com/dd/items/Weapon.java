package com.dd.items;

public abstract class Weapon extends Item {
	
	public Weapon(String name, int attackGain) {
		super(name);
		super.setStatChange(0, 0, attackGain, 0);
	}
	
	public Weapon() {
		super();
	}
}