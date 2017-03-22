package com.dd.entities;

import com.dd.Stats;

public class Entity{
	
	protected String name;
	protected Stats stats;
	protected boolean isAlive;
	
	public Entity(String name, int health, int maxHealth, int attack, int defense) {
		setName(name);
		setStats(new Stats(health, maxHealth, attack, defense));
		isAlive = true;
	}
	
	public Entity(String name, Stats stats){
		setName(name);
		setStats(stats);
		isAlive = true;
	}
	
	public Entity(String name){
		setName(name);
		setStats(new Stats());
		isAlive = true;
	}
	
	public Entity(){
		setName("player");
		setStats(new Stats());
		isAlive = true;
	}
	
	public boolean survives(){
		if(stats.getHealth() <= 0){
			die();
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
			System.out.println(getName() + "died!");
		}
	}
	
	public void attack(Entity entity){
		entity.takeDamage(stats.getAttack());
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Stats getStats(){
		return stats;
	}
	
	public void setStats(Stats stats){
		this.stats = stats;
	}
}
