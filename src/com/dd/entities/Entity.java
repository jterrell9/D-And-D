package com.dd.entities;

import com.dd.Stats;

public class Entity{
	
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String COLOR_BLACK = "\u001B[30m";
	public static final String COLOR_RED = "\u001B[31m";
	
	public String name;
	public Stats stats;
	public boolean isAlive = true;
	
	public Entity(String name, int health, int maxHealth, int attack, int defense) {
		this.name = name;
		stats = new Stats(health,maxHealth,attack,defense);
		isAlive = true;
	}
	public Entity(String name,Stats stats){
		this.name = name;
		this.stats = stats;
		isAlive = true;
	}
	public Entity(String name){
		this.name = name;
		this.stats = new Stats();
		isAlive = true;
	}
	public Entity(){
		this.name = "player";
		stats = new Stats();
		isAlive = true;
	}
	public boolean survives(){
		if(stats.getHealth() <= 0){
			stats.setHealth(0);
			isAlive = false;
			return false;
		}
		return true;
	}
	public void die(){
		stats.setHealth(0);
		isAlive = false;
	}
	public void takeDamage(int damage){
		stats.setHealth(stats.getHealth() - damage);
		if(!survives()){
			System.out.println("You just killed a " + name + "!");
		}
	}
	public void attack(Entity entity){
		entity.takeDamage(stats.getAttack());
	}
}
