package com.dd.levels;

import com.dd.entities.Monster;
import com.dd.items.Item;
import com.dd.dd_util.ConflictHandlingMap;
import java.util.Map;
import java.util.Set;

public class Room {
	private Map<String, Item> itemMap;
	private Map<String, Monster> monsterMap;
	
	public Room() {
		itemMap = new ConflictHandlingMap<Item>();
		monsterMap = new ConflictHandlingMap<Monster>();
	}

	public boolean isEmpty(){
		return itemMap.isEmpty() && monsterMap.isEmpty();
	}

	public void addItem(Item item){
		itemMap.put(item.getName(), item);
	}

	public Item removeItem(String itemName) throws UnknownItemException{
		Item retItem;
		if(!itemMap.containsKey(itemName))
			throw new UnknownItemException("The item \""
											+ itemName
											+ "\" does not exist in this room. Removal failed.");
		retItem = itemMap.get(itemName);
		itemMap.remove(itemName);
		return retItem;
	}

	public void discardItem(String itemName) throws UnknownItemException{
		if(itemMap.remove(itemName) != null)
			throw new UnknownItemException("The item \""
											+ itemName
											+ "\" does not exist in this room. Discard failed.");
	}

	public void addMonster(Monster monster){
		monsterMap.put(monster.name,monster);
	}

	public Monster removeMonster(String monsterName) throws UnknownMonsterException{
		Monster retMonster;
		if(!monsterMap.containsKey(monsterName))
			throw new UnknownMonsterException("The monster \""
												+ monsterName
												+ "\" does not exist in this room. Removal failed.");
		retMonster = monsterMap.get(monsterName);
		monsterMap.remove(monsterName);
		return retMonster;
	}

	public void discardMonster(String monsterName) throws UnknownMonsterException{
		if(monsterMap.remove(monsterName) != null)
			throw new UnknownMonsterException("The monster \""
												+ monsterName
												+ "\" does not exist in this room. Removal failed.");
	}

	public Set<String> getItemList(){
		return itemMap.keySet();
	}

	public Set<String> getMosterList(){
		return monsterMap.keySet();
	}

	//Write new toString stuff if needed

	public class UnknownItemException extends Exception{
		public UnknownItemException(String message){
			super(message);
		}
	}

	public class UnknownMonsterException extends Exception{
		public UnknownMonsterException(String message){
			super(message);
		}
	}
}
