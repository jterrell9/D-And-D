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
    	switch(args[0]) {
    	case "lefthand" :
    	case "left hand" :
    		try{
    			currRoom().addItem(player().getLeftHand());
       			player().discardEquipment(Equip.LEFTHAND);
       			output.append(player().getName() + " has dropped their left hand\n");
       		}
       		catch(EquipmentException ee) {
       			output.append(ee.toString());
       		}
    		break;
    		
    	default:
    		output.append("The body area \"" + args[0] + "\" is not a valid entry.\n"
    				+ "Type \"help\" for help using the examine command.");
    	}
   	output.append(currRoom().examineString());
   	Console.updateScreen(output.toString());
    }
}