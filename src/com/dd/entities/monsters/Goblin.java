package com.dd.entities.monsters;

import com.dd.entities.Monster;

public class Goblin extends Monster {

    //Constructor used when Goblin is created for specific rooms when rooms are generated
    public Goblin (String name, int health, int attack, int defense) {
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
        initDescription();
    }

    public void initDescription() {
        String desc = "You enter a small room where you see a small humanoid creature. You approach it and hear a growl, "
                + "\"You wanna mess with " + name + " the come at me!\"";
        setDescription(desc);
    }

    //Attack: The goblin swings with all its might a wooden club at you

    //Die: As you deal the final blow, the goblin's lifeless body on the ground, a sense of relief come upon you. The battle is won.

    public void examine() {
        // name + the goblin. He isn't very bright, but sure can hit hard
    }
}