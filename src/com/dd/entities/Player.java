package com.dd.entities;

import java.util.ArrayList;

import com.dd.DIR;
import com.dd.GameRunner;
import com.dd.Stats;
import com.dd.items.Armour;
import com.dd.items.Artifact;
import com.dd.items.Item;
import com.dd.items.Potion;
import com.dd.items.Shield;
import com.dd.items.Weapon;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public class Player extends Entity{

	public enum EQUIP{
		LEFTHAND,RIGHTHAND,HANDS,SUIT
	}
	public MapPosition mapPosition;
	private Item suit;
	private Item leftHand;
	private Item rightHand;
	private ArrayList<Item> inventoryList=new ArrayList<Item>();
	public Player(String name,MapPosition pos,Stats stats){
		super(name,stats);
		setMapPosition(pos);
	}
	public Player(String name,MapPosition pos,DungeonMap dungeonMap){
		super(name);
		this.mapPosition=pos;
	}
	public Player(String name){
		super(name);
		this.mapPosition=new MapPosition();
	}
	public void usePotion(Item item){
		if(item instanceof Potion){
			stats.changeStat(item.getStatChange());
		}else{
			System.out.println("ERROR item is not a potion");
		}
	}
	public void usePotionFromInv(Item item){	//make sure potion is in inventory
		if(item instanceof Potion){
			stats.changeStat(item.getStatChange());
			removefromInventory(item);
		}else{
			System.out.println("ERROR item is not a potion");
		}
	}
	public void addtoInventory(Item item){
		if(inventoryList.size()>=10){
			System.out.println("ERROR inventory is full");
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
			System.out.println("ERROR item does not exist in inventory");
		}
	}
	public Item getInventoryItem(int index){
		if(index<0 || index>=inventoryList.size()){
			System.out.println("ERROR inventory item doesn't exist");
			return null;
		}else{
			if(inventoryList.get(index)==null){
				System.out.println("ERROR inventory item doesn't exist");
				return null;
			}else{
				return inventoryList.get(index);
			}	
		}
	}
	
	public MapPosition move(DIR direction){		//move command
		DungeonMap map=GameRunner.game.dungeon;
		boolean roomExists=(map.getRoom(mapPosition.getPosition(direction))!=null);
		
		switch(direction){
		case NORTH:										//move north
			if(roomExists){
				mapPosition.translate(DIR.NORTH);
				System.out.println(name+" has moved North");
				return mapPosition;
			}else{
				break;
			}
		case SOUTH:										//move south
			if(roomExists){
				mapPosition.translate(DIR.SOUTH);
				System.out.println(name+" has moved South");
				return mapPosition;
			}else{
				break;
			}
		case EAST:										//move east
			if(roomExists){
				mapPosition.translate(DIR.EAST);
				System.out.println(name+" has moved East");
				return mapPosition;
			}else{
				break;
			}
		case WEST:										//move west
			if(roomExists){
				mapPosition.translate(DIR.WEST);
				System.out.println(name+" has moved West");
				return mapPosition;
			}else{
				break;
			}
		default:
			break;
		}
		System.out.println(COLOR_RED+"e.Player.move():no room at that location to move to"+COLOR_RESET);	//Invariant
		return mapPosition;
	}
	public void equip(Item item){
		if(item instanceof Weapon){
			Weapon weapon=(Weapon)item;
			if(!weapon.isTwoHanded()){	//if weapon is one handed
				if(leftHand==null){			//if hand1 is empty
					leftHand=item;
					System.out.println(item.getName()+" has been equipped to "+name+"'s left hand");
				}else if(rightHand==null){	//or if hand1 is full and hand2 is empty
					rightHand=item;
					System.out.println(item.getName()+" has been equipped to "+name+"'s right hand");
				}else{							//both hands are full
					System.out.println("ERROR your hands are full");
					return;
				}
			}else{		//if weapon is two handed
				if(leftHand==null && rightHand==null){	//if both hands empty
					leftHand=rightHand=item;
					System.out.println(item.getName()+" has been equipped to "+name+"'s hands");
				}else{							//if both hands are full
					System.out.println("ERROR your hands are full");
					return;
				}
			}
			getRoom().removeItem(item);
			stats.changeStat(item.getStatChange());	//update stats
		}
		if(item instanceof Shield){
			Shield shield=(Shield)item;
			if(leftHand==null){		//if hand1 is empty
				leftHand=shield;
				System.out.println(item.getName()+" has been equipped to "+name+"'s left hand");
			}else if(rightHand==null){	//if hand2 is empty
				rightHand=shield;
				System.out.println(item.getName()+" has been equipped to "+name+"'s right hand");
			}else{							//both hands are full
				System.out.println("ERROR your hands are full");
				return;
			}
			getRoom().removeItem(item);
			stats.changeStat(item.getStatChange());
		}
		if(item instanceof Armour){
			Armour armour=(Armour)item;
			if(suit==null){		//if there is no suit
				suit=armour;
				System.out.println(item.getName()+" has been equipped to "+name+"'s suit");
			}else{				//if there is already a suit
				System.out.println("ERROR you already have a suit");
				return;
			}
			getRoom().removeItem(item);
			stats.changeStat(item.getStatChange());
		}
		if(item instanceof Artifact){
			getRoom().removeItem(item);
			stats.changeStat(item.getStatChange());
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
					stats.changeStat(leftHand.getNegStatChange());
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
					stats.changeStat(rightHand.getNegStatChange());
					System.out.println(name+" has dropped "+rightHand.toString());
					rightHand=null;
				}else{
					System.out.println("ERROR this hand is already empty");
				}
				break;
			case HANDS:
				if(leftHand!=null && rightHand!=null && leftHand.equals(rightHand)){
					getRoom().addItem(leftHand);
					stats.changeStat(rightHand.getNegStatChange());
					System.out.println(name+" has dropped "+leftHand.toString());
					leftHand=rightHand=null;
				}else{
					System.out.println("ERROR hands are already empty or contains different objects");
				}
				break;
			case SUIT:
				if(suit!=null){
					getRoom().addItem(suit);
					stats.changeStat(suit.getNegStatChange());
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
			stats.changeStat(item.getNegStatChange());
			removefromInventory(item);
		}
		if(item instanceof Potion){
			getRoom().addItem(item);
			removefromInventory(item);
		}
	}
	public MapPosition getPostion(){
		return mapPosition;
	}
	public void setMapPosition(MapPosition p){
		if(p.x<0 || p.y<0)
			System.out.println("ERROR setMapPosition out of bounds");
		mapPosition=p;
	}
	public Room getRoom(){
		return GameRunner.game.dungeon.getRoom(mapPosition);
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
		return "\tLeft Hand:\t"+lh.toString()+
				"\n\tRight Hand:\t"+rh.toString() +
				"\n\tSuit:\t\t"+s.toString();
	}
	public String inventoryToString(){
		StringBuilder sb=new StringBuilder("\tInventory:");
		for(int i=0;i<inventoryList.size();i++){
			if(i==0){
				sb.append("\t"+(i+1)+". "+inventoryList.get(i).toString()+"\n");
			}else{
				sb.append("\t\t\t"+(i+1)+". "+inventoryList.get(i).toString()+"\n");
			}
		}	
		return sb.toString();
	}
	
	@Override
	public String toString(){
		return name;
	}
	public String statboardToString(){
		return	stats.toString()
				+"\n\n"+equipToString()
				+"\n"+inventoryToString();
				
	}
}
