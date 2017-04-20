package com.dd.command_util;

import com.dd.GameState;
import com.dd.entities.*;
import com.dd.dataTypes.enums.ItemType;
import com.dd.dataTypes.enums.PlayerType;
import com.dd.exceptions.InvalidArgumentException;
import com.dd.exceptions.InventoryException;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;

public abstract class CommandHandler {
	
	protected GameState gameState;
	protected DungeonMap dungeonMap;
	protected Room room;
	protected Player player;
    protected PlayerType playerType = PlayerType.NONE;
    protected ItemType itemType = ItemType.NONE;
	
	public CommandHandler(GameState gameState) {
    	initGameState(gameState);
	}
	
	public abstract void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException, InventoryException;

	protected void initGameState(GameState activeState) {
		this.gameState = activeState;
    	this.dungeonMap = gameState.getMap();
    	this.playerType = gameState.getPlayerType();
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
}