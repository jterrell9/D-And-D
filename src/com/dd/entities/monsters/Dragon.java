package com.dd.entities.monsters;

import com.dd.entities.Monster;
import com.dd.entities.Player;

public class Dragon extends Monster{

    //Constructor used when Dragon is created for specific rooms when rooms are generated
    public Dragon (String name, int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
    }

    //used when dragon attacks the player
    //pre: if(isFight && isAlive)
    //post: p1.getStats().getHealth() != 0
    public void attack(Player p1){
        //decides on which attack to do, either breath attack or claws, and deal damage
        //to player
    }

    //used when the dragon takes damage
    //pre: if(alive && fight)
    //post: if(stats.getHealth() == 0) => die()
    public void takeDamage(int damage){
        //logic for taking damage
    }

    // used when dragon health reaches 0
    // pre: if(getStats().getHealth() == 0)
    // pre: if(alive)
    // post: fight = false
    // post: alive = false
    public void die(){
        //set health to 0 and other logic to ensure the battle is over
    }
}