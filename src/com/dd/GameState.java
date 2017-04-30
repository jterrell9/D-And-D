package com.dd;

import com.dd.entities.*;
import com.dd.entities.players.Fighter;
import com.dd.entities.players.Wizard;
import com.dd.levels.DungeonMap;

import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    protected String name;
	protected Player activePlayer;
	protected DungeonMap map;
	
	public GameState(String name, Player newPlayer, DungeonMap map, int maxNumPlayers) {
	    this.name = name;	    
	   	this.activePlayer = newPlayer;
        this.map = map;
	}
	
	public GameState(String name, Player newPlayer, DungeonMap map) {
	    this.name = name;	    
	   	this.activePlayer = newPlayer;
        this.map = map;
	}
	
	public GameState(String name, DungeonMap map) {
        this.name = name;
        this.map = map;
    }

    public Player getActivePlayer() {
    	return this.activePlayer;
    }
    
    public void setActivePlayer(Player player) {
    	this.activePlayer = player;
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
    	return activePlayer instanceof Fighter;
    }
    
    public boolean isWizard() {
    	return activePlayer instanceof Wizard;
    }
}
