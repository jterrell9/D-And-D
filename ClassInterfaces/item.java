package interfaces;

public class item {
	
	private String name;
	private Stats StatModifyer;
	
	//parent class initializes only a name
	public item(String name) {
		}
	
	//sets the stat modifyer for each item
	public void setStatModifyer(int health,int maxHealth,int attack,int defense){
	}
	//returns the stat modifyer
	public Stats getStatModifyer(){
	}
	//returns a version of the stat modifyer that is negative
	//used to negatively modify the stats of a player when an item is dropped
	public Stats getNegStatModifyer(){
	}
	
	//returns the name field
	public String getName() {
		}
	//returns the name
	public String toString(){
		
	}
}
//classes for items that equip to different parts of the player each are a subclass of item
//Weapon class can only equip to player's hands, and can only increase the attack
class Weapon extends item{
	//boolean field used to distinguish whether the weapon requires two hands to equip
	private boolean isTwoHanded;
	
	//@invatiant attackGain >0
	public Weapon(String name,int attackGain,boolean isTwoHanded){
		
	}
	
	//returns boolean value for isTwoHanded
	public boolean isTwoHanded(){
		
	}
}

//Shield class can only be equipped to player's hand, and can only increase defense
class Shield extends item{
	//@invariant defenseGain>0
	public Shield(String name,int defenseGain){
	}
}

//Suit class can only be equipped to player's suit, and can only increase defense
class Suit extends item{
	//@invariant defenseGain>0
	public Armour(String name,int defenseGain){
	}
}

//Potion class can only be held in player's inventory, or used.  It does not alter stats until used
class Potion extends item{
	//@invariant healthGain>0
	public Potion(String name,int healthGain){
	}
}

//Artifact class can only be held in the player's inventory, and it can increase any stat
class Artifact extends item{
	//@invariant healthGain>0
	//@invariant maxHealthGain>0
	//@invariant attackGain>0
	//@invariant defenseGain>0
	public SpecialItem(String name,int healthGain,int maxHealthGain,int attackGain,int defenseGain){
		
	}
}
