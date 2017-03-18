package old;

public class Item {
	private String name;
	private Stats StatModifyer;
	public Item(String name) {
		this.name=name;
		StatModifyer=new Stats(0,0,0,0);
	}
	public String getName() {
		return name;
	}
	
	public void setStatChange(int health,int maxHealth,int attack,int defense){
		StatModifyer=new Stats(health,maxHealth,attack,defense);
	}
	public Stats getStatChange(){
		return StatModifyer;
	}
	public Stats getNegStatChange(){
		int negHealth=-1*StatModifyer.getHealth();
		int negMaxHealth=-1*StatModifyer.getMaxHealth();
		int negAttack=-1*StatModifyer.getAttack();
		int negDefense=-1*StatModifyer.getDefense();
		Stats negStats=new Stats(negHealth,negMaxHealth,negAttack,negDefense);
		return negStats;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
class Weapon extends Item{
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
			return "("+getClass().toString().substring(6)+") "+getName()+" [+"+super.getStatChange().getAttack()+" Attack] (two-handed)";
		}
		return "("+getClass().toString().substring(6)+") "+getName()+" [+"+super.getStatChange().getAttack()+" Attack]";
	}
}
class Shield extends Item{
	public Shield(String name,int defenseGain){
		super(name);
		super.setStatChange(0,0,0,defenseGain);
	}
	@Override
	public String toString(){
		return "("+getClass().toString().substring(6)+") "+getName()+" [+"+super.getStatChange().getDefense()+" Defense]";
	}
}
class Armour extends Item{
	public Armour(String name,int defenseGain){
		super(name);
		super.setStatChange(0,0,0,defenseGain);
	}
	@Override
	public String toString(){
		return "("+getClass().toString().substring(6)+") "+getName()+" [+"+super.getStatChange().getDefense()+" Defense]";
	}
}
class Potion extends Item{
	public Potion(String name,int healthGain){
		super(name);
		super.setStatChange(healthGain,0,0,0);
	}
	@Override
	public String toString(){
		return "("+getClass().toString().substring(6)+") "+getName()+" [+"+super.getStatChange().getHealth()+" Health]";
	}
	
}
class Artifact extends Item{
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
		return "("+getClass().toString().substring(6)+") "+getName()+" "+getModStr();
	}
}