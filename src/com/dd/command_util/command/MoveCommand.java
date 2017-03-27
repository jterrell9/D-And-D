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
			}
			else{
				Console.updateScreen("No Door in that Direction!");
				return;
			}
			break;
		case "south": 
			if(map().isRoomInDir(playerPos(), Direction.SOUTH)){
				playerPos().moveSouth();
			}
			else{
				Console.updateScreen("No Door in that Direction!");
				return;
			}
			break;
		case "east": 
			if(map().isRoomInDir(playerPos(), Direction.EAST)){
				playerPos().moveEast();
			}
			else{
				Console.updateScreen("No Door in that Direction!");
				return;
			}
			break;
		case "west": 
			if(map().isRoomInDir(playerPos(), Direction.WEST)){
				playerPos().moveWest();
			}
			else{
				Console.updateScreen("No Door in that Direction!");
				return;
			}
			break;
		default:
			Console.updateScreen("The argument \"" + args[0] + "\" is invalid.\n"
       			+ "Type \"move\" followed by north, south, east, or west");
			return;	
		}
		Console.updateScreen(player().getName() + " has moved through the " + args[0] +" door\n"
				+ currRoom().examineString());
    }
}