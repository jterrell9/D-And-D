package com.dd.command_util;

import java.io.FileNotFoundException;

import com.dd.DandD;
import com.dd.entities.Player;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public abstract class CommandHandler {

	//public abstract void handleCommand(String[] args, CommandOutputLog outputLog) throws CommandHandlerException, FileNotFoundException;
	public abstract void handleCommand(String[] args) throws CommandHandlerException, FileNotFoundException;

    public class CommandHandlerException extends Exception {
        public CommandHandlerException(String message) {
            super(message);
        }
    }
    
    protected StringBuilder output=new StringBuilder();
    
    public static Player player() {
		return DandD.getActiveGameState().getActivePlayer();
	}
	
	public static DungeonMap map(){
		return DandD.getActiveGameState().getMap();
	}
	
	public static MapPosition playerPos(){
		return player().getPostion();
	}
	
	public static Room currRoom(){
		return map().getRoom(playerPos());
	}
}