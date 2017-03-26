package com.dd.command_util;

import com.dd.command_util.CommandHandler;

import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private Map<String, CommandHandler> commandMap = new HashMap<String, CommandHandler>();
    private CommandOutputLog outputLog;

    public CommandParser(){}

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

    public void parseCommand(String command, String[] args) throws CommandHandler.CommandHandlerException, FileNotFoundException {
        CommandHandler handler = commandMap.get(command);
        if(handler == null)
            throw new IllegalArgumentException("The command \""
                                                    + command
                                                    + "\" is invalid.");
        try{
            handler.handleCommand(args, outputLog);
        }
        catch(CommandHandler.CommandHandlerException e){
            throw e;}
    }
}