package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
<<<<<<< HEAD
import com.dd.command_util.CommandOutputLog;
import com.dd.levels.DIR;
import com.dd.tester.Tester;
=======
import com.dd.levels.DIR;
>>>>>>> refs/remotes/origin/Testing

public class MoveCommand extends CommandHandler {
    public MoveCommand() {}

    @Override
<<<<<<< HEAD
    public void handleCommand(String[] args, CommandOutputLog outputLog){
    	if(args[0] != null){
			switch(args[0]){
			case "north": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.NORTH)){
					getRunnerPosition().moveNorth();
				}
				else{
					Tester.printToLog("No Door in that Direction!");
				}
				break;
			case "south": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.SOUTH)){
					getRunnerPosition().moveSouth();
				}
				else{
					Tester.printToLog("No Door in that Direction!");
				}
				break;
			case "east": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.EAST)){
					getRunnerPosition().moveEast();
				}
				else{
					Tester.printToLog("No Door in that Direction!");
				}
				break;
			case "west": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.WEST)){
					getRunnerPosition().moveWest();
				}
				else{
					Tester.printToLog("No Door in that Direction!");
				}
				break;
			default:
				Tester.printToLog("The argument \"" + args[0] + "\" is invalid.\n"
        			+ "Type \"move\" followed by north, south, east, or west");
				return;	
			}
			Tester.printToLog(getRunnerPlayer().getName() + " has moved through the " + args[0] +" door\n"
					+ getRunnerRoom().examineString());
		}
=======
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
>>>>>>> refs/remotes/origin/Testing
    }
}