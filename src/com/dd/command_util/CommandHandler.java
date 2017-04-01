package com.dd.command_util;

import java.io.FileNotFoundException;

import com.dd.DandD;
import com.dd.entities.Player;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public abstract class CommandHandler {
    protected String name;

    public CommandHandler(String name){
        this.name = name;
    }

	//public abstract void handleCommand(String[] args, CommandOutputLog outputLog) throws CommandHandlerException, FileNotFoundException;
	public abstract void handleCommand(String[] args, CommandOutputLog outputLog) throws FileNotFoundException;

	protected String getArgsString(String args[]){
        String argsStr = "";
        for(int i = 0; i < args.length - 1; i++) {
            argsStr += args[0] + " ";
        }
        argsStr += args[args.length - 1];
        return argsStr;
    }

    public String getName(){
	    return name;
    }
}