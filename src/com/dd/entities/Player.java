package com.dd.entities;

import com.dd.Stats;
import com.dd.items.Item;
import com.dd.items.Potion;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.dd_util.ConflictHandlingMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player extends Entity{
	public enum Equip{
		LEFTHAND, RIGHTHAND, HANDS, SUIT, NONE
	}

	public MapPosition mapPosition;
	private Item suit;
	private Item leftHand;
	private Item rightHand;

	private Map<String, Item> inventory = new ConflictHandlingMap<Item>();
	private int inventoryUsed = 0;
	private int maxInventorySize;

	public Player(String name, MapPosition pos, Stats stats){
		super(name, stats);
		setMapPosition(pos);
	}

	public Player(String name, MapPosition pos, DungeonMap dungeonMap){
		super(name);
		setMapPosition(pos);
	}

	public Player(String name){
		super(name);
		setMapPosition(new MapPosition());
	}

	public void usePotion(Item item){
		if(item instanceof Potion)
			stats.changeStat(item.getStatChange());
		else
			System.out.println("ERROR item is not a potion");
	}

	public void usePotionFromInventory(String potionName) throws InventoryException {
		Item potion = inventory.get(potionName);
		if(potion == null)
			throw new InventoryException("There are no potion of \""
					+ potionName
					+ "\" in the inventory of player \""
					+ name
					+ "\". Item removal failed.");
		else if(!(potion instanceof Potion))
			throw new InventoryException("The item \""
											+ potionName
											+ "\" in the inventory of player \""
											+ "\" is not a potion. Item not used.");
		stats.changeStat(potion.getStatChange());
		inventory.remove(potionName);
	}

	public void addtoInventory(Item item) throws InventoryException {
		if(inventoryUsed == maxInventorySize)
			throw new InventoryException("The inventory of player \""
											+ name
											+ "\" is already full. Item not added to inventory.");

		inventory.put(item.getName(), item);
		++inventoryUsed;
	}

	public Item removefromInventory(String itemName) throws InventoryException {
		Item retItem = inventory.get(itemName);
		if(retItem == null)
			throw new InventoryException("There are no items of \""
											+ itemName
											+ "\" in the inventory of player \""
											+ name
											+ "\". Item removal failed.");
		inventory.remove(itemName);
		return retItem;
	}

	public void discardfromInventory(String itemName, int amount) throws InventoryException {
		if(inventory.remove(itemName) == null)
			throw new InventoryException("There are no items of \""
											+ itemName
											+ "\" in the inventory of player \""
											+ name
											+ "\". Item discard failed.");
	}

	public void equip(Item item, Equip bodyArea) throws EquipmentException {
		String errorTrailer = "";
		boolean hadError = false;
		switch(bodyArea){
			case LEFTHAND:
				if(leftHand != null){
					hadError = true;
					errorTrailer = "the left hand is full.";
				}
				else
					leftHand = item;
				break;
			case RIGHTHAND:
				if(rightHand != null){
					hadError = true;
					errorTrailer = "the right hand is full.";
				}
				else
					rightHand = item;
				break;
			case HANDS:
				if(leftHand != null || rightHand != null){
					hadError = true;
					errorTrailer = "one or both hands are full.";
				}
				else
					leftHand = rightHand = item;
				break;
			case SUIT:
				if(suit != null){
					hadError = true;
					errorTrailer = "armor is already being worn.";
				}
				else
					suit = item;
				break;
			default:
				hadError = true;
				errorTrailer = "no body area was specified.";
		}
		if(hadError)
			throw new EquipmentException("The item \""
											+ item.getName()
											+ "\" was not equipped to the player \""
											+ name
											+ "\" because "
											+ errorTrailer);
	}

	public Item removeEquipment(Equip bodyArea) throws EquipmentException {
		Item retItem = null;
		String errorTrailer = "";
		boolean hadError = false;

		switch(bodyArea){
			case LEFTHAND:
				if(leftHand == null){
					hadError = true;
					errorTrailer = "the left hand is empty.";
				}
				else {
					retItem = leftHand;
					leftHand = null;
				}
				break;
			case RIGHTHAND:
				if(rightHand == null){
					hadError = true;
					errorTrailer = "the right hand is empty.";
				}
				else {
					retItem = rightHand;
					rightHand = null;
				}
				break;
			case HANDS:
				if(leftHand == null
						|| rightHand == null
						|| leftHand != rightHand){
					hadError = true;
					errorTrailer = "both hands are not holding the same item.";
				}
				else {
					retItem = leftHand;
					leftHand = rightHand = null;
				}
				break;
			case SUIT:
				if(suit == null){
					hadError = true;
					errorTrailer = "no armor is being worn.";
				}
				else {
					retItem = suit;
					suit = null;
				}
				break;
			default:
				hadError = true;
				errorTrailer = "no body area was specified.";
		}
		if(hadError)
			throw new EquipmentException("The item at the requested body area could not be removed becasue "
											+ errorTrailer);
		return retItem;
	}

	public void discardEquipment(Equip bodyArea) throws EquipmentException {
		String errorTrailer = "";
		boolean hadError = false;

		switch(bodyArea){
			case LEFTHAND:
				if(leftHand == null){
					hadError = true;
					errorTrailer = "the left hand is empty.";
				}
				else
					leftHand = null;
				break;
			case RIGHTHAND:
				if(rightHand == null){
					hadError = true;
					errorTrailer = "the right hand is empty.";
				}
				else
					rightHand = null;
				break;
			case HANDS:
				if(leftHand == null
						|| rightHand == null
						|| leftHand != rightHand){
					hadError = true;
					errorTrailer = "both hands are not holding the same item.";
				}
				else
					leftHand = rightHand = null;
				break;
			case SUIT:
				if(suit == null){
					hadError = true;
					errorTrailer = "no armor is being worn.";
				}
				else
					suit = null;
				break;
			default:
				hadError = true;
				errorTrailer = "no body area was specified.";
		}
		if(hadError)
			throw new EquipmentException("The item at the requested body area could not be removed becasue "
											+ errorTrailer);
	}

	public List<Item> removeAllEquipment(){
		List<Item> itemList = new ArrayList<Item>();
		if(leftHand != null || rightHand != null){
			if(leftHand == rightHand)
				itemList.add(leftHand);
			else{
				if(leftHand != null)
					itemList.add(leftHand);
				if(rightHand != null)
					itemList.add(rightHand);
			}
		}
		if(suit != null)
			itemList.add(suit);

		leftHand = rightHand = suit = null;
		return itemList;
	}

	public void discardAllEquipment(){
		leftHand = rightHand = suit = null;
	}

	public MapPosition getPostion(){
		return mapPosition;
	}

	public void setMapPosition(MapPosition p){
		mapPosition = p;
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

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String statboardToString(){
		return	stats.toString()
				+"\n\n"+equipToString()
				+"\n"+inventoryToString();
				
	}

	public class InventoryException extends Exception{
		public InventoryException(String message){
			super(message);
		}
	}

	public class EquipmentException extends Exception{
		public EquipmentException(String message) {
			super(message);
		}
	}
}
