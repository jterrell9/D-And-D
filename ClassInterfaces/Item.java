public class Item {
	//name field to name the item
	private String name;
	
	//stat modifier for adding stat gains when item is equipped or used, or dropped
	private Stats statModifier;
	
	//parent class initializes only a name
	public Item(String name){
		
	}
	
	//sets the stat modifier for each item
	//@post StatModifier=new Stats(health,maxHealth,attack,defense);
	public void setStatModifier(int health,int maxHealth,int attack,int defense){
		
	}
	
	//returns the stat modifier
	public Stats getStatModifier(){
		
	}
	
	//returns a version of the stat modifier that is negative
	//used to negatively modify the stats of a player when an item is dropped
	//@invariant the statModifier will not be changed
	public Stats getNegStatModifier(){
		
	}
	
	//returns the name field
	public String getName(){
		
	}
	
	//overrides the superclass's toString method
	public String toString(){
		
	}
	
//classes for items that equip to different parts of the player each are a subclass of item
//Weapon class can only equip to player's hands, and can only increase the attack
class Weapon extends Item{
	//boolean field used to distinguish whether the weapon requires two hands to equip
	private boolean isTwoHanded;
	
	//@pre attackGain > 0
	//@post super(name);
	//@post super.setStatChange(0,0,attackGain,0);
	public Weapon(String name,int attackGain,boolean isTwoHanded){
		
	}
	
	//returns boolean value for isTwoHanded
	public boolean isTwoHanded(){
		return isTwoHanded;
	}
}

//Shield class can only be equipped to player's hand, and can only increase defense
class Shield extends Item{
	//@pre defenseGain > 0
	//@post super(name);
	//@post super.setStatChange(0,0,0,defenseGain);
	public Shield(String name,int defenseGain){
		
	}
}

//Suit class can only be equipped to player's suit, and can only increase defense
class Suit extends Item{
	//@pre defenseGain > 0
	//@post super(name);
	//@post super.setStatChange(0,0,0,defenseGain);
	public Armour(String name,int defenseGain){
		
	}
}

//Potion class can only be held in player's inventory, or used.  It does not alter stats until used
class Potion extends Item{
	//@pre healthGain > 0
	//@post super(name);
	//@post super.setStatChange(healthGain,0,0,0);
	public Potion(String name,int healthGain){
		
	}
}

//Artifact class can only be held in the player's inventory, and it can increase any stat
class Artifact extends Item{
	//@pre healthGain >= 0
	//@pre maxHealthGain >= 0
	//@pre attackGain >= 0
	//@pre defenseGain >= 0
	//@post super(name);
	//@post super.setStatChange(healthGain,maxHealthGain,attackGain,defenseGain);
	public SpecialItem(String name,int healthGain,int maxHealthGain,int attackGain,int defenseGain){
		
	}
}
