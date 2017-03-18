package com.dd.entities;

public class Monster extends Entity{
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String COLOR_BLACK = "\u001B[30m";
	public static final String COLOR_RED = "\u001B[31m";
	
	public Monster(String name,int health,int attack,int defense) {
		super(name,health,health,attack,defense);
	}
	public int attackDamage(){
		return stats.getAttack();
	}
	public void examine(){
		System.out.println(name
				+"\nHealth:\t\t"+stats.getHealth()
				+"\nAttack/Defense:\t"+stats.getAttack()+"/"+stats.getDefense());
	}
	@Override
	public String toString(){
		return name
				+"\nHealth:\t\t"+stats.getHealth()+"/"+stats.getMaxHealth()
				+"\nAttack/Defense:\t"+stats.getAttack()+"/"+stats.getDefense();
	}
	
}
