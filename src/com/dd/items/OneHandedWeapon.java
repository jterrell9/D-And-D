package com.dd.items;

public class OneHandedWeapon extends Physical {
	
    public OneHandedWeapon(String name, int attackGain){
        super(name, attackGain, 0);
    }
    
    public OneHandedWeapon(){
    	super();
    }
    
    @Override
    public String typeToString() {
		return "One Handed Weapon";
	}
}