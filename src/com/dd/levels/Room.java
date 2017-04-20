package com.dd.levels;

import com.dd.entities.Monster;
import com.dd.exceptions.NullValueException;
import com.dd.exceptions.UnknownItemException;
import com.dd.exceptions.UnknownMonsterException;
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
	
	public String examineItems() {
		StringBuilder outputSB = new StringBuilder();
		if(hasItems()) {
			getItemMap().values().forEach((v) -> outputSB.append(
					v.titleToString() + " "
					+ v.examineToString() + "\n"));
		}
		else {
			outputSB.append("There are no items in this room. ");
		}
		return outputSB.toString();
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

	public void addMonster(Monster monster) {
		monsterMap.put(monster.getName(),monster);
	}

	public Monster removeMonster(String monsterName) throws UnknownMonsterException {
		Monster retMonster;
		if(!monsterMap.containsKey(monsterName)){
			throw new UnknownMonsterException("The monster \""
												+ monsterName
												+ "\" does not exist in this room. Removal failed. ");
		}
		retMonster = monsterMap.get(monsterName);
		monsterMap.remove(monsterName);
		return retMonster;
	}

	public void discardMonster(String monsterName) throws UnknownMonsterException {
		if(monsterMap.remove(monsterName) != null) {
			throw new UnknownMonsterException("The monster \""
												+ monsterName
												+ "\" does not exist in this room. Removal failed. ");
		}
	}

	public Map<String, Item> getItemMap() {
		return this.itemMap;
	}

	public Map<String, Monster> getMonsterMap() {
		return this.monsterMap;
	}
	
	public Monster getMonster() throws NullValueException {
		Monster outputMonster = null;
		for(Monster monster : this.monsterMap.values()) {
			outputMonster = monster;
		}
		if(outputMonster == null) {
			throw new NullValueException("No monsters in list. ");
		}
		return outputMonster;
	}
	
	public Monster getMonster(String name) throws UnknownMonsterException {
		if(!monsterMap.containsKey(name)){
			throw new UnknownMonsterException(name + " is not in this room. ");
		}
		return monsterMap.get(name);
	}
	
	public Item getItem(String name) throws UnknownItemException {
		if(!itemMap.containsKey(name)) {
			throw new UnknownItemException(name + " is not found in this room. ");
		}
		return itemMap.get(name);
	}
	
	public Item getItem(Item item) throws UnknownItemException {
		if(!itemMap.containsValue(item)) {
			throw new UnknownItemException(item.titleToString() + " is not found in this room. ");
		}
		return itemMap.get(item.getName());
	}
}
