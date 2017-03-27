package com.dd.command_util;

import java.io.FileNotFoundException;
import com.dd.GameRunner;
import com.dd.entities.Player;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public abstract class CommandHandler {

	public abstract void handleCommand(String[] args, CommandOutputLog outputLog) throws CommandHandlerException, FileNotFoundException;

	protected Player player = GameRunner.getActiveGameState().getActivePlayer();
	protected DungeonMap map = GameRunner.getActiveGameState().getMap();
	protected MapPosition playerPos = player.getPostion();
	protected Room currRoom = map.getRoom(playerPos);

    public class CommandHandlerException extends Exception {
        public CommandHandlerException(String message) {
            super(message);
        }
    }
    
    public String unsplitArgs(String[] arguments) {
    	String arg = arguments[0];
	    for(int i = 1; i < arguments.length && arguments[i] != null; i++) {
	    	arg = arg + " " + arguments[i];
	    }
    	return arg;
    }
}