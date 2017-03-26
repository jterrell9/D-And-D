package com.dd.items;

public class Suit extends Item {
	
	public Suit(String name, int defenseGain) {
		super(name);
		super.setStatChange(0, 0, 0, defenseGain);
	}
}