package com.dd.command_util.command;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.exceptions.*;
import com.dd.items.Item;
import com.dd.items.Potion;

public class UseCommand extends CommandHandler {
    
	public UseCommand(GameState gameState) {
		super(gameState);
    }

    @Override
    public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException {
    	if(dead){
    		outputLog.printToLog(player.titleToString() + " is dead. ");
    		return;
    	}
    	if(args[0] == null) {
    		throw new InvalidArgumentException("Choose something to " + commandName + ". "
    				+ "Type \"help\" for help using the " + commandName +" command. ");
    	}
    	setGlobalOutputLog(outputLog);
		updateState();
		
    	Item item = room.hasPotion(args[0]);
    	if(item == null) {
    		try {
				item = player.getInventory().get(args[0]);
			} catch (InventoryException IE) {
				outputLog.printToLog(IE.getMessage());
			}
    	}
    	if(item instanceof Potion) {
			try {
				player.usePotionFromInventory((Potion) item);
				outputLog.printToLog(player.titleToString() + " has used " + item.titleToString() + ". ");
			} catch (EquipmentException EE) {
				outputLog.printToLog(EE.getMessage());
			}
    	}
    	else {
    		outputLog.printToLog(item.titleToString() + " is not a Potion. ");
    		return;
    	}
    }
}
