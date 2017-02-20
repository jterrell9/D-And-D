
public class Item {
	private String name;
	private int[] statChange;
	public Item(String name) {
		this.name=name;
		this.statChange=new int[4];
	}
	public String getName() {
		return name;
	}
	public void setStatChange(int health,int maxHealth,int attack,int defense){
		statChange[0]=health;
		statChange[1]=maxHealth;
		statChange[2]=attack;
		statChange[3]=defense;
	}
	public int[] getStatChange(){
		return statChange;
	}
	public int[] getNegStatChange(){
		int[] negStatChange=new int[4];
		for(int i=0;i<negStatChange.length;i++){
			negStatChange[i]=-1*statChange[i];
		}
		return negStatChange;
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
}
class Shield extends Item{
	public Shield(String name,int defenseGain){
		super(name);
		super.setStatChange(0,0,0,defenseGain);
	}
}
class Armour extends Item{
	public Armour(String name,int defenseGain){
		super(name);
		super.setStatChange(0,0,0,defenseGain);
	}
}
class Potion extends Item{
	public Potion(String name,int healthGain){
		super(name);
		super.setStatChange(healthGain,0,0,0);
	}
}
class SpecialItem extends Item{
	public SpecialItem(String name,int healthGain,int maxHealthGain,int attackGain,int defenseGain){
		super(name);
		super.setStatChange(healthGain,maxHealthGain,attackGain,defenseGain);
	}
}