package com.dd.command_util.command;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.entities.Player;
import com.dd.levels.Direction;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;

public class MoveCommand extends CommandHandler {
	private Player player;
	private DungeonMap map;

    public MoveCommand(GameState gameState) {
    	player = gameState.getActivePlayer();
    	map = gameState.getMap();
	}

    @Override
    public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException {
    	if(args[0] == null) {
    		throw new InvalidArgumentException("Choose a direction to move. "
    				+ "Type \"help\" for help using the " + commandName +" command. ");
    	}
//WE CAN'T DO THIS EXCEPTION WITHOUT QUOTES
//    	if(args.length > 2) {
//    		throw new InvalidArgumentException("Type \"help\" for help using the " + commandName +" command. ");
//    	}
		MapPosition position = player.getPostion();
    	switch(args[0].toLowerCase()) {
    	case "north": 
			if(map.isRoomInDir(position, Direction.NORTH)){
					position.moveNorth();
					outputLog.printToLog("The " + player.titleToString() + " has moved through the North door. ");
					outputLog.printToLog(map.getRoom(player.getPostion()).enterRoomText());
			}
			else{
				outputLog.printToLog("No North Door! ");
			}
			break;
		
    	case "south": 
			if(map.isRoomInDir(position, Direction.SOUTH)){
				position.moveSouth();
				outputLog.printToLog(player.titleToString() + " has moved through the South door. ");
				outputLog.printToLog(map.getRoom(player.getPostion()).enterRoomText());
			}
			else{
				outputLog.printToLog("No South Door! ");
			}
			break;
		
    	case "east": 
			if(map.isRoomInDir(position, Direction.EAST)){
				position.moveEast();
				outputLog.printToLog(player.titleToString() + " has moved through the East door. ");
				outputLog.printToLog(map.getRoom(player.getPostion()).enterRoomText());
			}
			else{
				outputLog.printToLog("No East Door! ");
			}
			break;
		
    	case "west": 
			if(map.isRoomInDir(position, Direction.WEST)){
				position.moveWest();
				outputLog.printToLog(player.titleToString() + " has moved through the West door. ");
				outputLog.printToLog(map.getRoom(player.getPostion()).enterRoomText());
			}
			else{
				outputLog.printToLog("No West Door! ");
			}
			break;
		
    	default:
    		throw new InvalidArgumentException(args[0] + "is not a direction. "
    				+ "Type \"help\" for help using the move command. ");
		}
    }
}