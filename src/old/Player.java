package old;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Player {
	transient PrintStream output=new PrintStream(System.out);
	
	public enum EQUIP{
		LEFTHAND,RIGHTHAND,HANDS,SUIT
	}
	private String name;
	private Stats stat;
	private Point position;
	private Item suit;
	private Item leftHand;
	private Item rightHand;
	private ArrayList<Item> inventoryList;
	private boolean isAlive=true;
	private boolean isinFight;
	private Map map;
	public Player(String name,Point p,Stats s,Map map){
		setName(name);
		setPosition(p);
		setStat(s);
		this.map=map;
		inventoryList=new ArrayList<Item>();
		isAlive=true;
		isinFight=false;
	}
	public Player(){
		super();
	}
	public boolean isAlive(){
		return isAlive;
	}
	public void die() throws FileNotFoundException{
		isAlive=false;
		if(!isAlive()){
			output.println("You just died!"
					+ "\nTry Again!");
			Main.mainMenu();
		}
	}
	public void takeDamage(int damage) throws FileNotFoundException{
		stat.setHealth(stat.getHealth()-damage);
		if(stat.getHealth()<=0){
			die();
		}
	}
	public void attack(Monster monster){
		monster.takeDamage(stat.getAttack());
	}
	public void usePotion(Item item){	//make sure potion is in inventory
		if(item instanceof Potion){
			stat.changeStat(item.getStatChange());
		}else{
			System.out.println("ERROR item is not a potion");
		}
	}
	public void usePotionFromInv(Item item){	//make sure potion is in inventory
		if(item instanceof Potion){
			stat.changeStat(item.getStatChange());
			removefromInventory(item);
		}else{
			System.out.println("ERROR item is not a potion");
		}
	}
	public void addtoInventory(Item item){
		if(inventoryList.size()>=10){
			output.println("ERROR inventory is full");
		}
		inventoryList.add(item);
	}
	public void pickup(Item item){
		if(item instanceof Potion){
			addtoInventory(item);
			getRoom().removeItem(item);
		}
	}
	public void removefromInventory(Item item){
		if(inventoryList.contains(item)){
			inventoryList.remove(item);
		}else{
			output.println("ERROR item does not exist in inventory");
		}
	}
	public Item getInventoryItem(int index){
		if(index<0 || index>=inventoryList.size()){
			output.println("ERROR inventory item doesn't exist");
			return null;
		}else{
			if(inventoryList.get(index)==null){
				output.println("ERROR inventory item doesn't exist");
				return null;
			}else{
				return inventoryList.get(index);
			}	
		}
	}
	
	public Point move(Map.DIR direction,Map map) throws FileNotFoundException{		//move command
		switch(direction){
		case NORTH:										//move north
			if(map.getRoomDir(position,direction)!=null){
				position.translate(0,-1);
			}
			else{
				break;
			}
			System.out.println(getName()+" has moved North");
			return position;
		case SOUTH:										//move south
			if(map.getRoomDir(position,direction)!=null){
				position.translate(0,1);
			}else{
				break;
			}
			System.out.println(getName()+" has moved South");
			return position;
		case EAST:										//move east
			if(map.getRoomDir(position,direction)!=null){
				position.translate(1,0);
			}else{
				break;
			}
			System.out.println(getName()+" has moved East");
			return position;
		case WEST:										//move west
			if(map.getRoomDir(position,direction)!=null){
				position.translate(-1,0);
			}else{
				break;
			}
			System.out.println(getName()+" has moved West");
			return position;
		}
		System.out.println("ERROR no room at that location to move to");	//Invariant
		return position;
	}
	public void equip(Item item){
		if(item instanceof Weapon){
			Weapon weapon=(Weapon)item;
			if(!weapon.isTwoHanded()){	//if weapon is one handed
				if(leftHand==null){			//if hand1 is empty
					leftHand=item;
					System.out.println(item.getName()+" has been equipped to "+getName()+"'s left hand");
				}else if(rightHand==null){	//or if hand1 is full and hand2 is empty
					rightHand=item;
					System.out.println(item.getName()+" has been equipped to "+getName()+"'s right hand");
				}else{							//both hands are full
					System.out.println("ERROR your hands are full");
					return;
				}
			}else{		//if weapon is two handed
				if(leftHand==null && rightHand==null){	//if both hands empty
					leftHand=rightHand=item;
					System.out.println(item.getName()+" has been equipped to "+getName()+"'s hands");
				}else{							//if both hands are full
					System.out.println("ERROR your hands are full");
					return;
				}
			}
			getRoom().removeItem(item);
			stat.changeStat(item.getStatChange());	//update stats
		}
		if(item instanceof Shield){
			Shield shield=(Shield)item;
			if(leftHand==null){		//if hand1 is empty
				leftHand=shield;
				System.out.println(item.getName()+" has been equipped to "+getName()+"'s left hand");
			}else if(rightHand==null){	//if hand2 is empty
				rightHand=shield;
				System.out.println(item.getName()+" has been equipped to "+getName()+"'s right hand");
			}else{							//both hands are full
				System.out.println("ERROR your hands are full");
				return;
			}
			getRoom().removeItem(item);
			stat.changeStat(item.getStatChange());
		}
		if(item instanceof Armour){
			Armour armour=(Armour)item;
			if(suit==null){		//if there is no suit
				suit=armour;
				System.out.println(item.getName()+" has been equipped to "+getName()+"'s suit");
			}else{				//if there is already a suit
				System.out.println("ERROR you already have a suit");
				return;
			}
			getRoom().removeItem(item);
			stat.changeStat(item.getStatChange());
		}
		if(item instanceof Artifact){
			getRoom().removeItem(item);
			stat.changeStat(item.getStatChange());
			addtoInventory(item);
		}
	}
	
	public void drop(EQUIP E){
		switch(E){
			case LEFTHAND:
				if(leftHand!=null && rightHand!=null){
					if(leftHand.equals(rightHand)){
						drop(EQUIP.HANDS);
						return;
					}
				}
				if(leftHand!=null){
					getRoom().addItem(leftHand);
					stat.changeStat(leftHand.getNegStatChange());
					System.out.println(name+" has dropped "+leftHand.toString());
					leftHand=null;
				}else{
					System.out.println("ERROR this hand is already empty");
				}
				break;
			case RIGHTHAND:
				if(leftHand!=null && rightHand!=null){
					if(leftHand.equals(rightHand)){
						drop(EQUIP.HANDS);
						return;
					}
				}
				if(rightHand!=null){
					getRoom().addItem(rightHand);
					stat.changeStat(rightHand.getNegStatChange());
					System.out.println(name+" has dropped "+rightHand.toString());
					rightHand=null;
				}else{
					System.out.println("ERROR this hand is already empty");
				}
				break;
			case HANDS:
				if(leftHand!=null && rightHand!=null && leftHand.equals(rightHand)){
					getRoom().addItem(leftHand);
					stat.changeStat(rightHand.getNegStatChange());
					System.out.println(name+" has dropped "+leftHand.toString());
					leftHand=rightHand=null;
				}else{
					System.out.println("ERROR hands are already empty or contains different objects");
				}
				break;
			case SUIT:
				if(suit!=null){
					getRoom().addItem(suit);
					stat.changeStat(suit.getNegStatChange());
					System.out.println(name+" has dropped "+suit.toString());
					suit=null;
				}else{
					System.out.println("ERROR the dress is already empty");
				}
				break;
		}
	}
	public void drop(Item item){
		if(item instanceof Artifact){
			getRoom().addItem(item);
			stat.changeStat(item.getNegStatChange());
			removefromInventory(item);
		}
		if(item instanceof Potion){
			getRoom().addItem(item);
			removefromInventory(item);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Point getPostion(){
		return position;
	}
	public void setPosition(Point p){
		if(p.x<0 || p.y<0)
			System.out.println("ERROR setPosition out of bounds");
		position=p;
	}
	public Stats getStat(){
		return stat;
	}
	public void setStat(Stats s){
		this.stat=s;
	}
	public Room getRoom(){
		return map.getRoom(position);
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	
	public String equipToString(){
		StringBuilder lh=new StringBuilder();
		if(leftHand!=null)
			lh.append(leftHand.toString());
		else
			lh.append("empty");
		StringBuilder rh=new StringBuilder();
		if(rightHand!=null)
			rh.append(rightHand.toString());
		else
			rh.append("empty");
		StringBuilder s=new StringBuilder();
		if(suit!=null)
			s.append(suit.toString());
		else
			s.append("empty");
		return "Left Hand:\t"+lh.toString()+
				"\nRight Hand:\t"+rh.toString() +
				"\nSuit:\t\t"+s.toString();
	}
	public String inventoryToString(){
		StringBuilder sb=new StringBuilder("Inventory:");
		for(int i=0;i<inventoryList.size();i++){
			if(i==0){
				sb.append("\t"+(i+1)+". "+inventoryList.get(i).toString()+"\n");
			}else{
				sb.append("\t\t"+(i+1)+". "+inventoryList.get(i).toString()+"\n");
			}
		}	
		return sb.toString();
	}
	
	@Override
	public String toString(){
		return	stat.toString() +
				"\n\n"+equipToString()+
				"\n"+inventoryToString();
				
	}
	public boolean isinFight() {
		return isinFight;
	}
	public void fightTurn(Player player1,Monster enemy) throws FileNotFoundException {
		this.isinFight = true;
		while(player1.isAlive() && enemy.isAlive()){
			Main.command(player1);
			enemy.fightTurn(player1,enemy);
		}
		if(!player1.isAlive()){
			this.isinFight=false;
			die();
		}
		if(!enemy.isAlive()){
			player1.isinFight=false;
			enemy.die();
			getRoom().killMonster();
		}
	}
}
