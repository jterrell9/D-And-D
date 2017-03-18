package com.dd.entities.monsters;

import com.dd.entities.Monster;
import com.dd.entities.Player;

public class Zombie extends Monster{
    
    //Constructor used when Zombie is created for specific rooms when rooms are generated
    public Zombie (String name, int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
    }

    //used when Zombie attacks the player
    //pre: if(isFight && isAlive)
    //post: p1.getStats().getHealth() != 0
    public void attack(Player p1){
        // Zombie will deal damage and do a basic attack to the player
    }

    //used when the Zombie takes damage
    //pre: if(alive && fight)
    //post: if(stats.getHealth() == 0) => die()
    public void takeDamage(int damage){
        //logic for taking damage
    }

    // used when Zombie's health reaches 0
    // pre: if(getStats().getHealth() == 0)
    // pre: if(alive)
    // post: fight = false
    // post: alive = false
    public void die(){
        //25% chance the zombie comes back to life, increasing as the zombie dies more
    }
}