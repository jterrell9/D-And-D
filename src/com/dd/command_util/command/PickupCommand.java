package com.dd.command_util.command;

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
				/*NOT WORKING!!!
				for(String itemName : room.getItemList()) {
					item = room.getItem(itemName);
					if(item != null) {
			    		try{
			    			player.equip(item);
			    			room.removeItem(item.getName());
			    			outputLog.printToLog(player.titleToString() + " has equipped " + item.getName() + ". ");
			    		}
			    		catch(EquipmentException ee) {
			    			outputLog.printToLog(ee.toString());
			    		}
			    		catch(InventoryException ie) {
			    			outputLog.printToLog(ie.toString());
			    		} catch (UnknownItemException UIE) {
			    			outputLog.printToLog(UIE.toString());
						}
					}
			    	else {
			    		outputLog.printToLog("The item \"" + args[0] + "\" is not in this room. ");
			    	}
				}
				NOT WORKING!!*/
				break;
			default:
				item = room.getItem(args[0]);
				if(item != null) {
		    		try{
		    			player.equip(item);
		    			room.removeItem(item.getName());
		    			outputLog.printToLog(player.titleToString() + " has equipped " + item.getName() + ". ");
		    		}
		    		catch(EquipmentException ee) {
		    			outputLog.printToLog(ee.toString());
		    		}
		    		catch(InventoryException ie) {
		    			outputLog.printToLog(ie.toString());
		    		} catch (UnknownItemException UIE) {
		    			outputLog.printToLog(UIE.toString());
					}
				}
		    	else {
		    		outputLog.printToLog("The item \"" + args[0] + "\" is not in this room. ");
		    	}
			}
			outputLog.printToLog(room.examineString());
		}
		else {
			outputLog.printToLog("Type \"help\" for help using the equip command. ");
		}
    }
    
    private String equipItem(Item item) {
    	if(item != null){
    		try{
    			player.equip(item);
    			room.removeItem(item.getName());
    			return player.titleToString() + " has equipped " + item.getName() + ". ";
    		}
    		catch(EquipmentException ee) {
    			return ee.toString();
    		}
    		catch(InventoryException ie) {
    			return ie.toString();
    		} catch (UnknownItemException UIE) {
				return UIE.toString();
			}
    	}else {
    		return null;
    	}
    }
}