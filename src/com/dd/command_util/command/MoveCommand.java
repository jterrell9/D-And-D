package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.levels.DIR;
import com.dd.tester.Tester;

public class MoveCommand extends CommandHandler {
    public MoveCommand() {}

    @Override
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
    }
}