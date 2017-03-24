package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.levels.DIR;

public class MoveCommand extends CommandHandler {
    public MoveCommand() {}

    @Override
    public void handleCommand(String[] args){
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
				return;
			case "south": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.SOUTH)){
					getRunnerPosition().moveSouth();
					System.out.println(getRunnerPlayer().getName()
							+ " has moved South");
				}
				else{
					System.out.println("No Door in that Direction!");
				}
				return;
			case "east": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.EAST)){
					getRunnerPosition().moveEast();
					System.out.println(getRunnerPlayer().getName()
							+ " has moved East");
				}
				else{
					System.out.println("No Door in that Direction!");
				}
				return;
			case "west": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.WEST)){
					getRunnerPosition().moveWest();
					System.out.println(getRunnerPlayer().getName()
							+ " has moved West");
				}
				else{
					System.out.println("No Door in that Direction!");
				}
				return;
			}
		}
		System.out.println("Type 'move' followed by north, south, east, or west");
		return;
    }
}