package com.dd.command_util;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandHandler.CommandHandlerException;
import com.dd.command_util.command.ExamineCommand;
import com.dd.command_util.command.HelpCommand;
import com.dd.command_util.command.MenuCommand;
import com.dd.command_util.command.MoveCommand;
import com.dd.command_util.command.QuitCommand;
import com.dd.command_util.command.SaveCommand;

import javafx.scene.control.TextInputControl;

import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private Map<String, CommandHandler> commandMap = new HashMap<String, CommandHandler>();
    private CommandOutputLog outputLog;
    private String command = new String();
	private String[] arguments = {"~!", null};

    public CommandParser(){
    	registerCommand("help", new HelpCommand());
		registerCommand("menu", new MenuCommand());
		registerCommand("quit", new QuitCommand());
		registerCommand("move", new MoveCommand());
		registerCommand("examine", new ExamineCommand());
		registerCommand("save", new SaveCommand());
    }
    
    public void parse(String userInput) throws FileNotFoundException, CommandHandlerException {
		CommandParser parser = new CommandParser();
		String[] input = userInput.split(" ");
		command = input[0].toLowerCase();
		if(input.length > 1) {
			arguments[0] = userInput.substring(command.length() + 1);
			arguments[1] = null;
		}
		CommandHandler handler = commandMap.get(command);
		if(handler == null)
            throw new IllegalArgumentException("The command \""
                                                    + command
                                                    + "\" is invalid.");
        try{
            handler.handleCommand(arguments);
        }
        catch(CommandHandler.CommandHandlerException e){
            throw e;
        }
	}

    public CommandParser(CommandOutputLog outputLog) {
        this.outputLog = outputLog;
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
}