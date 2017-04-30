package com.dd.command_util;

import com.dd.GameState;
import com.dd.entities.*;
import com.dd.exceptions.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;

public abstract class CommandHandler {
	
	protected GameState gameState;
//	protected DungeonMap dungeonMap;
//	protected static Room room;
//	protected static Player player;
//	protected static Monster monster;
	protected static boolean examineMonster;
	protected static boolean monsterAttack = true;
//	protected boolean dead = false;
	
	public CommandHandler(GameState gameState) {
    	this.gameState = gameState;
	}
	
	public abstract void handleCommand(String commandName, String[] args) throws InvalidArgumentException;
	
	protected static void toLog(String text) {
		CommandParser.toLog(text);
	}
	
//	protected void updateState() {
//		player = gameState.getActivePlayer();
//		room = dungeonMap.getRoom(player.getPostion());
//		try {
//			monster = room.getMonster();
//		}
//		catch (NullMonsterException NME) {
//			monster = null;
//			//globalOutputLog.printToLog(NME.getMessage());
//		}
//		if(player.died()){
//			dead = true;
//		}
//	}
	
//	public void setGlobalOutputLog(CommandOutputLog outputLog) {
//		globalOutputLog = outputLog;
//	}
	
	public void monsterAttack() {
//		updateState();
//		if(room.hasMonster() && monsterAttack && !isDead()) {
//			try {
//	    		monster = room.getMonster();
//	    		monster.clearText();
//				monster.attack(player);
//				globalOutputLog.printToLog(monster.getText() + "\n");
//				if(examineMonster) {
//					examineMonster();
//				}
//			}
//			catch(NullMonsterException NME) {
//				globalOutputLog.printToLog(NME.getMessage());
//			}
//		}
//		monsterAttack = true;
	}
	
	public void examineRoom() {
		CommandParser.toLog(room().enterRoomText());
	}
	
	public void examineItems() {
		if(!room().hasItems()) {
			CommandParser.toLog("There are no items in this room. ");
			return;
		}
		room().getItemMap().values().forEach((v) -> CommandParser.toLog(
				v.titleToString() + " "
				+ v.examineToString() + "\n"));
	}
	
	public void examineMonster() {
		if(!hasMonster()) {
			CommandParser.toLog("There are no monsters in this room. ");
			return;
		}
		room().getMonsterMap().values().forEach((v) -> CommandParser.toLog(
				v.titleToString()
				+ "\nHealth: " + v.getStats().getHealth()
				+ "\nAttack/Defense: " + v.getStats().getAttack() + "/" + v.getStats().getDefense()
				+ "\n" + v.examineText()));
	}
	
	protected Player player() {
		return gameState.getActivePlayer();
	}
	
	protected DungeonMap dungeon() {
		return gameState.getMap();
	}
	
	protected Room room() {
		return dungeon().getRoom(player().getPostion());
	}
	
	protected Monster monster() {
		return room().getMonster();
	}
	
	protected boolean hasMonster() {
		return room().hasMonster();
	}
	
	protected boolean hasItems() {
		return room().hasItems();
	}
	
	public boolean isDead() {
		return player().getStats().getHealth() <= 0;
	}
}