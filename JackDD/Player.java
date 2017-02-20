import java.awt.Point;

public class Player {
	public enum EQUIP{
		LEFTHAND,RIGHTHAND,HANDS,SUIT
	}
	private String name;
	private Stats stat;
	private Point position;
	private Item suit;
	private Item leftHand;
	private Item rightHand;
	private Item[] inventory;
	public Player(String name,Point p,Stats s){
		setName(name);
		setPosition(p);
		setStat(s);
		inventory=new Item[10];
	}
	
	public Point move(Map.DIR direction,Map map){		//move command
		switch(direction){
		case NORTH:										//move north
			if(map.getNorth(position)!=null)
				position.translate(0,-1);
			else
				break;
			System.out.println(getName()+" has moved North");
			return position;
		case SOUTH:										//move south
			if(map.getSouth(position)!=null)
				position.translate(0,1);
			else
				break;
			System.out.println(getName()+" has moved South");
			return position;
		case EAST:										//move east
			if(map.getEast(position)!=null)
				position.translate(1,0);
			else
				break;
			System.out.println(getName()+" has moved East");
			return position;
		case WEST:										//move west
			if(map.getWest(position)!=null)
				position.translate(-1,0);
			else
				break;
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
			stat.changeStat(item.getStatChange());
		}
	}
	
	public void drop(EQUIP E){
		switch(E){
			case LEFTHAND:
				if(leftHand!=null){
					stat.changeStat(leftHand.getNegStatChange());
					System.out.println(name+" has dropped "+leftHand.toString());
					leftHand=null;
				}else{
					System.out.println("ERROR this hand is already empty");
				}
				break;
			case RIGHTHAND:
				if(rightHand!=null){
					stat.changeStat(rightHand.getNegStatChange());
					System.out.println(name+" has dropped "+rightHand.toString());
					rightHand=null;
				}else{
					System.out.println("ERROR this hand is already empty");
				}
				break;
			case HANDS:
				if(leftHand.equals(rightHand)){
					stat.changeStat(rightHand.getNegStatChange());
					System.out.println(name+" has dropped "+leftHand.toString());
					leftHand=rightHand=null;
				}else{
					System.out.println("ERROR hands are already empty or contains different objects");
				}
				break;
			case SUIT:
				if(suit!=null){
					stat.changeStat(suit.getNegStatChange());
					System.out.println(name+" has dropped "+suit.toString());
					suit=null;
				}else{
					System.out.println("ERROR the dress is already empty");
				}
				break;
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
	public Item[] getInventory(){
		return inventory;
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
	@Override
	public String toString(){
		return name+"-->" +
				stat.toString() +
				" Position ("+position.x+","+position.y+")"+
				"\n"+equipToString();
				
	}
}
