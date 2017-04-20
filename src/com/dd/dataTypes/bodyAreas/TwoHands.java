package com.dd.dataTypes.bodyAreas;

import java.io.Serializable;

import com.dd.dataTypes.enums.*;
import com.dd.exceptions.EquipmentException;
import com.dd.exceptions.ItemTypeException;
import com.dd.exceptions.NullValueException;
import com.dd.items.*;

public class TwoHands implements Serializable {
	
	private TwoHandedWeapon twoHandedWeapon;
	
	public TwoHands() {
		this.twoHandedWeapon = null;
	}
	
	public TwoHands(TwoHandedWeapon twoHandedWeapon) {
		set(twoHandedWeapon);
	}

	public TwoHands(Item item) throws EquipmentException {
		try {
			set(item);
		} catch (ItemTypeException ITE) {
			throw new EquipmentException(ITE.getMessage());
		}
	}
	
	public void set(Item item) throws ItemTypeException {
		if(item instanceof TwoHandedWeapon) {
			this.twoHandedWeapon = (TwoHandedWeapon) item;
		}
		else {
			throw new ItemTypeException(item.titleToString() + " cannot be equipped to both hands. ");
		}
	}
	
	public void set(TwoHandedWeapon twoHandedWeapon) {
		this.twoHandedWeapon = twoHandedWeapon;
	}
	
	public TwoHandedWeapon get() throws NullValueException {
		if(isEmpty()) {
			throw new NullValueException("both hands are empty or have different objects. ");
		}
		return this.twoHandedWeapon;
	}
	
	public void drop() throws NullValueException {
		if(isEmpty()) {
			throw new NullValueException("Suit area is empty. ");
		}
		this.twoHandedWeapon = null;
	}
	
	public boolean isEmpty() {
		return this.twoHandedWeapon == null;
	}
}
