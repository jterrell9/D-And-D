package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.levels.DIR;

public class MoveCommand extends CommandHandler {
    public MoveCommand() {}

    @Override
    public void handleCommand(String[] args, CommandOutputLog outputLog){
    	if(args[0] != null){
			switch(args[0]){
			case "north": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.NORTH)){
					getRunnerPosition().moveNorth();
					System.out.println(getRunnerPlayer().getName()
							+ " has moved North");
				}
				else{
					System.out.println("No Door in that Direction!");
				}
				break;
			case "south": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.SOUTH)){
					getRunnerPosition().moveSouth();
					System.out.println(getRunnerPlayer().getName()
							+ " has moved South");
				}
				else{
					System.out.println("No Door in that Direction!");
				}
				break;
			case "east": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.EAST)){
					getRunnerPosition().moveEast();
					System.out.println(getRunnerPlayer().getName()
							+ " has moved East");
				}
				else{
					System.out.println("No Door in that Direction!");
				}
				break;
			case "west": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.WEST)){
					getRunnerPosition().moveWest();
					System.out.println(getRunnerPlayer().getName()
							+ " has moved West");
				}
				else{
					System.out.println("No Door in that Direction!");
				}
				break;
			default:
				System.out.println("The argument \"" + args[0] + "\" is invalid.\n"
        			+ "Type \"move\" followed by north, south, east, or west");
				break;
					
			}
		}
		return;
    }
}