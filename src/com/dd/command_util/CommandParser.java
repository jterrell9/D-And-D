package com.dd.command_util;

import com.dd.command_util.CommandHandler;
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

<<<<<<< Upstream, based on origin/CommandParserUpdate
    public void setOutputLog(CommandOutputLog outputLog){
        this.outputLog = outputLog;
    }

    public void parseCommand(String command, String[] args) throws CommandHandler.CommandHandlerException {
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
=======
    public void parseCommand(String command, String[] args) {
    	try {
    		CommandHandler handler = commandMap.get(command);
        	handler.handleCommand(args);
        }
        catch(IllegalArgumentException IAE) {
        	System.out.println("The argument \"" + args[0] + "\" is invalid.\n"
        			+ "type 'help' for a list of commands.");
        }
    	catch(NullPointerException NPE) {
    		System.out.println("The command \"" + command + "\" is invalid.\n"
        			+ "type 'help' for a list of commands.");
    	}
>>>>>>> 3fc740d add try/catch block to parseCommand in CommandParser class
    }
}