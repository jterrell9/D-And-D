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
			examineStrBuilder.append(currRoom.examineString());
			break;
		case "monsters":
		case "monster":
			if(currRoom.hasMonster()) {
				for(String monsterName : currRoom.getMonsterList()) {
					examineStrBuilder.append("~" + monsterName + "\nHealth: "
							+ currRoom.getMonster(monsterName).getStats().getHealth()
							+ "\nAttack/Defense: "
							+ currRoom.getMonster(monsterName).getStats().getAttack()
							+ "/" + currRoom.getMonster(monsterName).getStats().getDefense()
							+ "\n\n" + currRoom.getMonster(monsterName).getDescription() + "\n");
				}
			}
			else {
				examineStrBuilder.append("There are no mosters in this room.");
			}
			break;
		case "items":
			if(currRoom.hasItems()) {
				for(String itemName : currRoom.getItemList()) {
					examineStrBuilder.append("~" + itemName + " " 
							+ currRoom.getItem(itemName).examineToString() + "\n");
				}
			}
			else {
				examineStrBuilder.append("There are no items in this room"); 
			}
			break;
		default:
			currRoom.getMonster(option);
    	}
    	Tester.updateRunner(examineStrBuilder.toString());
    }
}