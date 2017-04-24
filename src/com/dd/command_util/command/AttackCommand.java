package com.dd.command_util.command;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.entities.*;
import com.dd.exceptions.*;

public class AttackCommand extends CommandHandler {

    public AttackCommand(GameState gameState){
    	super(gameState);
    }

    @Override
    public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException{
    	setGlobalOutputLog(outputLog);
		updateState();
    	if(dead){
    		outputLog.printToLog(player.titleToString() + " is dead. ");
    		return;
    	}
    	if(args[0] != null) {
    		throw new InvalidArgumentException(commandName + " command does not require an argument. ");
    	}
		
		try{
			monster = room.getMonster();
			player.clearText();
			player.attack(monster);
			outputLog.printToLog(player.getText());
			player.clearText();
			if(monster.died()) {
				room.removeMonster(monster);
			}
			examineMonster = true;
		}
		catch(NullMonsterException UME) {
			outputLog.printToLog(UME.getMessage());
		}
    }
    
	public void monsterDied(String monsterName) {
		try{
			room.removeMonster(monsterName);
		}
		catch(NullMonsterException UME) {
			globalOutputLog.printToLog(UME.getMessage());
		}
	}
	
	public void monsterDied(Monster monster) {
		try{
			room.removeMonster(monster);
		}
		catch(NullMonsterException UME) {
			globalOutputLog.printToLog(UME.getMessage());
		}
	}
}