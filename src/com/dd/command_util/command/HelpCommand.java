package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.tester.Tester;

public class HelpCommand extends CommandHandler {
    public HelpCommand() {}
	
	@Override
	public void handleCommand(String[] args, CommandOutputLog outputLog) throws CommandHandlerException {
		Tester.printToLog("\tCommands"
				+ "'quit' to quit\n"
				+ "'menu'\n"
				+ "'save'\n"
				+ "'move' followed by a direction\n"
				+ "'examine ' followed by either 'room' or 'monster'\n"
				+ "'equip' followed by 'item' or 'inventory' followed by a valid number\n"
				+ "'drop' followed by player equip area, or 'inventory' followed by a valid number\n"
				+ "'pickup' followed by 'item' followed by a valid number representing a potion\n"
				+ "'use' followed by 'item' or 'inventory' followed by a valid number representing a potion\n"
				+ "'attack'");
	}
}