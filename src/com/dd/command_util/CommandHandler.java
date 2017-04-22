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
	
	protected String getArgsString(String args[]){
        String argsStr = "";
        for(int i = 0; i < args.length - 1; i++) {
            argsStr += args[0] + " ";
        }
        argsStr += args[args.length - 1];
        return argsStr;
    }
	
	public String monsterAttack() {
		String output = "";
		if(room.hasMonster()) {
    		Monster monster = room.getMonster();
    		for(Monster v : room.getMonsterMap().values()) {
    			output += v.titleToString()
    					+ "\nHealth: " + v.getStats().getHealth()
    					+ "\nAttack/Defense: " + v.getStats().getAttack() + "/" + v.getStats().getDefense()
    					+ "\n" + v.examineText();
    		}
			monster.attack(player);
			output += player.getText();
		}
		return output;
	}
	
	public void monsterDie(String monsterName) throws NullMonsterException {
		if(!room.getMonsterMap().containsKey(monsterName)) {
			throw new NullMonsterException(monsterName + " is not in this room. ");
		}
		try{
			room.removeMonster(monsterName);
		}
		catch(NullMonsterException UME) {
			
		}
	}
	
	public void monsterDie(Monster monster) {
		
	}
}