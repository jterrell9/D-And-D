package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.entities.Player.EquipmentException;
import com.dd.entities.Player.InventoryException;
import com.dd.items.*;
import com.dd.tester.Console;

public class EquipCommand extends CommandHandler {
    public EquipCommand() {}

    @Override
    public void handleCommand(String[] args) {
    	Item item = currRoom().getItem(args[0]);
    	if(item != null){
    		try{
    			player().equip(item);
    			output.append(player().getName() + " has equipped " + item.getName());
    		}
    		catch(EquipmentException ee) {
    			output.append(ee.toString());
    		}
    		catch(InventoryException ie) {
    			output.append(ie.toString());
    		}
    	}
    	else {
    		output.append("The item \"" + args[0] + "\" is not in this room.");
    	}
    	Console.updateScreen(output.toString());
    }
}