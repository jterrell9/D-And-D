package com.dd.entities;

public abstract class Monster extends Entity {

	public Monster(String name, int health, int attack, int defense) {
		super(name, health, health, attack, defense);
	}
	
	public String confrontText() {
        return "You encounter " + getTitle() + ". ";
    }
	
	public String examineText() {
        return getTitle();
    }
	
	@Override
	public String getType() {
		return "Monster";
	}
	
	@Override
	public String getTitle() {
		return "\"" + getName() + "\" the " + getType();
	}

	public enum MonsterType{
		BEHOLDER,
		DRAGON,
		GOBLIN,
		SKELETON,
		ZOMBIE
	}
}
