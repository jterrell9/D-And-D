package com.dd;

import java.io.Serializable;

public class StatModifier extends Stats {
	
	public StatModifier(int health, int maxHealth, int attack, int defense) {
		super(health, maxHealth, attack, defense);
	}
	
	@Override
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	@Override
	public void setHealth(int health) {
		this.health = health;
	}
	
	@Override
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	@Override
	public void setDefense(int defense) {
		this.defense = defense;
	}
}
