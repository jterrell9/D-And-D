package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.entities.Player.EquipmentException;
import com.dd.entities.Player.InventoryException;
import com.dd.items.*;
import com.dd.levels.Room.UnknownItemException;
import com.dd.tester.Console;

public class EquipCommand extends CommandHandler {
    public EquipCommand() {}

    @Override
    public void handleCommand(String[] args) {
    	Item item = currRoom().getItem(args[0]);
    	if(item != null){
    		try{
    			player().equip(item);
    			currRoom().removeItem(item.getName()); 
    			output.append(player().getName() + " has equipped " + item.getName() + "\n");
    		}
    		catch(EquipmentException ee) {
    			output.append(ee.toString() + "\n");
    		}
    		catch(InventoryException ie) {
    			output.append(ie.toString() + "\n");
    		} catch (UnknownItemException UIE) {
				output.append(UIE.toString() + "\n");
			}
    	}
    	else {
    		output.append("The item \"" + args[0] + "\" is not in this room.\n");
    	}
    	output.append(currRoom().examineString());
    	Console.updateScreen(output.toString());
    }
}