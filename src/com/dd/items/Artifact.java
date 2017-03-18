package com.dd.items;

public class Artifact extends Item{
	public Artifact(String name,int healthGain,int maxHealthGain,int attackGain,int defenseGain){
		super(name);
		super.setStatChange(healthGain,maxHealthGain,attackGain,defenseGain);
	}
	public String getModStr(){
		StringBuilder modStr=new StringBuilder();
		if(super.getStatChange().getHealth()>0){
			modStr.append("[+"+super.getStatChange().getHealth()+" Health]");
		}
		if(super.getStatChange().getMaxHealth()>0){
			modStr.append("[+"+super.getStatChange().getMaxHealth()+" Max Health]");
		}
		if(super.getStatChange().getAttack()>0){
			modStr.append("[+"+super.getStatChange().getAttack()+" Attack]");
		}
		if(super.getStatChange().getDefense()>0){
			modStr.append("[+"+super.getStatChange().getDefense()+" Defense]");
		}
		
		return modStr.toString();
	}
	@Override
	public String toString(){
		return "("+typeToString()+") "+getName()+" "+getModStr();
	}
}
