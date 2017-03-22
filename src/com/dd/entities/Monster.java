package com.dd.entities;

<<<<<<< HEAD
import com.dd.Stats;

public class Monster extends Entity {
=======
public class Monster extends Entity{
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String COLOR_BLACK = "\u001B[30m";
	public static final String COLOR_RED = "\u001B[31m";
	private String description;
>>>>>>> Added some descriptions and attack logic for Beholder
	
	public Monster(String name, int health, int attack, int defense) {
		super(name, health, health, attack, defense);
	}
<<<<<<< HEAD
=======
	@Override
	public String toString(){
		return name
				+"\nHealth:\t\t"+stats.getHealth()+"/"+stats.getMaxHealth()
				+"\nAttack/Defense:\t"+stats.getAttack()+"/"+stats.getDefense();
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
	
>>>>>>> Added some descriptions and attack logic for Beholder
}
