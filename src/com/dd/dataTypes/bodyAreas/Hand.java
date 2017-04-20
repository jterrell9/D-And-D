package com.dd.dataTypes.bodyAreas;

import com.dd.exceptions.EquipmentException;
import com.dd.exceptions.ItemTypeException;
import com.dd.items.*;

import java.io.Serializable;

public class Hand implements Serializable {
	
	private Item item;
	private boolean isEmpty;
	
	public Hand() {
		this.isEmpty = true;
	}

	public Hand(Item item) throws EquipmentException {
		try {
			set(item);
		} catch (ItemTypeException ITE) {
			throw new EquipmentException(ITE.getMessage());
		}
	}
	
	public void set(Item item) throws ItemTypeException {
		if(item instanceof OneHandedWeapon
				|| item instanceof Shield
				|| item instanceof Magical) {
			this.item = item;
			this.isEmpty = false;
		}
		else {
			throw new ItemTypeException(item.titleToString() + " cannot be equipped to left hand. ");
		}
	}
	
	public Item get() {
		return this.item;
	}
	
	public void drop() {
		if(!isEmpty()) {
			this.item = null;
		}
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
}