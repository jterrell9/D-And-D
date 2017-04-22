package com.dd.command_util;

import com.dd.GameState;
import com.dd.entities.*;
import com.dd.exceptions.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;

public abstract class CommandHandler {
	
	protected GameState gameState;
	protected DungeonMap dungeonMap;
	protected Room room;
	protected Player player;
	protected CommandOutputLog globalOutputLog;
	protected boolean monsterExamined;
	
	public CommandHandler(GameState gameState) {
    	initGameState(gameState);
	}
	
	public abstract void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException, InventoryException;

	protected void initGameState(GameState activeState) {
		this.gameState = activeState;
    	this.dungeonMap = gameState.getMap();
    	this.player = updateState();
	}
	
	protected Player updateState() {
		player = gameState.getActivePlayer();
		this.room = dungeonMap.getRoom(player.getPostion());
		return player;
	}
	
	public void setGlobalOutputLog(CommandOutputLog outputLog) {
		this.globalOutputLog = outputLog;
	}
	
	protected String getArgsString(String args[]){
        String argsStr = "";
        for(int i = 0; i < args.length - 1; i++) {
            argsStr += args[0] + " ";
        }
        argsStr += args[args.length - 1];
        return argsStr;
    }
	
	public void monsterAttack() {
		try {
    		Monster monster = room.getMonster();
			monster.attack(player);
			globalOutputLog.printToLog(monster.getText());
			if(!monsterExamined) {
				examineMonster();
			}
		}
		catch(NullMonsterException NME) {
			globalOutputLog.printToLog(NME.getMessage());
		}
	}
	
	public void examineRoom() {
		globalOutputLog.printToLog(room.enterRoomText());
	}
	
	public void examineItems() {
		if(room.hasItems()) {
			room.getItemMap().values().forEach((v) -> globalOutputLog.printToLog(
					v.titleToString() + " "
					+ v.examineToString() + "\n"));
		}
		else {
			globalOutputLog.printToLog("There are no items in this room. ");
		}
	}
	
	public void examineMonster() {
		if(room.hasMonster()) {
			room.getMonsterMap().values().forEach((v) -> globalOutputLog.printToLog(
					v.titleToString()
					+ "\nHealth: " + v.getStats().getHealth()
					+ "\nAttack/Defense: " + v.getStats().getAttack() + "/" + v.getStats().getDefense()
					+ "\n" + v.examineText()));
		}
		else {
			globalOutputLog.printToLog("There are no monsters in this room. ");
		}
	}
	
	public void monsterDied(String monsterName) throws NullMonsterException {
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