package com.dd.command_util;

import com.dd.GameState;
import com.dd.entities.*;
import com.dd.exceptions.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;

public abstract class CommandHandler {
	
	protected GameState gameState;
	protected static CommandOutputLog globalOutput;
	protected static boolean examineMonster;
	protected static boolean monsterAttack = true;
	
	public CommandHandler(GameState gameState) {
    	initGameState(gameState);
	}
	
	public abstract void handleCommand(String commandName, String[] args, CommandOutputLog output) throws InvalidArgumentException;

	protected void initGameState(GameState activeState) {
		this.gameState = activeState;
	}

	public void setGlobalOutput(CommandOutputLog outputLog) {
		globalOutput = outputLog;
	}
	
	public void monsterAttack() {
		if(room().hasMonster() && monsterAttack && !isDead()) {
    		monster().clearText();
			monster().attack(player());
			globalOutput.print(monster().getText() + "\n");
			if(examineMonster) {
				globalOutput.print(room().examineMonster());
			}
		}
		monsterAttack = true;
	}
	
	protected Player player() {
		return gameState.getActivePlayer();
	}
	
	protected DungeonMap map() {
		return gameState.getMap();
	}
	
	protected Room room() {
		return map().getRoom(player().getPostion());
	}
	
	protected Monster monster() {
		return room().getMonster();
	}

	public boolean isDead() {
		return player().isDead();
	}
}
