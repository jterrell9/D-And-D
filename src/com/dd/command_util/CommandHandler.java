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