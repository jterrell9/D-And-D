package com.dd.items;

public class OneHandedWeapon extends Weapon {
	
    public OneHandedWeapon(String name, int attackGain){
        super(name, attackGain);
    }
    
    public OneHandedWeapon(){
    	super();
    }
    
    @Override
    public String typeToString() {
		return "One Handed Weapon";
	}
}