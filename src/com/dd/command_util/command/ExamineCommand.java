package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.tester.Tester;

public class ExamineCommand extends CommandHandler {
    public ExamineCommand() {}

    @Override
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
    }
}