package com.dd.items;

public class Armour extends Item{
	public Armour(String name,int defenseGain){
		super(name);
		super.setStatChange(0,0,0,defenseGain);
	}
	@Override
	public String toString(){
		return "("+getClass().toString().substring(6)+") "+getName()+" [+"+super.getStatChange().getDefense()+" Defense]";
	}
}