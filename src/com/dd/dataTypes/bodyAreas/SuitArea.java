package com.dd.dataTypes.bodyAreas;

import java.io.Serializable;

import com.dd.exceptions.EquipmentException;
import com.dd.exceptions.NullItemException;
import com.dd.items.*;

public class SuitArea implements Serializable {
	
	private Suit suit;
	
	public SuitArea() {
		this.suit = null;
	}
	
	public SuitArea(Suit suit) {
		set(suit);
	}

	public SuitArea(Item item) throws EquipmentException {
		try {
			set(item);
		} catch (NullItemException ITE) {
			throw new EquipmentException(ITE.getMessage());
		}
	}
	
	public void set(Item item) throws NullItemException {
		if(item instanceof Suit) {
			this.suit = (Suit) item;
		}
		else {
			throw new NullItemException(item.getTitle() + " cannot be equipped to suit area. ");
		}
	}
	
	public void set(Suit suit) {
		this.suit = suit;
	}
	
	public Suit get() throws NullItemException {
		if(isEmpty()) {
			throw new NullItemException("Suit area is empty. ");
		}
		return this.suit;
	}
	
	public void drop() throws NullItemException {
		if(isEmpty()) {
			throw new NullItemException("Suit area is empty. ");
		}
		this.suit = null;
	}
	
	public boolean isEmpty() {
		return this.suit == null;
	}
}
