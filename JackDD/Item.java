
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
	public void setName(String name) {
		this.name = name;
	}
	public void setStatChange(int health,int maxHealth,int attack,int defense){
		statChange[0]=health;
		statChange[1]=maxHealth;
		statChange[2]=attack;
		statChange[3]=defense;
	}
	public int getHealthChange(){
		return statChange[0];
	}
	public int getMaxHealthChange(){
		return statChange[1];
	}
	public int getAttackChange(){
		return statChange[2];
	}
	public int getDefenseChange(){
		return statChange[3];
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