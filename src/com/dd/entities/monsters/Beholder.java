package com.dd.entities.monsters;

import com.dd.entities.Monster;
import com.dd.entities.Player;

public class Beholder extends Monster{

    //Constructor used when Beholder is created for specific rooms when rooms are generated
    public Beholder (String name, int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
    }

    //used when Beholder attacks the player
    //pre: if(isInFight && isAlive)
    //post: player.takeDamage(attack)
    public void attack(Player player){
        // Will use the Beholder's attackHelper method to help decide which attack it is using
    }

    //used only when attack method calls it
    public void attackHelper(Player player){
        // Logic to determine 1 of 10 different ray attack and then help with damage dealt
        // After determining that attack, will deal one more eye beam from main eye
    }

    //used when the Beholder takes damage
    //pre: if(isAlive && isInFight)
    //post: health-=damage
    //post: if(stats.getHealth() <= 0) => die()
    public void takeDamage(int damage){
        //logic for taking damage
    }

    // used when Beholder's health reaches 0
    // pre: if(getStats().getHealth() <= 0)
    // pre: if(isAlive)
    // post: isInFight = false
    // post: isAlive = false
    public void die(){
        //set health to 0 and other logic to ensure the battle is over
    }
}
