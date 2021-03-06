package com.dd.dataTypes.bodyAreas;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.dd.items.*;
import com.dd.exceptions.*;

public class Inventory implements Serializable {

	private int maxSize;
	private Map<String, Item> items;
	
	public Inventory(int maxSize) {
		this.maxSize = maxSize;
		this.items = new HashMap<String, Item>();
	}
	
	public Item get(Item item) throws InventoryException {
		if(!items.containsValue(item)) {
			throw new InventoryException(item.getTitle() + " is not in your inventory. ");
		}
		return items.get(item.getName());
	}
	
	public Item get(String itemName) throws InventoryException {
		if(!items.containsKey(itemName)) {
			throw new InventoryException(items.get(itemName).getTitle() + " is not in your inventory. ");
		}
		return items.get(itemName);
	}
	
	public void add(Item item) throws InventoryException {
		if(items.size() >= maxSize) {
			throw new InventoryException(item.getTitle() + " cannot be added to your inventory "
					+ "because it is full. ");
		}
		if(item instanceof Artifact 
				|| item instanceof Potion
				|| item instanceof Magical) {
			this.items.put(item.getName(), item);
		}
		else {
			throw new InventoryException("Only potions, artifacts, and certain magical items can "
					+ "be added to your inventory. ");
		}
	}
	
	public Item remove(Item item) throws InventoryException {
		if (!items.containsValue(item)) {
			throw new InventoryException(item.getTitle() + " is not in your inventory. ");
		}
		return items.remove(item.getName());
	}
	
	
	public Item remove(String itemName) throws InventoryException {
		if(!items.containsKey(itemName)) {
			throw new InventoryException(itemName + " is not in your inventory. ");
		}		
		return items.remove(items.get(itemName));
	}
	
	public Map<String, Item> getInventoryMap() {
		return items;
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
}	
