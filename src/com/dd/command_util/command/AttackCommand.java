package com.dd.command_util.command;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.entities.*;
import com.dd.exceptions.*;

public class AttackCommand extends CommandHandler {

    public AttackCommand(GameState gameState){
    	super(gameState);
    }

    @Override
    public void handleCommand(String commandName, String[] args) throws InvalidArgumentException{
    	if(isDead()){
    		toLog(player().titleToString() + " is dead. ");
    		return;
    	}
    	if(args[0] != null) {
    		throw new InvalidArgumentException(commandName + " command does not require an argument. ");
    	}
		
		try{
			player().attack(monster());
			if(player().died()) {
				room().removeMonster(monster());
			}
			examineMonster = true;
		}
		catch(NullMonsterException UME) {
			toLog(UME.getMessage());
		}
    }
    
	public void monsterDied(String monsterName) {
		try{
			room().removeMonster(monsterName);
		}
		catch(NullMonsterException UME) {
			toLog(UME.getMessage());
		}
	}
	
	public void monsterDied(Monster monster) {
		try{
			room().removeMonster(monster);
		}
		catch(NullMonsterException UME) {
			toLog(UME.getMessage());
		}
	}
}