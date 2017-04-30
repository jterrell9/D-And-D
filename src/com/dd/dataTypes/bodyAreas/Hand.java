package com.dd.dataTypes.bodyAreas;

import com.dd.exceptions.EquipmentException;
import com.dd.exceptions.NullItemException;
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
		} catch (NullItemException ITE) {
			throw new EquipmentException(ITE.getMessage());
		}
	}
	
	public void set(Item item) throws NullItemException {
		if(item instanceof OneHandedWeapon
				|| item instanceof Shield
				|| item instanceof Magical) {
			this.item = item;
		}
		else {
			throw new NullItemException(item.getTitle() + " cannot be equipped to left hand. ");
		}
	}
	
	public Item get() throws NullItemException {
		if(isEmpty()) {
			throw new NullItemException("Suit area is empty. ");
		}
		return this.item;
	}
	
	public void drop() throws NullItemException {
		if(isEmpty()) {
			throw new NullItemException("Suit area is empty. ");
		}
		this.item = null;
	}
	
	public boolean isEmpty() {
		return this.item == null;
	}
}