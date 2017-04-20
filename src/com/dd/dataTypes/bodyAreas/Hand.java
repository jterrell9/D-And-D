package com.dd.dataTypes.bodyAreas;

import com.dd.exceptions.EquipmentException;
import com.dd.exceptions.ItemTypeException;
import com.dd.exceptions.NullValueException;
import com.dd.items.*;

import java.io.Serializable;

public class Hand implements Serializable {
	
	private Item item;
	
	public Hand() {
		this.item = null;
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
		}
		else {
			throw new ItemTypeException(item.titleToString() + " cannot be equipped to left hand. ");
		}
	}
	
	public Item get() throws NullValueException {
		if(isEmpty()) {
			throw new NullValueException("Suit area is empty. ");
		}
		return this.item;
	}
	
	public void drop() throws NullValueException {
		if(isEmpty()) {
			throw new NullValueException("Suit area is empty. ");
		}
		this.item = null;
	}
	
	public boolean isEmpty() {
		return this.item == null;
	}
}