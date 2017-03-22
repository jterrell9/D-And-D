package com.dd.entities.monsters;

import com.dd.entities.Entity;
import com.dd.entities.Monster;
import com.dd.entities.Player;

import java.util.Random;

public class Dragon extends Monster{

    private String dragColor;
    private boolean breathAttack;

    //Constructor used when Dragon is created for specific rooms when rooms are generated
    public Dragon (String name, int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    	super(name,health,attack,defense);
        initDescription();
    }

    public void initDescription(){
        Random random = new Random();
        int color = random.nextInt(8) + 1;
        switch (color) {
            case 1: dragColor = "red";
                break;
            case 2: dragColor = "blue";
                break;
            case 3: dragColor = "black";
                break;
            case 4: dragColor = "green";
                break;
            case 5: dragColor = "white";
                break;
            case 6: dragColor = "bronze";
                break;
            case 7: dragColor = "brass";
                break;
            case 8: dragColor = "gold";
                break;
            case 9: dragColor = "silver";
                break;
        }
        String desc = "As you enter the large room, the first thing that catches your eye is jewels, mostly " + dragColor
                + ". As you walk around the abnormally large room, you hear a faint hum behind you. As you turn around, "
                + "a giant " + dragColor + " scaled dragon faces you, nostrils flared. \"Fool! You think you could steal"
                + "from " + getName() + " the " + dragColor + " dragon? For this, you shall die!\"";
        setDescription(desc);
    }

    // used when dragon health reaches 0
    // pre: if(getStats().getHealth() == 0)
    // pre: if(alive)
    // post: fight = false
    // post: alive = false
    public void takeDamage(int damage){
        //set health to 0 and other logic to ensure the battle is over
        stats.setHealth(stats.getHealth() - damage);
        if(!survives()){
            System.out.println("You just killed " + name + " the "+ dragColor +" dragon!");
        }
    }

    public void attack(Entity entity){
        Random random = new Random();
        if(breathAttack){
            entity.takeDamage(5);
            breathAttack = false;
        }else{
            if(random.nextInt(5) + 1 == 6){
                breathAttack = true;
            }
        }
        entity.takeDamage(stats.getAttack());
    }
}