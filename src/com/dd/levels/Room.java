package com.dd.levels;

import com.dd.entities.Monster;
import com.dd.items.Item;
import com.dd.dd_util.ConflictHandlingMap;
import java.util.Map;

public class Room {
	private Map<String, Item> itemMap;
	private Map<String, Monster> monsterMap;
	
	public Room() {
		itemMap = new ConflictHandlingMap<Item>();
		monsterMap = new ConflictHandlingMap<Monster>();
	}
	
	public String examineString() {
		StringBuilder examineStr = new StringBuilder();
		if(isEmpty()){
			examineStr.append("This room is empty.");
			return examineStr.toString();
		}
		if(hasMonster()) {	
			getMonsterList().values().forEach((v) -> examineStr.append(v.getDescription() + " "));
		}
		if(hasItems()) {
			examineStr.append("This room contains ");
			getItemList().forEach((k,v) -> examineStr.append("a " + v.typeToString() + " called \"" + k + "\" "));
		}
		return examineStr.toString();
	}

	public boolean isEmpty() {
		return getItemList().isEmpty() && getMonsterList().isEmpty();
	}
	
	public boolean hasMonster() {
		return !getMonsterList().isEmpty();
	}
	
	public boolean hasItems() {
		return !getItemList().isEmpty();
	}

	public void addItem(Item item) {
		itemMap.put(item.getName(), item);
	}

	public Item removeItem(String itemName) throws UnknownItemException {
		Item retItem;
		if(!itemMap.containsKey(itemName)) {
			throw new UnknownItemException("The item \""
											+ itemName
											+ "\" does not exist in this room. Removal failed. ");
		}
		retItem = itemMap.get(itemName);
		itemMap.remove(itemName);
		return retItem;
	}

	public void discardItem(String itemName) throws UnknownItemException {
		if(itemMap.remove(itemName) != null){
			throw new UnknownItemException("The item \""
											+ itemName
											+ "\" does not exist in this room. Discard failed. ");
		}
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

	public Map<String, Item> getItemList() {
		return itemMap;
	}

	public Map<String, Monster> getMonsterList() {
		return monsterMap;
	}
	
	public Monster getMonster(String name) {
		return monsterMap.get(name);
	}
	
	public Item getItem(String name) {
		return itemMap.get(name);
	}

	public class UnknownItemException extends Exception {
		public UnknownItemException(String message) {
			super(message);
		}
		
		@Override
		public String toString() {
			return super.toString().substring(41);
		}
	}

	public class UnknownMonsterException extends Exception {
		public UnknownMonsterException(String message){
			super(message);
		}
		
		@Override
		public String toString() {
			return super.toString().substring(44);
		}
	}
}
