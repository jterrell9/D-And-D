package com.dd.command_util;

import com.dd.GameState;
import com.dd.entities.*;
import com.dd.exceptions.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;

public abstract class CommandHandler {
	
	protected GameState gameState;
	protected static boolean examineMonster;
	protected static boolean monsterAttack = true;
	
	public CommandHandler(GameState gameState) {
    	initGameState(gameState);
	}
	
	public abstract void handleCommand(String commandName, String[] args) throws InvalidArgumentException;

	protected void initGameState(GameState activeState) {
		this.gameState = activeState;
	}
	
	public void monsterAttack() {
		if(room().hasMonster() && monsterAttack && !isDead()) {
			monster().attack(player());
			if(examineMonster) {
				print(room().examineMonster());
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

	protected boolean isDead() {
		return player().isDead();
	}
	
	protected static void print(String text) {
    	CommandOutputLog.print(text);
    }
}
