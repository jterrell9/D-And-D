package com.dd.items;

public class Shield extends Item {
	
	public Shield(String name, int defenseGain) {
		super(name);
		super.setStatChange(0, 0, 0, defenseGain);
	}
}
