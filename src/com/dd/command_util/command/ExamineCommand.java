package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.tester.Tester;

public class ExamineCommand extends CommandHandler {
    public ExamineCommand() {}

    @Override
    public void handleCommand(String[] args, CommandOutputLog outputLog) {
    	StringBuilder examineStrBuilder=new StringBuilder();
    	String option = unsplitArgs(args);
    	switch(option) {
		case "room":
			examineStrBuilder.append(getRunnerRoom().examineString());
			break;
		case "monsters":
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
				examineStrBuilder.append("There are no items in this room"); 
			}
			break;
		default:
			  getRunnerRoom().getMonster(option);
    	}
    	Tester.updateRunner(examineStrBuilder.toString());
    }
}