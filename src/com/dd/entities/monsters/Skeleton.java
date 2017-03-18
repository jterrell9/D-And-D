package com.dd.entities.monsters;

import com.dd.entities.Monster;
import com.dd.entities.Player;

public class Skeleton extends Monster{

    //Constructor used when Skeleton is created for specific rooms when rooms are generated
    public Skeleton (String name, int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
    }

    //used when skeleton attacks the player
    //pre: if(isFight && isAlive)
    //post: p1.getStats().getHealth() != 0
    public void attack(Player p1){
        // deal damage to player on basic attacks
    }

    //used when the skeleton takes damage
    //pre: if(alive && fight)
    //post: if(stats.getHealth() == 0) => die()
    public void takeDamage(int damage){
        //logic for taking damage
    }

    // used when skeletons health reaches 0
    // pre: if(getStats().getHealth() == 0)
    // pre: if(alive)
    // post: fight = false
    // post: alive = false
    public void die(){
        //set health to 0 and other logic to ensure the battle is over
    }
}