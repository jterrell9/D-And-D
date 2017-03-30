package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.entities.Player.Equip;
import com.dd.entities.Player.EquipmentException;
import com.dd.entities.Player.InventoryException;
import com.dd.items.Item;
import com.dd.tester.Console;

public class DropCommand extends CommandHandler {
    public DropCommand() {}

    @Override
    public void handleCommand(String[] args){
    	Item item = currRoom().getItem(args[0]);
    	if(item != null){
    		switch(args[0]) {
    		case "lefthand" :
    		case "left hand" :
    			try{
        			player().discardEquipment(Equip.LEFTHAND);
        			currRoom().addItem(item);
        			output.append(player().getName() + " has equipped " + item.getName() + "\n");
        		}
        		catch(EquipmentException ee) {
        			output.append(ee.toString());
        		}
    			break;
    		}
    	}
    	else {
    		output.append("The item \"" + args[0] + "\" is not in this room.\n");
    	}
    	output.append(currRoom().examineString());
    	Console.updateScreen(output.toString());
    }
}