package com.dd.entities.monsters;

import com.dd.entities.Monster;
import com.dd.entities.Entity;

import java.util.Random;

public class Zombie extends Monster {
    private boolean conSave;
    private int deathCounter = 0;
    
    //Constructor used when Zombie is created for specific rooms when rooms are generated
    public Zombie (String name, int health, int attack, int defense) {
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
        initDescription();
    }

    public void initDescription() {
        String desc = "As you enter the room, immediately you notice the smell of rotting meat. The smell is almost "
                + "too much to bear,";
        Random random = new Random();
        if(random.nextInt(1) + 1 == 2) {
            conSave = true;
            desc += " but you pull through and find the smell.";
        }
        else {
            conSave = false;
            desc += " and it is.";
        }
        desc += "As you peer down the room, you notice a humanoid creature which you can assume is the"
            +" source of the rotting smell. You ready yourself for the impending attack.";
        setDescription(desc);
    }

    //used when Zombie attacks the player
    //pre: if(isFight && isAlive)
    //post: p1.getStats().getHealth() != 0
    public void attack(Entity entity) {
        // Zombie will deal damage and do a basic attack to the player
        Random random = new Random();
        if(!conSave) {
            entity.takeDamage(2);
            // "The stench of the zombie causes you to lower your defenses slightly."
            if(random.nextInt(3) + 1 == 4) {
                conSave = true;
                // "However you find the strength to power through it."
            }
        }
        // "The zombie lunges at you and slashes with its claws"
        // "The zombie lunges at you and sinks its teeth into your skin"
        entity.takeDamage(this.stats.getAttack());
    }

    // used when Zombie's health reaches 0
    // pre: if(getStats().getHealth() == 0)
    // pre: if(alive)
    // post: fight = false
    // post: alive = false
    public void die() {
        //25% chance the zombie comes back to life, increasing as the zombie dies more
        Random random = new Random();
        int deathCounterCopy = deathCounter + 1;
        while(deathCounterCopy != 0) {
            int roll = random.nextInt(3) + 1;
            if(roll == 4) {
                // "As you deal a fatal blow, the zombie's eyes breath undead life once again"
                this.stats.setHealth(3);
                deathCounter++;
                return;
            }
            deathCounterCopy--;
        }
        // WIZARD "As you cast your final spell, exhausted by the fight, you find the zombie moves no more. The battle is won."
        // FIGHTER "You raise your weapon and take to the zombie's head. As it rolls onto the ground, you breath a sigh
        // of relief. The battle is won."
    }

    public void examine() {
        //name + " a zombie who looks hungry. The smell is awful eminating from the creature.";
    }
}