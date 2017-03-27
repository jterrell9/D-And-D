package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.tester.Tester;

public class HelpCommand extends CommandHandler {
    public HelpCommand() {}
	
	@Override
	public void handleCommand(String[] args, CommandOutputLog outputLog) throws CommandHandlerException {
		Tester.updateRunner("\t\tAVAILABLE COMMANDS\n"
				+ "\n"
				+ "attack <entity_name>\n"
				+ "\tInitiate an attack against the entity with name entity_name.\n"
				+ "\n"
				+ "move <direction>\n"
				+ "\tMove the player to the room in the specified direction of the room the player is\n" 
				+ "\tcurrently in. If no room exists in the specified direction this will fail. The\n" 
				+ "\tvalid directions are: north, south, east, and west.\n"
				+ "\n"
				+ "examine <entity_name> | <item_name> | room | monster(s) | items\n"
				+ "\tGet a description of either an entity with name entity_name, and item with name\n"
				+ "\titem_name, or the room the player is currently in, or a list of monsters or items.\n"
				+ "\n"
				+ "use <item_name>\n"
				+ "\tUse an item with the name item_name to receive its effects. If the item specified\n" 
				+ "\tis not a usable item this will fail.\n"
				+ "\n"
				+ "pickup <item_name>\n"
				+ "\tAdd the item with the name item_name to the player's inventory.\n"
				+ "\n"
				+ "drop <item_name>\n"
				+ "\tRemove an item with the name item_name from the players inventory. The item will\n" 
				+ "\tbe placed in the room the player is currently in.\n"
				+ "\n"
				+ "equip <item_name> <body_location>\n"
				+ "\tEquip an item with the name item_name to the player at the location body_location.\n" 
				+ "\tIf the specified location is already occupied this will fail.\n"
				+ "\n"
				+ "save\n"
				+ "\tSave the current game state.\n"
				+ "\n"
				+ "quit\n"
				+ "\tQuit the currently running game. This will return the player to the main menu.");
	}
}