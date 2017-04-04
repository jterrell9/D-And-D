package com.dd.entities;

import com.dd.Stats;
import com.dd.items.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.dd_util.ConflictHandlingMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Wizard extends Player {

	public Wizard(String name, MapPosition pos, Stats stats) {
		super(name, pos, stats);
	}

	public Wizard(String name) {
		super(name);
	}
	
	public Wizard() {
		super();
	}

	@Override
	public void equip(Item item) throws com.dd.entities.Player.InventoryException, com.dd.entities.Player.EquipmentException {
		super.equip(item);
		stats.changeStat(item.getStatChange());
	}

}
