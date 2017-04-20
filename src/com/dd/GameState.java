package com.dd;

import com.dd.dataTypes.enums.PlayerType;
import com.dd.entities.*;
import com.dd.levels.DungeonMap;

import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    protected String name;
	protected Player activePlayer;
	protected PlayerType playerType = PlayerType.NONE;
	protected int maxNumPlayers;
    protected List<Player> allActivePlayers = new ArrayList<Player>();
	protected DungeonMap map;
	
	public GameState(String name, Fighter newFighter, DungeonMap map, int maxNumPlayers) {
	    this.name = name;	    
	   	this.activePlayer = newFighter;
	   	this.playerType = PlayerType.FIGHTER;
        this.maxNumPlayers = maxNumPlayers;
        allActivePlayers = new ArrayList<Player>();
        this.map = map;
	}
	
	public GameState(String name, Wizard newWizard, DungeonMap map, int maxNumPlayers) {
	    this.name = name;	    
	   	this.activePlayer = newWizard;
	   	this.playerType = PlayerType.WIZARD;
        this.maxNumPlayers = maxNumPlayers;
        allActivePlayers = new ArrayList<Player>();
        this.map = map;
	}
	
	public GameState(String name, Fighter newFighter, DungeonMap map) {
	    this.name = name;	    
	   	this.activePlayer = newFighter;
	   	this.playerType = PlayerType.FIGHTER;
        this.maxNumPlayers = 1;
        allActivePlayers = new ArrayList<Player>();
        this.map = map;
	}
	
	public GameState(String name, Wizard newWizard, DungeonMap map) {
	    this.name = name;	    
	   	this.activePlayer = newWizard;
	   	this.playerType = PlayerType.WIZARD;
        this.maxNumPlayers = 1;
        allActivePlayers = new ArrayList<Player>();
        this.map = map;
	}
	
	public GameState(String name, DungeonMap map) {
        this.name = name;
        this.maxNumPlayers = 1;
        allActivePlayers = new ArrayList<Player>();
        this.map = map;
    }

    public Player getActivePlayer() {
    	return this.activePlayer;
    }
    
    public void setActivePlayer(Fighter fighter) {
    	this.activePlayer = fighter;
    }
    
    public void setActivePlayer(Wizard wizard) {
    	this.activePlayer = wizard;
    }
    
    public PlayerType getPlayerType() {
    	return playerType;
    }

    public List<Player> getPlayerList() {
        return allActivePlayers;
    }

    public DungeonMap getMap() {
        return map;
    }

    public void setMap(DungeonMap map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isFighter() {
    	return playerType == PlayerType.FIGHTER;
    }
    
    public boolean isWizard() {
    	return playerType == PlayerType.WIZARD;
    }

    public int getMaxNumPlayers() {
        return maxNumPlayers;
    }

    public void setMaxNumPlayers(int maxNumPlayers) {
        this.maxNumPlayers = maxNumPlayers;
    }

    public void addActivePlayer(Player player) {
        if(player == null)
            throw new IllegalArgumentException("Player passed to GameState is null. Addition failed.");
        if(allActivePlayers.contains(player))
            throw new IllegalArgumentException("Player \""
                                                + player.getName()
                                                + "is already active in this GameState. Addition failed.");
        allActivePlayers.add(player);
    }

    public void removeActivePlayer(Player player) {
        if(player == null)
            throw new IllegalArgumentException("Player passed to GameState is null. Removal failed.");
        if(!allActivePlayers.remove(player))
            throw new IllegalArgumentException("Player \""
                                                + player.getName()
                                                + "is not active in this GameState. Removal failed.");
    }
}
