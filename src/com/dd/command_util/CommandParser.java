package com.dd.command_util;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandHandler.CommandHandlerException;
import com.dd.command_util.command.*;
import com.dd.tester.Console;

import javafx.scene.control.TextInputControl;

import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private Map<String, CommandHandler> commandMap = new HashMap<String, CommandHandler>();
    private CommandOutputLog outputLog;
    private String command = new String();
	private String[] arguments = {null, null};
	String log;

    public CommandParser(){
    	registerCommand("menu", new MenuCommand());
		registerCommand("quit", new QuitCommand());
		registerCommand("move", new MoveCommand());
		registerCommand("examine", new ExamineCommand());
		registerCommand("save", new SaveCommand());
		registerCommand("drop", new DropCommand());
		registerCommand("attack", new AttackCommand());
		registerCommand("equip", new EquipCommand());
		registerCommand("help", new HelpCommand());
		registerCommand("pickup", new PickupCommand());
		registerCommand("use", new UseCommand());
    }
    
    public CommandParser(CommandOutputLog outputLog) {
        this.outputLog = outputLog;
    }
    
    public void parse(String userInput) throws FileNotFoundException, CommandHandlerException {
		if(userInput != null) {
			String[] input = userInput.split(" ");
			command = input[0].toLowerCase();
			if(input.length > 1) {
				arguments[0] = userInput.substring(command.length() + 1);
				arguments[1] = input[input.length-1];
			}
			CommandHandler handler = commandMap.get(command);
			if(handler == null) {
				Console.updateScreen("The command \""
						+ command
						+ "\" is invalid.");
				return;
			}
	        try{
	            handler.handleCommand(arguments);
	            log = handler.getLog();
	        }
	        catch(CommandHandler.CommandHandlerException E){
	            handler.output.append(E.toString());
	        }
	        catch(IllegalArgumentException IAE){
	            handler.output.append(IAE.toString());
	        }
		}
	}
    
    public void initCommands() {
    	registerCommand("menu", new MenuCommand());
		registerCommand("quit", new QuitCommand());
		registerCommand("move", new MoveCommand());
		registerCommand("examine", new ExamineCommand());
		registerCommand("save", new SaveCommand());
		registerCommand("drop", new DropCommand());
		registerCommand("attack", new AttackCommand());
		registerCommand("equip", new EquipCommand());
		registerCommand("help", new HelpCommand());
		registerCommand("pickup", new PickupCommand());
		registerCommand("use", new UseCommand());
    }

    public void registerCommand(String commandName, CommandHandler commandHandler) {
        if(commandMap.containsKey(commandName))
            throw new IllegalArgumentException("The command \""
                                                + commandName
                                                + "\" has already been registerd with this CommandParser. Registration failed.");
        if(commandHandler == null)
            throw new IllegalArgumentException("The command handler passed for \""
                                                    + commandName
                                                    + "\" is null. Registration failed.");
        commandMap.put(commandName, commandHandler);
    }

    public void unregisterCommand(String commandName, CommandHandler commandHandler) {
        if(!commandMap.containsKey(commandName))
            throw new IllegalArgumentException("The command \""
                                                + commandName
                                                + "\" has not been registered with this CommandParser. Unregistration failed.");
    }

    public void setOutputLog(CommandOutputLog outputLog){
        this.outputLog = outputLog;
    }
    
    public String getLog(){
    	return log;
    }
}