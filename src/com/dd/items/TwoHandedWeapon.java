package com.dd.items;

public class TwoHandedWeapon extends Weapon {
    public TwoHandedWeapon(String name, int attackGain){
        super(name, attackGain);
    }
    
    public TwoHandedWeapon() {
		super();
	}
    
    @Override
    public String typeToString() {
		return "Two Handed Weapon";
	}
}