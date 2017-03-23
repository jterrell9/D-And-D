package com.dd.entities.monsters;

import com.dd.entities.Monster;
import com.dd.entities.Entity;

public class Goblin extends Monster{

    //Constructor used when Goblin is created for specific rooms when rooms are generated
    public Goblin (String name, int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
        initDescription();
    }

    public void initDescription(){
        String desc = "You enter a small room where you see a small humanoid creature. You approach it and hear a growl, "
                + "\"You wanna mess with " + name + " the come at me!\"";
        setDescription(desc);
    }
}