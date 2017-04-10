package com.dd.network;

import com.dd.entities.Player;
import com.dd.levels.DungeonMap;

public class ClientGameState extends NetworkGameState {
    private Player activePlayer;

    public ClientGameState(String name){
        super(name);
    }

    public ClientGameState(String name, Player activePlayer, DungeonMap map){
        super(name, map);
        activePlayer = activePlayer;
    }

    public Player getActivePlayer(){
        return activePlayer;
    }
}
