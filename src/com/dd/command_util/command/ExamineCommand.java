package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
<<<<<<< HEAD
import com.dd.command_util.CommandOutputLog;
=======
>>>>>>> refs/remotes/origin/Testing
import com.dd.tester.Tester;

public class ExamineCommand extends CommandHandler {
    public ExamineCommand() {}

    @Override
<<<<<<< HEAD
    public void handleCommand(String[] args, CommandOutputLog outputLog) {
    	StringBuilder examineStrBuilder=new StringBuilder();
    	switch(args[0]){
		case "room":
			examineStrBuilder.append(getRunnerRoom().examineString());
			break;
		case "monster":
			if(getRunnerRoom().hasMonster()) {
				for(String monsterName : getRunnerRoom().getMonsterList()) {
					examineStrBuilder.append("~" + monsterName + "\nHealth: "
							+ getRunnerRoom().getMonster(monsterName).getStats().getHealth()
							+ "\nAttack/Defense: "
							+ getRunnerRoom().getMonster(monsterName).getStats().getAttack()
							+ "/" + getRunnerRoom().getMonster(monsterName).getStats().getDefense()
							+ "\n\n" + getRunnerRoom().getMonster(monsterName).getDescription() + "\n");
				}
			}
			else {
				examineStrBuilder.append("There are no mosters in this room.");
			}
			break;
		case "items":
			if(getRunnerRoom().hasItems()) {
				for(String itemName : getRunnerRoom().getItemList()) {
					examineStrBuilder.append("~" + itemName + " " 
							+ getRunnerRoom().getItem(itemName).examineToString() + "\n");
				}
			}
			else {
				Tester.printToLog("There are no items in this room"); 
			}
    	}
    	Tester.printToLog(examineStrBuilder.toString());
=======
    public void handleCommand(String[] args){
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
>>>>>>> refs/remotes/origin/Testing
    }
}