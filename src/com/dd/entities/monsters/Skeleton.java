package com.dd.entities.monsters;

import com.dd.entities.Monster;
import com.dd.entities.Player;

import java.util.Random;

public class Skeleton extends Monster{

    //Constructor used when Skeleton is created for specific rooms when rooms are generated
    public Skeleton (String name, int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
        initDescription();
    }

    public void initDescription() {
        String desc = "As you enter the room, you don't notice anything from the start. A few moments pass and you "
                + "start to walk around. As you take your first steps into the room proper, you hear a clatter of bones."
                + " You realize in the corner next to you there is a humanoid skeleton walking towards you.";
        setDescription(desc);
    }

    // used when skeletons health reaches 0
    // pre: if(getStats().getHealth() == 0)
    // pre: if(alive)
    // post: fight = false
    // post: alive = false
    public void die(){
        //set health to 0 and other logic to ensure the battle is over
        Random random = new Random();
        if(random.nextInt(5) + 1 == 5
                || this.stats.getAttack() == 1
                || this.stats.getDefense() == 1){
            this.stats.setHealth(1);
            this.stats.setAttack(this.stats.getAttack() - 1);
            this.stats.setDefense(this.stats.getDefense() - 1);
        }
        else {
            super.die();
        }
    }
}