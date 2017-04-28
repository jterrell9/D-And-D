package com.dd.levels;

import com.dd.entities.Monster;
import com.dd.exceptions.NullItemException;
import com.dd.exceptions.NullMonsterException;
import com.dd.items.*;
import com.dd.dd_util.ConflictHandlingMap;

import java.io.Serializable;
import java.util.Map;

public class Room implements Serializable {
	
	private Map<String, Item> itemMap;
	private Map<String, Monster> monsterMap;
	
	public Room() {
		itemMap = new ConflictHandlingMap<Item>();
		monsterMap = new ConflictHandlingMap<Monster>();
	}
	
	public void addItem(Item item) {
		itemMap.put(item.getName(), item);
	}
	
	public Item removeItem(String itemName) throws NullItemException {
		if(!itemMap.containsKey(itemName)){
			throw new NullItemException(itemName + " not found in room. ");
		}
		return itemMap.remove(itemName);
	}
	
	public Item removeItem(Item item) throws NullItemException {
		if(!itemMap.containsValue(item)){
			throw new NullItemException("item not found in room. ");
		}
		return itemMap.remove(item.getName());
	}

	public void addMonster(Monster monster) {
		monsterMap.put(monster.getName(),monster);
	}

	public Monster removeMonster(String monsterName) throws NullMonsterException {
		if(!monsterMap.containsKey(monsterName)){
			throw new NullMonsterException("The monster \""
												+ monsterName
												+ "\" does not exist in this room. Removal failed. ");
		}
		return monsterMap.remove(monsterName);
	}
	
	public Monster removeMonster(Monster monster) throws NullMonsterException {
		if(!monsterMap.containsValue(monster)){
			throw new NullMonsterException(monster.getTitle()
												+ " does not exist in this room. Removal failed. ");
		}
		return monsterMap.remove(monster.getName());
	}
	

	public Map<String, Item> getItemMap() {
		return this.itemMap;
	}

	public Map<String, Monster> getMonsterMap() {
		return this.monsterMap;
	}
	
	public Monster getMonster() throws NullMonsterException {
		if(!hasMonster()) {
			throw new NullMonsterException("This room has no monsters. ");
		}
		Monster outputMonster = null;
		for(Monster monster : this.monsterMap.values()) {
			outputMonster = monster;
		}
		return outputMonster;
	}
	
	public Monster getMonster(String name) throws NullMonsterException {
		if(!monsterMap.containsKey(name)){
			throw new NullMonsterException(name + " is not in this room. ");
		}
		return monsterMap.get(name);
	}
	
	public Item getItem(String name) throws NullItemException {
		if(!itemMap.containsKey(name)) {
			throw new NullItemException(name + " is not found in this room. ");
		}
		return itemMap.get(name);
	}
	
	public Item getItem(Item item) throws NullItemException {
		if(!itemMap.containsValue(item)) {
			throw new NullItemException(item.titleToString() + " is not found in this room. ");
		}
		return itemMap.get(item.getName());
	}
	
	public Potion hasPotion(String potionName) {
		try {
			return (Potion) getItem(potionName);
		}
		catch(NullItemException NIE) {
			return null;
		}
		
	}
	
	public String enterRoomText() {
		StringBuilder outputText = new StringBuilder();
		if(isEmpty()){
			outputText.append("This room is empty.");
			return outputText.toString();
		}
		if(hasMonster()) {	
			getMonsterMap().values().forEach((v) -> outputText.append(v.confrontText() + " "));
		}
		if(hasItems()) {
			outputText.append("This room contains ");
			getItemMap().forEach((k, v) -> outputText.append("a " + v.typeToString() + " called \"" + k + "\" "));
		}
		return outputText.toString();
	}
	
	public boolean isEmpty() {
		return getItemMap().isEmpty() && getMonsterMap().isEmpty();
	}
	
	public boolean hasMonster() {
		return !getMonsterMap().isEmpty();
	}
	
	public boolean hasItems() {
		return !getItemMap().isEmpty();
	}
}
