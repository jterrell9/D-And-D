package com.dd.command_util;

import com.dd.GameState;
import com.dd.entities.*;
import com.dd.exceptions.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;

public abstract class CommandHandler {
	
	protected GameState gameState;
	protected DungeonMap dungeonMap;
	protected static Room room;
	protected static Player player;
	protected static Monster monster;
	protected static CommandOutputLog globalOutputLog;
	protected static boolean examineMonster;
	protected static boolean monsterAttack = true;
	protected boolean dead = false;
	
	public CommandHandler(GameState gameState) {
    	initGameState(gameState);
	}
	
	public abstract void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException;

	protected void initGameState(GameState activeState) {
		this.gameState = activeState;
    	this.dungeonMap = gameState.getMap();
    	updateState();
	}
	
	protected void updateState() {
		player = gameState.getActivePlayer();
		room = dungeonMap.getRoom(player.getPostion());
		try {
			monster = room.getMonster();
		}
		catch (NullMonsterException NME) {
			monster = null;
			//globalOutputLog.printToLog(NME.getMessage());
		}
		if(player.isDead()){
			dead = true;
		}
	}
	
	public void setGlobalOutputLog(CommandOutputLog outputLog) {
		globalOutputLog = outputLog;
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
		updateState();
		if(room.hasMonster() && monsterAttack && !isDead()) {
			try {
	    		monster = room.getMonster();
	    		monster.clearText();
				monster.attack(player);
				globalOutputLog.printToLog(monster.getText() + "\n");
				if(examineMonster) {
					globalOutputLog.printToLog(room.examineMonster());
				}
			}
			catch(NullMonsterException NME) {
				globalOutputLog.printToLog(NME.getMessage());
			}
		}
		monsterAttack = true;
	}
	
	public boolean isDead() {
		return dead;
	}
}