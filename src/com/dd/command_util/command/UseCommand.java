package com.dd.command_util.command;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.command_util.CommandHandler.InvalidArgumentException;
import com.dd.entities.Player;
import com.dd.entities.Player.InventoryException;
import com.dd.items.Item;
import com.dd.items.Potion;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;
import com.dd.levels.Room.UnknownItemException;

public class UseCommand extends CommandHandler {
	
	private Player player;
	private DungeonMap dungeonMap;
	private Room room;
    
	public UseCommand(GameState gameState) {
		this.player = gameState.getActivePlayer();
		this.dungeonMap = gameState.getMap();
		this.room = dungeonMap.getRoom(player.getPostion());
    }

    @Override
    public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException {
    	this.room = dungeonMap.getRoom(player.getPostion());
    	if(args[0] == null) {
    		throw new InvalidArgumentException("Choose something to pickup. "
    				+ "Type \"help\" for help using the " + commandName +" command. ");
    	}
    	Item item = null;
    	if(player.getInventory().containsKey(args[0])) {
    		item = player.getInventory().get(args[0]);
    	}
    	else {
    		if(room.getItemList().containsKey(args[0])) {
        		item = room.getItemList().get(args[0]);
        	}
        	else {
        		outputLog.printToLog("this room does not conatain \""
        				+ args[0] + "\". ");
        		return;
        	}
    	}
    	if(item instanceof Potion) {
			try {
				player.usePotionFromInventory(item.getName());
				outputLog.printToLog(player.titleToString() + " has used " + item.titleToString() + ". ");
			} catch (InventoryException IE) {
				outputLog.printToLog(IE.getMessage());
			}
    	}
    	else {
    		outputLog.printToLog(item.titleToString() + " is not a Potion. ");
    		return;
    	}
    }
}