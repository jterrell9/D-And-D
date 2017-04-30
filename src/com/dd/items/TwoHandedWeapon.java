package com.dd.items;

public class TwoHandedWeapon extends Physical {
    public TwoHandedWeapon(String name, int attackGain){
        super(name, attackGain, 0);
    }
    
    @Override
    public String getType() {
		return "Two Handed Weapon";
	}
}