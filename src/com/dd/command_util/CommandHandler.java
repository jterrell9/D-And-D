package com.dd.command_util;

import java.io.FileNotFoundException;
<<<<<<< HEAD
=======
import java.util.Scanner;
>>>>>>> refs/remotes/origin/Testing

import com.dd.GameRunner;
import com.dd.entities.Player;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public abstract class CommandHandler {
<<<<<<< HEAD

	public abstract void handleCommand(String[] args, CommandOutputLog outputLog) throws CommandHandlerException, FileNotFoundException;
=======
	
    public abstract void handleCommand(String[] args);
>>>>>>> refs/remotes/origin/Testing

    public class CommandHandlerException extends Exception {
        public CommandHandlerException(String message) {
            super(message);
        }
    }
    
<<<<<<< HEAD
    public static Player getRunnerPlayer() {
=======
    public static Player getRunnerPlayer(){
>>>>>>> refs/remotes/origin/Testing
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