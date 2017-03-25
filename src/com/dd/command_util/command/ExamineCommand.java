package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.tester.Tester;

public class ExamineCommand extends CommandHandler {
    public ExamineCommand() {}

    @Override
    public void handleCommand(String[] args, CommandOutputLog outputLog){
    	switch(args[0]){
		case "room":
			if(!getRunnerRoom().getMosterList().isEmpty()){
				System.out.println("This Room Has A Monster:");
				for(String monsterName:getRunnerRoom().getMosterList()){
					System.out.println(monsterName);
				}
			}
			if(!getRunnerRoom().getItemList().isEmpty()){
				System.out.println("This room has the following items:");
				int i=1;
				for(String itemName:getRunnerRoom().getItemList()){
					System.out.println(getRunnerRoom().getItem(itemName).toString());
					i++;
				}
			}
			else{
				System.out.println("This room is empty.");
			}
			break;
		
		case "monster":
			if(!getRunnerRoom().getMosterList().isEmpty()){
				for(String monsterName:getRunnerRoom().getMosterList()){
					System.out.println(monsterName
							+"\nHealth:\t\t"
							+getRunnerRoom().getMonster(monsterName).getStats().getHealth()
							+"\nAttack/Defense:\t"
							+getRunnerRoom().getMonster(monsterName).getStats().getAttack()
							+"/"+getRunnerRoom().getMonster(monsterName).getStats().getDefense());
				}
			}
			else{
				System.out.println("There are no mosters in this room.");
			}
			return;
    	}
    }
}