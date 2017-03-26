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
					Tester.updateRunner("No Door in that Direction!");
					return;
				}
				break;
			case "south": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.SOUTH)){
					getRunnerPosition().moveSouth();
				}
				else{
					Tester.updateRunner("No Door in that Direction!");
					return;
				}
				break;
			case "east": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.EAST)){
					getRunnerPosition().moveEast();
				}
				else{
					Tester.updateRunner("No Door in that Direction!");
					return;
				}
				break;
			case "west": 
				if(getRunnerMap().isRoomInDir(getRunnerPosition(), DIR.WEST)){
					getRunnerPosition().moveWest();
				}
				else{
					Tester.updateRunner("No Door in that Direction!");
					return;
				}
				break;
			default:
				Tester.updateRunner("The argument \"" + args[0] + "\" is invalid.\n"
        			+ "Type \"move\" followed by north, south, east, or west");
				return;	
			}
			Tester.updateRunner(getRunnerPlayer().getName() + " has moved through the " + args[0] +" door\n"
					+ getRunnerRoom().examineString());
		}
    }
}