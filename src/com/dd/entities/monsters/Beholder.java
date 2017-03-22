package com.dd.entities.monsters;

import com.dd.entities.Monster;
import com.dd.entities.Player;

import java.util.Random;

public class Beholder extends Monster {
    //Starting health should be between 15 and 20
    //Constructor used when Beholder is created for specific rooms when rooms are generated
    public Beholder (String name, int health, int attack, int defense) {
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
        initDescription();
    }

    public void initDescription() {
        String desc = "You enter a room and get a strange feeling in the air. You turn around and see a creature" +
        " with multiple eyes. \"Hahaha!\" it laughs, fear tingling through your spine. \"I am " + this.getName() +
                " feared amongst many, known by few. Those who know me know to fear me. Those who don't, soon will\"";

        this.setDescription(desc);
    }

    //used when Beholder attacks the player
    //pre: if(isInFight && isAlive)
    //post: player.takeDamage(attack)
    public void attack(Player player) {
        // Will use the Beholder's attackHelper method to help decide which attack it is using
        Random random = new Random();
        int attackPatern = random.nextInt(9) + 1;
        switch(attackPatern){
            case 1, 3, 5, 7, 9:
                player.takeDamage(2 + attackPatern);
                break;
            case 2, 4, 6, 8, 10:
                player.takeDamage(4 + attackPatern/2);
                break;
        }
        if(attackPatern == 10) {
            player.takeDamage(10);
        }
    }

    // used when Beholder's health reaches 0
    // pre: if(getStats().getHealth() <= 0)
    // pre: if(isAlive)
    // post: isInFight = false
    // post: isAlive = false
    public void die(){
        Random random = new Random();
        int diePercentage = random.nextInt(19) + 1;
        if (diePercentage == 20) {
            this.stats.addHealth(4);
        }
        else {
            super.die();
        }
    }
}
