package com.dd.items;

public class Potion extends Item{
	public Potion(String name,int healthGain){
		super(name);
		super.setStatChange(healthGain,0,0,0);
	}
	@Override
	public String toString(){
		return "("+getClass().toString().substring(6)+") "+getName()+" [+"+super.getStatChange().getHealth()+" Health]";
	}
	
}
