package com.dd.entities.players;

import com.dd.Stats;
import com.dd.entities.Player;
import com.dd.exceptions.*;
import com.dd.items.*;
import com.dd.levels.MapPosition;

public class Fighter extends Player {

	public Fighter(String name, MapPosition pos, Stats stats) {
		super(name, pos, stats);
	}

	public Fighter(String name, MapPosition startPosition) {
		super(name);
		setMapPosition(startPosition);
	}
	
	public Fighter() {
		super();
		setMapPosition(new MapPosition());
	}

	@Override
	public void pickup(Item item) throws EquipmentException {
		if(item instanceof Artifact || item instanceof Potion) {
			try {
				addtoInventory(item);
			} catch (InventoryException e) {
				throw new EquipmentException(item.titleToString() 
						+ " could not be picked up because " + titleToString() + "'s "
						+ "inventory is full");
			}
		}
		else if(item instanceof Shield || item instanceof OneHandedWeapon){
			if(leftHand.isEmpty()) {
				try {
					leftHand.set(item);
				}
				catch (NullItemException ITE) {
					throw new EquipmentException(item.titleToString() + "could not be equpped to " + titleToString() +"'s left hand. ");
				}
			}
			else if(rightHand.isEmpty()) {
				try {
					rightHand.set(item);
				}
				catch (NullItemException e) {
					throw new EquipmentException(item.titleToString() + "could not be equpped to " + titleToString() +"'s right hand. ");
				}
			}
			else {
				throw new EquipmentException(item.titleToString() 
						+ " could not be picked up because both of " 
						+ titleToString() + "'s hands are full. ");
			}
		}
		else if(item instanceof Suit) {
			if(suitArea.isEmpty()) {
				suitArea.set(item);
			}
			else {
				throw new EquipmentException(item.titleToString() 
						+ " could not be equipped because " 
						+ titleToString() + " is already wearing a suit. ");
			}
		}
		else if(item instanceof TwoHandedWeapon) {
			if(twoHands.isEmpty()
					&& leftHand.isEmpty()
					&& rightHand.isEmpty()) {
				twoHands.set(item);
			}
			else {
				throw new EquipmentException(item.titleToString() 
						+ " could not be equipped because both of " 
						+ titleToString() + "'s hands need to be empty. ");
			}
		}
		else if(item instanceof Magical) {
			throw new EquipmentException(item.titleToString() 
					+ " could not be equipped because "
					+ "fighters cannot use " + item.typeToString() + " items. ");
		}
		else {
			throw new EquipmentException(item.getName()
					+ " is of an unknown type. ");
		}
		changeStats(item.getStatChange());
	}

}
