package com.dd.items;

public class Weapon extends Item{
	private boolean isTwoHanded;
	public Weapon(String name,int attackGain,boolean isTwoHanded){
		super(name);
		super.setStatChange(0,0,attackGain,0);
		this.isTwoHanded=isTwoHanded;
	}
	public boolean isTwoHanded(){
		return isTwoHanded;
	}
	@Override
	public String toString(){
		if(isTwoHanded()){
			return "("+typeToString()+") "+getName()+" [+"+super.getStatChange().getAttack()+" Attack] (two-handed)";
		}
		return "("+typeToString()+") "+getName()+" [+"+super.getStatChange().getAttack()+" Attack]";
	}
}