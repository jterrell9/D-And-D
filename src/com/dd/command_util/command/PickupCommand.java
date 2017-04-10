package com.dd.command_util.command;

import java.util.ArrayList;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.entities.Player;
import com.dd.entities.Player.EquipmentException;
import com.dd.entities.Player.InventoryException;
import com.dd.items.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;
import com.dd.levels.Room.UnknownItemException;

public class PickupCommand extends CommandHandler {
	private Player player;
	private DungeonMap dungeonMap;
	private Room room;

    public PickupCommand(GameState gameState) {
    	this.player = gameState.getActivePlayer();
		this.dungeonMap = gameState.getMap();
		this.room = dungeonMap.getRoom(player.getPostion());
		
	}

    @Override
    public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) {
		if(args[0] != null) {
			Item item = null;
			switch(args[0]) {
			case "items":
				ArrayList<String> itemNames = new ArrayList<String>();;
				for(String itemName : room.getItemList().keySet()) {
					item = room.getItem(itemName);
		    		try {
		    			player.equip(item);
		    			if(player.isEquipSuccess()) {
		    				itemNames.add(itemName);
		    			}
		    		}
		    		catch(EquipmentException ee) {
		    			outputLog.printToLog(ee.toString());
		    		}
		    		catch(InventoryException ie) {
		    			outputLog.printToLog(ie.toString());
		    		}
				}
				for(String itemName : itemNames) {
		    		try {
		    			room.removeItem(itemName);
		    		}
		    		catch (UnknownItemException UIE) {
		    			outputLog.printToLog(UIE.toString());
					}
				}
		    	itemNames.forEach((k) -> outputLog.printToLog(player.titleToString() + " has equipped " + k + ". "));
		    	player.resetEquipSuccess();
				break;
			default:
				item = room.getItem(args[0]);
				if(item != null) {
					try {
		    			player.equip(item);
		    		}
		    		catch(EquipmentException ee) {
		    			outputLog.printToLog(ee.toString());
		    		}
		    		catch(InventoryException ie) {
		    			outputLog.printToLog(ie.toString());
		    		}
		    		try {
		    			room.removeItem(item.getName());
		    		}
		    		catch (UnknownItemException UIE) {
		    			outputLog.printToLog(UIE.toString());
					}
		    		outputLog.printToLog(player.titleToString() + " has equipped " + item.getName() + ". ");
				}
		    	else {
		    		outputLog.printToLog("The item \"" + args[0] + "\" is not in this room. ");
		    	}
			}
			outputLog.printToLog(room.enterRoomText());
		}
		else {
			outputLog.printToLog("Type \"help\" for help using the equip command. ");
		}
    }
}