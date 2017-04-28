package com.dd.entities;

import java.util.HashMap;

import com.dd.Stats;
import com.dd.dataTypes.bodyAreas.*;
import com.dd.dataTypes.enums.*;
import com.dd.dd_util.ConflictHandlingMap;
import com.dd.items.*;
import com.dd.levels.MapPosition;
import com.sun.javafx.collections.MappingChange.Map;
import com.dd.exceptions.*;

public abstract class Player extends Entity {

	protected MapPosition mapPosition;

	protected Hand leftHand;
	protected Hand rightHand;
	protected TwoHands twoHands;
	protected SuitArea suitArea;
	protected Inventory inventory;
	protected boolean dropSuccess;

	public Player(String name, MapPosition pos, Stats stats) {
		super(name, stats);
		setMapPosition(pos);
		initBody();
	}

	public Player(String name) {
		super(name);
		setMapPosition(new MapPosition());
		initBody();
	}
	
	public Player() {
		super();
		setMapPosition(new MapPosition());
		initBody();
	}
	
	public void initBody() {
		this.leftHand = new Hand();
		this.rightHand  = new Hand();
		this.twoHands = new TwoHands();
		this.suitArea = new SuitArea();
		this.inventory = new Inventory(10);
	}
	
	public abstract void pickup(Item item) throws EquipmentException;
	
	public void drop(Equip bodyArea) throws EquipmentException {
		resetDropSuccess();
		Item dropItem = null;
		String errorTrailer = "";
		switch(bodyArea) {
			case LEFTHAND:
				if(!leftHand.isEmpty()) {
					dropItem = leftHand.get();
					leftHand.drop();
					dropSuccess = true;
				}
				else {
					errorTrailer = "the left hand is empty. ";
				}
				break;
			case RIGHTHAND:
				if(!rightHand.isEmpty()) {
					dropItem = rightHand.get();
					rightHand.drop();
					dropSuccess = true;
				}
				else {
					errorTrailer = "the right hand is empty. ";
				}
				break;
			case HANDS:
				if(!twoHands.isEmpty()) {
					dropItem = twoHands.get();
					rightHand.drop();
					dropSuccess = true;
				}
				else {
					errorTrailer = "You are not holding a two handed weapon. ";
				}
				break;
			case SUIT:
				if(!suitArea.isEmpty()) {
					dropItem = suitArea.get();
					suitArea.drop();
					dropSuccess = true;
				}
				else {
					errorTrailer = "no suit is being worn. ";
				}
				break;
			default:
				dropSuccess = false;
				errorTrailer = "no body area was specified. ";
		}
		if(!isDropSuccess()) {
			throw new EquipmentException("The item could not be dropped because "
											+ errorTrailer);
		}
		else {
			changeStats(dropItem.getNegStatChange());
		}
	}
	
	public void usePotion(Potion potion) throws EquipmentException {
		changeStats(potion.getStatChange());
	}

	public void usePotionFromInventory(Potion potion) throws EquipmentException {
		try {
			inventory.get(potion);
			inventory.remove(potion);
			usePotion(potion);
		} 
		catch (InventoryException IE) {
			throw new EquipmentException(IE.getMessage());
		}
		
	}
	
	public void addtoInventory(Item item) throws InventoryException {
		this.inventory.add(item);
	}

	public void removeFromInventory(Item item) throws InventoryException {
		resetDropSuccess();
		if(!inventory.getInventoryMap().containsValue(item)) {
			throw new InventoryException(item.titleToString() + " is not in your inventory. ");
		}
		this.inventory.remove(item);
		dropSuccess = true;
	}
	
	public void removeFromInventory(String itemName) throws InventoryException {
		resetDropSuccess();
		if(!inventory.getInventoryMap().containsKey(itemName)) {
			throw new InventoryException(itemName + " is not in your inventory. ");
		}
		this.inventory.remove(itemName);
		dropSuccess = true;
	}
	
	public MapPosition getPostion() {
		return mapPosition;
	}

	public void setMapPosition(MapPosition p) {
		mapPosition = p;
	}

	public String getName() {
		return name;
	}
	
	public Item getLeftHand() {
		return leftHand.get();
	}

	public Item getRightHand() {
		return rightHand.get();
	}

	public TwoHandedWeapon get() {
		return twoHands.get();
	}

	public Suit getSuitArea() {
		return suitArea.get();
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public boolean isDropSuccess() {
		return dropSuccess;
	}
	
	public void resetDropSuccess() {
		dropSuccess = false;
	}
	
	public String equipToString() {
		StringBuilder lh = new StringBuilder();
		if(!leftHand.isEmpty())
			lh.append(leftHand.get().getName() + " " + leftHand.get().examineToString());
		else
			lh.append("empty");
		StringBuilder rh = new StringBuilder();
		if(!rightHand.isEmpty())
			rh.append(rightHand.get().getName() + " " + rightHand.get().examineToString());
		else
			rh.append("empty");
		StringBuilder s = new StringBuilder();
		if(!suitArea.isEmpty())
			s.append(suitArea.get().getName() + " " + suitArea.get().examineToString());
		else
			s.append("empty");
		return "Left Hand:  " + lh.toString() + "\n"
				+ "Right Hand: " + rh.toString() + "\n"
				+ "Suit:       " + s.toString();
	}

	public String inventoryToString() {
		StringBuilder sb = new StringBuilder("Inventory:\n");
		int i = 0;
		for(Item item : inventory.getInventoryMap().values()){
			sb.append("\t" + ++i + " " + item.getName() + " " + item.examineToString() + "\n");
		}
		return sb.toString();
	}
	
	public String statboardToString() {
		return	stats.toString()
				+ "\n" + equipToString()
				+ "\n" + inventoryToString();
				
	}
}
