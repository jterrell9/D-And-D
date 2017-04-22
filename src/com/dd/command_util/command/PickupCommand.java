package com.dd.command_util.command;

import java.util.ArrayList;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.exceptions.*;
import com.dd.items.*;

public class PickupCommand extends CommandHandler {

    public PickupCommand(GameState gameState) {
    	super(gameState);
	}

    @Override
    public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException {
    	if(args[0] == null) {
    		throw new InvalidArgumentException("Choose something to " + commandName + ". "
    				+ "Type \"help\" for help using the " + commandName +" command. ");
    	}
    	setGlobalOutputLog(outputLog);
		updateState();
		
    	player.resetPickupSuccess();
		Item item = null;
		switch(args[0]) {
		case "items":
			ArrayList<String> equippedItemNames = new ArrayList<String>();
			for(Item equippedItem : room.getItemMap().values()) {
				player.resetPickupSuccess();
				try {
					item = room.getItem(equippedItem);
				}
				catch(NullItemException UIE) {
					outputLog.printToLog(UIE.getMessage());
				}
	    		try {
					player.pickup(item);
	    			if(player.isPickupSuccess()) {
	    				equippedItemNames.add(equippedItem.getName());
	    			}
	    		}
	    		catch(EquipmentException E) {
	    			outputLog.printToLog(E.getMessage());
	    		}
			}
			for(String itemName : equippedItemNames) {
	    		try {
	    			room.removeItem(itemName);
	    			outputLog.printToLog(player.titleToString() + " has equipped " + itemName + ". ");
	    		}
	    		catch (NullItemException UIE) {
	    			outputLog.printToLog(UIE.getMessage());
				}
			}
	    	player.resetPickupSuccess();
			break;
		default:
			try {
				item = room.getItem(args[0]);
			}
			catch(NullItemException UIE) {
				outputLog.printToLog(UIE.getMessage());
				return;
			}
			try {
				player.pickup(item);
    		}
			catch(EquipmentException E) {
    			outputLog.printToLog(E.getMessage());
    			return;
    		}
    		try {
    			if(player.isPickupSuccess()) {
    				room.removeItem(item.getName());
    				outputLog.printToLog(player.titleToString() + " has equipped " + item.titleToString() + ". ");
    			}
    		}
    		catch (NullItemException UIE) {
    			outputLog.printToLog(UIE.getMessage());
    			return;
			}
		}
		if(!room.hasItems()) {
			outputLog.printToLog("This room now has no items. ");
			return;
		}
		outputLog.printToLog("This room now contains the following items:\n");
		examineItems();	
    }
}