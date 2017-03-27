package com.dd.command_util.command;

import com.dd.Console;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.levels.Direction;

public class MoveCommand extends CommandHandler {
    public MoveCommand() {}

    @Override
    public void handleCommand(String[] args, CommandOutputLog outputLog) {
		
    	switch(args[0]){
		
    	case "north": 
			if(map().isRoomInDir(playerPos(), Direction.NORTH)){
					playerPos().moveNorth();
					output.append(player().getName() + " has moved through the North door\n");
			}
			else{
				output.append("No North Door!\n");
			}
			break;
		
    	case "south": 
			if(map().isRoomInDir(playerPos(), Direction.SOUTH)){
				playerPos().moveSouth();
				output.append(player().getName() + " has moved through the South door\n");
			}
			else{
				output.append("No South Door!\n");
			}
			break;
		
    	case "east": 
			if(map().isRoomInDir(playerPos(), Direction.EAST)){
				playerPos().moveEast();
				output.append(player().getName() + " has moved through the East door\n");
			}
			else{
				output.append("No East Door!\n");
			}
			break;
		
    	case "west": 
			if(map().isRoomInDir(playerPos(), Direction.WEST)){
				playerPos().moveWest();
				output.append(player().getName() + " has moved through the West door\n");
			}
			else{
				output.append("No West Door!\n");
			}
			break;
		
    	default:
    		output.append("The argument \"" + args[0] + "\" is invalid.\n"
       			+ "Type \"move\" followed by north, south, east, or west\n");
			return;	
		}
    	output.append(currRoom().examineString());
    	Console.updateScreen(output.toString());
    }
}