package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.controller_util.controller.RunningGameController;

public class HelpCommand extends CommandHandler {
    public HelpCommand() {}
	
	@Override
	public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog){
    	if(args.length != 0){
			outputLog.printToLog("Invalid arguments \""
					+ getArgsString(args)
					+ "\" passed to help command.");
		}

		outputLog.printToLog(RunningGameController.printLnTitle('~', "AVAILABLE COMMANDS", 50)
				+ "\"attack <entity_name>\"\n"
				+ "Initiate an attack against the entity with name\n"
				+ "entity_name.\n"
				+ "\n"
				+ "\"enter\"\n"
				+ "Enter the dungeon, begin the game."
				+ "\n"
				+ "\"move <direction>\"\n"
				+ "\n"
				+ "Move the player to the room in the specified\n"
				+ "direction of the room the player is currently in.\n"
				+ "If no room exists in the specified direction this\n"
				+ "will fail. The valid directions are: north, south,\n"
				+ "east, and west.\n"
				+ "\n"
				+ "\"examine room | monsters | items | <name>\"\n"
				+ "Get a description of either an entity by name,\n"
				+ "and item by name, or the room the player is\n"
				+ "currently in, or a list of monsters or items.\n"
				+ "\n"
				+ "\"use <item name>\"\n"
				+ "Use an item with the name item_name to receive\n"
				+ "its effects. If the item specified is not a usable\n"
				+ "item this will fail.\n"
				+ "\n"
				+ "\"pickup <item name>\"\n"
				+ "Add the item with the name to the player's inventory.\n"
				+ "\n"
				+ "\"drop <item name>\"\n"
				+ "Remove an item with the name item name from the\n"
				+ "players inventory. The item will be placed in the\n"
				+ "room the player is currently in.\n"
				+ "\n"
				+ "\"equip <item name>\"\n"
				+ "Equip an item with the name to the player at the\n"
				+ "If the location is already occupied this will fail.\n");
	}
}