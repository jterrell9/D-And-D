package com.dd.entities.monsters;

import com.dd.entities.Entity;
import com.dd.entities.Monster;
import com.dd.entities.Player;

import java.util.Random;

public class Beholder extends Monster {

    private boolean revieved = false;

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
    public void attack(Entity entity) {
        // Will use the Beholder's attackHelper method to help decide which attack it is using
        Random random = new Random();
        int attackPatern = random.nextInt(9) + 1;
        // Determines which attack the beholder does
        // TODO: Description for attack and death
        switch(attackPatern){
            case 1: case 3:case 5:case 7: case 9:
                entity.takeDamage(2 + attackPatern);
                break;
            case 2:case 4:case 6:case 8:case 10:
                entity.takeDamage(4 + attackPatern/2);
                break;
        }
        //Critical hit by the Beholder
        if(attackPatern == 10) {
            entity.takeDamage(10);
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
        // Being beings of supreme intelect, the beholder can
        if (diePercentage == 20 && !revieved) {
            this.stats.addHealth(4);
            revieved = true;
        }
        else {
            super.die();
        }
    }
}
