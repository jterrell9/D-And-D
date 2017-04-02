package com.dd.items;

public class TwoHandedWeapon extends Physical {
    public TwoHandedWeapon(String name, int attackGain){
        super(name, attackGain, 0);
    }
    
    public TwoHandedWeapon() {
		super();
	}
    
    @Override
    public String typeToString() {
		return "Two Handed Weapon";
	}
}