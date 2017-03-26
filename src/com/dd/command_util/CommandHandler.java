package com.dd.command_util;

import java.io.FileNotFoundException;
import com.dd.GameRunner;
import com.dd.entities.Player;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public abstract class CommandHandler {

	public abstract void handleCommand(String[] args, CommandOutputLog outputLog) throws CommandHandlerException, FileNotFoundException;

    public class CommandHandlerException extends Exception {
        public CommandHandlerException(String message) {
            super(message);
        }
    }
    
    public String unsplitArgs(String[] arguments) {
    	String arg = arguments[0];
    	//if(arguments[1] != null) {
	    	for(int i = 1; i < arguments.length && arguments[i] != null; i++) {
	    		arg = arg + " " + arguments[i];
	    	}
    	//}
    	return arg;
    }
    
    public static Player getRunnerPlayer() {
		return GameRunner.getActiveGameState().getActivePlayer();
	}
	
	public static DungeonMap getRunnerMap(){
		return GameRunner.getActiveGameState().getMap();
	}
	
	public static MapPosition getRunnerPosition(){
		return getRunnerPlayer().getPostion();
	}
	
	public static Room getRunnerRoom(){
		return getRunnerMap().getRoom(getRunnerPosition());
	}
}